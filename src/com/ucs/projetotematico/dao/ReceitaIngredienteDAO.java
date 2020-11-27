package com.ucs.projetotematico.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucs.projetotematico.entity.ReceitaIngrediente;
import com.ucs.projetotematico.entity.ReceitaIngrediente;

public class ReceitaIngredienteDAO extends ModelDao<ReceitaIngrediente> {
	private UnidadeDAO unidadeDAO;
	private IngredienteDAO ingredienteDAO;
	private ReceitaDAO receitaDAO;
	
	ReceitaIngredienteDAO() {
		super.setConn(super.openConnection());
		super.setModel(new ReceitaIngrediente());
		this.unidadeDAO = new UnidadeDAO(super.getConn());
		this.receitaDAO = new ReceitaDAO(super.getConn());
		this.ingredienteDAO = new IngredienteDAO(super.getConn());
	}

	ReceitaIngredienteDAO(Connection conn) {
		super.setConn(conn);
		this.unidadeDAO = new UnidadeDAO(super.getConn());
		this.receitaDAO = new ReceitaDAO(super.getConn());
		this.ingredienteDAO = new IngredienteDAO(super.getConn());
		super.setModel(new ReceitaIngrediente());
	}	
	
	@Override
	public List<ReceitaIngrediente> findAll() {
		final List<ReceitaIngrediente> receitas = new ArrayList<ReceitaIngrediente>();

		super.findAll(rs -> {
			final ReceitaIngrediente receita = convertResultSet(rs);
			receitas.add(receita);

		});

		return receitas;
	}

	@Override
	public ReceitaIngrediente findById(Integer id) {
		final ReceitaIngrediente model = new ReceitaIngrediente();

		super.findById((rs) -> {
			final ReceitaIngrediente receita = convertResultSet(rs);

			model.setId(receita.getId());
			model.setQuantidade(receita.getQuantidade());
			model.setReceita(receita.getReceita());
			model.setUnidade(receita.getUnidade());
			model.setIngrediente(receita.getIngrediente());
			

		}, id);
		return model;
	}

	@Override
	public boolean remove(Integer id) {
		return super.remove(id);
	}

	@Override
	public void saveOrUpdate(ReceitaIngrediente model) {
		final Map<String, String> map = new HashMap<String, String>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}
		
		map.put("id_receita", model.getReceita().getId().toString());
		map.put("id_ingrediente", model.getIngrediente().getId().toString());
		map.put("id_unidade", model.getUnidade().getId().toString());

		super.saveOrUpdate(map);

	}

	@Override
	public List<ReceitaIngrediente> findLike(ReceitaIngrediente model) {
		final Map<String, String> map = new HashMap<String, String>();
		final List<ReceitaIngrediente> receitas = new ArrayList<ReceitaIngrediente>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}
		map.put("id_receita", model.getReceita().getId().toString());
		map.put("id_ingrediente", model.getIngrediente().getId().toString());
		map.put("id_unidade", model.getUnidade().getId().toString());

		super.findLike(map, rs -> {
			final ReceitaIngrediente receita = convertResultSet(rs);
			receitas.add(receita);
		});

		return receitas;
	}

	@Override
	public ReceitaIngrediente convertResultSet(ResultSet resultSet) {
		final ReceitaIngrediente model = new ReceitaIngrediente();
		try {
			model.setId(resultSet.getInt("id"));
			int idReceita = resultSet.getInt("id_receita");			
			int idIngrediente = resultSet.getInt("id_ingrediente");
			int idUnidade = resultSet.getInt("id_unidade");
			model.setQuantidade(resultSet.getInt("quantidade"));
			
			model.setReceita(getReceitaDAO().findById(idReceita));
			model.setIngrediente(getIngredienteDAO().findById(idIngrediente));
			model.setUnidade(unidadeDAO.findById(idUnidade));
			
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return model;
	}
	public UnidadeDAO getUnidadeDAO() {
		return unidadeDAO;
	}


	public void setUnidadeDAO(UnidadeDAO unidadeDAO) {
		this.unidadeDAO = unidadeDAO;
	}


	public IngredienteDAO getIngredienteDAO() {
		return ingredienteDAO;
	}


	public void setIngredienteDAO(IngredienteDAO ingredienteDAO) {
		this.ingredienteDAO = ingredienteDAO;
	}


	public ReceitaDAO getReceitaDAO() {
		return receitaDAO;
	}


	public void setReceitaDAO(ReceitaDAO receitaDAO) {
		this.receitaDAO = receitaDAO;
	}
	
	public static void main(String[] args) {
		ReceitaIngredienteDAO r = new ReceitaIngredienteDAO();
	
		ReceitaIngrediente model = new ReceitaIngrediente();
		model.setId(2);
	}
}
