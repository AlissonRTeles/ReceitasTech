package com.ucs.projetotematico.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucs.projetotematico.entity.Restricao;

public class RestricaoDAO extends ModelDao<Restricao> {

	RestricaoDAO() {
		super.setConn(super.openConnection());
		super.setModel(new Restricao());
	}

	RestricaoDAO(Connection conn) {
		super.setConn(conn);
		super.setModel(new Restricao());
	}

	@Override
	public List<Restricao> findAll() {
		final List<Restricao> receitas = new ArrayList<Restricao>();

		super.findAll(rs -> {
			final Restricao receita = convertResultSet(rs);
			receitas.add(receita);

		});

		return receitas;
	}

	@Override
	public Restricao findById(Integer id) {
		final Restricao model = new Restricao();

		super.findById((rs) -> {
			final Restricao receita = convertResultSet(rs);

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
	public void saveOrUpdate(Restricao model) {
		final Map<String, String> map = new HashMap<String, String>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());

		super.saveOrUpdate(map);

	}

	@Override
	public List<Restricao> findLike(Restricao model) {
		final Map<String, String> map = new HashMap<String, String>();
		final List<Restricao> receitas = new ArrayList<Restricao>();

		map.put("id", null);

		if (model.getId() != null) {
			map.put("id", model.getId().toString());
		}

		map.put("nome", model.getNome());

		super.findLike(map, rs -> {
			final Restricao receita = convertResultSet(rs);
			receitas.add(receita);
		});

		return receitas;
	}

	@Override
	public Restricao convertResultSet(ResultSet resultSet) {
		final Restricao model = new Restricao();
		try {
			model.setId(resultSet.getInt("id"));
			model.setNome(resultSet.getString("nome"));
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

	public static void main(String[] args) {
		final RestricaoDAO u = new RestricaoDAO();

		u.findAll().forEach(l -> System.out.println(l.toString()));
	}
}
