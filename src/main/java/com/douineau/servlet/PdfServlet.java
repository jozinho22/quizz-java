package com.douineau.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.douineau.entity.Question;
import com.douineau.entity.Reponse;
import com.douineau.entity.User;
import com.lowagie.text.DocumentException;

/**
 * Servlet implementation class PdfServlet
 */
@WebServlet("/pdf")
public class PdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PdfServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String inputFile = "resultats.jsp";
		String url = new File(inputFile).toURI().toURL().toString();
		String outputFile = "resultats.pdf";
		OutputStream os = new FileOutputStream(outputFile);

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(url);
		renderer.layout();
		try {
			renderer.createPDF(os);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		os.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String inputFile = "resultats.jsp";
		String url = new File(inputFile).toURI().toURL().toString();
		String outputFile = "resultats.pdf";
		OutputStream os = new FileOutputStream(outputFile);

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(url);
		renderer.layout();
		try {
			renderer.createPDF(os);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		os.close();
		
		
	}

	private void end(PDPageContentStream contentStream) throws IOException {
		contentStream.endText();
		contentStream.close();
	}

	private void start(PDPageContentStream contentStream) throws IOException {
		contentStream.beginText();
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
	}

}
