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
import com.velo.Entitie.Sortie;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AddSortieController implements Initializable {

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
    private TextField tf_guide;
    @FXML
    private TextArea tx_desc;
    @FXML
    private ComboBox<String> cb_time;
    @FXML
    private JFXButton btn_back;
    @FXML
    private TextField tf_org;
    @FXML
    private TextField tf_title;
    @FXML
    private TextField tf_circuit;
    @FXML
    private JFXButton btn_valid;
    
    
    ObservableList<String> time_list = FXCollections.observableArrayList("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00");
    @FXML
    private Label title_vide;
    @FXML
    private Label desc_vide;
    @FXML
    private Label date_vide;
    @FXML
    private Label circuit_vide;
    @FXML
    private Label org_vide;
    @FXML
    private Label guide_vide;

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
    private void addSortie(ActionEvent event) throws SQLException {
//        if (tf_title.getText().trim().length() > 0)
//{
//        String titre = tf_title.getText();
//}
//else
//{
//            title_vide.setTextFill(Color.TOMATO);
//            title_vide.setText("Server Error : Check");
//}
        
        String titre = tf_title.getText();
        String description = tx_desc.getText();
        LocalDate date = dp_date.getValue();
        java.sql.Date datee = Date.valueOf(date);
        String heure = cb_time.getValue();
        String circuit = tf_circuit.getText();
        String organisateur = tf_org.getText();
        String guide = tf_guide.getText();
        Sortie s = new Sortie(titre, description, datee, heure, circuit, organisateur,guide);
        ServiceSortie ss = new ServiceSortie();
        
         if(ss.validField(titre)){
                       s.setTitre(tf_title.getText()); }
       
           else {
       Alert alert = new Alert(Alert.AlertType.WARNING);
       alert.setTitle("Alert");
       alert.setHeaderText(null);
       alert.setContentText("field must be fulled!");
       alert.showAndWait();
       } 
       if(ss.validField(description)){
                       s.setDescription(tx_desc.getText()); }
       
           else {
       Alert alert = new Alert(Alert.AlertType.WARNING);
       alert.setTitle("Alert");
       alert.setHeaderText(null);
       alert.setContentText("field must be fulled!");
       alert.showAndWait();
       } 
       if(ss.validField(circuit)){
                       s.setCircuit(tf_circuit.getText()); }
       
           else {
       Alert alert = new Alert(Alert.AlertType.WARNING);
       alert.setTitle("Alert");
       alert.setHeaderText(null);
       alert.setContentText("field must be fulled!");
       alert.showAndWait();
       }
       if(ss.validField(organisateur)){
                       s.setOrganisateur(tf_org.getText()); }
       
           else {
       Alert alert = new Alert(Alert.AlertType.WARNING);
       alert.setTitle("Alert");
       alert.setHeaderText(null);
       alert.setContentText("field must be fulled!");
       alert.showAndWait();
       } 
       if(ss.validField(guide)){
                       s.setGuide(tf_guide.getText()); }
       
           else {
       Alert alert = new Alert(Alert.AlertType.WARNING);
       alert.setTitle("Alert");
       alert.setHeaderText(null);
       alert.setContentText("field must be fulled!");
       alert.showAndWait();
       } 
       
        ss.ajouter(s);
        try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("ListSortie.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        if((ss.validField(titre))&&(ss.validField(description))&&(ss.validField(circuit))&&(ss.validField(organisateur))&&(ss.validField(guide))){
         try{
            ss.ajouter(s);
        try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("ListSortie.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        }catch (SQLException ex){
            System.out.println(ex);
            
        }}
        
        
        
        
    }

    @FXML
    private void back(ActionEvent event) {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("ListSortie.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
