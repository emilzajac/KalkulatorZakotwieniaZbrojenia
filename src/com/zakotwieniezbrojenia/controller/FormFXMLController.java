/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakotwieniezbrojenia.controller;

import com.zakotwieniezbrojenia.model.Beton;
import com.zakotwieniezbrojenia.model.Zakotwienie;
import com.zakotwieniezbrojenia.model.PretZbrojeniowy;
import com.zakotwieniezbrojenia.model.Zaklad;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author emilzz
 */
public class FormFXMLController implements Initializable {

    ObservableList<String> klasaBetonuList = FXCollections.observableArrayList(
            "B15", "B20", "B25", "B30", "B37", "B45", "B50", "B55", "B60");

    ObservableList<String> klasaStaliList = FXCollections.observableArrayList(
            "A-0", "A-I", "A-II", "A-III", "A-IIIN");

    ObservableList<Integer> srednicaPretowList = FXCollections.observableArrayList(
            6, 8, 10, 12, 14, 16, 18, 20, 22, 25, 28, 32, 36);

    @FXML
    private Label wynik_lb;
    @FXML
    private Label wynik_lbd;
    @FXML
    private Label wynik_lb_min;

    @FXML
    private ComboBox klasaBetonuComboBox;
    @FXML
    private ComboBox klasaStaliComboBox;
    @FXML
    private ComboBox srednicaPretaComboBox;

    @FXML
    private RadioButton pretProstyRadioButton;
    @FXML
    private RadioButton pretZagietyRadioButton;

    @FXML
    private RadioButton pretSciskanyRadioButton;
    @FXML
    private RadioButton pretRozciaganyRadioButton;

    @FXML
    private RadioButton pretGladkiRadioButton;
    @FXML
    private RadioButton pretZebrowanyRadioButton;

    @FXML
    private CheckBox dobreWarunkiPrzyczepnosciCheckBox;
    @FXML
    private CheckBox obciazenieWielokrotnieZmienneCheckBox;
    @FXML
    private CheckBox pretyGrupowaneParamiCheckBox;

    @FXML
    private TextField zbrojenieWymaganeTextField;
    @FXML
    private TextField zbrojeniePrzyjeteTextField;
    
    
    @FXML
    private Label wynik_ls;
    
    @FXML
    private TextField wartosc_a_TextField1;
    @FXML
    private TextField wartosc_b_TextField1;
    
    @FXML
    private CheckBox MniejNiz30ProcentPretowCheckBox1;

    @FXML
    private void obliczActionMouseEvent(MouseEvent event) {
        try {
            oblicz();
        } catch (Exception e) {
            alert();
        }      
    }

    @FXML
    private void obliczActionEvent(ActionEvent event) {
        try {
            oblicz();
        } catch (Exception e) {
            alert();
        }
    }

    @FXML
    private void obliczKeyEvent(KeyEvent event) {
        try {
            oblicz();
        } catch (Exception e) {
        }
    }

    @FXML
    private void zamknijAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void infoAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zakotwieniezbrojenia/view/HelpFXML.fxml"));
            Parent rootl = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("O programie");
            stage.setScene(new Scene(rootl));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        klasaBetonuComboBox.setItems(klasaBetonuList);
        klasaBetonuComboBox.setValue("B15");
        klasaStaliComboBox.setItems(klasaStaliList);
        klasaStaliComboBox.setValue("A-0");
        srednicaPretaComboBox.setItems(srednicaPretowList);
        pretZagietyRadioButton.setTooltip(new Tooltip("Jeżeli w strefie haka lub pętli grubość otulenia betonem \n"
                + "w kierunku prostopadlym do płaszczyzny zagięcia wynosi co najmniej 3\u00F8"));
    }

    public void alert() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Ostrzeżenie");
        alert.setContentText("Proszę uzupełnić wszystkie pola!");
        alert.showAndWait();
    }

    public void oblicz() throws Exception {
        PretZbrojeniowy p = new PretZbrojeniowy(
                (int) srednicaPretaComboBox.getValue(),
                (String) klasaStaliComboBox.getValue());

        Beton beton = new Beton((String) klasaBetonuComboBox.getValue());

        Zakotwienie dlugoscZakotwienia = new Zakotwienie(
                dobreWarunkiPrzyczepnosciCheckBox.isSelected(),
                pretZebrowanyRadioButton.isSelected(),
                obciazenieWielokrotnieZmienneCheckBox.isSelected(),
                pretProstyRadioButton.isSelected(),
                pretRozciaganyRadioButton.isSelected(),
                pretyGrupowaneParamiCheckBox.isSelected(),
                beton.getKlasa(),
                p.getSrednica(),
                p.getGranicaPlastycznosci_fyd(),
                //Zamiana na kropkę gdy użytkownik wpisze przecinek
                Double.valueOf(zbrojeniePrzyjeteTextField.getText().replace(',', '.')),
                Double.valueOf(zbrojenieWymaganeTextField.getText().replace(',', '.')));
        
        Zaklad zaklad = new Zaklad(
                MniejNiz30ProcentPretowCheckBox1.isSelected(), 
                Double.valueOf(wartosc_a_TextField1.getText().replace(',', '.')),
                Double.valueOf(wartosc_b_TextField1.getText().replace(',', '.')),
                pretRozciaganyRadioButton.isSelected(), 
                p.getSrednica() / 10);
        
        System.out.println("alfa_1: " + zaklad.alfa_1());
       
        DecimalFormat df = new DecimalFormat(".#");
        String lb = String.valueOf(df.format(dlugoscZakotwienia.podstawowaDlugoscZakotwienia_lb() / 10));
        String lbd = String.valueOf(df.format(dlugoscZakotwienia.obliczeniowaDlugoscZakotwienia_l_bd() / 10));
        String lb_min = String.valueOf(df.format(dlugoscZakotwienia.minimalnaDlugoscZakotwienia_lb_min() / 10));
        
        String ls = String.valueOf(df.format(
                zaklad.dlugoscZakladu_ls(
                dlugoscZakotwienia.obliczeniowaDlugoscZakotwienia_l_bd(),
                dlugoscZakotwienia.podstawowaDlugoscZakotwienia_lb(),
                dlugoscZakotwienia.alfa_A()) / 10));

        wynik_lb.setText(lb + " cm");
        wynik_lbd.setText(lbd + " cm");
        wynik_lb_min.setText(lb_min + " cm");
        
        wynik_ls.setText(ls + " cm");
    }
}
