<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<tag:begin />
<tag:navbar />

<div id="index-section" class="container">

	<div id="inner-section">
		<div id="header-section">
			<p>Quizz terminé !</p>
			<p>${user.score}/${nbQuestions}</p>
		</div>
		<div class="center">
			<form action="pdf" method="POST">
				<input name="score" type="text" value="${user}" hidden="true">
				<button type="submit">
					<i id="dl-pdf" class="fa fa-file-pdf-o fa-3x" aria-hidden="true"></i>
				</button>
			</form>
		</div>
	</div>
</div>

<tag:footer />
<tag:end />