package com.ucs.projetotematico.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucs.projetotematico.entity.Receita;
import com.ucs.projetotematico.entity.ReceitaIngrediente;

public class ReceitaDAO extends ModelDao<Receita> {
	private ReceitaIngredienteDAO receitaIngredienteDAO;

	public ReceitaDAO() {

		super.setModel(new Receita());
		this.receitaIngredienteDAO = new ReceitaIngredienteDAO();
	}

	@Override
	public List<Receita> findAll() {
		final List<Receita> receitas = new ArrayList<Receita>();

		super.findAll(rs -> {
			final Receita receita = convertResultSet(rs);
			receitas.add(receita);

		});

		return receitas;
	}

	@Override
	public Receita findById(Integer id) {
		final Receita model = new Receita();

		super.findById((rs) -> {
			final Receita receita = convertResultSet(rs);

			model.setId(receita.getId());
			model.setDescricao(receita.getDescricao());
			model.setNome(receita.getNome());
			model.setModoPreparo(receita.getModoPreparo());
			model.setReceitaIngredientes(receita.getReceitaIngredientes());

		}, id);
		return model;
	}

	@Override
	public boolean remove(Integer id) {
		return super.remove(id);
	}

	@Override
	public void saveOrUpdate(Receita model) {
		final Map<String, String> map = new HashMap<String, String>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());
		map.put("descricao", model.getDescricao());
		map.put("modopreparo", model.getModoPreparo());

		super.saveOrUpdate(map);

	}

	@Override
	public List<Receita> findLike(Receita model) {
		final Map<String, String> map = new HashMap<String, String>();
		final List<Receita> receitas = new ArrayList<Receita>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());
		map.put("descricao", model.getDescricao());
		map.put("modopreparo", model.getModoPreparo());

		super.findLike(map, rs -> {
			final Receita receita = convertResultSet(rs);
			receitas.add(receita);
		});

		return receitas;
	}

	@Override
	public Receita convertResultSet(ResultSet resultSet) {
		final Receita model = new Receita();

		try {
			model.setId(resultSet.getInt("id"));
			model.setDescricao(resultSet.getString("descricao"));
			model.setNome(resultSet.getString("nome"));
			model.setModoPreparo(resultSet.getString("modopreparo"));

			final Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("id_receita", model.getId());

			model.setReceitaIngredientes(new ArrayList<ReceitaIngrediente>());

			if (getReceitaIngredienteDAO() != null) {
				getReceitaIngredienteDAO().findByInt(map, rs -> {
					model.getReceitaIngredientes().add(getReceitaIngredienteDAO().convertResultSet(rs));
				});

			}

		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

	public ReceitaIngredienteDAO getReceitaIngredienteDAO() {
		return receitaIngredienteDAO;
	}

	public void setReceitaIngredienteDAO(ReceitaIngredienteDAO receitaIngredienteDAO) {
		this.receitaIngredienteDAO = receitaIngredienteDAO;
	}

	public static void main(String[] args) {
		final ReceitaDAO r = new ReceitaDAO();

		System.out.println(r.findById(1).toString());
	}
}
