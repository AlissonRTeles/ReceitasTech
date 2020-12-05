package com.ucs.projetotematico.entity;

public class RestricaoIngrediente extends ModelAbstract {
	private String tableName = "restricaoingrediente";
	private Integer idIngrediente;
	private Integer idRestricao;

	public Integer getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(Integer idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public Integer getIdRestricao() {
		return idRestricao;
	}

	public void setIdRestricao(Integer idRestricao) {
		this.idRestricao = idRestricao;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;

	}
}
