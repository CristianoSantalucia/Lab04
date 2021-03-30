package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController
{
	Model model;
	
	@FXML private ResourceBundle resources;
	@FXML private URL location;
	@FXML private ComboBox<String> comboBoxCorsi;
	@FXML private TextArea txtResult;

	@FXML
	void doCercaCorsi(ActionEvent event)
	{

	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event)
	{

	}

	@FXML
	void doCompeteName(ActionEvent event) //complete
	{

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

	}

	@FXML
	void selectCorso(ActionEvent event)
	{

	}

	@FXML
	void initialize()
	{
		assert comboBoxCorsi != null : "fx:id=\"comboBoxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
	}

	public void setModel(Model model)
	{
		this.model = model;
		
		comboBoxCorsi.getItems().addAll(model.getNomeTuttiCorsi());
	}
}
