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
		<a href="theme">
		<% 
			String theme = (String) session.getAttribute("theme");
			out.print(TagBuilder.buildTagThemeButton(theme));
		%>
		</a>
	</div>
</nav>
