package com.ucs.projetotematico.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ucs.projetotematico.entity.Usuario;

public class UsuarioDB {
	
	private Connection conn;
	private static UsuarioDB instancia;
	
	
	private UsuarioDB() {
		this.openConnection();
	}

	public static UsuarioDB getInstancia() {
		
		if(instancia==null) {
			instancia = new UsuarioDB();
		}
	
		return instancia;
		
	}

	private void openConnection() {
		
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/Proj1"; //colocar dados do banco receitasTech
		String user = "postgres";
		String pwd = "postgres";
		
		try {
			
			Class.forName(driver);
			this.conn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Driver JDBC não encontrado");
		} catch (SQLException se) {
			System.out.println("Falha na conexão " + se.getMessage());
		}
		
		
	}
	//Cadastro de Usuario (nome/senha)
	
	//Busca de Usuario
	public Usuario buscaUsuarioPorNome(int id) {
		
		Usuario u = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt= conn.prepareStatement("select nome from usuario where nome = ?");
			pstmt.setInt(1, id); //fazer a logica
			rs = pstmt.executeQuery();
			while(rs.next()) {
				u = new Usuario();
				u.setNomeUsuario(rs.getString("nome")); //pegar o setter do nome
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return u;
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
}
