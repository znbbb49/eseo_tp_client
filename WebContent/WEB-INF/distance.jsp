<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><%@include file="/WEB-INF/villes.css"%></style>
<title>Calcul distance</title>
</head>
<body>

	<div style="position: fixed; left: 5px; top: 5px ;"><li><a
				href="accueil"> Retour à la page d'accueil</a> </li> </div>
		
	<div style="position: fixed; top: 50px ;"><form method="post" action="">
	
	<select name="ville1" id="ville1">
    <option value="">Ville 1</option>
    
    <c:forEach items="${ ville }" var="current" varStatus="status">
		<option value="${current.nomCommune}">${current.nomCommune}</option>		
	</c:forEach>
	</select>
	
	
	<select name="ville2" id="ville2">
    <option value="">Ville 2</option>
    
    <c:forEach items="${ ville }" var="current" varStatus="status">
		<option value="${current.nomCommune}">${current.nomCommune}</option>		
	</c:forEach>
	</select>
	
	<input value="Calculer" type="submit" />
	
	</form>
	
		<c:if test="${!empty distance}" >

			<c:out value="La distance entre ${ ville1 } et ${ ville2 } est de ${ distance } km"></c:out>

</c:if>

</body>
</html>