package com.ucs.projetotematico.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ucs.projetotematico.entity.Ingrediente;
import com.ucs.projetotematico.entity.ModelAbstract;
import com.ucs.projetotematico.entity.Receita;
import com.ucs.projetotematico.entity.Receita_Ingrediente;

public class ReceitaIngredienteDB extends ModelDao<Receita>{

	private Connection conn;
	private static ReceitaIngredienteDB instancia;


	private ReceitaIngredienteDB() {
		conn = super.openConnection();
	}
	
	public static ReceitaIngredienteDB getInstancia() {

		if(instancia==null) {
			instancia = new ReceitaIngredienteDB();
		}

		return instancia;

	}

	public Connection getConnection()  {
		return conn;
	}

	@Override
	public List<Receita> findAll() {
		return null;
	}

	@Override
	public Receita findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(Receita model) {
		// TODO Auto-generated method stub
		
	}
}
