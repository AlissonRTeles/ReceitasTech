package com.ucs.projetotematico.entity;

public class Ingrediente extends ModelAbstract{
	private static String tableName = "ingrediente";
	private String nome;
	
	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Ingrediente [nome=" + nome + ", getId()=" + getId() + "]";
	}
}
