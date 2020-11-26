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
import java.util.function.Consumer;

import com.ucs.projetotematico.entity.ModelAbstract;

public abstract class ModelDao<M extends ModelAbstract> implements ModelDaoInterface<M> {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	private M model;

	public Connection openConnection() {
		final Properties prop = loadFile();

		final String driver = prop.getProperty("driver");
		final String url = prop.getProperty("url");
		final String user = prop.getProperty("user");
		final String pwd = prop.getProperty("pwd");

		try {

			Class.forName(driver);
			this.conn = DriverManager.getConnection(url, user, pwd);
		} catch (final ClassNotFoundException cnfe) {
			System.out.println("Driver JDBC não encontrado");
		} catch (final SQLException se) {
			System.out.println("Falha na conexão " + se.getMessage());
		}

		return conn;
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
	public void remove(Integer id) {
		try {
			final String sql = "delete from " + getModel().getTableName() + " where id=?";
			final PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			prepareStatement.execute();
		} catch (final SQLException se) {
			System.out.println("Não foi possível conectar ao Banco de Dados");
			se.printStackTrace();
		}
	}

	public void saveOrUpdate(Map<String, String> map) {

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
