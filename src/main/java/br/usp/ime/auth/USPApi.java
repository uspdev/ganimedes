package br.usp.ime.auth;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

import br.usp.ime.util.Recursos;

public class USPApi extends DefaultApi10a {

	private String AUTHORIZE_URL;
	private String REQUEST_TOKEN_RESOURCE;
	private String ACCESS_TOKEN_RESOURCE;

	public USPApi() {

		Recursos recursos = new Recursos();

		this.AUTHORIZE_URL = recursos.getResourceValue("AUTHORIZE_URL");
		this.REQUEST_TOKEN_RESOURCE = recursos.getResourceValue("REQUEST_TOKEN_RESOURCE");
		this.ACCESS_TOKEN_RESOURCE = recursos.getResourceValue("ACCESS_TOKEN_RESOURCE");

	}

	// TESTES

	// private static final String AUTHORIZE_URL =
	// "https://labs.uspdigital.usp.br/wsusuario/oauth/authorize?oauth_token=%s";
	// private static final String REQUEST_TOKEN_RESOURCE =
	// "labs.uspdigital.usp.br/wsusuario/oauth/request_token";
	// private static final String ACCESS_TOKEN_RESOURCE =
	// "labs.uspdigital.usp.br/wsusuario/oauth/access_token";

	// PRODUCAO
	// private static final String AUTHORIZE_URL =
	// "https://uspdigital.usp.br/wsusuario/oauth/authorize?oauth_token=%s";
	// private static final String REQUEST_TOKEN_RESOURCE =
	// "uspdigital.usp.br/wsusuario/oauth/request_token";
	// private static final String ACCESS_TOKEN_RESOURCE =
	// "uspdigital.usp.br/wsusuario/oauth/access_token";

	@Override
	public String getAccessTokenEndpoint() {
		return "http://" + ACCESS_TOKEN_RESOURCE;
	}

	@Override
	public String getRequestTokenEndpoint() {
		return "http://" + REQUEST_TOKEN_RESOURCE;
	}

	@Override
	public String getAuthorizationUrl(Token requestToken) {
		return String.format(AUTHORIZE_URL, requestToken.getToken());
	}

	public static class SSL extends USPApi {
		@Override
		public String getAccessTokenEndpoint() {
			return "https://" + this.getACCESS_TOKEN_RESOURCE();
		}

		@Override
		public String getRequestTokenEndpoint() {
			return "https://" + this.getREQUEST_TOKEN_RESOURCE();
		}
	}

	public static class Authenticate extends SSL {
		private static final String AUTHENTICATE_URL = "";

		@Override
		public String getAuthorizationUrl(Token requestToken) {
			return String.format(AUTHENTICATE_URL, requestToken.getToken());
		}
	}

	public static class Authorize extends SSL {
	}

	public String getAUTHORIZE_URL() {
		return AUTHORIZE_URL;
	}

	public String getREQUEST_TOKEN_RESOURCE() {
		return REQUEST_TOKEN_RESOURCE;
	}

	public String getACCESS_TOKEN_RESOURCE() {
		return ACCESS_TOKEN_RESOURCE;
	}

}
