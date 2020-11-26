package com.ucs.projetotematico.entity;

import java.io.Serializable;

public class Usuario implements Serializable{

	private String nome_usuario;
	private String senha_usuario;
	private int id_usuario;

	public Usuario(){}

	public Usuario(String nome_usuario, String senha_usuario, int id_usuario) {
		super();
		this.nome_usuario = nome_usuario;
		this.senha_usuario = senha_usuario;
		this.id_usuario = id_usuario;
	}

	public String getNomeUsuario() {
		return nome_usuario;
	}

	public void setNomeUsuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	public String getSenha() {
		return senha_usuario;
	}

	public void setSenha(String senha_usuario) {
		this.senha_usuario = senha_usuario;
	}

	public int getIdUsuario() {
		return id_usuario;
	}

	public void setIdUsuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("Id = ");
		sb.append(this.getIdUsuario());
		sb.append(" ");

		sb.append(",Nome = ");
		sb.append(this.getNomeUsuario());
		sb.append(" ");

		sb.append(",Senha = ");
		sb.append(this.getSenha());
		sb.append(" ");

		return sb.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = prime * result + codigo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		// if (codigo != other.codigo)
		// return false;
		return true;
	}
}