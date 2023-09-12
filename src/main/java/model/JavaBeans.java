package model;

import java.util.Date;

public class JavaBeans {
	private String tarefaId;
	private String nomeTarefa;
	private String descricao;
	private Date dataTarefa;

	public JavaBeans() {
		super();
	}

	public JavaBeans(String tarefaId, String nomeTarefa, String descricao, Date data) {
		super();
		this.tarefaId = tarefaId;
		this.nomeTarefa = nomeTarefa;
		this.descricao = descricao;
		this.dataTarefa = data;
	}

	public String getTarefaId() {
		return tarefaId;
	}

	public void setTarefaId(String tarefaId) {
		this.tarefaId = tarefaId;
	}

	public String getNomeTarefa() {
		return nomeTarefa;
	}

	public void setNomeTarefa(String nomeTarefa) {
		this.nomeTarefa = nomeTarefa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataTarefa() {
		return dataTarefa;
	}

	public void setDataTarefa(Date dataTarefa) {
		this.dataTarefa = dataTarefa;
	}
}
