<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<tag:begin />
<tag:navbar />

<div id="index-section" class="container">
	<div id="inner-section" class="container">

		<div id="header-section">
			<p>Bienvenue sur le quizz java</p>
		</div>
		<div id="input-name">
			<form action="test" method="GET">
				<input name="input-name" type="text" class="form-control"
					placeholder="Votre nom">
				<div id="index-btn" class="input-group-append">
					<button class="btn" type="submit">Commencer</button>
				</div>
			</form>
		</div>
	</div>
</div>

<tag:footer />
<tag:end />