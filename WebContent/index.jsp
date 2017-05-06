<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<script type="text/javascript" src="../assets/javascript/fonction.js"></script>
		<link rel="stylesheet" href="assets/CSS/styles.css" media="screen" />
		<link rel="icon" type="image/png" href="assets/images/icon.png" />
		
		<title>Connexion</title>
		
	</head>

<body>
	
	<div id=contenu align="center">
						
		<h1 align="center">Bookenaton</h1>
		<br/>
		<form method="post">
				   Login : <input name="login" type="text"/><br/>
			Mot de passe : <input name="password" type="password"/><br/>
			<input name="Valider" type="submit"/>
			
			<%
				String erreur = (String) request.getAttribute("erreur");
				if(erreur != null){
					%> 
						<%= erreur %>
					<% 
				}
			%>
		</form>
		
	</div>
		
</body>

</html>