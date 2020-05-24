<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<tag:begin />
<tag:navbar />

<div id="index-section" class="container">
	<div id="inner-section">
		<div id="header-section">
			<p>Quizz termin� !</p>
			<p>${user.score}/${nbQuestions}</p>
		</div>
<!-- 		<div class="center"> -->
			<form id="form" action="resultats" method="POST">
				<input name="score" type="text" value="${user}" hidden="true">
				<div id="resultats-btn">
					<button class="btn" type="submit">Voir les r�sultats</button>
				</div>
			</form>
<!-- 		</div> -->
	</div>
</div>

<tag:footer />
<tag:end />