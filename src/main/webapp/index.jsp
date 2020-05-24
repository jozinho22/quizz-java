<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<tag:begin />
<tag:navbar />

<div id="index-section" class="container">
	<div id="inner-section">

		<div id="title-section">
			<h4>Bienvenue sur le quizz java</h4>
		</div>
		<div id="desc-section">
			<h5>- 20 questions sur le thème de Java et son environnement</h5>
			<h5>- 20 secondes par question</h5>
			<h5>- vos résultats imprimables en pdf à l'issue du quizz</h5>
			<h5>- blabla</h5>
			<h5>- blabla</h5>
			
			<form action="test" method="GET">
				<input name="input-name" type="text" class="form-control"
					placeholder="Votre nom" hidden=true value="joss">
				<div id="start-btn">
					<button class="btn" type="submit">Commencer</button>
				</div>
			</form>
		</div>
	</div>

</div>

<%-- <tag:footer /> --%>
<tag:end />