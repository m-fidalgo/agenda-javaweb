<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agenda</title>
</head>
<body>
	<h3>Inserir Contato</h3>
	<form method="post" action="${pageContext.request.contextPath}/agenda/inserir">
		<p>Nome</p>
		<p><input type="text" name="nome"></p>
		<p>Idade</p>
		<p><input type="number" name="idade"></p>
		<p>Telefone</p>
		<p><input type="text" name="tel"></p>
		<button type="submit">Salvar</button>
	</form>
</body>
</html>