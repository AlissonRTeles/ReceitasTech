package com.ucs.projetotematico.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ucs.projetotematico.dao.UsuarioDAO;
import com.ucs.projetotematico.entity.Restricao;
import com.ucs.projetotematico.entity.Usuario;

public class CadastroView extends JFrame implements ActionListener {

	private JButton bCadastra, bVolta, bLimpa;
	private JPanel fundo, botoes, campos;

	private JTextField tUsuario, tSenha, tConfirma, tRestricao;

	private Usuario usuario;

	private void init() {

		this.setTitle("Cadastro");
		this.setSize(500, 200);

		tUsuario = new JTextField("");
		tSenha = new JTextField("");
		tConfirma = new JTextField("");
		tRestricao = new JTextField("");

		bCadastra = new JButton("Cadastrar");
		bCadastra.addActionListener(this);
		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);
		bLimpa = new JButton("Limpar tudo");
		bLimpa.addActionListener(this);

		campos = new JPanel(new GridLayout(4, 2));
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());

		campos.add(new JLabel("Usu�rio:"));
		campos.add(tUsuario);
		campos.add(new JLabel("Senha:"));
		campos.add(tSenha);
		campos.add(new JLabel("Confirmar senha:"));
		campos.add(tConfirma);
		campos.add(new JLabel("Restri��o:"));
		campos.add(tRestricao);

		botoes.add(bCadastra);
		botoes.add(bVolta);
		botoes.add(bLimpa);

		fundo.add(campos, BorderLayout.CENTER);
		fundo.add(botoes, BorderLayout.SOUTH);

		this.getContentPane().add(fundo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setVisible(true);

	}

	public CadastroView() {
		this.usuario = new Usuario();

		this.init();
	}

	public static void main(String[] args) {

		final CadastroView pg3 = new CadastroView();
		// pg3.init();

	}

	private void acaoLimpar() {
		tUsuario.setText("");
		tSenha.setText("");
		tConfirma.setText("");
		tRestricao.setText("");
	}

	private void acaoVoltar() {

		new InicialView().setVisible(true);
		this.dispose();
	}

	private void acaoCadastra() {
		final Restricao restricao = new Restricao();
		restricao.setId(1);

		this.usuario.setNome(tUsuario.getText());
		this.usuario.setSenha(tSenha.getText());
		this.usuario.setRestricao(restricao);

		if (validaSenha(usuario.getSenha(), tConfirma.getText())) {
			final UsuarioDAO dao = new UsuarioDAO();

			dao.saveOrUpdate(usuario);

			JOptionPane.showMessageDialog(this, "usuário cadastrado com sucesso!");
			dao.closeConnection();

			new NavegaView().setVisible(true);
			this.dispose();

		} else {
			JOptionPane.showMessageDialog(this, "a senha deve ser confirmada!");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bCadastra)) {
			this.acaoCadastra();
		} else if (e.getSource().equals(bVolta)) {
			this.acaoVoltar();
		} else if (e.getSource().equals(bLimpa)) {
			this.acaoLimpar();
		}

	}

	public boolean validaSenha(final String senha, final String confirmaSenha) {
		return senha.equals(confirmaSenha);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
