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


public class Navega extends JFrame implements ActionListener {
	
	
	private JButton bPesquisa, bLimpa;
	private JPanel fundo, botoes, campos;
	
	private JTextField tIngredientes;
	
	
	private void init() {
		
		this.setTitle("Pesquisa");
		this.setSize(500, 200);
		
		tIngredientes = new JTextField("");
		
		bPesquisa = new JButton("Pesquisar");
		bPesquisa.addActionListener(this);
		bLimpa = new JButton("Limpar");
		bLimpa.addActionListener(this);
		
		campos = new JPanel(new GridLayout(4,2));
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());
		
		campos.add(new JLabel("Digite os ingredientes:"));
		campos.add(tIngredientes);
		
		botoes.add(bPesquisa);
		botoes.add(bLimpa);
		
		fundo.add(campos,BorderLayout.CENTER);
		fundo.add(botoes,BorderLayout.SOUTH);

		this.getContentPane().add(fundo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
		this.setVisible(true);
		
	}
	
	public Navega () {
		this.init();
	}
	
	public static void main(String[] args) {
		
		Navega pg4 = new Navega();
		//pg4.init();
		
	}
	
	private void acaoLimpar() {
		tIngredientes.setText("");
	}
	
	private void acaoPesquisar() {
		new Pesquisa().setVisible(true);
		this.dispose();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(bPesquisa)) {
			this.acaoPesquisar();
		}  else if(e.getSource().equals(bLimpa)) {
			this.acaoLimpar();
		}
		
	}
	
}
