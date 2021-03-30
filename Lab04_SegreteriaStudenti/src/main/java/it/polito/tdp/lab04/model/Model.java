package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model 
{
	CorsoDAO corsoDao = new CorsoDAO();
	
	/**
	 * @return i nomi di tutti i corsi 
	 */
	public Collection<String> getNomeTuttiCorsi()
	{
		List<String> lista = new ArrayList<>();
		for (Corso c : corsoDao.getTuttiICorsi())
			lista.add(c.getNome());
		return lista;
	}
}
