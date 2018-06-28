package br.com.proway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.com.proway.bean.CursosBean;
import br.com.proway.connection.ConnectionFactory;

public class CursosDao {

	// Atributo para obter a conex�o
	private Connection conexao;

	// Construtor
	public CursosDao() {
		// Maneiras de se obter uma conex�o
		// ConnectionFactory cf = new ConnectionFactory();
		// this.conexao = cf.obterConexao();

		this.conexao = new ConnectionFactory().obterConexao();
	}

	// M�todo para cadastrar um curso
	public void cadastrarCurso(CursosBean cb) {

		// Comando SQL
		String sql = "INSERT INTO cursos (nomeCurso, valorCurso) VALUES (?, ?)";

		// Tentar realizar o comando SQL
		try {

			// Enviando os par�metros e executando
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setString(1, cb.getNomeCurso());
			pstmt.setDouble(2, cb.getValorCurso());
			pstmt.execute();

			// Fechar a conex�o
			pstmt.close();

		} catch (Exception e) {

			// Caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao inserir os dados");

		}

	}

	// M�todo para selecionar cursos
	public DefaultTableModel listarCursos() {

		// Criando o DefaultTableModel
		DefaultTableModel modelo = new DefaultTableModel();

		// Definir as colunas do DefaultTableModel
		modelo.addColumn("C�digo");
		modelo.addColumn("Curso");
		modelo.addColumn("Valor");

		// Comando SQL
		String sql = "SELECT * FROM cursos";

		// Tentar realizar o comando SQL
		try {

			// Conectar e selecionar o comando SQL
			Statement stmt = conexao.createStatement();

			// Executando comando SQL e obtendo dados
			ResultSet rs = stmt.executeQuery(sql);

			// Listando cursos
			while (rs.next()) {

				modelo.addRow(
						new Object[] { rs.getInt("idCurso"), rs.getString("nomeCurso"), rs.getDouble("valorCurso") });

			}

			// Fechar a conex�o
			stmt.close();

		} catch (Exception e) {

			// Caso haja falhas na sele��o
			JOptionPane.showMessageDialog(null, "Falha ao selecionar os cursos.");

		}

		// Retornar o DefaultTableModel
		return modelo;

	}

	// M�todo para excluir um curso
	public void excluirCurso(int idCurso) {

		// Comando SQL
		String sql = "DELETE FROM cursos WHERE idCurso = ?";

		// Tentar realizar o comando SQL
		try {

			// Enviando os par�metros e executando
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setInt(1, idCurso);
			pstmt.execute();

			// Fechar a conex�o
			pstmt.close();

		} catch (Exception e) {

			// Caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao inserir os dados");

		}

	}

	// M�todo para obter o nome e o valor do curso
	public CursosBean obterNomeValorCurso(int idCurso) {

		// Criando um objeto da classe CursosBean
		CursosBean cb = new CursosBean();

		// Comando SQL
		String sql = "SELECT * FROM cursos WHERE idCurso = ?";

		// Tentar realizar o comando SQL
		try {

			// Enviando os par�metros
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setInt(1, idCurso);

			// Executando e retornando dados
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				cb.setNomeCurso(rs.getString("nomeCurso"));
				cb.setValorCurso(rs.getDouble("valorCurso"));
			}

			// Fechar a conex�o
			pstmt.close();

		} catch (Exception e) {

			// Caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao selecionar os dados" + e.getMessage());

		}

		// Retorno
		return cb;

	}

	// M�todo para alterar o curso
	public void alterarCurso (CursosBean cb) {


		// Comando SQL
		String sql = "UPDATE cursos SET nomeCurso = ?, valorCurso = ? WHERE idCurso = ?";

		// Tentar realizar o comando SQL
		try {

			// Enviando os par�metros			
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setString(1, cb.getNomeCurso());
			pstmt.setDouble(2, cb.getValorCurso());
			pstmt.setInt(3, cb.getIdCurso());

			// Executando a��o
			pstmt.execute();
			
			// Fechar a conex�o
			pstmt.close();

		} catch (Exception e) {

			// Caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao selecionar os dados" + e.getMessage());

		}


	}

}
