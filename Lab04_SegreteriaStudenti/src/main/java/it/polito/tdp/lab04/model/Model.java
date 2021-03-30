package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model 
{
	CorsoDAO corsoDao = new CorsoDAO();
	
	/**
	 * @return tutti gli oggetti {@code Corso} del db
	 */
	public Collection<Corso> getTuttiCorsi()
	{
		return corsoDao.getTuttiICorsi();
	}
	/**
	 * @return una lista di stringhe contenente tutti i nomi dei corsi 
	 */
	public Collection<String> getNomeTuttiCorsi()
	{
		List<String> lista = new ArrayList<>();
		for (Corso c : corsoDao.getTuttiICorsi())
			lista.add(c.getNome());
		return lista;
	}
	
	/**
	 * passato il @param codiceIns
	 * @return del {@code Corso} relativo
	 */
	public Corso getCorso(String codiceIns)
	{
		return corsoDao.getCorso(codiceIns);
	}
}
