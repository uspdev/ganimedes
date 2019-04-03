package br.usp.ime.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfLayer;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Permite a sobreescrita de um arquivo PDF, utilizando iText
 * 
 * @author paulo_villegas
 * 
 */
public class PdfOverwriter {

	protected Document document;
	protected PdfReader reader;
	protected PdfWriter writer;
	protected PdfContentByte cb;
	protected PdfImportedPage page;
	protected PdfLayer page1_text;
	protected PdfLayer page1;
	protected Font currentFont;
	protected float height;
	protected int alignment;
	protected int paragraphLeftIdentation;
	protected int paragraphRightIdentation;
	protected int paragraphFirstLineIdentation;
	protected int lineSpacing;
	protected float wordSpacing;
	protected float characterSpacing;
	protected Map<String, Integer> phrases;
	protected static boolean rawMargins = false;
	
	public static void enableRawMargins(boolean enabled){
		rawMargins = enabled;
	}

	/**
	 * Instancia um objeto de PdfOverwriter, indicando o arquivo de origem e o
	 * arquivo onde serão salvas as alterações
	 * 
	 * @param inputFile
	 *            Arquivo PDF que será usado como base
	 * @param outputFile
	 *            Arquivo de PDF que conterá as alterações. Se não existir,
	 *            será criado
	 * @throws DocumentException
	 * @throws IOException
	 */
	public PdfOverwriter(String inputFile, String outputFile)
			throws DocumentException, IOException {

		this(inputFile, new FileOutputStream(outputFile));
	}
	
	public PdfOverwriter(String inputFile, OutputStream outputFile)
			throws DocumentException, IOException {
		this(inputFile, outputFile, PageSize.A4);
	}

	/**
	 * Instancia um objeto de PdfOverwriter, indicando o arquivo de origem e o
	 * stream contendo as alterações. Este método é especialmente útil para
	 * gerar arquivos PDF e envia-los pelo browser.
	 * 
	 * @param inputFile
	 *            Arquivo PDF que será usado como base
	 * @param outputFile
	 *            Stream onde será gravado o PFD
	 * @throws DocumentException
	 * @throws IOException
	 */
	public PdfOverwriter(String inputFile, OutputStream outputFile, Rectangle pageSize)
			throws DocumentException, IOException {

		// Container para as p�ginas a serem sobreescritas
		document = new Document(pageSize);

		// PDF de origem, a ser sobreescrito
		reader = new PdfReader(inputFile);

		// Documento onde as altera��es ser�o salvas
		writer = PdfWriter.getInstance(document, outputFile);
		writer.setPdfVersion(PdfWriter.VERSION_1_5);
		
		//override default margins (raw margins)
		if(rawMargins)	document.setMargins(0, 0, 0, 0);
		
		document.open();

		// P�gina importada do PDF original
		page = writer.getImportedPage(reader, 1);
		
		//System.out.println("page.getBoundingBox: " +  page.getBoundingBox());
		
		
		height = page.getHeight();

		currentFont = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.NORMAL);
		phrases = new LinkedHashMap<String, Integer>();

		paragraphLeftIdentation = 0;
		paragraphRightIdentation = 0;
		paragraphFirstLineIdentation = 0;
		lineSpacing = 20;
		wordSpacing = 0.0f;

		// Acesso direto ao conte�do a ser escrito
		cb = writer.getDirectContentUnder();
		
		alignment = Element.ALIGN_TOP;

		beginPage();

	}

	public void setJustified() {
		alignment = Element.ALIGN_JUSTIFIED;
	}

	public void setCentered() {
		alignment = Element.ALIGN_CENTER;
	}
	
	public void setRightAligned(){
		alignment = Element.ALIGN_RIGHT;
	}
	
	public void setLeftAligned(){
		alignment = Element.ALIGN_LEFT;
	}
	
	public void setFontSize(float size){
		currentFont.setSize(size);
	}

	public void setLineSpacing(int spacing) {
		lineSpacing = spacing;
	}
	
	public void setFont(Font font){
		currentFont = font;
	}

	public float getWordSpacing() {
		return wordSpacing;
	}

	public void setWordSpacing(float wordSpacing) {
		this.wordSpacing = wordSpacing;
		cb.setWordSpacing(wordSpacing);
	}

	public float getCharacterSpacing() {
		return characterSpacing;
	}

	public void setCharacterSpacing(float characterSpacing) {
		this.characterSpacing = characterSpacing;
		cb.setCharacterSpacing(characterSpacing);
	}

	protected void beginPage() throws IOException {

		// Adiciona a p�gina importada do PDF original
		cb.addTemplate(page, 0, 0);
		page1 = new PdfLayer("Page 1", writer);
		cb.setWordSpacing(wordSpacing);
		cb.beginLayer(page1);
		cb.setCharacterSpacing(characterSpacing);

	}

	public void newPage() throws IOException {
		cb.endLayer();
		document.newPage();
		beginPage();
	}

	/**
	 * Escreve um trecho de texto no PDF nas coordenadas especificadas.
	 * Contrário ao modo como iText funciona e de acordo com a maneira como
	 * coordenadas são expressas em computadores, a origem das coordenadas fica
	 * no canto superior esquerdo, o que permite usar as coordenadas informadas
	 * por um editor gráfico.
	 * 
	 * @param text
	 *            O texto a ser escrito
	 * @param x
	 *            A posição x (horizontal), em pixels, onde o texto começará
	 *            a ser escrito
	 * @param y
	 *            A posição y (vertical), em pixels, onde o texto começará a
	 *            ser escrito
	 */
	public void write(String text, float x, float y) {
		Phrase phrase = new Phrase();
		phrase.setFont(currentFont);
		phrase.add(text);

		ColumnText.showTextAligned(cb, alignment, phrase, x, height
				- y, 0);
	}
	
	public void writeRotated(String text, float x, float y, float rotation) {
		Phrase phrase = new Phrase();
		phrase.setFont(currentFont);
		phrase.add(text);

		ColumnText.showTextAligned(cb, alignment, phrase, x, height
				- y, rotation);
	}

	public void writeParagraph(String text) throws DocumentException {
		Paragraph paragraph = new Paragraph(text, currentFont);
		paragraph.setAlignment(alignment);
		paragraph.setLeading(lineSpacing);
		paragraph.setIndentationLeft(paragraphLeftIdentation);
		paragraph.setIndentationRight(paragraphRightIdentation);
		paragraph.setFirstLineIndent(paragraphFirstLineIdentation);
		document.add(paragraph);
	}
	
	public void writeParagraph(Map<String, Font> phrases) throws DocumentException{
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(alignment);
		paragraph.setLeading(lineSpacing);
		paragraph.setIndentationLeft(paragraphLeftIdentation);
		paragraph.setIndentationRight(paragraphRightIdentation);
		paragraph.setFirstLineIndent(paragraphFirstLineIdentation);
		
		for (Map.Entry<String, Font> phrase : phrases.entrySet()) {
		    paragraph.add( new Phrase(phrase.getKey(), phrase.getValue()));
		}
		
		document.add(paragraph);
	}
	
	public void addPhrase(String phrase, int style){
		phrases.put(phrase, style);
	}
	
	public void writeParagraph() throws DocumentException{
		if(phrases.isEmpty()){
			return;
		}
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(alignment);
		paragraph.setLeading(lineSpacing);
		paragraph.setIndentationLeft(paragraphLeftIdentation);
		paragraph.setIndentationRight(paragraphRightIdentation);
		paragraph.setFirstLineIndent(paragraphFirstLineIdentation);
		
		Font tmpFont;
		
		for (Map.Entry<String, Integer> phrase : phrases.entrySet()) {
			tmpFont = new Font(currentFont);
			tmpFont.setStyle(phrase.getValue());
		    paragraph.add( new Phrase(phrase.getKey(), tmpFont));
		}
		document.add(paragraph);
		phrases = new LinkedHashMap<String, Integer>();
	}

	public void setParagraph(int leftMargin, int rightMargin,
			int firstLineMargin) {
		paragraphLeftIdentation = leftMargin;
		paragraphRightIdentation = rightMargin;
		paragraphFirstLineIdentation = firstLineMargin;
	}

	public void skipLines(int qtd) throws DocumentException {
		if (qtd < 1) {
			return;
		}
		for (int i = 0; i < qtd; i++) {
			writeParagraph(" ");
		}
	}

	/**
	 * Fecha o arquivo, salvando as altera��es no arquivo de destino.
	 */
	public void close() {
		cb.endLayer();
		document.close();
	}

}
