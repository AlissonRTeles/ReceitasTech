package com.ucs.projetotematico.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.ucs.projetotematico.entity.ModelAbstract;

public abstract class ModelDao<M extends ModelAbstract> implements ModelDaoInterface<M> {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	private M model;

	public Connection openConnection() {

		Connection connection = null;

		final Properties prop = loadFile();

		final String driver = prop.getProperty("driver");
		final String url = prop.getProperty("url");
		final String user = prop.getProperty("user");
		final String pwd = prop.getProperty("pwd");

		try {

			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, pwd);
		} catch (final ClassNotFoundException cnfe) {
			System.out.println("Driver JDBC não encontrado");
		} catch (final SQLException se) {
			System.out.println("Falha na conexão " + se.getMessage());
		}

		return connection;
	}

	public void findAll(Consumer<ResultSet> action) {

		try {
			setStmt(conn.createStatement());
			setRs(getStmt().executeQuery("select * from " + getModel().getTableName()));

			while (getRs().next()) {

				action.accept(getRs());
			}

		} catch (final SQLException se) {
			System.out.println("Não foi possível conectar ao Banco de Dados");
			se.printStackTrace();
		}

	}

	public void findById(Consumer<ResultSet> action, Integer id) {
		try {
			final String sql = "select * from " + getModel().getTableName() + " where id=?";
			final PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, id);

			setRs(prepareStatement.executeQuery());

			while (getRs().next()) {

				action.accept(getRs());
			}

		} catch (final SQLException se) {
			System.out.println("Não foi possível conectar ao Banco de Dados");
			se.printStackTrace();
		}
	}

	@Override
	public boolean remove(Integer id) {
		try {
			final String sql = "delete from " + getModel().getTableName() + " where id=?";
			final PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			return prepareStatement.execute();
		} catch (final SQLException se) {
			System.out.println("Não foi possível conectar ao Banco de Dados");
			se.printStackTrace();
		}

		return false;
	}

	public void saveOrUpdate(Map<String, String> map) {
		final String idString = map.get("id");
		if (idString != null) {
			final Integer id = Integer.valueOf(idString);
			update(map, id);
		} else {
			save(map);
		}

	}

	private void save(Map<String, String> map) {
		String sql = "insert into " + getModel().getTableName();
		map.remove("id");

		sql = sql.concat(" ( ");
		sql = sql.concat(map.keySet().stream().collect(Collectors.joining(",")));
		sql = sql.concat(" ) ");

		sql = sql.concat(" values ");

		sql = sql.concat(" ( ");
		sql = sql.concat(map.values().stream().collect(Collectors.joining("','", "'", "'")));
		sql = sql.concat(" ) ");

		try {

			final PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.executeUpdate();
		} catch (final SQLException se) {
			System.out.println("Não foi possível conectar ao Banco de Dados");
			se.printStackTrace();
		}
	}

	public void findLike(Map<String, String> map, Consumer<ResultSet> action) {
		String sql = "select * from " + getModel().getTableName();

		map.remove("id");

		sql = sql.concat(" where ");
		final Set<String> keySet = map.keySet();

		for (final String k : map.keySet()) {
			if (map.get(k) != null) {
				sql = sql.concat(k + " like '%" + map.get(k) + "%' and");
			}
		}

		sql = sql.concat(" id is not null");

		try {

			final PreparedStatement prepareStatement = conn.prepareStatement(sql);
			setRs(prepareStatement.executeQuery());

			while (getRs().next()) {
				action.accept(getRs());
			}

		} catch (final SQLException se) {
			System.out.println("Não foi possível conectar ao Banco de Dados");
			se.printStackTrace();
		}
	}

	public void findByInt(Map<String, Integer> map, Consumer<ResultSet> action) {
		String sql = "select * from " + getModel().getTableName();
		sql = sql.concat(" where ");
		final Set<String> keySet = map.keySet();

		for (final String k : map.keySet()) {
			if (map.get(k) != null) {
				sql = sql.concat(k + "=" + map.get(k) + " and");
			}
		}

		sql = sql.concat(" id is not null");

		try {

			final PreparedStatement prepareStatement = conn.prepareStatement(sql);
			setRs(prepareStatement.executeQuery());

			while (getRs().next()) {
				action.accept(getRs());
			}

		} catch (final SQLException se) {
			System.out.println("Não foi possível conectar ao Banco de Dados");
			se.printStackTrace();
		}
	}

	public void findByString(Map<String, String> map, Consumer<ResultSet> action) {
		String sql = "select * from " + getModel().getTableName();
		sql = sql.concat(" where ");

		final Set<String> keySet = map.keySet();

		for (final String k : map.keySet()) {
			if (map.get(k) != null) {
				sql = sql.concat(k + "='" + map.get(k) + "' and ");
			}
		}

		sql = sql.concat(" id is not null");

		try {

			final PreparedStatement prepareStatement = conn.prepareStatement(sql);
			setRs(prepareStatement.executeQuery());

			while (getRs().next()) {
				action.accept(getRs());
			}

		} catch (final SQLException se) {
			System.out.println("Não foi possível conectar ao Banco de Dados");
			se.printStackTrace();
		}
	}

	private void update(Map<String, String> map, Integer id) {

	}

	public void closeConnection() {
		try {
			if (this.conn != null) {
				this.conn.close();
			}
		} catch (final SQLException se) {
			se.printStackTrace();
		}
	}

	public Properties loadFile() {
		final Properties prop = new Properties();
		try (InputStream input = new FileInputStream("connection.properties")) {
			prop.load(input);
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public M getModel() {
		return model;
	}

	public void setModel(M model) {
		this.model = model;
	}

}
