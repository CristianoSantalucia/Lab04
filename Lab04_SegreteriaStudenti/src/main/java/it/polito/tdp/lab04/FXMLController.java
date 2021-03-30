package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController
{
	String MESS_ERRORE = "INSERIRE UN CODICE MATRICOLA CORRETTO (O ESISTENTE)";
	Model model;
	
	@FXML private ResourceBundle resources;
	@FXML private URL location;
	
	@FXML private ComboBox<String> comboBoxCorsi;
	@FXML private TextField txtInputMatricola;
	@FXML private TextField txtNome;
	@FXML private TextField txtCognome;
	@FXML private TextArea txtResult;
	
	@FXML
	void selectCorso(ActionEvent event)
	{
		String nomeCorso = comboBoxCorsi.getValue();
		txtResult.setText("SELEZIONATO CORSO DI: " + nomeCorso + "\n\n");
	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event)
	{
		String nomeCorso = comboBoxCorsi.getValue();
		txtResult.setText(model.stampaStudentiIscrittiCorso(nomeCorso));
	}
	
	@FXML
	void doCercaCorsi(ActionEvent event)
	{
		
	}

	@FXML
	void doCompleteName(ActionEvent event) 
	{
		String matricola = txtInputMatricola.getText();
		Integer codiceMatricola = null;
		try
		{
			codiceMatricola = Integer.parseInt(matricola);
		} 
		catch (Exception e)
		{
			txtResult.setText(MESS_ERRORE);
			return;
		}
		Studente s = model.getStudente(codiceMatricola);
		
		if(s!=null)
		{
			txtNome.setText(s.getNome());
			txtCognome.setText(s.getCognome());
		}
		else 
		{
			txtResult.setText(MESS_ERRORE);
		}
	}

	@FXML
	void doIscrivi(ActionEvent event)
	{
		
	}

	@FXML
	void doMatricolaCodins(ActionEvent event)
	{

	}

	@FXML
	void doReset(ActionEvent event)
	{
		txtResult.clear();
		txtInputMatricola.clear();
		txtNome.clear();
		txtCognome.clear();
	}

	@FXML
	void initialize()
	{
		assert comboBoxCorsi != null : "fx:id=\"comboBoxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtInputMatricola != null : "fx:id=\"txtInputMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
	}

	public void setModel(Model model)
	{
		this.model = model;
		
		comboBoxCorsi.getItems().add(" ");
		comboBoxCorsi.getItems().addAll(model.getNomeTuttiCorsi());
	}
}
