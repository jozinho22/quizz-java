<%@ page import="com.douineau.utils.TagBuilder"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>Quizz java</title>

		<link rel="shortcut icon" href="">		
		<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">		
		<link rel="stylesheet" href="css/font/font-awesome-4.7.0/css/font-awesome.min.css" >	
		

		<link rel="stylesheet" href="css/stylesheet.css">		
		<% 
			System.out.println("from begin.jsp - theme = " + (String) request.getAttribute("theme"));
			System.out.println("--------------------------------");

			out.print(TagBuilder.buildTagImportCss((String) request.getAttribute("theme")));
			
		%>
<!-- 		<link id="importCss" rel="stylesheet" href="css/stylesheet-dark.css"> -->
<!-- 		<link id="importCss" rel="stylesheet" href="css/stylesheet-light.css">	 -->
		
		<script src="js/jquery.min.js"></script>

		
	</head>
	
	<body id="mainback">

		