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
if (user != null) {
	ok = true;
} else {
	response.sendRedirect("error");
}

Integer timeOut = (Integer) session.getAttribute("time-out");
%>

<tag:begin />
<tag:navbar />

<div id="resultats-section" class="container">
	<div id="inner-section-resultats">
		<div id="resultats">
		<%-- 			<c:forEach items="${map}" var="entry"> --%>
			<p>Voici vos résultats</p>
			<hr>
			<%-- 					<h4>${entry.key.texte}</h4> --%>
			<%
					int k = 0;
					for (Map.Entry<Question, Reponse> entry : user.getMap().entrySet()) {
						
						//Question
						out.print(TagBuilder.buildTag("h4", TagBuilder.buildTag("b", "Question n°" + ++k + " : ") + entry.getKey().getTexte()));
						//Réponse
						if(entry.getValue() != null) {
							out.print(TagBuilder.buildTag("h5", "Vous avez répondu : "));
							out.print(TagBuilder.buildTag("h5", " - " + entry.getValue().getTexte()));
							if(entry.getValue().getIsTrue()) {
								out.print("<i class=\"fa fa-wright\" style=\"font-size: 36px\"></i>");
								out.print(TagBuilder.buildTag("h5", "Bonne réponse !!!", "display:inline-block"));
							} else {
								out.print("<i class=\"fa fa-false\" style=\"font-size: 36px\"></i>");
								out.print(TagBuilder.buildTag("h5", "Mauvaise réponse...", "display:inline-block"));
							}
						} else {
							out.print(TagBuilder.buildTag("h5", "(Vous n'avez pas répondu...)"));
						}
						
						// Si pas de réponse ou mauvaise réponse
						if(entry.getValue() == null || !entry.getValue().getIsTrue()) {
							out.print(TagBuilder.buildTag("h5", "La bonne réponse était :"));
							for(Reponse reponse : entry.getKey().getReponses()) {
								if(reponse.getIsTrue()) {
									out.print(TagBuilder.buildTag("h5", " - " + reponse.getTexte()));
								}
							}
						}
							
						out.print("<hr>");
					}
					
					%>
		<%-- 			</c:forEach> --%>
		</div>
	</div>

</div>

<tag:relative-footer />
<tag:end />