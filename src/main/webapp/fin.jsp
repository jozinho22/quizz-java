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

<%@ include file="/WEB-INF/tags/begin.jsp" %>
<%@ include file="/WEB-INF/tags/navbar.jsp" %>

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
					<input id="sessionTheme" name="theme" value="" hidden="true">
					<button class="btn" type="submit">Voir les résultats</button>
				</div>
			</form>
<!-- 		</div> -->
	</div>
</div>

<%@ include file="/WEB-INF/tags/end.jsp" %>