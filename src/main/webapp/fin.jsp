<%@ page import="com.douineau.entity.User"%>
<%@ page import="com.douineau.utils.SessionUtil"%>

<%
    User user = SessionUtil.checkSessionByUuidAndGetUser(response, session);
%>

<%@ include file="/WEB-INF/tags/begin.jsp" %>
<%@ include file="/WEB-INF/tags/navbar.jsp" %>

<div id="index-section" class="container">
	<div id="inner-section">
			<p>Quizz termin� !</p>
			<p>${user.score}/${nbQuestions}</p>
<!-- 		<div class="center"> -->
			<form id="form" action="resultats" method="POST">
				<div id="resultats-btn">
					<input id="sessionTheme" name="theme" value="" hidden="true">
					<button class="btn" type="submit">Voir les r�sultats</button>
				</div>
			</form>
<!-- 		</div> -->
	</div>
</div>

<%@ include file="/WEB-INF/tags/end.jsp" %>