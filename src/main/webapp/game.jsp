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

	Integer clock = null;
	Question currentQuestion = null;
	List<Reponse> reponses = null;
	List<Long> inputIds = null;
				
	if(request.getAttribute("permission") == null) {
		response.sendRedirect("error");
	} else {
	 	currentQuestion = (Question) request.getAttribute("question");
	 	reponses = currentQuestion.getReponses();
	 	inputIds = new ArrayList<Long>();
	 	
	 	for(int k = 0; k < reponses.size() ; k++) {
	 		inputIds.add(reponses.get(k).getId());
	 	}
		
	 	clock = currentQuestion.getClock();
	    PrintUtil.printInfo("game.jsp", "scriplet Java", "currentQuestion.getClock()", clock);
	}
	
%>

<%@ include file="/WEB-INF/tags/begin-dynamic.jsp"%>
<%@ include file="/WEB-INF/tags/navbar.jsp"%>

<form action="game" method="POST" id="form" name="form">
<!-- <form action="game" method="GET" id="form" name="form"> -->

	<div id="timer-section">
		Temps restant : <input id="clock" type="text" name="clock" disabled="disabled">
		s.
	</div>

	<div id="game-section" class="container">
		<div id="inner-section">
			<div id="question-section">
				<h4 style="text-decoration: underline;">${question.topic}</h4>
				<br>
				<h4>${question.texte}</h4>
			</div>
			<div id="reponses-section" class="form-check">
				<input name="id-question" value="${question.id}" hidden="true">
				<c:forEach items="${question.reponses}" var="reponse">
					<input id="${reponse.id}" name="id-reponse" value="${reponse.id}"
						type="checkbox" class="form-check-input">
					<h5>${reponse.texte}</h5>
				</c:forEach>
			</div>

			<div id="next-btn">
				<input id="sessionTheme" name="theme" value="" hidden="true">
				<input id="timeOut" name="time-out" value="" hidden="true">
				<button onclick="stopTimer();" class="btn btn-outline-secondary" type="submit">Valider</button>
			</div>
		</div>
	</div>
</form>

<script>
	
// 	location.reload();
	function disableCheckBox(id) {	
		//		console.log(document.getElementById(id));
		document.getElementById(id).disabled = "disabled";
	}

	var clockJs = <%=clock%>;

	var onTimer = true;

	function timer() {
	 	document.getElementById("clock").value = clockJs;
	 	
		if(onTimer) {
			$.ajax({
				url:"/ROOT/game?clock=" + clockJs,
				method: "PUT"
			});	
			document.getElementById("clock").value = clockJs;
			console.log("Valeur envoyée à TimerServlet : " + clockJs);
	 		if (clockJs-- > 0) {
				document.form.clock.value = clockJs;
				window.setTimeout("timer()", 1000);
			} else {
				stopTimer();
				var ids = <%=inputIds%>;
				for(var k = 0; k < ids.length ; k++) {
					disableCheckBox(ids[k]);
				}
//						 	document.getElementById("form").submit();
			}	

		} else {
			console.log("in timer() - timer stopped");
		}
	 } 
	
	timer();
	
	function stopTimer() {
		console.log("in stopTimer()");
		onTimer = false;
	}
	
// 	function stopTimerAndCheckInputs() {
// 		stopTimer();
<%-- 		var ids = <%=inputIds%>; --%>
// 		for(var k = 0; k < ids.length ; k++) {
// 			console.log(document.getElementById(id));
// 			console.log(document.getElementById(id).checked);
// 		}
// 	}
		
	// check only one box at time
	$(document).ready(function() {
		$('input:checkbox').click(function() {
			$('input:checkbox').not(this).prop('checked', false);
		});
	});

</script>

<%@ include file="/WEB-INF/tags/end.jsp"%>