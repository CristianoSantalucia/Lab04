package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController
{
	private final String ERRORE_INPUT_CORSO = "INSERIRE UN CODICE MATRICOLA CORRETTO (O ESISTENTE)";
	private final String ERRORE_INPUT_STUDENTE = "INSERIRE UN CODICE MATRICOLA CORRETTO (O ESISTENTE)";
	private Model model;

	@FXML private ResourceBundle resources;
	@FXML private URL location;

	@FXML private ComboBox<Corso> comboBoxCorsi;
	@FXML private TextField txtInputMatricola;
	@FXML private TextField txtNome;
	@FXML private TextField txtCognome;
	@FXML private TextArea txtResult;

	@FXML void selectCorso(ActionEvent event)
	{
		if(comboBoxCorsi.getValue() != null)
			txtResult.setText("SELEZIONATO CORSO DI: " + comboBoxCorsi.getValue().getNome() + "\n\n");
		else txtResult.setText(ERRORE_INPUT_CORSO);
	}

	@FXML void doCercaIscrittiCorso(ActionEvent event)
	{
		txtResult.setText("ELENCO ISCITTI:\n" + model.stampaStudentiIscrittiCorso(comboBoxCorsi.getValue().getNome()));
	}

	@FXML void doCercaCorsi(ActionEvent event)
	{

	}

	@FXML void doCompleteName(ActionEvent event)
	{
		String matricola = txtInputMatricola.getText();
		Integer codiceMatricola = null;
		try
		{
			codiceMatricola = Integer.parseInt(matricola);
		}
		catch (Exception e)
		{
			txtResult.setText(ERRORE_INPUT_STUDENTE);
			return;
		}

		Studente s = model.getStudente(codiceMatricola);

		if (s != null)
		{
			txtNome.setText(s.getNome());
			txtCognome.setText(s.getCognome());
		}
		else txtResult.setText(ERRORE_INPUT_STUDENTE);
	}

	@FXML void doIscrivi(ActionEvent event)
	{

	}

	@FXML void doMatricolaCodins(ActionEvent event)
	{

	}

	@FXML void doReset(ActionEvent event)
	{
		txtResult.clear();
		txtInputMatricola.clear();
		txtNome.clear();
		txtCognome.clear();
		comboBoxCorsi.setValue(null);
	}

	@FXML void initialize()
	{
		assert comboBoxCorsi != null : "fx:id=\"comboBoxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtInputMatricola != null
				: "fx:id=\"txtInputMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
	}

	public void setModel(Model model)
	{
		assert model != null : "MODEL PASSATO COME PARAMETRO NULLO";

		this.model = model;

		comboBoxCorsi.getItems().add(new Corso(null, 0, " ", 0));
		comboBoxCorsi.getItems().addAll(model.getTuttiCorsi());
	}
}
