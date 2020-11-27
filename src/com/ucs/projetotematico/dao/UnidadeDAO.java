package com.ucs.projetotematico.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucs.projetotematico.entity.Unidade;

public class UnidadeDAO extends ModelDao<Unidade> {
	
	private UnidadeDAO() {
		super.setConn(super.openConnection());
		super.setModel(new Unidade());
	}


	@Override
	public List<Unidade> findAll() {
		final List<Unidade> receitas = new ArrayList<Unidade>();

		super.findAll(rs -> {
			final Unidade receita = convertResultSet(rs);
			receitas.add(receita);

		});

		return receitas;
	}

	@Override
	public Unidade findById(Integer id) {
		final Unidade model = new Unidade();

		super.findById((rs) -> {
			final Unidade receita = convertResultSet(rs);

			model.setId(receita.getId());
			model.setTipo(receita.getTipo());

		}, id);
		return model;
	}

	@Override
	public boolean remove(Integer id) {
		return super.remove(id);
	}

	@Override
	public void saveOrUpdate(Unidade model) {
		final Map<String, String> map = new HashMap<String, String>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("tipo", model.getTipo());

		super.saveOrUpdate(map);

	}

	@Override
	public List<Unidade> findLike(Unidade model) {
		final Map<String, String> map = new HashMap<String, String>();
		final List<Unidade> receitas = new ArrayList<Unidade>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("tipo", model.getTipo());

		super.findLike(map, rs -> {
			final Unidade receita = convertResultSet(rs);
			receitas.add(receita);
		});

		return receitas;
	}

	@Override
	public Unidade convertResultSet(ResultSet resultSet) {
		final Unidade model = new Unidade();
		try {
			model.setId(resultSet.getInt("id"));
			model.setTipo(resultSet.getString("tipo"));
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return model;
	}
	
}
