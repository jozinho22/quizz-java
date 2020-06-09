<%@ page import="com.douineau.utils.PrintUtil"%>
	
	<script>

		var theme = '${theme}';
				
		if(theme == "dark" || theme == "light") {
			processChangeTheme(theme);
		}
	
		function getTheme(thisButton) {
	
			var actualTheme = thisButton.value;
			
			if (actualTheme == "dark") {	
				theme= "light";
			} else if (actualTheme == "light") {	
				theme= "dark";
			}
			
			processChangeTheme(theme);
		}
		
		function processChangeTheme(theme) {

			var newButtonText;
			var newButtonValue;
			var newHref;
			var newParameter;
						
			if (theme == "dark") {	
				newButtonValue = "dark";
				newButtonText = "Passer en mode light";
				newHref = "css/stylesheet-dark.css";
				newParameter = newButtonValue;
	
			} else if (theme == "light") {	
				newButtonValue = "light";
				newButtonText = "Passer en mode dark";
				newHref = "css/stylesheet-light.css";
				newParameter = newButtonValue;
			}
			
			document.getElementById("changeThemeBtn").value = newButtonValue;
			document.getElementById("changeThemeBtn").innerHTML = newButtonText;
			document.getElementById("importCss").href = newHref;
			
			document.getElementById("sessionTheme").value = newParameter;
	
		}
	
	</script>
	
	</body>
</html>