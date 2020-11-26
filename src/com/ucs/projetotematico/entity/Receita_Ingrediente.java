package com.ucs.projetotematico.entity;

import java.util.ArrayList;

import java.util.List;

public class Receita_Ingrediente {

	private int id;
	private int quantidade;
	private int id_receita;
	private int id_ingrediente;
	private int id_unidade;
	private List<Ingrediente> ingredientes;
	
	public Receita_Ingrediente() {
		this.ingredientes = new ArrayList<Ingrediente>();
	}

	public Receita_Ingrediente(Integer id, Integer quantidade, Integer id_receita, Integer id_ingrediente, Integer id_unidade) {
		this();
		this.id = id;
		this.quantidade = quantidade;
		this.id_receita = id_receita;
		this.id_ingrediente = id_ingrediente;
		this.id_unidade = id_unidade;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getIdReceita() {
		return id_receita;
	}

	public void setIdReceita(int id_receita) {
		this.id_receita = id_receita;
	}
	
	public int getIdIngrediente() {
		return id_ingrediente;
	}

	public void setIdIngrediente(int id_ingrediente) {
		this.id_ingrediente = id_ingrediente;
	}
	
	public int getIdUnidade() {
		return id_unidade;
	}

	public void setIdUnidade(int id_unidade) {
		this.id_unidade = id_unidade;
	}
	
	public void addIngredientes(Ingrediente ingredientes) {
		ingredientes.setReceitaIngrediente(this);
		this.ingredientes.add(ingredientes);
	}

	public void removeIngredientes(Ingrediente ingredientes) {
		this.ingredientes.remove(ingredientes);
	}
	
	public int getQuantosIngredientes() {
		return this.ingredientes.size();
	}
	
	public List<Ingrediente> getPets() {
		return ingredientes;
	}

}
