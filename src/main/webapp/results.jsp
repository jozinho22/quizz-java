<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.douineau.entity.User"%>
<%@ page import="com.douineau.entity.Question"%>
<%@ page import="com.douineau.entity.Reponse"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.douineau.utils.TagBuilder"%>
<%@ page import="com.douineau.utils.SessionUtil"%>
<%@ page import="com.douineau.utils.RequestUtil"%>

<%

	boolean ok = false;

	User user = null;
	
	if(request.getAttribute("permission") == null) {
		response.sendRedirect("error");
	} else {
		ok = true;
		user = (User) session.getAttribute("user");
	}
	
%>

<%@ include file="/WEB-INF/tags/begin-dynamic.jsp"%>
<%@ include file="/WEB-INF/tags/navbar.jsp"%>

<div id="results-section" class="container">
	<div id="inner-section-results">
		<%-- 			<c:forEach items="${map}" var="entry"> --%>
		<p>Voici vos résultats</p>
		<p>${user.score}/${user.nbQuestionsTotal}</p>
		<hr>
		<%-- 					<h4>${entry.key.texte}</h4> --%>
		<%
			
			if(ok) {
				int k = 0;
				for (Map.Entry<Question, Reponse> entry : user.getMap().entrySet()) {
	
					//Question
					out.print(TagBuilder.buildTagResults("h4",
					TagBuilder.buildTagResults("b", "Question n°" + ++k + " : ") + entry.getKey().getTexte()));
					//Réponse
					out.print("<div id=\"results-reponses\">");
					if (entry.getValue() != null) {
						
						if (entry.getValue().getIsTrue()) {
							out.print("<i class=\"fa fa-check\" aria-hidden=\"true\" style=\"font-size: 20px; color: green; \"></i>");
							out.print(TagBuilder.buildTagResults("h5", entry.getValue().getTexte(), "display:inline;"));
						} else {
							out.print("<i class=\"fa fa-close\" aria-hidden=\"true\" style=\"font-size: 20px; color: red; \"></i>");
							out.print(TagBuilder.buildTagResults("h5", entry.getValue().getTexte(), "display:inline;"));
						}
					} else {
						out.print("<i class=\"fa fa-close\" aria-hidden=\"true\" style=\"font-size: 20px; color: red; \"></i>");
						out.print(TagBuilder.buildTagResults("h5", "(Vous n'avez pas répondu...)", "display:inline;"));
					}
					
					// Si pas de réponse ou mauvaise réponse
					if (entry.getValue() == null || !entry.getValue().getIsTrue()) {
						for (Reponse reponse : entry.getKey().getReponses()) {
							if (reponse.getIsTrue()) {
								out.print("<br>");
								out.print("<i class=\"fa fa-check\" aria-hidden=\"true\" style=\"font-size: 20px; color: green; \"></i>");
								out.print(TagBuilder.buildTagResults("h5", reponse.getTexte(), "display:inline"));
							}
						}
					}
					
					out.print("</div>");
					out.print("<hr>");
				}
			}
		%>
		<%-- 			</c:forEach> --%>
		<div id="return-btn">
			<input id="sessionTheme" name="theme" value="" hidden="true">
			<a href="index">
				<button class="btn" type="submit">Retour au quizz</button>
			</a>
		</div>
	</div>

</div>

<script>
	history.pushState(null, null, location.href);
	history.back();
	history.forward();
	window.onpopstate = function() {
		history.go(1);
	};
</script>

<%@ include file="/WEB-INF/tags/end.jsp"%>