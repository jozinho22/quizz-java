<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

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
					<input name="reponse" value="${reponses.get(0).id}" type="checkbox" class="form-check-input"
						id="checkbox-reponse">
					<h5>${reponses.get(0).texte}</h5>
					<input name="reponse" value="${reponses.get(1).id}" type="checkbox" class="form-check-input"
						id="checkbox-reponse">
					<h5>${reponses.get(1).texte}</h5>
					<input name="reponse" value="${reponses.get(2).id}" type="checkbox" class="form-check-input"
						id="checkbox-reponse">
					<h5>${reponses.get(2).texte}</h5>
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