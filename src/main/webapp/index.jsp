<%@ include file="/WEB-INF/tags/begin-static.jsp"%>
<%@ include file="/WEB-INF/tags/navbar.jsp" %>

<div id="index-section" class="container">
	<div id="inner-section">

		<div id="title-section">
			<h4 style="text-decoration: underline;">Bienvenue sur le quizz java</h4>
		</div>
		<div id="desc-section">
			<h5>- 10 questions sur le thème de Java et son environnement</h5>
			<h5>- un timer de 5 minutes</h5>
			<h5>- une seule bonne réponse par question</h5>
			
			<form action="game" method="GET">
<!-- 				<input id="input" name="uuid" type="text" class="form-control" hidden=true> -->
				<input id="sessionTheme" name="theme" value="dark" hidden="true">
				<div id="start-btn">
					<button class="btn" type="submit">C'est parti !</button>
				</div>
			</form>
		</div>
	</div>

</div>

<%@ include file="/WEB-INF/tags/end.jsp" %>