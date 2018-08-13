package br.com.proway.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.CursosBean;
import br.com.proway.dao.CursosDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlterarCursosView extends JFrame {

	private JPanel contentPane;
	private JTextField txtValorCurso;
	private JTextField txtNomeCurso;
	private JTextField txtIdCurso;

	
	public AlterarCursosView(int idCurso) {
		
		//Objeto
		CursosDao cd = new CursosDao();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Nome do curso");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(50, 60, 124, 27);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Valor do curso");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(50, 109, 124, 27);
		contentPane.add(label_1);
		
		JButton btnAlterarCurso = new JButton("Alterar Curso");
		btnAlterarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Instanciar objeto da classe CursosBean
				CursosBean cb = new CursosBean();
				cb.setIdCurso(idCurso);
				cb.setNomeCurso(txtNomeCurso.getText());
				cb.setValorCurso(Double.parseDouble(txtValorCurso.getText()));
				
				//Mensagem
				JOptionPane.showMessageDialog(null, "Curso alterado com sucesso!");
				
				//Chamar método para atualizar a tabela
				cd.alterarCurso(cb);
				
				//Fechar JFrame
				dispose();
				
				//Abrir novo JFrame
				CursosView cv = new CursosView();
				cv.setVisible(true);
				
			}
		});
		
		btnAlterarCurso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAlterarCurso.setBounds(10, 167, 135, 33);
		contentPane.add(btnAlterarCurso);
		
		CursosBean cb = new CursosBean();
		cb = cd.obterNomeValorCurso(idCurso);
		
		txtValorCurso = new JTextField(String.valueOf(cb.getValorCurso()));
		txtValorCurso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtValorCurso.setColumns(10);
		txtValorCurso.setBounds(184, 109, 204, 33);
		contentPane.add(txtValorCurso);
		
		txtNomeCurso = new JTextField(cb.getNomeCurso());
		txtNomeCurso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNomeCurso.setColumns(10);
		txtNomeCurso.setBounds(184, 60, 204, 33);
		contentPane.add(txtNomeCurso);
		
		JLabel label_2 = new JLabel("Nome do curso");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(50, 11, 124, 27);
		contentPane.add(label_2);
		
		txtIdCurso = new JTextField();
		txtIdCurso.setEditable(false);
		txtIdCurso.setText(String.valueOf(idCurso));
		txtIdCurso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtIdCurso.setColumns(10);
		txtIdCurso.setBounds(184, 11, 204, 33);
		contentPane.add(txtIdCurso);
		
		JButton btnExcluirCurso = new JButton("Excluir Curso");
		btnExcluirCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Verifica se realmente quer excluir
				int verifica = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "", 0);
				
				//Condicional
				if(verifica == 0){
					JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
					
					//Executar a ação de exclusão
					new CursosDao().excluirCurso(idCurso);
					
				}else{
					JOptionPane.showMessageDialog(null, "Ação cancelada.");
				}
				
				//Fecha o formulário e instancia o CursosView
				dispose();
				CursosView cv = new CursosView();
				cv.setVisible(true);
				
			}
		});
		btnExcluirCurso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExcluirCurso.setBounds(168, 167, 135, 33);
		contentPane.add(btnExcluirCurso);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Fechar formulário
				dispose();
				
				//Abrir o formulário de cursos
				CursosView cv = new CursosView();
				cv.setVisible(true);
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancelar.setBounds(329, 167, 135, 33);
		contentPane.add(btnCancelar);
	}
}
