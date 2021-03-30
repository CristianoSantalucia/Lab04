package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO
{
	/**
	 * @return tutti i corsi salvati nel db
	 */
	public List<Corso> getTuttiICorsi()
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

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codiceIns)
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

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public void getStudentiIscrittiAlCorso(Corso corso)
	{
		// TODO
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
