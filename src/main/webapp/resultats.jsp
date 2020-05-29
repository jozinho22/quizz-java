<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.douineau.entity.User"%>
<%@ page import="com.douineau.entity.Question"%>
<%@ page import="com.douineau.entity.Reponse"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.douineau.utils.TagBuilder"%>
<%@ page import="com.douineau.utils.SessionUtil"%>

<%
	User user = SessionUtil.checkSessionByUuidAndGetUser(response, session);
	System.out.println("from resultats.jsp - user = " + user);

%>

<%@ include file="/WEB-INF/tags/begin.jsp" %>
<%@ include file="/WEB-INF/tags/navbar.jsp" %>

<div id="resultats-section" class="container">
	<div id="inner-section-resultats">
		<div id="resultats">
			<%-- 			<c:forEach items="${map}" var="entry"> --%>
			<p>Voici vos r�sultats</p>
			<p>${user.score}/${nbQuestions}</p>
			<hr>
			<%-- 					<h4>${entry.key.texte}</h4> --%>
			<%
				int k = 0;
				for (Map.Entry<Question, Reponse> entry : user.getMap().entrySet()) {
	
					//Question
					out.print(TagBuilder.buildTagResultats("h4",
					TagBuilder.buildTagResultats("b", "Question n�" + ++k + " : ") + entry.getKey().getTexte()));
					//R�ponse
					if (entry.getValue() != null) {
						
						if (entry.getValue().getIsTrue()) {
							out.print("<i class=\"fa fa-check\" aria-hidden=\"true\" style=\"font-size: 20px; color: green; \"></i>");
							out.print(TagBuilder.buildTagResultats("h5", entry.getValue().getTexte(), "display:inline;"));
						} else {
							out.print("<i class=\"fa fa-close\" aria-hidden=\"true\" style=\"font-size: 20px; color: red; \"></i>");
							out.print(TagBuilder.buildTagResultats("h5", entry.getValue().getTexte(), "display:inline;"));
						}
					} else {
						out.print("<i class=\"fa fa-close\" aria-hidden=\"true\" style=\"font-size: 20px; color: red; \"></i>");
						out.print(TagBuilder.buildTagResultats("h5", "(Vous n'avez pas r�pondu...)", "display:inline;"));
					}
	
					// Si pas de r�ponse ou mauvaise r�ponse
					if (entry.getValue() == null || !entry.getValue().getIsTrue()) {
						for (Reponse reponse : entry.getKey().getReponses()) {
							if (reponse.getIsTrue()) {
								out.print("<br>");
								out.print("<i class=\"fa fa-check\" aria-hidden=\"true\" style=\"font-size: 20px; color: green; \"></i>");
								out.print(TagBuilder.buildTagResultats("h5", reponse.getTexte(), "display:inline"));
							}
						}
					}
	
					out.print("<hr>");
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

</div>

<%@ include file="/WEB-INF/tags/end.jsp" %>