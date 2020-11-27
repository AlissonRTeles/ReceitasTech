package com.ucs.projetotematico.entity;

public class Unidade extends ModelAbstract{
	private String tableName = "unidade";
	private String tipo;
	
	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Unidade [tipo=" + tipo + ", getId()=" + getId() + "]";
	}

}
