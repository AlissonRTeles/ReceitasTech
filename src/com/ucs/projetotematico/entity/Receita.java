package com.ucs.projetotematico.entity;

public class Receita extends ModelAbstract{
	private String tableName;
	private String nome_receita;
	private int id_receita;
	private String descricao_receita;
	
	public Receita(){
		
	}

	public Receita(String nome_receita, int id_receita, String descricao_receita) {
		this.nome_receita = nome_receita;
		this.id_receita = id_receita;
		this.descricao_receita = descricao_receita;
	}
	
	public String getNome() {
		return nome_receita;
	}

	public void setNome(String nome) {
		this.nome_receita = nome_receita;
	}
	
	public Integer getIdReceita() {
		return id_receita;
	}

	public void setIdReceita(Integer id) {
		this.id_receita = id_receita;
	}
	
	public String getDescricao() {
		return descricao_receita;
	}
	
	public void setDescricao(String descricao_receita) {
		this.descricao_receita = descricao_receita;
	}
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id_receita == null) ? 0 : id_receita.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Receitas other = (Receitas) obj;
//		if (id_receita == null) {
//			if (other.id_receita != null)
//				return false;
//		} else if (!id_receita.equals(other.id_receita))
//			return false;
//		return true;
//	}

	@Override
	public String toString() {
		return "Ingrediente [id=" + id_receita + ", nome=" + nome_receita + "]";
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
