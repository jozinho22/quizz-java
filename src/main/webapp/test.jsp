<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% 
	boolean ok = false ;
	String name = (String) session.getAttribute("input-name");
	if(name != null) {
		ok = true;
	} else {
		response.sendRedirect("error");
	}
	
%>
<tag:begin />
<tag:navbar />
<div id="section">
	<form action="test" method="POST">

		<div id="inner-section">
			<div id="section-header">
				<h4>${question.texte}</h4>
			</div>
			<div id="reponses">
				<div class="form-check">
					<c:forEach items="${reponses}" var="reponse">
						<input name="reponse" value="${reponse.id}" type="checkbox"
							class="form-check-input" id="checkbox-reponse">
						<h5>${reponse.texte}</h5>
					</c:forEach>
				</div>
			</div>
		</div>

		<div id="next">
			<button id="btn-next" class="btn btn-outline-secondary" type="submit">Valider</button>
		</div>

	</form>

</div>

<script>
	$(document).ready(function() {
		$('input:checkbox').click(function() {
			$('input:checkbox').not(this).prop('checked', false);
		});
	});

</script>

<tag:footer />
<tag:end />