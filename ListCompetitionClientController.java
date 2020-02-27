/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.velo.Entitie.Competition;
import com.velo.Service.ServiceCompetition;
import com.velo.Service.ServiceParticipant;
import com.velo.Service.ServiceSortie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ListCompetitionClientController implements Initializable {

    @FXML
    private JFXButton btn_event;
    @FXML
    private JFXButton btn_home;
    @FXML
    private Label name;
    @FXML
    private Pane pn_request;
    @FXML
    private TableView<Competition> table;
    @FXML
    private TableColumn<?, ?> cl_name;
    @FXML
    private TableColumn<?, ?> cl_date;
    @FXML
    private TableColumn<?, ?> cl_time;
    @FXML
    private TableColumn<?, ?> cl_circuit;
    @FXML
    private TableColumn<?, ?> cl_length;
    @FXML
    private TableColumn<?, ?> cl_prize;
    @FXML
    private JFXButton btn_participate;
    @FXML
    private JFXTextField tf_filter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Competition> listu  = FXCollections.observableArrayList();
     try {
        
         ServiceCompetition sc = new ServiceCompetition();
         for(Competition c: sc.readAll()) {
             listu.add(c);
         }
     
     } catch (SQLException ex) {
         Logger.getLogger(ServiceSortie.class.getName()).log(Level.SEVERE, null, ex);
     }

         //mettre les données dans la table view:    
         cl_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
         cl_date.setCellValueFactory(new PropertyValueFactory<>("date"));
         cl_time.setCellValueFactory(new PropertyValueFactory<>("heure"));
         cl_circuit.setCellValueFactory(new PropertyValueFactory<>("circuit"));
         cl_length.setCellValueFactory(new PropertyValueFactory<>("long_circuit"));
         cl_prize.setCellValueFactory(new PropertyValueFactory<>("recompense"));
         
        //load dummy data
        table.setItems(listu);
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("ChoixEventClient.fxml"));
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
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("HomeClient.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addParticipant(ActionEvent event) throws SQLException {
         ObservableList<Competition> SelectedRows, allpeople;
     
     allpeople = table.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =table.getSelectionModel().getSelectedItems();
     
     for(Competition c:SelectedRows){
       allpeople.add(c);
         ServiceParticipant sp = new ServiceParticipant();
       sp.ajouterParticipant(c);
    }
    }

    @FXML
    
         private void chercher(ActionEvent event) {
        String text = tf_filter.getText();
              ObservableList<Competition> listu  = FXCollections.observableArrayList();
     try {
        
         ServiceCompetition sc = new ServiceCompetition();
         for(Competition c: sc.RechercheCompetition(text))
             listu.add(c);
            table.setItems(listu); 
     
     } catch (SQLException ex) {
         Logger.getLogger(ListCompetitionController.class.getName()).log(Level.SEVERE, null, ex);
     }   
    
    }
    }
    
