package com.ucs.projetotematico.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Cadastro extends JFrame implements ActionListener {
	
	
	private JButton bCadastra, bVolta, bLimpa;
	private JPanel fundo, botoes, campos;
	
	private JTextField tUsuario, tSenha, tConfirma, tRestricao;
	
	
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
		
		campos = new JPanel(new GridLayout(4,2));
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());
		
		campos.add(new JLabel("Usuário:"));
		campos.add(tUsuario);
		campos.add(new JLabel("Senha:"));
		campos.add(tSenha);
		campos.add(new JLabel("Confirmar senha:"));
		campos.add(tConfirma);
		campos.add(new JLabel("Restrição:"));
		campos.add(tRestricao);
		
		botoes.add(bCadastra);
		botoes.add(bVolta);
		botoes.add(bLimpa);
		
		fundo.add(campos,BorderLayout.CENTER);
		fundo.add(botoes,BorderLayout.SOUTH);

		this.getContentPane().add(fundo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
		this.setVisible(true);
		
	}
	
	public Cadastro () {
		this.init();
	}
	
	public static void main(String[] args) {
		
		Cadastro pg3 = new Cadastro();
		//pg3.init();
		
	}
	
	private void acaoLimpar() {
		tUsuario.setText("");
		tSenha.setText("");
		tConfirma.setText("");
		tRestricao.setText("");
	}
	
	private void acaoVoltar() {
		
		new Inicial().setVisible(true);
		this.dispose();
	}
	
	private void acaoCadastra() {
		
		new Navega().setVisible(true);
		this.dispose();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(bCadastra)) {
			this.acaoCadastra();
		} else if(e.getSource().equals(bVolta)) {
			this.acaoVoltar();
		} else if(e.getSource().equals(bLimpa)) {
			this.acaoLimpar();
		}
		
	}
	
}
