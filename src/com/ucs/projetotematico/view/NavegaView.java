package com.ucs.projetotematico.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ucs.projetotematico.dao.IngredienteDAO;
import com.ucs.projetotematico.dao.ReceitaDAO;
import com.ucs.projetotematico.dao.ReceitaIngredienteDAO;
import com.ucs.projetotematico.entity.Ingrediente;
import com.ucs.projetotematico.entity.ReceitaIngrediente;

public class NavegaView extends JFrame implements ActionListener {

	private IngredienteDAO ingredienteDAO;
	private ReceitaIngredienteDAO receitaIngredienteDAO;

	private JButton bPesquisa, bLimpa;
	private JPanel fundo, botoes, campos;

	private JTextField tIngredientes;

	private void init() {

		this.ingredienteDAO = new IngredienteDAO();
		this.receitaIngredienteDAO = new ReceitaIngredienteDAO();
		this.setTitle("Pesquisa");
		this.setSize(500, 200);

		tIngredientes = new JTextField("");

		bPesquisa = new JButton("Pesquisar");
		bPesquisa.addActionListener(this);
		bLimpa = new JButton("Limpar");
		bLimpa.addActionListener(this);

		campos = new JPanel(new GridLayout(4, 2));
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());

		campos.add(new JLabel("Digite os ingredientes com separador ',':"));
		campos.add(tIngredientes);

		botoes.add(bPesquisa);
		botoes.add(bLimpa);

		fundo.add(campos, BorderLayout.CENTER);
		fundo.add(botoes, BorderLayout.SOUTH);

		this.getContentPane().add(fundo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setVisible(true);

	}

	public NavegaView() {
		this.init();
	}

	public static void main(String[] args) {

		final NavegaView pg4 = new NavegaView();
		// pg4.init();

	}

	private void acaoLimpar() {
		tIngredientes.setText("");
	}

	private void acaoPesquisar() {
		final String[] lista = tIngredientes.getText().split(",");
		final List<Ingrediente> listaIgredientes = new ArrayList<Ingrediente>();
		final List<ReceitaIngrediente> listaRI = new ArrayList<ReceitaIngrediente>();

		for (final String ingredienteString : lista) {
			final Ingrediente filter = new Ingrediente();
			filter.setNome(ingredienteString);
			listaIgredientes.addAll(getIngredienteDAO().findLike(filter));
		}

		listaIgredientes.forEach(ingrediente -> {
			final ReceitaIngrediente filter = new ReceitaIngrediente();
			filter.setIngrediente(ingrediente);
			final List<ReceitaIngrediente> filteredItems = getReceitaIngredienteDAO().findLike(filter);

			filteredItems.forEach(fi -> {
				final ReceitaDAO dao = new ReceitaDAO(ingredienteDAO.getConn());
				fi.setReceita(dao.findById(fi.getIdReceita()));
			});

			listaRI.addAll(filteredItems);
		});

		ingredienteDAO.closeConnection();
		receitaIngredienteDAO.closeConnection();

		new PesquisaView(listaRI);
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bPesquisa)) {
			this.acaoPesquisar();
		} else if (e.getSource().equals(bLimpa)) {
			this.acaoLimpar();
		}

	}

	public IngredienteDAO getIngredienteDAO() {
		return ingredienteDAO;
	}

	public void setIngredienteDAO(IngredienteDAO ingredienteDAO) {
		this.ingredienteDAO = ingredienteDAO;
	}

	public ReceitaIngredienteDAO getReceitaIngredienteDAO() {
		return receitaIngredienteDAO;
	}

	public void setReceitaIngredienteDAO(ReceitaIngredienteDAO receitaIngredienteDAO) {
		this.receitaIngredienteDAO = receitaIngredienteDAO;
	}
}
