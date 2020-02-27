/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.velo.Entitie.Competition;
import com.velo.Entitie.Sortie;
import com.velo.Service.ServiceCompetition;
import com.velo.Service.ServiceSortie;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AddCompetitionController implements Initializable {

    @FXML
    private JFXButton btn_event;
    @FXML
    private JFXButton btn_home;
    @FXML
    private Label name;
    @FXML
    private Pane pn_elect;
    @FXML
    private DatePicker dp_date;
    @FXML
    private TextField tf_prize;
    @FXML
    private TextArea tx_desc;
    @FXML
    private JFXButton btn_valid;
    @FXML
    private ComboBox<String> cb_time;
    @FXML
    private JFXButton btn_back;
    @FXML
    private TextField tf_org;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_circuit;
    @FXML
    private TextField tf_length;
    @FXML
    private TextField tf_limit;
    
    ObservableList<String> time_list = FXCollections.observableArrayList("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00");


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         cb_time.setValue("08:00");
      cb_time.setValue("09:00");
      cb_time.setValue("10:00");
      cb_time.setValue("11:00");
      cb_time.setValue("12:00");
      cb_time.setValue("13:00");
      cb_time.setValue("14:00"); 
      cb_time.setValue("15:00");
      cb_time.setValue("16:00");
      cb_time.setItems(time_list);
        
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("ChoixEvent.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goHome(ActionEvent event) {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addCompetition(ActionEvent event) throws SQLException {
        String nom = tf_name.getText();
        String description = tx_desc.getText();
        LocalDate date = dp_date.getValue();
        java.sql.Date datee = Date.valueOf(date);
        String heure = cb_time.getValue();
        String circuit = tf_circuit.getText();
        String longueur = tf_length.getText();
        int longu = Integer.parseInt(longueur.trim());
        String prix = tf_prize.getText();
        String organisateur = tf_org.getText();
        String max = tf_limit.getText();
        int maximum = Integer.parseInt(max.trim());
        Competition c = new Competition(nom, description, datee, heure, circuit, longu, organisateur, heure, maximum);
        ServiceCompetition sc = new ServiceCompetition();
        sc.ajouter(c);
        try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("ListCompetition.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void back(ActionEvent event) {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("ListCompetition.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
