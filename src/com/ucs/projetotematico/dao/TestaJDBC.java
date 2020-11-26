package com.ucs.projetotematico.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaJDBC {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://lallah.db.elephantsql.com:5432/veidolmf";
		String user = "veidolmf";
		String pwd = "fcDDoltznIIvtZlHrdGr9zU_CoD9klQF";
		
			try {
			
			//Busca no banco
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println(conn);
			
			System.out.println("Buscando os dados da tabela :\n");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from receita");

			while (rs.next()) {
				System.out.print(rs.getString("nome"));
				System.out.print(" ");
				
			}
		
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Não foi possível encontrar o driver JDBC");
		} catch (SQLException se) {
			System.out.println("Não foi possível conectar ao Banco de Dados");
			se.printStackTrace();
		} finally {
			try {
				
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} 
	}	
}
