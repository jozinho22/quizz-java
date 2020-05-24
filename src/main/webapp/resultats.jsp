<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.douineau.entity.User"%>
<%@ page import="com.douineau.entity.Question"%>
<%@ page import="com.douineau.entity.Reponse"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.douineau.utils.TagBuilder"%>

<tag:begin />
<tag:navbar />

<% 
User user = (User) request.getAttribute("user");
%>
<div id="resultats-section" class="container">
	<div id="inner-section-resultats">
		<div id="resultats">
		<%-- 			<c:forEach items="${map}" var="entry"> --%>
			<p>Voici vos r�sultats</p>
			<hr>
			<%-- 					<h4>${entry.key.texte}</h4> --%>
			<%
					int k = 0;
					for (Map.Entry<Question, Reponse> entry : user.getMap().entrySet()) {
						
						//Question
						out.print(TagBuilder.buildTag("h4", TagBuilder.buildTag("b", "Question n�" + ++k + " : ") + entry.getKey().getTexte()));
						//R�ponse
						if(entry.getValue() != null) {
							out.print(TagBuilder.buildTag("h5", " - " + entry.getValue().getTexte()));
							if(entry.getValue().getIsTrue()) {
								out.print(TagBuilder.buildTag("h5", "Bonne r�ponse !!!"));
							} else {
								out.print(TagBuilder.buildTag("h5", "Mauvaise r�ponse..."));
							}
						} else {
							out.print(TagBuilder.buildTag("h5", "(Vous n'avez pas r�pondu...)"));
						}
						
						// Si pas de r�ponse ou mauvaise r�ponse
						if(entry.getValue() == null || !entry.getValue().getIsTrue()) {
							out.print(TagBuilder.buildTag("h5", "La bonne r�ponse �tait :"));
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

<tag:footer />
<tag:end />