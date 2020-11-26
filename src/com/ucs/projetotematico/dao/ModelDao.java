package com.ucs.projetotematico.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.ucs.projetotematico.entity.ModelAbstract;
import com.ucs.projetotematico.entity.Receita;

public abstract class ModelDao<M extends ModelAbstract> implements ModelDaoInterface<M>{
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private M model;
	
	public Connection openConnection() {
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://lallah.db.elephantsql.com:5432/veidolmf";
		String user = "veidolmf";
		String pwd = "fcDDoltznIIvtZlHrdGr9zU_CoD9klQF";

		try {

			Class.forName(driver);
			this.conn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Driver JDBC não encontrado");
		} catch (SQLException se) {
			System.out.println("Falha na conexão " + se.getMessage());
		}
		
		return conn;
	}
	
	public void findAll(Consumer<ResultSet> action){
		
		try {
			setStmt(conn.createStatement());
			setRs(getStmt().executeQuery("select * from " + getModel().getTableName()));

			while (getRs().next()) {
							
				action.accept(getRs());
			}
			
		} catch (SQLException se) {
			System.out.println("Não foi possível conectar ao Banco de Dados");
			se.printStackTrace();
		}

	}
	
	public void findById(Consumer<ResultSet> action,Integer id) {
		try {
			String sql = "select * from " + getModel().getTableName()+" where id=?";
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			
			setRs(prepareStatement.executeQuery());
			
			while (getRs().next()) {
							
				action.accept(getRs());
			}
			
		} catch (SQLException se) {
			System.out.println("Não foi possível conectar ao Banco de Dados");
			se.printStackTrace();
		}
	}
	
	public void remove(Integer id) {
		try {
			String sql = "delete from " + getModel().getTableName()+" where id=?";
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			prepareStatement.execute();
		} catch (SQLException se) {
			System.out.println("Não foi possível conectar ao Banco de Dados");
			se.printStackTrace();
		}
	}
	
	public void saveOrUpdate(Map<String, String> map) {
		
	}
	
	
	public void closeConnection() {
		try {
			if(this.conn != null) {
				this.conn.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
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
