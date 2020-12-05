package com.ucs.projetotematico.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ucs.projetotematico.entity.Receita;
import com.ucs.projetotematico.entity.ReceitaIngrediente;
import com.ucs.projetotematico.entity.Usuario;

public class ReceitaView extends JFrame implements ActionListener {
	private Receita receita;
	private Usuario usuario;

	private JButton bVolta;
	private JPanel fundo, botoes, campos, camposTextArea;

	private JTextField tIngredientes;
	private JTextArea jTAModoPreparo;
	private JTextArea jTAIgredientes;

	private void init() {

		this.setTitle("Receita");
		this.setSize(500, 700);

		bVolta = new JButton("Voltar");
		bVolta.addActionListener(this);

		campos = new JPanel(new GridLayout(2, 1));
		fundo = new JPanel(new BorderLayout());
		botoes = new JPanel(new FlowLayout());
		jTAModoPreparo = new JTextArea(receita.getModoPreparo(), 50, 10);
		jTAModoPreparo.setEditable(false);

		jTAIgredientes = new JTextArea(getIngredientes(receita.getReceitaIngredientes()));
		jTAIgredientes.setEditable(false);

		campos.add(new JLabel("Receita:"));
		campos.add(new JLabel(receita.getDescricao()));

		camposTextArea = new JPanel(new GridLayout(4, 1));
		camposTextArea.add(new JLabel("Modo Preparo:"));
		camposTextArea.add(new JScrollPane(jTAModoPreparo));

		camposTextArea.add(new JLabel("Ingredientes:"));
		camposTextArea.add(new JScrollPane(jTAIgredientes));

		botoes.add(bVolta);

		fundo.add(campos, BorderLayout.NORTH);
		fundo.add(camposTextArea, BorderLayout.CENTER);
		fundo.add(botoes, BorderLayout.SOUTH);

		this.getContentPane().add(fundo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setVisible(true);

	}

	public ReceitaView() {
		this.init();
	}

	public String getIngredientes(List<ReceitaIngrediente> listaIngredientes) {
		String ingredientes = "";

		for (final ReceitaIngrediente rI : listaIngredientes) {
			ingredientes = ingredientes.concat(rI.getQuantidade() + " " + rI.getUnidade().getTipo() + " - " + rI.getIngrediente().getNome() + "\n");
		}

		return ingredientes;
	}

	public ReceitaView(List<Receita> lista, Integer lineSelected, Usuario usuario) {

		this.receita = lista.get(lineSelected);
		this.usuario = usuario;

		this.init();

	}

	public static void main(String[] args) {

		final ReceitaView pg6 = new ReceitaView();
		// pg6.init();

	}

	private void acaoVoltar() {

		new NavegaView(usuario).setVisible(true);
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bVolta)) {
			this.acaoVoltar();
		}

	}

}
