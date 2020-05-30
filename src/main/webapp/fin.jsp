<%@ page import="com.douineau.entity.User"%>
<%@ page import="com.douineau.utils.SessionUtil"%>
<%@ page import="com.douineau.utils.RequestUtil"%>
<%@ page import="com.douineau.utils.PrintUtil"%>

<%
	
	if(request.getAttribute("permission") == null) {
		response.sendRedirect("error");
	} 

%>

<%@ include file="/WEB-INF/tags/begin-dynamic.jsp"%>
<%@ include file="/WEB-INF/tags/navbar.jsp"%>

<div id="index-section" class="container">
	<div id="inner-section">
		<p>Quizz terminé !</p>
		<p>${user.score}/${user.nbQuestionsTotal}</p>
		<form id="form" action="resultats" method="POST">
			<div id="resultats-btn">
				<input id="sessionTheme" name="theme" value="" hidden="true">
				<button class="btn" type="submit">Voir les résultats</button>
			</div>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/tags/end.jsp"%>