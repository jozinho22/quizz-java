<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<tag:begin />
<tag:navbar />
<div id="section" class="container">
	<div id="inner-section">

		<div id="section-header">
			<p>Bienvenue sur le quizz java</p>
		</div>

		<div id="input-name">
			<form action="test" method="GET">
				<input name="input-name" type="text" class="form-control"
					placeholder="Votre nom">
				<div class="input-group-append">
					<button class="btn" type="submit">Commencer</button>
				</div>
			</form>
		</div>
	</div>
</div>
<tag:footer />
<tag:end />