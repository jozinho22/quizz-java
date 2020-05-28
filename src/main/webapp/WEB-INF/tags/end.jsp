	<% 
	
		String theme = null;
		if(session.getAttribute("theme") != null) {
			theme = (String) session.getAttribute("theme");
			System.out.println("theme from TestServlet : " + theme);
		}
		
	 %>
	
	<script>

		var theme = '<%= theme %>';
		if(theme == "dark" || theme == "light") {
			console.log("$(document).ready - theme = " + theme);
			processChangeTheme(theme);
		}
	
		function changeTheme(thisButton) {
			console.log("changeTheme() " + thisButton+ ")");
			
			console.log("thisButton.value =  " + thisButton.value);
	
			var actualTheme = thisButton.value;
			
			if (actualTheme == "dark") {	
				theme= "light";
			} else if (actualTheme == "light") {	
				theme= "dark";
			}
			console.log("theme =  " + theme);
			
			processChangeTheme(theme);
		}
		
		function processChangeTheme(theme) {
			var newButtonText;
			var newButtonValue;
			var newHref;
			var newParameter;
			
			console.log("processChangeTheme(theme) - theme = " + theme);
			
			if (theme == "dark") {	
				console.log("if (theme == " + theme+ ")");
				newButtonValue = "dark";
				newButtonText = "Passer en mode light";
				newHref = "css/stylesheet-dark.css";
				newParameter = newButtonValue;
	
			} else if (theme == "light") {	
				console.log("if (theme == " + theme+ ")");
				newButtonValue = "light";
				newButtonText = "Passer en mode dark";
				newHref = "css/stylesheet-light.css";
				newParameter = newButtonValue;
			}
			
			document.getElementById("changeThemeBtn").value = newButtonValue;
			document.getElementById("changeThemeBtn").innerHTML = newButtonText;
			
			document.getElementById("importCss").href = newHref;
			console.log("apres - document.getElementById(\"importCss\").href = " + document.getElementById("importCss").href)
			
			document.getElementById("sessionTheme").value = newParameter;
	
		}
	
	</script>
	
	</body>
</html>