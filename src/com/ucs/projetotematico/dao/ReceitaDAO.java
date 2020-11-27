package com.ucs.projetotematico.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucs.projetotematico.entity.Receita;

public class ReceitaDAO extends ModelDao<Receita> {

	private ReceitaDAO() {

		super.setConn(super.openConnection());
		super.setModel(new Receita());
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
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

}
