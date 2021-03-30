package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model 
{
	CorsoDAO crsDao = new CorsoDAO();
	StudenteDAO stDao = new StudenteDAO();
	
	/**
	 * @return tutti gli oggetti {@code Corso} del db
	 */
	public Collection<Corso> getTuttiCorsi()
	{
		return crsDao.getTuttiICorsi();
	}
	/**
	 * @return una lista di stringhe contenente tutti i nomi dei corsi 
	 */
	public Collection<String> getNomeTuttiCorsi()
	{
		List<String> lista = new ArrayList<>();
		for (Corso c : crsDao.getTuttiICorsi())
			lista.add(c.getNome());
		return lista;
	}
	
	/**
	 * passato il @param codiceIns
	 * @return del {@code Corso} relativo
	 */
	public Corso getCorsoFromCodins(String codiceIns)
	{
		return crsDao.getCorsoFromCodins(codiceIns);
	}
	
	/**
	 * passato il @param nomeCorso
	 * @return del {@code Corso} relativo
	 */
	public Corso getCorsoFromName(String nomeCorso)
	{
		return crsDao.getCorsoFromName(nomeCorso);
	}
	
	/**
	 * @param {@code Corso} passato per cercare gli studenti iscritti al relativo corso
	 * @return {@code Collection} di studenti trovati
	 */
	public Collection<Studente> getStudentiIscrittiCorso(String nomeCorso)
	{
		Corso corso = this.getCorsoFromName(nomeCorso);
		if (corso != null)
			System.out.println(crsDao.getStudentiIscrittiAlCorso(corso));
			return crsDao.getStudentiIscrittiAlCorso(corso);
	}
	
	public String stampaStudentiIscrittiCorso(String codCorso)
	{
		String s = "";
		for (Studente studente : this.getStudentiIscrittiCorso(codCorso))
			s += String.format("%-10s %-20s %-20s %-10s\n",studente.getMatricola(),studente.getNome(),studente.getCognome(),studente.getCds());
		return s;
	}
	
	
	//******************STUDENTE*******************\\
	
	public Studente getStudente(int matricola)
	{
		return stDao.getStudente(matricola);
	}
}
