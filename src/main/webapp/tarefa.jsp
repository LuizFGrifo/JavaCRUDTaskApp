<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!-- Importar SimpleDateFormat -->

<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("tarefas");
SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Defina o formato desejado
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de tarefas</title>
<link rel="icon" href="images/licao.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de tarefas</h1>
	<a href="novo.html" class="Botao1">Nova tarefa</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>ID</th>
				<th>Tarefa</th>
				<th>Descrição</th>
				<th>Data</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getTarefaId()%></td>
				<td><%=lista.get(i).getNomeTarefa()%></td>
				<td><%=lista.get(i).getDescricao()%></td>
				<td><%=dateFormat.format(lista.get(i).getDataTarefa())%></td>
				<td><a href="select?idtar=<%=lista.get(i).getTarefaId()%>"
					class="Botao1">Editar</a> <a href="javascript: confirmar(<%=lista.get(i).getTarefaId()%>)"
					class="Botao2">Deletar</a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>
