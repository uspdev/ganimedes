package br.usp.ime.ganimedes.documentos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.usp.ime.ganimedes.model.Convenio;
import br.usp.ime.ganimedes.model.Empresa;
import br.usp.ime.util.DataUtility;
import br.usp.ime.util.Recursos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class RelatorioEmpresa {

	class MyFooter extends PdfPageEventHelper {
		Font ffont = new Font(Font.FontFamily.HELVETICA, 6, Font.NORMAL);

		public void onEndPage(PdfWriter writer, Document document) {

			Date data = new Date();
			Recursos recursos = new Recursos();
			String app = recursos.getResourceValue("app");

			PdfContentByte cb = writer.getDirectContent();
			// Phrase header = new Phrase("this is a header", ffont);
			Phrase footer = new Phrase(app + " " + DataUtility.dateToString(data), ffont);
			// ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header, (document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, 0);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, footer, (document.right() - document.left()) / 2 - 20, document.bottom() - 10, 0);
			// ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, footer, 20, 20 , 0);
		}
	}

	public void gerarDocumento(List<Empresa> empresas, Date dta) throws DocumentException, IOException {

		Document pdf = new Document(PageSize.A4, 20, 20, 50, 50);
		// left, right, top, bottom
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			PdfWriter writer = PdfWriter.getInstance(pdf, baos);
			pdf.open();

			MyFooter event = new MyFooter();
			writer.setPageEvent(event);

			pdf = montarRelatorio(empresas, dta, pdf);
			// this.onEndPage(writer, pdf);

			pdf.close();

		} catch (DocumentException de) {
			System.err.println(de.getMessage());

		} finally {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment;filename=convenios.pdf");
			response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();
			out.close();
			FacesContext.getCurrentInstance().responseComplete();
		}

	}

	public Document montarRelatorio(List<Empresa> empresas, Date dta, Document pdf) throws DocumentException {

		Font fn = new Font(Font.FontFamily.HELVETICA, 6, Font.NORMAL);
		Font fb = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD);
		Font fd = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);

		PdfPTable t = new PdfPTable(3);
		float[] columnWidths = { 8f, 2f, 2f };
		t.setWidths(columnWidths);

		t.addCell(getCellTitle("Empresas com convênio vigente em " + DataUtility.dateToStringDate(dta), fb, true, 3));

		t.addCell(getCell("Razão Social e CNPJ", fb, true, 1));
		t.addCell(getCell("Processo", fb, true, 1));
		t.addCell(getCell("Período", fb, true, 1));

		for (Empresa empresa : empresas) {


			for (Convenio convenio : empresa.getConvenios()) {
				try {

					t.addCell(getCell("\n" + empresa.getNome() + " " + empresa.getCnpj(), fn, false, 1));
					t.addCell(getCell("\n" + convenio.getNumpro(), fn, false, 1));
					t.addCell(getCell("\n" + convenio.getPeriodo(), fn, false, 1));

				} catch (NullPointerException e) {

				}
			}

		}

		t.addCell(getCell("\n Total: " + empresas.size(), fn, false, 3));
		
		
		try {
			pdf.add(t);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return pdf;
	}

	private PdfPCell getCell(String t, Font f, boolean center, int colspan) {

		Paragraph paragraph = new Paragraph(new Phrase(t, f));

		paragraph.setAlignment(Element.ALIGN_TOP);

		PdfPCell c = new PdfPCell();
		c.addElement(paragraph);

		c.setBorder(Rectangle.BOTTOM);
		c.setBorder(Rectangle.LEFT);
		c.setBorder(Rectangle.RIGHT);
		c.setBorder(Rectangle.TOP);
		c.setUseAscender(true);
		c.setUseDescender(true);
	
		c.setColspan(colspan);
		c.setPaddingTop(-5f);
		c.setPaddingBottom(0f);
		if (center) {
			c.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		return c;
	}

	private PdfPCell getCellTitle(String t, Font f, boolean center, int colspan) {
		Paragraph paragraph = new Paragraph(new Phrase(t, f));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		PdfPCell c = new PdfPCell();
		c.addElement(paragraph);
		c.setColspan(colspan);

		c.setUseAscender(true);
		c.setUseDescender(true);

		c.setBorder(Rectangle.BOTTOM);

		c.setPaddingTop(10f);
		c.setPaddingBottom(10f);

		if (center) {
			c.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		return c;
	}

}