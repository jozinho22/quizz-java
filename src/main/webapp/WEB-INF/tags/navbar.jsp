<%@ page import="com.douineau.utils.TagBuilder"%>
<%@ page import="com.douineau.utils.ServletEnum"%>

<nav id="navbar" class="navbar navbar-expand-sm navbar-dark">

	<div class="row" style="width: 110%;">

		<div id="nav-elem-1" class="col-12 col-sm-4 col-md-4 text-center">
			<%
				if (ServletEnum.GAME.getJspPath().equals(request.getServletPath())) {
					out.print(TagBuilder.buildTagTimer());
				} 
			%>
		</div>
		<div id="nav-elem-2" class="col-12 col-sm-4 col-md-4 text-center">
			<div class="navbar-brand text-center">
				<div id="stack" class="container text-center">
					<a href="https://stackoverflow.com/users/6741310/jozinho22"
						target="_blank"> <img onmouseover="displayImage(this)"
						onmouseout="hideImage(this)" style="opacity: 0.01"
						src="https://stackoverflow.com/users/flair/6741310.png?theme=dark"
						width="150" height="40" style="margin-top:15px">
					</a>
				</div>
				<div id="copyright" class="container text-center">
					<small>Copyright &copy; Jozinho 2020</small> <br>
				</div>

			</div>
		</div>
		<div id="nav-elem-3" class="col-12 col-sm-4 col-md-4 text-center">
			<!-- 			<div id="theme-btn" class="pull-right"> -->
			<!-- 				<button id="changeThemeBtn" onclick="getTheme(this);" class="btn" -->
			<!-- 					type="submit" value="dark">Passer en mode light</button> -->
			<!-- 			</div> -->
			<div id="theme-btn" class="pull-right">
				<label class="switch"> 
				<input id="changeThemeBtn"
					type="checkbox" onclick="getTheme(this);"
					 value="dark"> 
					<span class="slider round" onmouseover="attentionLesYeux();"></span>
				</label>
			</div>
		</div>
	</div>
	
</nav>

<script>

	var alreadyClicked = false;
	
	function attentionLesYeux() {
		if(!alreadyClicked) {
			alert("Attention surtout ne cliquez pas �a risque de piquer les yeux ^^");
			alreadyClicked = true;
		} 
	}

	function displayImage(img) {
		img.style.opacity = '1';
	}

	function hideImage(img) {
		img.style.opacity = '0.01';
	}

	var theme = '${theme}';
	console.log(theme);

	if (theme == "dark") {
		document.getElementById("changeThemeBtn").checked = false;
	} else if (theme == "light") {
		document.getElementById("changeThemeBtn").checked = true;
	}
</script>
