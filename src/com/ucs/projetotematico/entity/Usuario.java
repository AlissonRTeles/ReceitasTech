package com.ucs.projetotematico.entity;

public class Usuario extends ModelAbstract {
	private String tableName = "usuario";
	private String nome;
	private String senha;
	private Restricao restricao;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Restricao getRestricao() {
		return restricao;
	}

	public void setRestricao(Restricao restricao) {
		this.restricao = restricao;
	}

	@Override
	public String toString() {
		return "Usuario [tableName=" + tableName + ", nome=" + nome + ", senha=" + senha + ", restricao=" + restricao + ", getRestricao()=" + getRestricao() + "]";
	}

}