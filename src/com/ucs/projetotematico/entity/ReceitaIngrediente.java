package com.ucs.projetotematico.entity;

public class ReceitaIngrediente extends ModelAbstract {
	private String tableName = "receitaingrediente";
	private Integer quantidade;
	private Unidade unidade;
	private Receita receita;
	private Integer idReceita;
	private Ingrediente ingrediente;

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;

	}

	public Integer getIdReceita() {
		return idReceita;
	}

	public void setIdReceita(Integer idReceita) {
		this.idReceita = idReceita;
	}

	@Override
	public String toString() {
		return "ReceitaIngrediente [quantidade=" + quantidade + ", unidade=" + unidade.toString() + ", receita=" + receita.toString() + ", ingrediente=" + ingrediente.toString() + "]";
	}

}
