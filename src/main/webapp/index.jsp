<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<tag:begin />
<tag:navbar />
<div id="section" class="container">
	<div id="inner-section" >
		<h1>Bienvenue sur le quizz java</h1>
	</div>
	<div id="section-footer">
		<form action="test" method="GET">
			<button id="btn-start" class="btn btn-success" type="submit">Commencer</button>
		</form>
	</div>
</div>
<tag:footer />
<tag:end />