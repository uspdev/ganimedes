package br.usp.ime.ganimedes.documentos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usp.ime.ganimedes.model.Anuncio;
import br.usp.ime.ganimedes.model.Curso;
import br.usp.ime.ganimedes.model.ENivelCurso;
import br.usp.ime.ganimedes.model.ERegime;
import br.usp.ime.util.DataUtility;
import br.usp.ime.util.Recursos;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class DocumentoAnuncio {

	public void gerarDocumento(Anuncio a) throws DocumentException, IOException {

		Document pdf = new Document(PageSize.A4, 10, 10, 10, 10);
		// left, right, top, bottom
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(pdf, baos);
			pdf.open();
			pdf = montarAnuncio(a, pdf);
			pdf.close();

		} catch (DocumentException de) {
			System.err.println(de.getMessage());

		} finally {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment;filename=anuncio_" + a.getId() + ".pdf");
			response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();
			out.close();
			FacesContext.getCurrentInstance().responseComplete();
		}

	}

	public Document montarAnuncio(Anuncio a, Document pdf) throws DocumentException {

		String salario = "";

		if (a.getRegimeTrabalho().equals(ERegime.ESTAGIO)) {
			salario = "Bolsa Auxílio: ";
		} else {
			salario = "Remuneração: ";
		}

		PdfPTable t = new PdfPTable(2);
		float[] columnWidths = { 2f, 5f };
		t.setWidths(columnWidths);

		Font fn = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL);
		Font fb = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
		Font fd = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
		DataUtility du = new DataUtility();

		int posCel = 0;
		PdfPCell cell[] = new PdfPCell[30];

		// celulas obrigatorias

		try {

			t.addCell(getLogoCell(a));
			t.addCell(getCell("\n" + a.getTitvag(), fd, false, true));
		} catch (NullPointerException e) {

		}

		try {
			t.addCell(getCell("\nEmpresa: ", fb, true, false));
			t.addCell(getCell("\n" + a.getNomeEmpresa(), fn, true, false));
		} catch (NullPointerException e) {
		}

		
		try {
			t.addCell(getCell("\nDescrição: ", fb, true, false));
			t.addCell(getCell("\n" + a.getDescricaoEmpresa(), fn, true, false));
		} catch (NullPointerException e) {
		}
		
		
		try {
			t.addCell(getCell("Área de Atuação: ", fb, true, false));
			t.addCell(getCell(a.getAreaAtuacao(), fn, true, false));
		} catch (NullPointerException e) {

		}

		try {
			t.addCell(getCell("Local: ", fb, true, false));
			t.addCell(getCell(a.getLoctrb(), fn, true, false));
		} catch (NullPointerException e) {
		}

		try {
			t.addCell(getCell("\nAtividades:", fb, false, false));
			t.addCell(getCell(a.getDesvag() + "\n\n", fn, false, false));
		} catch (NullPointerException e) {
		}

		try {
			t.addCell(getCell("\nPerfil do Candidato: ", fb, false, false));
			t.addCell(getCell(a.getPerfilCandidato() + "\n\n", fn, false, false));
		} catch (NullPointerException e) {

		}

		try {
			t.addCell(getCell("Curso(s): ", fb, true, false));

			String cursos = "";

			for (Curso c : a.getCursos()) {

				if (c.getNivel().equals(ENivelCurso.POS)) {
					cursos = cursos + c.getNivel() + " " + c.getNome() + "\n";
				} else {
					cursos = cursos + c.getNome() + "\n";
				}

			}

			t.addCell(getCell(cursos, fn, true, false));

		} catch (NullPointerException e) {

		}

		try {
			t.addCell(getCell("Regime: ", fb, true, false));
		if (a.getHorsem() == 0) {
			t.addCell(getCell(a.getRegimeTrabalho().getValor() + " (Carga horária a combinar)", fn, true, false));
		} else {
			t.addCell(getCell(a.getRegimeTrabalho().getValor() + " (" + a.getHorsem().toString() + " horas semanais)", fn, true, false));
		}
		} catch (NullPointerException e) {
			t.addCell(getCell(a.getRegimeTrabalho().getValor() + " (Carga horária a combinar)", fn, true, false));
		}

		try {
			t.addCell(getCell("Benefícios:", fb, true, false));
			t.addCell(getCell(a.getBenofr(), fn, true, false));
		} catch (NullPointerException e) {

		}

		try {
			t.addCell(getCell(salario, fb, true, false));
			t.addCell(getCell(a.getSalmesFormatado(), fn, true, false));
		} catch (NullPointerException e) {
			t.addCell(getCell("a combinar", fn, true, false));
		}

		try {

			t.addCell(getCell("\nCandidate-se: ", fb, false, false));

			if (!a.getUrlweb().isEmpty()) {
				t.addCell(getCell("Website: ", fb, true, false));
				t.addCell(getCell(a.getUrlweb(), fn, true, false));
			}

		} catch (NullPointerException e) {

		}

		try {
			if (!a.getEmailInscricao().isEmpty()) {
				t.addCell(getCell("E-mail: ", fb, true, false));
				t.addCell(getCell(a.getEmailInscricao(), fn, true, false));
			}
		} catch (NullPointerException e) {

		}

		if (!a.getNumtelInscricao().isEmpty()) {

			try {
				t.addCell(getCell("Telefone: ", fb, true, false));
				t.addCell(getCell(a.getNumtel(), fn, true, false));
			} catch (NullPointerException e) {

			}

		}

		try {
			pdf.add(t);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pdf;
	}

	private PdfPCell getCell(String t, Font f, boolean simples, boolean center) {
		PdfPCell c = new PdfPCell(new Phrase(t, f));
		if (center) {
			c.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		c.setPadding(5);
		c.setBorder(Rectangle.NO_BORDER);
		if (!simples) {
			c.setColspan(2);
		}

		return c;
	}

	private PdfPCell getLogoCell(Anuncio a) {
		PdfPCell c = new PdfPCell();

		c.setHorizontalAlignment(Element.ALIGN_CENTER);

		c.setPadding(5);
		c.setBorder(Rectangle.NO_BORDER);

		Image img = null;

		try {
			img = Image.getInstance(a.getLogotipo());
			img.scaleAbsolute(70f, 70f);
			c.addElement(img);

		} catch (BadElementException | IOException | NullPointerException e) {
			// nada a fazer

		}

		return c;
	}

	private Image getLogoIme() throws BadElementException, MalformedURLException, IOException {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Recursos recursos = new Recursos();
		String appName = recursos.getResourceValue("app-name");
		String logoUrl = "http://" + req.getLocalName() + ":" + req.getLocalPort() + "/" + appName + "/resources/images/logo-ime.png";
		Image img = Image.getInstance(logoUrl);
		// img.scalePercent(15);
		return img;
	}

}