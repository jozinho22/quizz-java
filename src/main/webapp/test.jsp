<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.douineau.entity.User"%>
<%@ page import="com.douineau.entity.Question"%>
<%@ page import="com.douineau.entity.Reponse"%>

<%@ page import="java.util.Timer"%>
<%@ page import="com.douineau.utils.SessionUtil"%>
<%@ page import="com.douineau.utils.RequestUtil"%>
<%@ page import="com.douineau.utils.PrintUtil"%>

<%@ page import="com.douineau.utils.TagBuilder"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>


<%

	Integer timeOut = null;
	Question currentQuestion = null;
	List<Reponse> reponses = null;
	List<Long> idsToDisable = null;
				
	if(request.getAttribute("permission") == null) {
		response.sendRedirect("error");
	} else {
	 	currentQuestion = (Question) request.getAttribute("question");
	 	reponses = currentQuestion.getReponses();
	 	idsToDisable = new ArrayList<Long>();
	 	
	 	for(int k = 0; k < reponses.size() ; k++) {
	 		idsToDisable.add(reponses.get(k).getId());
	 	}
		
	    timeOut = currentQuestion.getTimeOut();
	}
%>

<%@ include file="/WEB-INF/tags/begin-dynamic.jsp"%>
<%@ include file="/WEB-INF/tags/navbar.jsp" %>

<form action="test" method="POST" id="form" name="form">

	<div id="timer-section">
		Temps restant : <input type="text" name="clock" value="<%=timeOut%>">
		s.
	</div>

	<div id="test-section" class="container">
		<div id="inner-section">
			<div id="question-section">
				<h4 style="text-decoration: underline;">${question.topic}</h4>
				<br>
				<h4>${question.texte}</h4>
			</div>
			<div id="reponses-section" class="form-check">
				<c:forEach items="${reponses}" var="reponse">
					<input name="id-question" value="${question.id}" hidden="true">
<%-- 					<% 
// 						out.print(TagBuilder.buildTagCheckBoxes(isDone))
					%> --%>
					<input id="${reponse.id}" name="id-reponse" value="${reponse.id}" type="checkbox"
						class="form-check-input">
					<h5>${reponse.texte}</h5>
				</c:forEach>
			</div>

			<div id="next-btn">
				<input id="sessionTheme" name="theme" value="" hidden="true">
				<input id="timeOut" name="time-out" value="" hidden="true">
				<button class="btn btn-outline-secondary"
					type="submit">Valider</button>
			</div>
		</div>
	</div>
</form>

<script>
	var timeOutJs =<%=timeOut%>;
	
	console.log("timeOutJs : " + timeOutJs);
	document.getElementById("timeOut").value = timeOutJs;
	
	function disableCheckBox(id) {			
		console.log("disableCheckBoxes - reponse.id " + id + ")");
		document.getElementById(id).disabled = "disabled";
	}
	
	function timer() {
		if (--timeOutJs > 0) {
			document.form.clock.value = timeOutJs;
			window.setTimeout("timer()", 1000);
		} else {	
			var ids = <%=idsToDisable%>;
			
			for(var k = 0; k < ids.length ; k++) {
				disableCheckBox(ids[k]);
			}
			document.getElementById("timeOut").value = timeOutJs;
// 			document.getElementById("form").submit();
		}
	}
	
	$(document).ready(function() {
	 	timer();
	});
	
	// check only one box at time
	$(document).ready(function() {
		$('input:checkbox').click(function() {
			$('input:checkbox').not(this).prop('checked', false);
		});
	});

</script>

<%@ include file="/WEB-INF/tags/end.jsp" %>