package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/** Módulo de conexão **/
	// Parâmetro de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbTarefas?useTimezone=true&serverTimezone-UTC";
	private String user = "user";
	private String password = "password";

	// Método de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver); // Vai ler o driver
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/** CRUD CREATE **/
	public void inserirTarefa(JavaBeans tarefas) {
		String create = "INSERT INTO tarefas (nomeTarefa, descricao, dataTarefa) VALUES (?, ?, ?)";
		try {
			// Abrir conexão com o BD
			Connection con = conectar();
			// Preparar a Query para execução no BD
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?) pelo conteúdo das variáveis
			pst.setString(1, tarefas.getNomeTarefa());
			pst.setString(2, tarefas.getDescricao());
			// Converter a data de Java para SQL
			java.util.Date dataTarefa = tarefas.getDataTarefa();
			java.sql.Date dataSql = new java.sql.Date(dataTarefa.getTime());
			pst.setDate(3, dataSql);
			// Executar a Query
			pst.executeUpdate();
			// Encerrar a conexão com o BD
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** CRUD READ **/
	public ArrayList<JavaBeans> listarTarefas() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> tarefas = new ArrayList<>();
		String read = "SELECT * FROM tarefas ORDER BY dataTarefa";
		try {
			// Abrir conexão com o BD
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String idtar = rs.getString(1);
				String nome = rs.getString(2);
				String descricao = rs.getString(3);
				java.sql.Date dataSql = rs.getDate(4);
				java.util.Date data = new java.util.Date(dataSql.getTime());
				// Populando o ArrayList
				tarefas.add(new JavaBeans(idtar, nome, descricao, data));
			}
			con.close();
			return tarefas;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/** CRUD UPDATE **/
	// Selecionar a tarefa
	public void selecionarTarefa(JavaBeans tarefa) {
		String read2 = "SELECT * FROM tarefas WHERE tarefaID = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, tarefa.getTarefaId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				tarefa.setTarefaId(rs.getString(1));
				tarefa.setNomeTarefa(rs.getString(2));
				tarefa.setDescricao(rs.getString(3));
				java.sql.Date dataSql = rs.getDate(4);
				java.util.Date dataUtil = new java.util.Date(dataSql.getTime());
				tarefa.setDataTarefa(dataUtil);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Editar o contato
	public void alterarTarefa(JavaBeans tarefa) {
		String create = "UPDATE tarefas SET nomeTarefa = ?, descricao = ?, dataTarefa = ? WHERE tarefaID = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, tarefa.getNomeTarefa());
			pst.setString(2, tarefa.getDescricao());
	        java.util.Date dataTarefa = tarefa.getDataTarefa();
	        java.sql.Date dataSql = new java.sql.Date(dataTarefa.getTime());
	        pst.setDate(3, dataSql);
			pst.setString(4, tarefa.getTarefaId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** CRUD DELETE **/
	public void deletarTarefa(JavaBeans tarefa) {
		String delete = "DELETE FROM tarefas WHERE tarefaID = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, tarefa.getTarefaId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
