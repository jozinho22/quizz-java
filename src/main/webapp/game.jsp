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
<%@ include file="/WEB-INF/tags/navbar.jsp"%>

<form action="game" method="GET" id="form" name="form">
	<!-- <form action="game" method="GET" id="form" name="form"> -->

	<div id="timer-section">
		Temps restant : <input id="clock" type="text" name="clock"
			disabled="disabled"> s.
	</div>

	<div id="game-section" class="container">
		<div id="inner-section">
			<div id="question-section">
				<input id="${question.id}" name="id-question" value="${question.id}" hidden="true">
				<input id="isDone" value="${question.isDone}" hidden="true">
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
				<input id="timeOut" name="time-out" value="" hidden="true">
				<button onclick="stopTimer();" class="btn btn-outline-secondary"
					type="submit">Valider</button>
			</div>
		</div>
	</div>
</form>

<script>

	history.pushState(null, null, location.href);
	history.back();
	history.forward();
	window.onpopstate = function() {
		history.go(1);
	};

	function disableCheckBoxes() {
		var reponseSection = document.getElementById("reponses-section");
		var children = reponseSection.children;

		for (var k = 0; k < children.length; k++) {
			if (k % 2 == 0) {
				children[k].disabled = "disabled";
			}
		}
	}

	var clock = 0;

	var getTimerUrl = "/ROOT/timer";

	$.ajax({
		url : getTimerUrl,
		success : function(data) {
			clock = data;
			console.log(getTimerUrl + " reponse : " + data);
			timer();
		}
	});

	// 	var onTimer = true;

	function timer() {

		// 		if(onTimer) {
		if (clock-- > 0) {
			document.form.clock.value = clock;
			var postTimerUrl = "/ROOT/timer?clock=" + clock;
			$.ajax({
				url : postTimerUrl,
				method : "POST"
			});
			window.setTimeout("timer()", 1000);
		} else {
			stopTimer();
		}

	}

	function stopTimer() {
		disableCheckBoxes();
		console.log("in stopTimer()");
	}

	// check only one box at time
	$(document).ready(function() {
		$('input:checkbox').click(function() {
			$('input:checkbox').not(this).prop('checked', false);
		});
	});
</script>

<%@ include file="/WEB-INF/tags/end.jsp"%>