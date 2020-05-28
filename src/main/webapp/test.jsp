<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.douineau.entity.User"%>
<%@ page import="java.util.Timer"%>

<%

	Integer timeOut = 0;
	User user = (User) session.getAttribute("user");
	if (user == null) {
		response.sendRedirect("error");
	} else {
	    timeOut = (Integer) request.getAttribute("time-out");
	}
	
// 	int k;
// 	Timer t = new Timer();
	
// 	for(k = 0; k < timeOut ; k++) {
// 		if (k++ > timeOut) {
// 		} else {
// 			t.wait(1000);
// 			System.out.println(k);
// 		}
// 	}
	
%>

<%@ include file="/WEB-INF/tags/begin.jsp" %>
<%@ include file="/WEB-INF/tags/navbar.jsp" %>

<script>
<%-- 	var timeout =<%=timeOut%>; --%>

// 	function timer() {
// 		if (--timeout > 0) {
// 			document.form.clock.value = timeout;
// 			window.setTimeout("timer()", 1000);
// 		} else {			
// 			document.getElementById("form").submit();
// 		}
// 	}
</script>
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
					<input name="id-reponse" value="${reponse.id}" type="checkbox"
						class="form-check-input" id="checkbox-reponse">
					<h5>${reponse.texte}</h5>
				</c:forEach>
			</div>

			<div id="next-btn">
				<input id="sessionTheme" name="theme" value="light" hidden="true">
				<button class="btn btn-outline-secondary"
					type="submit">Valider</button>
			</div>
		</div>
	</div>
</form>



<script >
	
	// check only one box at time
	$(document).ready(function() {
		$('input:checkbox').click(function() {
			$('input:checkbox').not(this).prop('checked', false);
		});
	});

// 	$(document).ready(function() {
// 	 	timer();
// 	});
</script>

<%@ include file="/WEB-INF/tags/end.jsp" %>