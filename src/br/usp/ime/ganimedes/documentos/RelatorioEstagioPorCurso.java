package br.usp.ime.ganimedes.documentos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.usp.ime.ganimedes.model.CursoGr;
import br.usp.ime.ganimedes.model.Documento;
import br.usp.ime.ganimedes.model.Estagio;
import br.usp.ime.util.DataUtility;
import br.usp.ime.util.Recursos;

import com.itextpdf.text.Chunk;
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

public class RelatorioEstagioPorCurso {

	class MyFooter extends PdfPageEventHelper {
		Font ffont = new Font(Font.FontFamily.HELVETICA, 6, Font.NORMAL);

		public void onEndPage(PdfWriter writer, Document document) {

			Date data = new Date();
			Recursos recursos = new Recursos();
			String app = recursos.getResourceValue("app");

			PdfContentByte cb = writer.getDirectContent();
			// Phrase header = new Phrase("Relatório de Estágios", ffont);
			Phrase footer = new Phrase(app + " " + DataUtility.dateToString(data), ffont);
			// ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header, (document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, 0);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, footer, (document.right() - document.left()) / 2 - 20, document.bottom() - 10, 0);

		}
	}

	public void gerarDocumento(HashMap<CursoGr, List<Estagio>> mapaRelatorio, Date di, Date df) throws DocumentException, IOException {

		Document pdf = new Document(PageSize.A4, 20, 20, 50, 50);
		// left, right, top, bottom
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			PdfWriter writer = PdfWriter.getInstance(pdf, baos);
			pdf.open();

			MyFooter event = new MyFooter();
			writer.setPageEvent(event);

			pdf = montarRelatorio(mapaRelatorio, di, df, pdf);
			// this.onEndPage(writer, pdf);

			pdf.close();

		} catch (DocumentException de) {
			System.err.println(de.getMessage());

		} finally {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment;filename=estagios.pdf");
			response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();
			out.close();
			FacesContext.getCurrentInstance().responseComplete();
		}

	}

	public Document montarRelatorio(HashMap<CursoGr, List<Estagio>> mapaRelatorio, Date di, Date df, Document pdf) throws DocumentException {

		Font fn = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
		Font fb = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);

	     Paragraph paragraph = new Paragraph();
	     Chunk chunk = new Chunk("Estágios no período de " + DataUtility.dateToStringDate(di) + " até " + DataUtility.dateToStringDate(df), fb);
	     paragraph.add(chunk);
	     pdf.add(paragraph);
		
		for (Map.Entry<CursoGr, List<Estagio>> entry : mapaRelatorio.entrySet()) {

			PdfPTable t = new PdfPTable(3);
			float[] columnWidths = { 3f, 3f, 2f };
			t.setWidths(columnWidths);

			

			CursoGr c = entry.getKey();
			List<Estagio> estagios = entry.getValue();

			t.addCell(getCellTitle("\n" + c.getCodcur() + "  " + c.getCodhab() + "  " + c.getNomcurso(), fb, true, 3));

			t.addCell(getCell("Aluno", fb, true, 1));
			t.addCell(getCell("Empresa", fb, true, 1));
			t.addCell(getCell("Período", fb, true, 1));

			for (Estagio e : estagios) {
				try {
					t.addCell(getCell("\n" + e.getAluno().getNompes() + Chunk.NEWLINE + e.getAluno().getCodpes() + " " + e.getAluno().getCodhab(), fn, false, 1));
					t.addCell(getCell("\n" + e.getEmpresa().getNome() + Chunk.NEWLINE + e.getEmpresa().getCnpj(), fn, false, 1));
					t.addCell(getCell("\n" + e.getPeriodo(), fn, false, 1));

				} catch (NullPointerException ex) {
					ex.printStackTrace();
				}
			}

			
			
			t.addCell(getCell("\n Total: " + estagios.size(), fn, false, 3));
			
			try {
				pdf.add(t);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}

		return pdf;
	}

	private PdfPCell getCell(String t, Font f, boolean center, int colspan) {

		Paragraph paragraph = new Paragraph(new Phrase(t, f));
		paragraph.setAlignment(Element.ALIGN_TOP);

		PdfPCell c = new PdfPCell();
		c.addElement(paragraph);

		c.setColspan(colspan);
		c.setBorder(Rectangle.BOTTOM);
		c.setPaddingTop(-10f);
		c.setPaddingBottom(5f);
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
		c.setBorder(Rectangle.NO_BORDER);
		c.setPaddingTop(20f);
		c.setPaddingBottom(20f);

		if (center) {
			c.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		return c;
	}

}