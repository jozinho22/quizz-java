<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ page import="java.util.UUID"%>

<% 
	String uuid = UUID.randomUUID().toString();
%>

<%@ include file="/WEB-INF/tags/begin.jsp" %>
<tag:navbar />

<div id="index-section" class="container">
	<div id="inner-section">

		<div id="title-section">
			<h4>Bienvenue sur le quizz java</h4>
		</div>
		<div id="desc-section">
			<h5>- 20 questions sur le th�me de Java et son environnement</h5>
			<h5>- 20 secondes par question</h5>
			<h5>- une seule bonne r�ponse</h5>
			<h5>- chaque r�ponse est d�finitive</h5>
			<h5>- interdiction de rafra�chir la page</h5>
			<h5>- vos r�sultats imprimables en pdf � l'issue du quizz</h5>
			
			<form action="test" method="GET">
				<input id="input" name="uuid" type="text" class="form-control" hidden=true>
				<div id="start-btn">
					<button class="btn" type="submit">Commencer</button>
				</div>
			</form>
		</div>
	</div>

</div>

<script>
	var uuid = '<%=uuid%>' ;
	document.getElementById("input").value = uuid ;
	console.log(uuid)
</script>

<tag:end />