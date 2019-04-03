package br.usp.ime.ganimedes.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.usp.ime.util.DataUtility;
import br.usp.ime.util.OrdenadorDocumentoDtaEnt;
import br.usp.ime.util.OrdenadorJornadaData;
import br.usp.ime.util.OrdenadorRemuneracaoData;
import br.usp.ime.util.OrdenadorSupervisorData;

@Entity
@Table(name = "ESTAGIO")
public class Estagio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Empresa empresa;

	@ManyToOne
	private Aluno aluno;

	@OneToMany(mappedBy = "estagio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Documento> documentos = new ArrayList<Documento>();

	@OneToMany(mappedBy = "estagio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Supervisor> supervisores = new ArrayList<Supervisor>();

	@OneToMany(mappedBy = "estagio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Remuneracao> remuneracoes = new ArrayList<Remuneracao>();

	@OneToMany(mappedBy = "estagio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Jornada> jornadas = new ArrayList<Jornada>();

	@OneToMany(mappedBy = "estagio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Notificacao> notificacoes = new ArrayList<Notificacao>();

	public Estagio() {

	}

	public Estagio(Integer id) {
		super();
		this.id = id;
	}

	@Transient
	public Date getDtaini() {

		try {
			return this.getTermoCompromisso().getDtaini();

		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * Retorna a data final do estagio Precisa percorrer os documentos para encontrar a data final do estagio.
	 * 
	 * Se existir apenas o termo de compromisso, a data final sera a data declarada no termo de compromisso
	 * 
	 * @return
	 */

	@Transient
	public Date getDtafim() {
		try {
			return this.getTermoRescisao().getDtafim();
		} catch (NullPointerException e) {

		}

		try {
			return this.getTermoRealizacao().getDtafim();
		} catch (NullPointerException e) {

		}

		if (!this.getTermosAditivos().isEmpty()) {
			return this.getTermosAditivos().get(this.getTermosAditivos().size() - 1).getDtafim();
		}

		try {
			return this.getTermoCompromisso().getDtafim();
		} catch (NullPointerException e) {

		}

		return null;
	}

	@Transient
	public String getPeriodo() {
		String p = "Indeterminado";

		if ((this.getDtaini() != null) && (this.getDtafim() != null)) {
			p = DataUtility.dateToStringDate(this.getDtaini()) + " a " + DataUtility.dateToStringDate(this.getDtafim());
		}

		return p;
	}

	@Transient
	public boolean isAtivo() {

		Date hoje = new Date();

		try {
			if ((this.getDtaini().before(hoje)) && (this.getDtafim().after(hoje))) {

				for (Documento d : this.getDocumentos()) {
					if (d.getTipo().equals(ETipoDoc.COM) && d.getStatusDoc().equals(EStatusDoc.DEFERIDO)) {
						return true;
					}

				}
			}
		} catch (NullPointerException e) {
			return false;
		}

		return false;
	}

	@Transient
	public boolean temEncerramentoAprovado() {

		for (Documento d : this.getDocumentos()) {
			if (d.getTipo().equals(ETipoDoc.REA) && d.getStatusDoc().equals(EStatusDoc.DEFERIDO)) {
				return true;
			}

			if (d.getTipo().equals(ETipoDoc.RES) && d.getStatusDoc().equals(EStatusDoc.DEFERIDO)) {
				return true;
			}
		}
		return false;
	}

	@Transient
	public boolean proximoEncerramento() {

		if (this.isAtivo()) {

			Date hoje = new Date();
			Calendar d1 = Calendar.getInstance();
			d1.setTime(hoje);

			Calendar d2 = Calendar.getInstance();
			d2.setTime(this.getDtafim());

			if (DataUtility.daysBetween(d1, d2) <= 30) {
				return true;
			}
		}
		return false;
	}

	@Transient
	public String getNomeEmpresaVigencia() {
		if (this.isAtivo()) {
			return " *** " + this.getEmpresa().getNome() + " *** ";
		} else {
			return this.getEmpresa().getNome();
		}
	}

	private Documento getTermoCompromisso() {

		for (Documento d : this.getDocumentos()) {
			if (d.getTipo().equals(ETipoDoc.COM)) {
				return d;
			}
		}

		return null;

	}

	private List<Documento> getTermosAditivos() {
		List<Documento> documentos = new ArrayList<Documento>();
		for (Documento d : this.getDocumentos()) {

			try {
				if (d.getTipo().equals(ETipoDoc.ADI)) {
					documentos.add(d);
				}
			} catch (NullPointerException e) {
			}

		}

		// classifica os documentos pela data fim crescente

		Collections.sort(documentos, new OrdenadorDocumentoDtaEnt());

		return documentos;
	}

	private Documento getTermoRescisao() {
		for (Documento d : this.getDocumentos()) {
			if (d.getTipo().equals(ETipoDoc.RES)) {
				return d;
			}
		}

		return null;

	}

	private Documento getTermoRealizacao() {
		for (Documento d : this.getDocumentos()) {
			if (d.getTipo().equals(ETipoDoc.REA)) {
				return d;
			}
		}

		return null;

	}

	public boolean existeTermoCompromisso() {
		for (Documento d : this.getDocumentos()) {
			if (d.getTipo().equals(ETipoDoc.COM)) {
				return true;
			}
		}
		return false;
	}

	@Transient
	public boolean temTermoCompromissoAprovado() {

		for (Documento d : this.getDocumentos()) {
			if (d.getTipo().equals(ETipoDoc.COM) && d.getStatusDoc().equals(EStatusDoc.DEFERIDO)) {
				return true;
			}
		}
		return false;
	}

	public boolean existeErroDatas() {
		for (Documento d : this.getDocumentos()) {
			if (d.getDtaini().after(d.getDtafim())) {
				return true;
			}
		}
		return false;
	}

	public String getRemuneracao() {

		double r = 0;
		String rf = "";

		if (!this.getRemuneracoes().isEmpty()) {
			Collections.sort(this.getRemuneracoes(), new OrdenadorRemuneracaoData());
			r = this.getRemuneracoes().get(this.getRemuneracoes().size() - 1).getRemuneracao();
		}

		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

		rf = nf.format(r);

		return rf;

	}

	public String getSupervisor() {

		String s = "";

		if (!this.getSupervisores().isEmpty()) {
			Collections.sort(this.getSupervisores(), new OrdenadorSupervisorData());
			s = this.getSupervisores().get(this.getSupervisores().size() - 1).getNome();
		}

		return s;

	}

	public Integer getJornada() {

		Integer j = 0;

		if (!this.getJornadas().isEmpty()) {
			Collections.sort(this.getJornadas(), new OrdenadorJornadaData());
			j = this.getJornadas().get(this.getJornadas().size() - 1).getJornada();
		}

		return j;

	}

	public boolean existeConflitoDatas() {

		List<Documento> documentos1 = new ArrayList<Documento>();
		List<Documento> documentos2 = new ArrayList<Documento>();

		documentos1.add(this.getTermoCompromisso());
		documentos1.addAll(this.getTermosAditivos());

		documentos2.add(this.getTermoCompromisso());
		documentos2.addAll(this.getTermosAditivos());

		for (Documento d1 : documentos1) {
			for (Documento d2 : documentos2) {

				if (d1 == d2) {
					continue;
				}

				try {
					if (d1.getDtaini().after(d2.getDtaini()) && d1.getDtaini().before(d2.getDtafim())) {
						return true;
					}
				} catch (NullPointerException e) {
					return true;
				}

			}

			return false;
		}

		/*
		 * for (Documento d : this.getDocumentos()) { if (d.getTipo().equals(ETipoDoc.COM)) { return true; } }
		 */
		return false;
	}

	public boolean deveRelatorioAtividade() {

		/*
		 * o estagio deve relatorio de atividade se:
		 * 
		 * ATIVO e nao tem nenhum relatorio de atividade ou termo de aditamento com prorrogacao de prazo por um periodo maior que 180 dias
		 * 
		 * INATIVO e não tem nenhum termo de rescisao ou realizacao
		 */

		// obtem o numero de dias trabalhados
		// desde o ultimo relatorio de atividades
		// se esse numero for > 180 dias entao deve

		Date hoje = new Date();
		Date dataRelatorio = this.getDtaini();

		if (dataRelatorio != null) {

			for (Documento d : this.getDocumentos()) {

				if (d.getTipo().equals(ETipoDoc.REA) && d.getStatusDoc().equals(EStatusDoc.DEFERIDO)) {
					return false;
				}

				if (d.getTipo().equals(ETipoDoc.RES) && d.getStatusDoc().equals(EStatusDoc.DEFERIDO)) {
					return false;
				}

				if (d.getTipo().equals(ETipoDoc.ATV) && d.getStatusDoc().equals(EStatusDoc.DEFERIDO)) {
					if (dataRelatorio.before(d.getDtafim()))
						dataRelatorio = d.getDtafim();
				}

				if (d.getTipo().equals(ETipoDoc.ADI) && d.getStatusDoc().equals(EStatusDoc.DEFERIDO)) {
					if (d.getDtaini().after(this.getDtaini())) {
						if (dataRelatorio.before(d.getDtaini()))
							dataRelatorio = d.getDtaini();
					}
				}

			}

			Calendar d1 = Calendar.getInstance();
			Calendar d2 = Calendar.getInstance();

			if (dataRelatorio.before(hoje)) {
				d1.setTime(hoje);
				d2.setTime(dataRelatorio);
				int d = DataUtility.daysBetween(d1, d2);
				if (d > 180) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean encerradoSemComprovacao() {
		Date hoje = new Date();
		if (this.temTermoCompromissoAprovado() && !this.temEncerramentoAprovado() && this.getDtafim().before(hoje)) {
			return true;
		}

		return false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Documento> getDocumentos() {
		Collections.sort(documentos, new OrdenadorDocumentoDtaEnt());
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public List<Supervisor> getSupervisores() {
		return supervisores;
	}

	public void setSupervisores(List<Supervisor> supervisores) {
		this.supervisores = supervisores;
	}

	public List<Remuneracao> getRemuneracoes() {
		return remuneracoes;
	}

	public void setRemuneracoes(List<Remuneracao> remuneracoes) {
		this.remuneracoes = remuneracoes;
	}

	public List<Jornada> getJornadas() {
		return jornadas;
	}

	public void setJornadas(List<Jornada> jornadas) {
		this.jornadas = jornadas;
	}

	public List<Notificacao> getNotificacoes() {
		return notificacoes;
	}

	public void setNotificacoes(List<Notificacao> notificacoes) {
		this.notificacoes = notificacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estagio other = (Estagio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estagio [id=" + id + "]";
	}

}