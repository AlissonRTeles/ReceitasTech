package com.ucs.projetotematico.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucs.projetotematico.entity.Receita;

public class ReceitaIngredienteDB extends ModelDao<Receita> {

	private ReceitaIngredienteDB() {

		super.setConn(super.openConnection());
		super.setModel(new Receita());
	}

	@Override
	public List<Receita> findAll() {
		final List<Receita> receitas = new ArrayList<Receita>();

		super.findAll(rs -> {
			final Receita receita = new Receita();

			try {

				receita.setId(rs.getInt("id"));
				receita.setDescricao(rs.getString("descricao"));
				receita.setNome(rs.getString("nome"));

			} catch (final SQLException e) {
				e.printStackTrace();
			}
			receitas.add(receita);

		});

		return receitas;
	}

	@Override
	public Receita findById(Integer id) {
		final Receita receita = new Receita();

		super.findById(rs -> {
			try {
				receita.setId(rs.getInt("id"));
				receita.setNome(rs.getString("nome"));
				receita.setDescricao(rs.getString("descricao"));

			} catch (final SQLException e) {
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
	public Receita findLike(Receita m) {
		super.findLike(map);
		return null;
	}

	public static void main(String[] args) {
		final ReceitaIngredienteDB t = new ReceitaIngredienteDB();

		final Receita receita = new Receita();
		receita.setNome("alisson");
		receita.setDescricao("descricao");

	}
}
