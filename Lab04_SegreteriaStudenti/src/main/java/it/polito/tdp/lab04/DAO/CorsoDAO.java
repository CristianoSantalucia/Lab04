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

public class CorsoDAO
{
	/**
	 * @return tutti i corsi salvati nel db
	 */
	public Collection<Corso> getTuttiICorsi()
	{
		List<Corso> corsi = new LinkedList<Corso>();

		final String sql = "SELECT * FROM corso ";

		try
		{
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
//			st.setInt(1, periodo);
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

	/**
	 * Dato un codice insegnamento, @return il corso relativo
	 * @param codiceIns
	 */
	public Corso getCorsoFromName(String nomeCorso)
	{
		final String sql = "SELECT * FROM corso as c WHERE c.nome = ?";

		try
		{
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, nomeCorso);
			
			ResultSet rs = st.executeQuery();

			Corso corso = null;
			
			while (rs.next())
			{

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd"); 

				corso = new Corso(codins, numeroCrediti, nome, periodoDidattico);
			}

			rs.close();
			st.close();
			conn.close();

			return corso;

		} catch (SQLException e)
		{
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	/**
	 * Dato un codice insegnamento, @return il corso relativo
	 * @param codiceIns
	 */
	public Corso getCorsoFromCodins(String codiceIns)
	{
		final String sql = "SELECT * FROM corso as c WHERE c.codins = ?";

		try
		{
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codiceIns);
			
			ResultSet rs = st.executeQuery();

			Corso corso = null;
			
			while (rs.next())
			{

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd"); 

				corso = new Corso(codins, numeroCrediti, nome, periodoDidattico);
			}

			rs.close();
			st.close();
			conn.close();

			return corso;

		} catch (SQLException e)
		{
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/**
	 * @return una {@code List} di studenti iscritti al corso passato come parametro
	 * @param {@code Corso}
	 */
	public Collection<Studente> getStudentiIscrittiAlCorso(Corso corso)
	{
		List<Studente> studenti = new ArrayList<>();

		final String sql = "SELECT s.matricola,s.cognome,s.nome,s.cds "
				+ "FROM corso AS c, iscrizione AS i, studente AS s "
				+ "WHERE c.codins = i.codins "
				+ "		AND s.matricola = i.matricola "
				+ "		AND c.codins = ?";

		try
		{
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, corso.getCodins());
			
			ResultSet rs = st.executeQuery();

			while (rs.next())
			{
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("cds");

				Studente studente = new Studente(matricola, cognome, nome, cds);
				studenti.add(studente);
			}

			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e)
		{
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}

		return studenti;
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso)
	{
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
