<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.douineau.entity.User"%>
<%@ page import="com.douineau.entity.Question"%>
<%@ page import="com.douineau.entity.Reponse"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.douineau.utils.TagBuilder"%>

<%
	boolean ok = false;
	User user = (User) session.getAttribute("user");
	if (user == null) {
		response.sendRedirect("error");
	} else {
		ok = true;
	}
%>

<tag:begin />
<tag:navbar />

<div id="resultats-section" class="container">
	<div id="inner-section-resultats">
		<div id="resultats">
			<%-- 			<c:forEach items="${map}" var="entry"> --%>
			<p>Voici vos r�sultats</p>
			<p>${user.score}/${nbQuestions}</p>
			<hr>
			<%-- 					<h4>${entry.key.texte}</h4> --%>
			<%
				if (ok) {
					int k = 0;
					for (Map.Entry<Question, Reponse> entry : user.getMap().entrySet()) {
	
						//Question
						out.print(TagBuilder.buildTag("h4",
						TagBuilder.buildTag("b", "Question n�" + ++k + " : ") + entry.getKey().getTexte()));
						//R�ponse
						if (entry.getValue() != null) {
							out.print(TagBuilder.buildTag("h5", "Vous avez r�pondu : "));
							
							if (entry.getValue().getIsTrue()) {
								out.print("<i class=\"fa fa-check\" aria-hidden=\"true\" style=\"font-size: 20px; color: green; \"></i>");
								out.print(TagBuilder.buildTag("h5", " - " + entry.getValue().getTexte(), "display:inline-block;"));
							} else {
								out.print("<i class=\"fa fa-close\" aria-hidden=\"true\" style=\"font-size: 20px; color: red; \"></i>");
								out.print(TagBuilder.buildTag("h5", " - " + entry.getValue().getTexte(), "display:inline-block;"));
							}
						} else {
							out.print(TagBuilder.buildTag("h5", "(Vous n'avez pas r�pondu...)"));
						}
	
						// Si pas de r�ponse ou mauvaise r�ponse
						if (entry.getValue() == null || !entry.getValue().getIsTrue()) {
							out.print(TagBuilder.buildTag("h5", "La bonne r�ponse �tait :"));
							for (Reponse reponse : entry.getKey().getReponses()) {
								if (reponse.getIsTrue()) {
									out.print("<i class=\"fa fa-check\" aria-hidden=\"true\" style=\"font-size: 20px; color: green; \"></i>");
									out.print(TagBuilder.buildTag("h5", " - " + reponse.getTexte(), "display:inline-block;"));
								}
							}
						}
	
						out.print("<hr>");
					}
				}
			%>
			<%-- 			</c:forEach> --%>
			<div id="return-btn">
				<a href="index.jsp">
					<button class="btn" type="submit">Retour au quizz</button>
				</a> 
				<a href="pdf">
					<i class="fa fa-file-pdf-o" aria-hidden="true"></i>
				</a>
			</div>
		</div>
	</div>

</div>

<%-- <tag:relative-footer /> --%>
<tag:end />