package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model 
{
	private CorsoDAO crsDao = new CorsoDAO();
	private StudenteDAO stDao = new StudenteDAO();
	
	//*****************CORSO********************\\
	
	/**
	 * @return tutti gli oggetti {@code Corso} del db
	 */
	public Collection<Corso> getTuttiCorsi()
	{
		return crsDao.getTuttiICorsi();
	} 
	/**
	 * @param {@code Corso} passato per cercare gli studenti iscritti al relativo corso
	 * @return {@code Collection} di studenti trovati
	 */
	public Collection<Studente> getStudentiIscrittiCorso(Corso corso)
	{
		if (corso != null)
			return crsDao.getStudentiIscrittiAlCorso(corso);
		else return null;
	} 
	
	//******************STUDENTE*******************\\
	
	/**
	 * @return di un oggetto {@code Studente} data la matricola
	 */
	public Studente getStudente(Studente studente)
	{
		return stDao.getStudente(studente);
	}
	/**
	 * @return di una {@code Collection<Corso>} cui lo studente risulta iscritto
	 */
	public Collection<Corso> getIscrizioniStudente(Studente studente)
	{
		if(this.getStudente(studente) != null)
			return stDao.getIscrizioniStudente(studente);
		else return null;
	}
	
	//*****************ISCRIZIONE*********************\\
	
	public boolean iscrivi(Studente studente, Corso corso)
	{
		return (crsDao.inscriviStudenteACorso(studente, corso));
	}
}
