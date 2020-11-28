package com.ucs.projetotematico.entity;

import java.util.List;

public class Receita extends ModelAbstract {
	private static String tableName = "receita";
	private String nome;
	private String descricao;
	private String modoPreparo;
	private List<ReceitaIngrediente> receitaIngredientes;

	public Receita() {

	}

	public Receita(String nome_receita, int id_receita, String descricao_receita) {
		super.setId(id_receita);
		this.nome = nome_receita;
		this.descricao = descricao_receita;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao_receita) {
		this.descricao = descricao_receita;
	}

	public String getModoPreparo() {
		return modoPreparo;
	}

	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}

	public List<ReceitaIngrediente> getReceitaIngredientes() {
		return receitaIngredientes;
	}

	public void setReceitaIngredientes(List<ReceitaIngrediente> receitaIngredientes) {
		this.receitaIngredientes = receitaIngredientes;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;

	}

	@Override
	public String toString() {
		return "Receita [nome=" + nome + ", descricao=" + descricao + ", modoPreparo=" + modoPreparo + "]";
	}

}
