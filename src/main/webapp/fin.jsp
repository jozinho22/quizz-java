<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<tag:begin />
<tag:navbar />

<div id="index-section" class="container">
	<div id="inner-section">
		<div id="header-section">
			<p>Quizz terminé !</p>
			<p>${user.score}/${nbQuestions}</p>
		</div>
<!-- 		<div class="center"> -->
			<form id="form" action="resultats" method="POST">
				<input name="score" type="text" value="${user}" hidden="true">
				<button type="submit" class="btn">
					Voir les résultats
				</button>
			</form>
<!-- 		</div> -->
	</div>
</div>

<tag:footer />
<tag:end />