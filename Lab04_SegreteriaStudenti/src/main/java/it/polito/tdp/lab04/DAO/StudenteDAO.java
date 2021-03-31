package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO
{
	/**
	 * @return un oggetto {@code Studente} data la matricola
	 */
	public Studente getStudente(Studente studente)
	{
		final String sql = 
				"SELECT * "
				+ "FROM studente as s "
				+ "WHERE s.matricola = ?";

		try
		{
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, studente.getMatricola());
			
			ResultSet rs = st.executeQuery();
			
			Studente stud = null;

			while (rs.next())
			{
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("cds");

				stud = new Studente(matricola, cognome, nome, cds);
			}

			rs.close();
			st.close();
			conn.close();

			return stud;

		} catch (SQLException e)
		{
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	/**
	 * @return di una {@code Collection<Corso>} a cui e' iscritto lo {@code Studente} passato come parametro 
	 */
	public Collection<Corso> getIscrizioniStudente(Studente studente)
	{
		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM corso AS c, iscrizione AS i, studente AS s "
				+ " 	WHERE c.codins = i.codins "
				+ "		AND s.matricola = i.matricola " 
				+ "		AND s.matricola = ?";

		List<Corso> corsi = new ArrayList<>();
		
		try
		{
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, studente.getMatricola());
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next())
			{
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso corso = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(corso);
			}

			rs.close();
			st.close();
			conn.close();

			return corsi;

		} catch (SQLException e)
		{
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
}
