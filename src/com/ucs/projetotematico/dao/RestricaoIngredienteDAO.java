package com.ucs.projetotematico.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucs.projetotematico.entity.RestricaoIngrediente;

public class RestricaoIngredienteDAO extends ModelDao<RestricaoIngrediente> {

	public RestricaoIngredienteDAO() {
		super.setModel(new RestricaoIngrediente());
	}

	@Override
	public List<RestricaoIngrediente> findAll() {
		final List<RestricaoIngrediente> restricaoIngredientes = new ArrayList<RestricaoIngrediente>();

		super.findAll(rs -> {
			final RestricaoIngrediente restricaoIngrediente = convertResultSet(rs);
			restricaoIngredientes.add(restricaoIngrediente);

		});

		return restricaoIngredientes;
	}

	@Override
	public RestricaoIngrediente findById(Integer id) {
		final RestricaoIngrediente model = new RestricaoIngrediente();

		super.findById((rs) -> {
			final RestricaoIngrediente receita = convertResultSet(rs);

			model.setId(receita.getId());
			model.setIdIngrediente(receita.getIdIngrediente());
			model.setIdRestricao(receita.getIdRestricao());

		}, id);
		return model;
	}

	@Override
	public boolean remove(Integer id) {
		return super.remove(id);
	}

	@Override
	public void saveOrUpdate(RestricaoIngrediente model) {
		final Map<String, String> map = new HashMap<String, String>();

		super.saveOrUpdate(map);

	}

	@Override
	public List<RestricaoIngrediente> findLike(RestricaoIngrediente model) {
		final Map<String, Integer> map = new HashMap<String, Integer>();
		final List<RestricaoIngrediente> receitas = new ArrayList<RestricaoIngrediente>();

		if (model.getId() != null) {
			map.put("id", model.getId());
		}
		if (model.getIdIngrediente() != null) {
			map.put("id_ingrediente", model.getIdIngrediente());
		}

		if (model.getIdRestricao() != null) {
			map.put("id_restricao", model.getIdRestricao());
		}

		super.findByInt(map, rs -> {
			final RestricaoIngrediente receita = convertResultSet(rs);
			receitas.add(receita);
		});

		return receitas;

	}

	@Override
	public RestricaoIngrediente convertResultSet(ResultSet resultSet) {
		final RestricaoIngrediente model = new RestricaoIngrediente();
		try {
			model.setId(resultSet.getInt("id"));
			model.setIdIngrediente(resultSet.getInt("id_ingrediente"));
			model.setIdRestricao(resultSet.getInt("id_restricao"));
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return model;
	}
}
