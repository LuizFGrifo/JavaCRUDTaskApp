<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda de tarefas</title>
<link rel="icon" href="images/licao.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar tarefa</h1>
	<form name="frmTarefa" action="update">
		<table>
			<tr>
				<td><input type="text" name="idtar" id="Caixa2" readonly
					value="<%out.print(request.getAttribute("idtar"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1"
					value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="descricao" class="Caixa1"
					value="<%out.print(request.getAttribute("descricao"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="data" class="Caixa1"
					value="<%out.print(request.getAttribute("data"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>