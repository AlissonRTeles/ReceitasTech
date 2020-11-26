package com.ucs.projetotematico.entity;

public class Ingrediente {
	
	private Integer id;
	private String nome;
	private Receita_Ingrediente receita_ingrediente;
	
	public Ingrediente() {
	
	}
	
	public Ingrediente(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Ingrediente(Integer id, String nome, Receita_Ingrediente receita_ingrediente) {
		this.id = id;
		this.nome = nome;
		this.receita_ingrediente = receita_ingrediente;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Receita_Ingrediente getReceitaIngrediente() {
		return receita_ingrediente;
	}

	public void setReceitaIngrediente(Receita_Ingrediente receita_ingrediente) {
		this.receita_ingrediente = receita_ingrediente;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Ingrediente other = (Ingrediente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nome=" + nome + "]";
	}


}
