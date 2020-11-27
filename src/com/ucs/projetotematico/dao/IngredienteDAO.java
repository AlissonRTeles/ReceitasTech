package com.ucs.projetotematico.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucs.projetotematico.entity.Ingrediente;
import com.ucs.projetotematico.entity.Ingrediente;

public class IngredienteDAO extends ModelDao<Ingrediente> {
	
	private IngredienteDAO() {
		super.setConn(super.openConnection());
		super.setModel(new Ingrediente());
	}


	@Override
	public List<Ingrediente> findAll() {
		final List<Ingrediente> receitas = new ArrayList<Ingrediente>();

		super.findAll(rs -> {
			final Ingrediente receita = convertResultSet(rs);
			receitas.add(receita);

		});

		return receitas;
	}

	@Override
	public Ingrediente findById(Integer id) {
		final Ingrediente model = new Ingrediente();

		super.findById((rs) -> {
			final Ingrediente receita = convertResultSet(rs);

			model.setId(receita.getId());
			model.setNome(receita.getNome());

		}, id);
		return model;
	}

	@Override
	public boolean remove(Integer id) {
		return super.remove(id);
	}

	@Override
	public void saveOrUpdate(Ingrediente model) {
		final Map<String, String> map = new HashMap<String, String>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());

		super.saveOrUpdate(map);

	}

	@Override
	public List<Ingrediente> findLike(Ingrediente model) {
		final Map<String, String> map = new HashMap<String, String>();
		final List<Ingrediente> receitas = new ArrayList<Ingrediente>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());

		super.findLike(map, rs -> {
			final Ingrediente receita = convertResultSet(rs);
			receitas.add(receita);
		});

		return receitas;
	}

	@Override
	public Ingrediente convertResultSet(ResultSet resultSet) {
		final Ingrediente model = new Ingrediente();
		try {
			model.setId(resultSet.getInt("id"));
			model.setNome(resultSet.getString("nome"));
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return model;
	}
	
}
