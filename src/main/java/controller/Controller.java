package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

//Parte das requisições
@WebServlet(urlPatterns = { "/MyController", "/main", "/insert", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO();
	JavaBeans tarefas = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main")) { // Verifica qual requisição está sendo enviada
			tarefas(request, response);
		} else if (action.equals("/insert")) {
			novaTarefa(request, response);
		} else if (action.equals("/select")) {
			listarTarefa(request, response);
		} else if (action.equals("/update")) {
			editarTarefa(request, response);
		} else if (action.equals("/delete")) {
			removerTarefa(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	// Listar tarefas - Vair redirecionar para o tarefa.jsp
	protected void tarefas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que irá receber os dados do JavaBeans
		ArrayList<JavaBeans> lista = dao.listarTarefas();
		// Encaminhar a lista ao documento tarefa.jsp
		request.setAttribute("tarefas", lista);
		RequestDispatcher rd = request.getRequestDispatcher("tarefa.jsp");
		rd.forward(request, response);
	}

	// Nova tarefa
	protected void novaTarefa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setar as variaveis JavaBeans
		tarefas.setNomeTarefa(request.getParameter("nome"));
		tarefas.setDescricao(request.getParameter("descricao"));

		String dataString = request.getParameter("data");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dataTarefa = dateFormat.parse(dataString);
			tarefas.setDataTarefa(dataTarefa);
		} catch (ParseException e) {
			// Lidar com erros de parsing da data, se necessário
			e.printStackTrace();
		}
		// Invocar o método inserirTarefa passando o objeto tarefas
		dao.inserirTarefa(tarefas);
		// Redirecionamento para o documento tarefa.jsp
		response.sendRedirect("main");
	}

	// Editar contato
	protected void listarTarefa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebe o id da tarefa que será editado
		String idtar = request.getParameter("idtar");
		// Setar a variavel JavaBeans
		tarefas.setTarefaId(idtar);
		// Executar o método selecinarTarefa (DAO)
		dao.selecionarTarefa(tarefas);
		// Setar os atributos do formulário com o conteudo JavaBeans
		request.setAttribute("idtar", tarefas.getTarefaId());
		request.setAttribute("nome", tarefas.getNomeTarefa());
		request.setAttribute("descricao", tarefas.getDescricao());
		request.setAttribute("data", tarefas.getDataTarefa());
		// Encaminhar para o documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	protected void editarTarefa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setar as variaveis JavaBeans
		tarefas.setTarefaId(request.getParameter("idtar"));
		tarefas.setNomeTarefa(request.getParameter("nome"));
		tarefas.setDescricao(request.getParameter("descricao"));
		String dataString = request.getParameter("data");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Use o formato correto para sua data
		try {
			Date dataTarefa = dateFormat.parse(dataString);
			tarefas.setDataTarefa(dataTarefa);
		} catch (ParseException e) {
			// Lidar com erros de parsing da data, se necessário
			e.printStackTrace();
		}
		// Executar o método alterarTarefa
		dao.alterarTarefa(tarefas);
		// Redirecionar para o documento tarefa.jsp (Atualizando alterações)
		response.sendRedirect("main");
	}

	// Remover um contato
	protected void removerTarefa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id da tarefa a ser excluida
		String idtar = request.getParameter("idtar");
		// Setar a variável idtar JavaBeans
		tarefas.setTarefaId(idtar);
		// Executar o método deletarTarefa
		dao.deletarTarefa(tarefas);
		// Redirecionar para o documento tarefa.jsp (Atualizando alterações)
		response.sendRedirect("main");
	}
}
