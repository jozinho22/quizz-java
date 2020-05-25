<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ page import="com.douineau.entity.User"%>

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

<div id="index-section" class="container">
	<div id="inner-section">
			<p>Quizz terminé !</p>
			<p>${user.score}/${nbQuestions}</p>
<!-- 		<div class="center"> -->
			<form id="form" action="resultats" method="POST">
				<%
					if(ok) {
						out.print("<input name=\"score\" type=\"text\" value=\"${user}\" hidden=\"true\">");
					}
				%>
<%-- 				<input name="score" type="text" value="${user}" hidden="true"> --%>
				<div id="resultats-btn">
					<button class="btn" type="submit">Voir les résultats</button>
				</div>
			</form>
<!-- 		</div> -->
	</div>
</div>

<%-- <tag:footer /> --%>
<tag:end />