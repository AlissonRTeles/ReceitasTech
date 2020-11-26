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


public class Inicial extends JFrame implements ActionListener {
	
	
	private JButton bLoga, bCadastra;
	private JPanel fundo, botoes, campos;
	
	
	private void init() {
		
		this.setTitle("Login");
		this.setSize(280, 80);
		
		bLoga = new JButton("Login");
		bLoga.addActionListener(this);
		bCadastra = new JButton("Cadastrar");
		bCadastra.addActionListener(this);
		
		campos = new JPanel(new GridLayout(4,2));
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());

		botoes.add(bLoga);
		botoes.add(bCadastra);
		
		fundo.add(botoes,BorderLayout.CENTER);

		this.getContentPane().add(fundo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
		
	}
	
	public Inicial () {
		this.init();
	}
	
	
	public static void main(String[] args) {
		
		Inicial pg1 = new Inicial();
		//pg1.init();
		
	}
	
	private void acaoLogar() {
		
		new Login().setVisible(true);
		this.dispose();
	}
	
	private void acaoCadastrar() {
		
		new Cadastro().setVisible(true);
		this.dispose();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(bLoga)) {
			this.acaoLogar();
		} else if(e.getSource().equals(bCadastra)) {
			this.acaoCadastrar();
		}
		
	}
	
}
