<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="villes.css">
<style><%@include file="/WEB-INF/villes.css"%></style>
<title>Liste des villes</title>
</head>
<body>
<div><center><h1>Liste des villes </h1><br> </center> </div>
	
<div style="position: fixed; rigth: 5px; top: 5px ;"><a
				href="accueil"> Retour à la page d'accueil</a>  </div>
	
	<div style="position: fixed; top: 40px ;">	
	<a href="afficheVille?page=${numPage }
				<%Integer pages = (Integer) request.getAttribute("numPage");
				if (pages <= 1) {
					pages = 2;
				}%>
				<%=pages - 1%>">Page précédente</a> </div>
		<div style="position: fixed;  top: 75px ;">
		 <a href="afficheVille?page=
				<%Integer pagesS = (Integer) request.getAttribute("numPage");%>
				<%=pagesS + 1%>">Page suivante</a> </div>
				
	<div>	
	<table>
		<tr>
			<td>Code commune</td>
			<td>Nom commune</td>
			<td>Code Postal</td>
			<td>Libelle acheminement</td>
			<td> Latitude </td>
			<td> Longitude </td>
			
		</tr>
		<c:forEach items="${villesPage }" var="villee">		    	
				<tr>
					<td><c:out value="${ villee.codeCommune}" /> </td>
					<td><c:out value="${ villee.nomCommune}" /></td>
					<td><c:out value="${ villee.codePostal}" /></td>
					<td><c:out value="${ villee.libelleAcheminement}" /></td>
					<td><c:out value="${ villee.latitude}" /></td>
					<td><c:out value="${ villee.longitude}" /></td>
					<td><a href="modifierVille?codeCommune=${villee.codeCommune}&ville=${villee.nomCommune}">Modifier</a></td>

				</tr>
			
		</c:forEach>
	</table> </div>		

</body>
</html>