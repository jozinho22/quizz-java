	<script>

		var theme = '${theme}';
		console.log(theme);	
		
		if(theme == "dark" || theme == "light") {
			processChangeTheme(theme);
		}
	
		function getTheme(thisInput) {
	
			var actualTheme = thisInput.value;
			
			if (actualTheme == "dark") {	
				theme= "light";
			} else if (actualTheme == "light") {	
				theme= "dark";
			}
			
			processChangeTheme(theme);
		}
		
		// checked = "dark"
		function processChangeTheme(theme) {

// 			var newButtonText;
			var newInputValue;
			var newHref;
			var newParameter;
// 			var checked;
			
			if (theme == "dark") {	
				newInputValue = "dark";
// 				newButtonText = "Passer en mode light";
				newHref = "css/stylesheet-dark.css";
				newParameter = newInputValue;
// 				checked = true;
	
			} else if (theme == "light") {	
				newInputValue = "light";
// 				newButtonText = "Passer en mode dark";
				newHref = "css/stylesheet-light.css";
				newParameter = newInputValue;
// 				checked = false;

			}
						
			document.getElementById("changeThemeBtn").value = newInputValue;
// 			document.getElementById("changeThemeBtn").checked = checked;
// 			document.getElementById("changeThemeBtn").innerHTML = newButtonText;
			document.getElementById("importCss").href = newHref;
			
			document.getElementById("sessionTheme").value = newParameter;
	
		}
	
	</script>
	
	</body>
</html>