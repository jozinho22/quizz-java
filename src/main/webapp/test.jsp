<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	boolean ok = false;
String name = (String) session.getAttribute("input-name");
if (name != null) {
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

		    $.post('test', function(data) {
		        alert(data);
		    });
			///disable submit-button etc
		}
	}
</script>
<form action="test" method="POST" name="form">

	<div id="timer-section">
		Temps restant : <input type="text" name="clock" value="<%=timeOut%>"> secondes.
	</div>

	<div id="section" class="container">
		<div id="inner-section">
			<div id="header-section">
				<h4>${question.texte}</h4>
			</div>
			<hr>
			<div id="reponses">
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

	timer();
</script>

<tag:footer />
<tag:end />