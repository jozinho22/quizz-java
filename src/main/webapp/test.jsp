<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<tag:begin />
<tag:navbar />
<div id="section">

	<div id="inner-section">
		<div id="question">
			<h4>${question.texte}</h4>
		</div>
		<div id="reponses">
			<div class="form-check">
				<input type="checkbox" class="form-check-input"
					id="materialChecked2">
				<h5>Paris</h5>
				<input type="checkbox" class="form-check-input"
					id="materialChecked2">
				<h5>Moscou</h5>
				<input type="checkbox" class="form-check-input"
					id="materialChecked2">
				<h5>Rio</h5>
				<input type="checkbox" class="form-check-input"
					id="materialChecked2">
				<h5>Rome</h5>
			</div>
		</div>
	</div>
	<div id="section-footer">
		<form action="test" method="POST">
			<button id="btn-start" class="btn btn-success" type="submit">Valider</button>
		</form>
	</div>

</div>

<tag:footer />
<tag:end />