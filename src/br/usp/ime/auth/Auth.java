package br.usp.ime.auth;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import br.usp.ime.ganimedes.dao.DaoPapel;
import br.usp.ime.ganimedes.dao.DaoUsuario;
import br.usp.ime.ganimedes.ejb.DaoReplicadoInterface;
import br.usp.ime.ganimedes.model.Papel;
import br.usp.ime.ganimedes.model.Usuario;
import br.usp.ime.ganimedes.view.MessageBean;
import br.usp.ime.ganimedes.view.PageTransitionBean;
import br.usp.ime.log.Logger;
import br.usp.ime.util.IntegerUtil;
import br.usp.ime.util.PasswordGenerator;
import br.usp.ime.util.Recursos;
import net.sf.json.JSONObject;

@Named(value = "Auth")
@RequestScoped
public class Auth {

	Recursos recursos = new Recursos();
	String appName = recursos.getResourceValue("app-name");

	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

	FacesContext fc = FacesContext.getCurrentInstance();

	@Inject
	Usuario usuarioLogado;

	@Inject
	private Logger logger;

	@EJB
	DaoUsuario daoUsuario;

	@EJB
	DaoPapel daoPapel;

	@EJB
	DaoReplicadoInterface daoReplicado;

	OAuthService oauthService;

	@Inject
	MessageBean mb;

	@Inject
	PageTransitionBean pt;

	private String codlog;
	private String senha;

	String ip = request.getRemoteAddr();

	public Auth() {

		String oauthKey = recursos.getResourceValue("oauth-key");
		String oauthSecret = recursos.getResourceValue("oauth-secret");
		oauthService = new ServiceBuilder().provider(USPApi.SSL.class).apiKey(oauthKey).apiSecret(oauthSecret).build();
	}

	public void getTokenUsp() {
		Token requestToken = oauthService.getRequestToken();
		usuarioLogado.setOauthToken(requestToken);
		String urlAutenticacao = oauthService.getAuthorizationUrl(usuarioLogado.getOauthToken());

		try {
			response.sendRedirect(urlAutenticacao);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void autenticarUSP() {

		if (!usuarioLogado.isAutenticado()) {
			if (usuarioLogado.getOauthVerifier() == null) {

				try {
					String oauthVerifier = request.getParameter("oauth_verifier");
					usuarioLogado.setOauthVerifier(new Verifier(oauthVerifier));
				} catch (NullPointerException | IllegalArgumentException npe) {
					// logout();
					System.out.println("Acesso Negado");
				}

			}

			Token accessToken = oauthService.getAccessToken(usuarioLogado.getOauthToken(), usuarioLogado.getOauthVerifier());

			// obter informacoes do usuario
			String protectedResource = recursos.getResourceValue("PROTECTED_RESOURCE_URL");

			OAuthRequest request = new OAuthRequest(Verb.POST, protectedResource);
			oauthService.signRequest(accessToken, request);
			Response response = request.send();
			JSONObject j = JSONObject.fromObject(response.getBody());

			// obtem os dados do usuario com dados do oauth
			Integer codpes = Integer.valueOf((String) j.get("loginUsuario"));
			String nompes = (String) j.get("nomeUsuario");
			String emailPrincipal = (String) j.get("emailPrincipalUsuario");
			String emailUsp = (String) j.get("emailUspUsuario");


			if (codpes != null) {
				usuarioLogado.setAutenticado(true);

				// obtem os dados do usuario do banco local
				// mas pode ocorrer de ser o primeiro acesso da pessoa
				// entao criamos ele com o perfil de aluno localmente
				Usuario usuario = daoUsuario.buscarUsuario(codpes);

				if (usuario == null) {
					// cria o usuario novo como aluno
					usuario = new Usuario();
					usuario.setDataCadastro(new Date());
					// obtem os dados do usuario com dados do oauth
					usuario.setCodpes(codpes);
					usuario.setNompes(nompes);
					usuario.setCodlog(emailPrincipal);
					usuario.setAtivado(true);
					usuario.getPapeis().add(daoPapel.buscarPapel("ALUNO"));

					String senhaGerada = PasswordGenerator.generatePassword();
					usuario.setSalt(Base64.encodeBase64String(PasswordGenerator.generateSalt()));
					usuario.setSenha(DigestUtils.sha256Hex(senhaGerada + usuario.getSalt()));

					usuario = (Usuario) daoUsuario.persist(usuario);

				}

				// ATENCAO O USUARIO DO BANCO NAO EH O MESMO DA SESSAO
				usuarioLogado.setCodpes(usuario.getCodpes());
				usuarioLogado.setNompes(usuario.getNompes());
				usuarioLogado.setEmail(usuario.getEmail());
				usuarioLogado.setPapeis(usuario.getPapeis());
				usuarioLogado.setGrupos(usuario.getGrupos());
				usuarioLogado.setAutorizado(this.autorizar());

				logger.logar("AUTENTICACAO USP", "OK", ip, (Usuario) daoUsuario.buscarUsuario(usuarioLogado.getCodpes()));
				// redireciona 
				if (usuarioLogado.isAutorizado()) {

					if (usuarioLogado.isAnunciante()) {
						pt.chamaHomeEmpresa();
					}

					if (usuarioLogado.isAluno()) {
						if (daoReplicado.isAluno(usuarioLogado.getCodpes())) {
							pt.chamaHomeAluno();
						} else {
							pt.chamaLogout();
						}
					} else {
						pt.chamaHome();
					}

				} else {
					mb.addMessage("acesso_negado", "main", FacesMessage.SEVERITY_ERROR);
					logger.logar("AUTENTICACAO USP", "NEGADO", ip + "  " + this.getCodlog(), null);
					return;
				}

			}

		}

	}

	public void autenticar() {

		if (!usuarioLogado.isAutenticado()) {

			// recupera o usuario do bd
			Usuario usuario = new Usuario();
			if (IntegerUtil.isInt(this.getCodlog())) {
				usuario = daoUsuario.buscarUsuario(Integer.valueOf(this.getCodlog()));
			} else {
				usuario = daoUsuario.buscarUsuario(this.getCodlog());
			}

			if (usuarioLogado == null || usuario == null) {
				mb.addMessage("acesso_negado", "main", FacesMessage.SEVERITY_ERROR);
				logger.logar("AUTENTICACAO LOCAL", "NEGADO", ip + "  " + this.getCodlog(), null);
				return;
			} else {

				// ATENCAO: o objeto usuario recuperado do bd nao eh o mesmo da sessao
				usuarioLogado.setCodpes(usuario.getCodpes());
				usuarioLogado.setNompes(usuario.getNompes());
				usuarioLogado.setEmail(usuario.getEmail());
				usuarioLogado.setPapeis(usuario.getPapeis());
				usuarioLogado.setGrupos(usuario.getGrupos());
				usuarioLogado.setId(usuario.getId());

				String senhaCriptografada = DigestUtils.sha256Hex(this.getSenha() + usuario.getSalt());
				if (senhaCriptografada.equals(usuario.getSenha())) {
					usuarioLogado.setAutenticado(true);
					logger.logar("AUTENTICACAO LOCAL", "OK", ip, (Usuario) daoUsuario.buscarUsuario(usuarioLogado.getCodpes()));
					usuarioLogado.setAutorizado(this.autorizar());

					if (usuarioLogado.isAutorizado()) {

						if (usuarioLogado.isAnunciante()) {
							pt.chamaHomeEmpresa();
						}

						if (usuarioLogado.isAluno()) {
							if (daoReplicado.isAluno(usuarioLogado.getCodpes())) {
								pt.chamaHomeAluno();
							} else {
								pt.chamaLogout();
							}

						} else {
							pt.chamaHome();
						}

					} else {
						mb.addMessage("acesso_negado", "main", FacesMessage.SEVERITY_ERROR);
						logger.logar("AUTENTICACAO LOCAL", "NEGADO", ip + "  " + this.getCodlog(), null);
						return;
					}
				} else {
					mb.addMessage("acesso_negado", "main", FacesMessage.SEVERITY_ERROR);
					logger.logar("AUTENTICACAO LOCAL", "NEGADO", ip + "  " + this.getCodlog(), null);
					return;
				}
			}
		}

	}

	public boolean autorizar() {

		if (!this.usuarioLogado.isAutorizado()) {
			String usuario = usuarioLogado.getCodpes() + "@" + ip;

			for (Papel pu : usuarioLogado.getPapeis()) {
				for (Papel ps : daoPapel.getPapeis()) {

					if (ps.getNome().equalsIgnoreCase(pu.getNome())) {
						usuarioLogado.setAutorizado(true);
						System.out.println("    A U T O R I Z A D O . . . " + usuario);
						logger.logar("AUTORIZACAO", "OK", ip, (Usuario) daoUsuario.buscarUsuario(usuarioLogado.getCodpes()));
						return true;
					}
				}
			}

			System.out.println("    N A O    A U T O R I Z A D O . . . " + usuario);
		}

		return usuarioLogado.isAutorizado();

	}

	public void logout() {
		// fc.responseComplete();
		System.out.println("L O G O U T " + usuarioLogado.getCodpes());
		logger.logar("LOGOUT", "OK", ip, (Usuario) daoUsuario.buscarUsuario(usuarioLogado.getCodpes()));

		String url;

		if (usuarioLogado.getOauthToken() == null) {
			url = recursos.getResourceValue("logout-url-local");
		} else {
			url = recursos.getResourceValue("logout-url-usp");
		}

		try {
			fc.getExternalContext().redirect(url);
			fc.getExternalContext().invalidateSession();
		} catch (IOException e) {
			System.out.println("URL nao encontrada");
		}

	}

	public String getCodlog() {
		return codlog;
	}

	public void setCodlog(String codlog) {
		this.codlog = codlog;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}