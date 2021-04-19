<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><%@include file="/WEB-INF/villes.css"%></style>
<title>Insert title here</title>
</head>
<body>
<center><h2>${name }:</h2> </center>
	<div style="position: fixed; left: 5px; top: 5px ;"><li><a
				href="accueil"> Retour à la page d'accueil</a> </li> </div>
	<form method="post" action="">
		
	    
	    <label for="nomCommune">Nom commune : </label>
	    <input type="text" name="nomCommune"  placeholder="Saisir un nouveau nom" id="nomCommune" required> <br>
	    
	    <label for="codePostal">Code postal : </label>
	    <input type="text" name="codePostal" placeholder="Saisir le code postal" id="codePostal" required><br>
	    
	    <label for="libelleAcheminement">Libelle acheminement : </label>
	    <input type="text" name="libelleAcheminement" placeholder="Saisir le libelle" id="libelleAcheminement" required><br>
	    
	    <label for="ligne">Ligne : </label>
	    <input type="text" name="ligne" placeholder="Saisir la ligne" id="ligne" required><br>
	    
	    <label for="latitude">Latitude : </label>
	    <input type="text" name="latitude" placeholder="Saisir la nouvelle latitude" id="latitude" required><br>
	    
	    <label for="longitude">Longitude : </label>
	    <input type="text" name="longitude" placeholder="Saisir la nouvelle longitude" id="longitude" required><br>
	    
	   <input type="submit" value="Modifier la ville"/>
	</form>
</body>
</html>