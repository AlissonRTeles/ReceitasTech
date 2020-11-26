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


public class Receita extends JFrame implements ActionListener {
	
	
	private JButton bVolta;
	private JPanel fundo, botoes, campos;
	
	private JTextField tIngredientes;
	
	
	private void init() {
		
		this.setTitle("Receita");
		this.setSize(500, 200);
		
		
		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);
		
		campos = new JPanel(new GridLayout(4,2));
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());
		
		campos.add(new JLabel("Receita:"));
		
		botoes.add(bVolta);
		
		fundo.add(campos,BorderLayout.CENTER);
		fundo.add(botoes,BorderLayout.SOUTH);

		this.getContentPane().add(fundo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
		this.setVisible(true);
		
	}
	
	public Receita () {
		this.init();
	}
	
	public static void main(String[] args) {
		
		Receita pg6 = new Receita();
		//pg6.init();
		
	}
	
	private void acaoVoltar() {
		
		new Pesquisa().setVisible(true);
		this.dispose();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(bVolta)) {
			this.acaoVoltar();
		}  
		
	}
	
}
