<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<tag:begin />
<tag:session-navbar />
<div id="section">
	<form action="test" method="POST">
	
		<div id="inner-section">
			<div id="section-header">
				<h4>${question.texte}</h4>
			</div>
			<div id="reponses">
				<input name="reponse" id="reponse" >
				<div class="form-check">
					<input type="checkbox" class="form-check-input"
						id="checkbox-reponse">
					<h5>${reponses.get(0).texte}</h5>
					<input type="checkbox" class="form-check-input"
						id="checkbox-reponse">
					<h5>${reponses.get(1).texte}</h5>
					<input type="checkbox" class="form-check-input"
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
	
	function sendReponse(this) {
		var input = document.getElementById("reponse").value = '';
	}
</script>

<tag:footer />
<tag:end />