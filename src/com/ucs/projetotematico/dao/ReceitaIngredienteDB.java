package com.ucs.projetotematico.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ucs.projetotematico.entity.Receita;

public class ReceitaIngredienteDB extends ModelDao<Receita>{

	private ReceitaIngredienteDB() {
		
		super.setConn(super.openConnection());
		super.setModel(new Receita());
	}

	@Override
	public List<Receita> findAll() {
		List<Receita> receitas = new ArrayList<Receita>();

		super.findAll(rs->{
			Receita receita = new Receita();
			
			try {
				
				receita.setId(rs.getInt("id"));
				receita.setDescricao(rs.getString("descricao"));
				receita.setNome(rs.getString("nome"));
				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			receitas.add(receita);
		}
		);

		return receitas;
	}

	@Override
	public Receita findById(Integer id) {
		Receita receita = new Receita();
		
		super.findById(rs->{
			try {
				receita.setId(rs.getInt("id"));
				receita.setNome(rs.getString("nome"));
				receita.setDescricao(rs.getString("descricao"));
				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}, id);
		return receita;
	}

	@Override
	public void remove(Integer id) {
		super.remove(id);
	}

	@Override
	public void saveOrUpdate(Receita model) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		ReceitaIngredienteDB t = new ReceitaIngredienteDB();
		
	}
}
