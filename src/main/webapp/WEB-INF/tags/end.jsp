	<% 
	
		String theme = null;
		System.out.println("from end.jsp (Java code) - theme = " + request.getParameter("theme"));
		System.out.println("--------------------------------");
		theme = (String) request.getAttribute("theme");
		
	 %>
	
	<script>

		var theme = '<%= theme %>';
		console.log("theme : " + theme);
		console.log("in js");

		if(theme == "dark" || theme == "light") {
			console.log("in js : if(theme == \"dark\" || theme == \"light\") {");
			processChangeTheme(theme);
		}
	
		function changeTheme(thisButton) {
	
			var actualTheme = thisButton.value;
			
			if (actualTheme == "dark") {	
				theme= "light";
			} else if (actualTheme == "light") {	
				theme= "dark";
			}
			
			processChangeTheme(theme);
		}
		
		function processChangeTheme(theme) {
			console.log("in js : processChangeTheme(" + theme + ");");

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