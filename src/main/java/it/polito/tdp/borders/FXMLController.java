
package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.ChoiceBox;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="comboBox"
	private ChoiceBox<Country> comboBox; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doCalcolaConfini(ActionEvent event) {
		txtResult.clear();
		int anno;

		try {
			String s = txtAnno.getText();
			anno = Integer.parseInt(s);
		}catch(NumberFormatException nfe) {
			txtResult.setText("Inserire un valore numerico!");
			return;
		}

		if(anno<1816 && anno>2016) {
			txtResult.setText("L'anno deve essere compreso fra 1816 e 2016.");
			return;
		}else {

			model.creaGrafo(anno);
			txtResult.appendText("Grafo creato con " + model.numVertici()+ " vertici e " +model.numArchi()+ " archi!\n");

			List<Country> vertici = model.getVertici();

			comboBox.getItems().addAll(vertici);

			txtResult.appendText(String.format("Numero di componenti connesse: %d\n", model.getComponentiConnesse()));

			Map<Country, Integer> nazioni = model.getNazioni();
			for(Country c: nazioni.keySet()) {
				txtResult.appendText(c.toString() + "\t" + nazioni.get(c)+"\n");
			}
		}
	}


	@FXML
	void doStatiRaggiungibili(ActionEvent event) {

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
		assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
	}
}
