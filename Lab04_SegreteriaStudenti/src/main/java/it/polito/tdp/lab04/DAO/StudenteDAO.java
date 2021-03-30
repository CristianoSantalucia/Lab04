package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO
{
	/**
	 * @return un oggetto {@code Studente} la quale matricola è passata come parametro per la ricerca
	 * @param codice matricola
	 */
	public Studente getStudente(int codMatricola)
	{
		final String sql = 
				"SELECT * "
				+ "FROM studente as s "
				+ "WHERE s.matricola = ?";

		try
		{
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codMatricola);
			
			ResultSet rs = st.executeQuery();
			
			Studente studente = null;

			while (rs.next())
			{
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("cds");

				studente = new Studente(matricola, cognome, nome, cds);
			}

			rs.close();
			st.close();
			conn.close();

			return studente;

		} catch (SQLException e)
		{
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
}
