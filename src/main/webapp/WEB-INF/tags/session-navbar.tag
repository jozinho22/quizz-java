<% 
	boolean ok = false ;
	String name = (String) session.getAttribute("input-name");
	if(name != null) {
		ok = true;
	} else {
		response.sendRedirect("error");
	}
	
	Integer nbRestantes = 0;
	if(request.getAttribute("nbRestantes") != null) {
		nbRestantes = (Integer) request.getAttribute("nbRestantes");
	}
%>

<nav id="navbar" class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="navbar-brand text-center">
		Quizz Java
	</div>
</nav>