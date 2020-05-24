<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ page import="com.douineau.entity.User"%>

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

<div id="index-section" class="container">
	<div id="inner-section">
		<div id="header-section">
			<p>Quizz terminé !</p>
			<p>${user.score}/${nbQuestions}</p>
		</div>
<!-- 		<div class="center"> -->
			<form id="form" action="resultats" method="POST">
				<input name="score" type="text" value="${user}" hidden="true">
				<div id="resultats-btn">
					<button class="btn" type="submit">Voir les résultats</button>
				</div>
			</form>
<!-- 		</div> -->
	</div>
</div>

<tag:footer />
<tag:end />