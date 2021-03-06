package com.ucs.projetotematico.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ucs.projetotematico.dao.UsuarioDAO;
import com.ucs.projetotematico.entity.Restricao;
import com.ucs.projetotematico.entity.Usuario;

public class LoginVIew extends JFrame implements ActionListener {
	private final Connection connection;
	private UsuarioDAO usuarioDAO;

	private JButton bEntra, bVolta, bLimpa;
	private JPanel fundo, botoes, campos;

	private JTextField tUsuario, tSenha;

	private void init() {
		this.usuarioDAO = new UsuarioDAO(connection);

		this.setTitle("Login");
		this.setSize(500, 200);

		tUsuario = new JTextField("");
		tSenha = new JTextField("");

		bEntra = new JButton("Entrar");
		bEntra.addActionListener(this);
		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);
		bLimpa = new JButton("Limpar");
		bLimpa.addActionListener(this);

		campos = new JPanel(new GridLayout(4, 2));
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());

		campos.add(new JLabel("Usuário:"));
		campos.add(tUsuario);
		campos.add(new JLabel("Senha:"));
		campos.add(tSenha);

		botoes.add(bEntra);
		botoes.add(bVolta);
		botoes.add(bLimpa);

		fundo.add(campos, BorderLayout.CENTER);
		fundo.add(botoes, BorderLayout.SOUTH);

		this.getContentPane().add(fundo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setVisible(true);

	}

	public LoginVIew(Connection connection) {
		this.connection = connection;
		this.init();
	}

	private void acaoLimpar() {
		tUsuario.setText("");
		tSenha.setText("");
	}

	private void acaoVoltar() {

		new InicialView(connection).setVisible(true);
		this.dispose();
	}

	private void acaoEntrar() {
		final Usuario model = new Usuario();
		model.setNome(tUsuario.getText());
		model.setSenha(tSenha.getText());

		final Usuario find = getUsuarioDAO().find(model);
		if (find.getRestricao() == null || find.getRestricao().getId() == null) {
			
			Restricao restricao = new Restricao();
			restricao.setId(-999);
			find.setRestricao(restricao);;
		}

		if (find.getId() == null) {
			JOptionPane.showMessageDialog(this, "usuário não cadastrado!");
		} else {

			new NavegaView(find, connection).setVisible(true);
			this.dispose();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bEntra)) {
			this.acaoEntrar();
		} else if (e.getSource().equals(bVolta)) {
			this.acaoVoltar();
		} else if (e.getSource().equals(bLimpa)) {
			this.acaoLimpar();
		}

	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}
