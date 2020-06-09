<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.douineau.entity.User"%>
<%@ page import="com.douineau.entity.Question"%>
<%@ page import="com.douineau.entity.Reponse"%>

<%@ page import="com.douineau.utils.SessionUtil"%>
<%@ page import="com.douineau.utils.RequestUtil"%>
<%@ page import="com.douineau.utils.PrintUtil"%>
<%@ page import="com.douineau.utils.TagBuilder"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<%
	
	if (request.getAttribute("permission") == null) {
		response.sendRedirect("error");
	} 

%>

<%@ include file="/WEB-INF/tags/begin-dynamic.jsp"%>
<%@ include file="/WEB-INF/tags/navbar-game.jsp"%>

<form action="game" method="GET" id="form" name="form">

	<div class="progress">
		<div id="progress" class="progress-bar" role="progressbar"
			aria-valuenow="" aria-valuemin="0" aria-valuemax="100"></div>
	</div>

	<div id="game-section" class="container">
		<div id="inner-section">
			<div id="question-section">
				<input id="${question.id}" name="id-question" value="${question.id}"
					hidden="true"> <input id="isDone"
					value="${question.isDone}" hidden="true">
				<h4 style="text-decoration: underline;">${question.topic}</h4>
				<br>
				<h4>${question.texte}</h4>
			</div>
			<div id="reponses-section" class="form-check">
				<c:forEach items="${question.reponses}" var="reponse">
					<input id="${reponse.id}" name="id-reponse" value="${reponse.id}"
						type="checkbox" class="form-check-input">
					<h5>${reponse.texte}</h5>
				</c:forEach>
			</div>
			<div id="next-btn">
				<input id="sessionTheme" name="theme" value="" hidden="true">
				<button class="btn btn-outline-secondary" type="submit">Valider</button>
			</div>
		</div>
	</div>
</form>

<div id="message-section"></div>

<script>

	var progressStyle = (10 - '${user.nbQuestionsRestantes}') * 10;
	var style = "width:" + progressStyle + "%;"
	document.getElementById("progress").style = style;
	
	history.pushState(null, null, location.href);
	history.back();
	history.forward();
	window.onpopstate = function() {
		history.go(1);
	};

	var clock;
	
	var getTimerUrl = "timer";
	
	$.ajax({
		url : getTimerUrl,
		success : function(data) {
			clock = data;
			timer();
		}
	});
	
	function timer() {
		
		if (clock-- > 0) {
			document.getElementById("clock").value = clock;
			var postTimerUrl = "timer?clock=" + clock;
			$.ajax({
				url : postTimerUrl,
				method : "POST"
			});
			window.setTimeout("timer()", 1000);
		} else {
			displayLastQuestionMessage();
		}
	
	}
	
	function displayLastQuestionMessage() {
		document.getElementById("message-section").innerHTML = 'Le temps est écoulé, vous pouvez tout de même valider votre question avant de voir vos résultats'
	}

	// check only one box at time
	$(document).ready(function() {
		$('input:checkbox').click(function() {
			$('input:checkbox').not(this).prop('checked', false);
		});
	});
	
</script>

<%@ include file="/WEB-INF/tags/end.jsp"%>