<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<tag:begin />
<tag:navbar />

<div id="index-section" class="container">

	<div id="inner-section">
		<div id="question">
			<p>Quizz terminé !</p>
			<p>${score}/${nbQuestions}</p>
		</div>
	</div>
</div>

<tag:footer />
<tag:end />