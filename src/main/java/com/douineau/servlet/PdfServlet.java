package com.douineau.servlet;

import java.io.IOException;
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
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontFactory;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.douineau.entity.Question;
import com.douineau.entity.Reponse;
import com.douineau.entity.User;

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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// PdfBox
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		
		// Retrieving the pages of the document
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		start(contentStream);
		contentStream.showText("Voici les réponses que vous avez répondues");

		for (Map.Entry<Question, Reponse> entry : user.getMap().entrySet()) {
			
			contentStream.showText("Question posée :");
			contentStream.showText(entry.getKey().getTexte());

			if (entry.getValue() != null) {
				contentStream.showText("Reponse faite");
				contentStream.showText(entry.getValue().getTexte());
				if (entry.getValue().getIsTrue()) {
					contentStream.showText("Bonne réponse !!!");
					contentStream.newLine();  
				} else {
					contentStream.showText("Essaye encore...");
					contentStream.showText("La bonne réponse était :");
					contentStream.newLine();  
					
					for(Reponse reponse : entry.getKey().getReponses()) {
						if(reponse.getIsTrue()) {
							contentStream.showText(reponse.getTexte());
						}
					}
				}

			} else {
				contentStream.showText("Pas de réponse..");
				contentStream.newLine();
			}
		}

		contentStream.showText("Score global");
		contentStream.showText(user.getScore().toString());
		contentStream.newLine();
		
		contentStream.showText("Merci d'avoir participé à ce quizz...");
		
		end(contentStream);

		document.save("C:\\Users\\josselin.douineau\\projects\\quizz-java\\Resultats.pdf");
		document.close();

		response.sendRedirect("C:\\Users\\josselin.douineau\\projects\\quizz-java\\Resultats.pdf");
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
