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
<center><h2>Supprimer une ville </h2></center>
	<div style="position: fixed; rigth: 5px; top: 5px ;"><a
				href="accueil"> Retour à la page d'accueil</a>  </div>
	<form method="post" action="">
		<label for="codeCommune"></label> <select name="codeCommune" id="codeCommune">
			<option value="">Quelle ville souhaitez-vous supprimer ? </option>
			<c:forEach items="${ sessionScope.villes }" var="ville" varStatus="status">
				<option value="${ ville.codeCommune }">${ ville.codeCommune }: ${ ville.nomCommune }</option>
			</c:forEach>
		</select>
		<input type="submit" value="Supprimer"/>
	</form>
</body>
</html>