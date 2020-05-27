<%@tag import="com.douineau.utils.TagBuilder"%>
<nav id="navbar" class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="navbar-brand text-center">
		<div id="stack" class="container text-center">
			<a href="https://stackoverflow.com/users/6741310/josselin-douineau"
				target="_blank"> <img onmouseover="displayImage(this)"
				onmouseout="hideImage(this)" style="opacity: 0.01"
				src="https://stackoverflow.com/users/flair/6741310.png?theme=dark"
				width="150" height="40" style="margin-top: 15px">
			</a>
		</div>
		<div id="copyright" class="container text-center">
			<small>Copyright &copy; Jozinho 2020</small> <br>
		</div>

	</div>

	<div id="theme-btn" class="pull-right">
		<button id="changeThemeBtn" onclick="changeTheme(this);" class="btn" type="submit" value="dark">Passer en mode light</button> 
	</div>
</nav>

<script>

	function changeTheme(thisButton) {
		
		var theme = thisButton.value;

		var newButtonText;
		var newButtonValue;
		var newHref;
		
		if (theme == "light") {			
			newButtonValue = "dark";
			newButtonText = "Passer en mode light";
			newHref = "css/stylesheet-dark.css";

		} else if (theme == "dark") {			
			newButtonValue = "light";
			newButtonText = "Passer en mode dark";
			newHref = "css/stylesheet.css";
		}
		
		document.getElementById("changeThemeBtn").value = newButtonValue;
		document.getElementById("changeThemeBtn").innerHTML = newButtonText;

		document.getElementById("importCss").href = newHref;
	}
	
	function displayImage(img) {
		img.style.opacity = '1';
	}
	
	function hideImage(img) {
		img.style.opacity = '0.1';
	}
</script>
