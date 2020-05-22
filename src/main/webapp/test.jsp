<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.douineau.entity.User" %>

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

<script>
	var timeout =<%=timeOut%>;

	function timer() {
		if (--timeout > 0) {
			document.form.clock.value = timeout;
			window.setTimeout("timer()", 1000);
		} else {
			console.log('timeout');
			
			document.getElementById("form").submit();
 // 		     location.reload();
// 			document.location.href = "test?input-name=fg";
		}
	}
</script>
<form action="test" method="POST" id="form" name="form">

	<div id="timer-section">
		Temps restant : <input type="text" name="clock" value="<%=timeOut%>"> s.
	</div>

	<div id="test-section" class="container">
		<div id="inner-section">
			<div id="header-section">
				<h4>${question.texte}</h4>
			</div>
			<hr>
			<div id="reponses-section" class="container">
				<div class="form-check">
					<c:forEach items="${reponses}" var="reponse">
						<input name="reponse" value="${reponse.id}" type="checkbox"
							class="form-check-input" id="checkbox-reponse">
						<h5>${reponse.texte}</h5>
					</c:forEach>
				</div>
			</div>

			<div id="next">
				<button id="btn-next" class="btn btn-outline-secondary"
					type="submit">Valider</button>
			</div>
		</div>
	</div>
</form>



<script>
	// check only one box at time
	$(document).ready(function() {
		$('input:checkbox').click(function() {
			$('input:checkbox').not(this).prop('checked', false);
		});
	});

// 	timer();
</script>

<tag:footer />
<tag:end />