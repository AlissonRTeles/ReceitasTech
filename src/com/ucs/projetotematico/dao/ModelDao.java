package com.ucs.projetotematico.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ucs.projetotematico.entity.ModelAbstract;

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
