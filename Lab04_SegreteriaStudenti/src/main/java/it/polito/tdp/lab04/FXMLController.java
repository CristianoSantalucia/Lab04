package it.polito.tdp.lab04;

import java.net.URL;
import java.util.*;

import it.polito.tdp.lab04.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController
{
	private final String ERRORE_INPUT_CORSO = "CODICE CORSO NON CORRETTO (O INESISTENTE)";
	private final String ERRORE_INPUT_MATRICOLA = "CODICE MATRICOLA NON CORRETTO (O INESISTENTE)\n"
													+ "-> CLICCA SU \"ISCRIVI SE VUOI AGGIUNGERE UNO STUDENTE AL DATABASE\"";
	
	private Model model;

	@FXML private ResourceBundle resources;
	@FXML private URL location; //
	@FXML private ComboBox<Corso> comboBoxCorsi;
	@FXML private TextField txtInputMatricola;
	@FXML private TextField txtNome;
	@FXML private TextField txtCognome;
	@FXML private TextArea txtResult;

	/***************CORSI*****************/
	
	@FXML void selectCorso(ActionEvent event)
	{
		if (comboBoxCorsi.getValue() != null)
			txtResult.setText("SELEZIONATO CORSO DI: " + comboBoxCorsi.getValue().getNome() + "\n\n");
		else txtResult.setText(ERRORE_INPUT_CORSO);
	}
	@FXML void doCercaIscrittiCorso(ActionEvent event)
	{
		Collection<Studente> listaStudenti = model.getStudentiIscrittiCorso(comboBoxCorsi.getValue());
		
		if(listaStudenti != null)
			txtResult.setText("ELENCO ISCITTI:\n" + this.stampaIscritti(listaStudenti));
		else txtResult.setText(ERRORE_INPUT_CORSO);
	}
	private StringBuilder stampaIscritti(Collection<Studente> lista)
	{
		StringBuilder sb = new StringBuilder();
		for (Studente studente : lista)
			sb.append(String.format("%-10s %-25s %-25s %-10s\n",
					studente.getMatricola(),
					studente.getNome(),
					studente.getCognome(),
					studente.getCds()));
		return sb;
	}
	
	/***************STUDENTI***************/
	
	@FXML void doCompleteName(ActionEvent event)
	{
		Studente s = this.getStudente();
		if (s != null)
		{
			txtNome.setText(s.getNome());
			txtCognome.setText(s.getCognome());
		} 
	}
	@FXML void doCercaCorsi(ActionEvent event)
	{
		Studente s = this.getStudente();
		if (s != null)
		{
			List<Corso> corsi = new ArrayList<Corso>(model.getIscrizioniStudente(s)); 
			txtResult.setText("ELENCO ISCITTI:\n" + this.stampaCorsiStudente(corsi));
		}
	}
	private Studente getStudente()
	{
		try
		{
			Integer codiceMatricola = Integer.parseInt(txtInputMatricola.getText());
			Studente s = model.getStudente(new Studente(codiceMatricola, null, null, null));
			if (s != null)
				return s;
			else
			{
				txtResult.setText(ERRORE_INPUT_MATRICOLA);
				return null;
			}
		}
		catch (Exception e)
		{
			txtResult.setText(ERRORE_INPUT_MATRICOLA);
			return null;
		}
	}
	private StringBuilder stampaCorsiStudente(Collection<Corso> lista)
	{
		StringBuilder sb = new StringBuilder();
		for (Corso corso : lista)
			sb.append(String.format("%-10s %-45s %-5s %-5s\n",
					corso.getCodins(),
					corso.getNome(),
					corso.getNumeroCrediti(),
					corso.getPeriodoDidattico()));
		return sb;
	}
	@FXML void doIscrivi(ActionEvent event)
	{

	}
	@FXML void doMatricolaCodins(ActionEvent event)
	{

	}
	/****************RESET**********************/
	
	@FXML void doReset(ActionEvent event)
	{
		txtResult.clear();
		txtInputMatricola.clear();
		txtNome.clear();
		txtCognome.clear();
		comboBoxCorsi.setValue(null);
	}
	/*******************************************/
	@FXML void initialize()
	{
		assert comboBoxCorsi != null : "fx:id=\"comboBoxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtInputMatricola != null : "fx:id=\"txtInputMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
	}
	public void setModel(Model model)
	{
		assert model != null : "MODEL PASSATO COME PARAMETRO NULLO";
		this.model = model;
		
		txtResult.setStyle("-fx-font-family: monospace");

		comboBoxCorsi.getItems().add(new Corso(null, 0, " ", 0));
		comboBoxCorsi.getItems().addAll(model.getTuttiCorsi());
	}
}
