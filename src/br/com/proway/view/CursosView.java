package br.com.proway.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.proway.bean.CursosBean;
import br.com.proway.dao.CursosDao;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CursosView extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeCurso;
	private JTextField txtValorCurso;
	private JTable tblListarCursos;
	private JScrollPane scrollPane;

	public CursosView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNomeCurso = new JLabel("Nome do curso");
		lblNomeCurso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeCurso.setBounds(44, 44, 124, 27);
		contentPane.add(lblNomeCurso);

		JLabel lblValorCurso = new JLabel("Valor do curso");
		lblValorCurso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValorCurso.setBounds(44, 93, 124, 27);
		contentPane.add(lblValorCurso);

		txtNomeCurso = new JTextField();
		txtNomeCurso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNomeCurso.setBounds(178, 44, 182, 33);
		contentPane.add(txtNomeCurso);
		txtNomeCurso.setColumns(10);

		txtValorCurso = new JTextField();
		txtValorCurso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtValorCurso.setColumns(10);
		txtValorCurso.setBounds(178, 93, 182, 33);
		contentPane.add(txtValorCurso);

		JButton btnCadastrarCurso = new JButton("Cadastrar Curso");
		btnCadastrarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Obter dados informados
				CursosBean cb = new CursosBean();
				cb.setNomeCurso(txtNomeCurso.getText());
				cb.setValorCurso(Double.parseDouble(txtValorCurso.getText()));

				// Executar envio dos dados
				CursosDao cd = new CursosDao();
				cd.cadastrarCurso(cb);

				// Limpar campos
				txtNomeCurso.setText("");
				txtValorCurso.setText("");

				// Cursor no campo nome curso
				txtNomeCurso.requestFocus();

			}
		});
		btnCadastrarCurso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastrarCurso.setBounds(121, 142, 158, 33);
		contentPane.add(btnCadastrarCurso);

		// Objecto da classe CursosDao
		CursosDao cd = new CursosDao();

		scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 201, 356, 178);
		contentPane.add(scrollPane);

		tblListarCursos = new JTable();
		tblListarCursos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				// Obter a linha selecionada
				int linhaSelecionada = tblListarCursos.getSelectedRow();
				int idCurso = (int) tblListarCursos.getValueAt(linhaSelecionada, 0);

				// Fechar esse formulário
				dispose();

				// Instanciar outro formulário
				AlterarCursosView acv = new AlterarCursosView(idCurso);
				acv.setVisible(true);
			}
		});

		scrollPane.setViewportView(tblListarCursos);
		tblListarCursos.setModel(cd.listarCursos());
	}
}
