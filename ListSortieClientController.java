/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.velo.Entitie.Sortie;
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
public class ListSortieClientController implements Initializable {

    @FXML
    private JFXButton btn_event;
    @FXML
    private JFXButton btn_home;
    @FXML
    private Label name;
    @FXML
    private Pane pn_request;
    @FXML
    private TableView<Sortie> table;
    @FXML
    private TableColumn<?, ?> cl_title;
    @FXML
    private TableColumn<?, ?> cl_date;
    @FXML
    private TableColumn<?, ?> cl_time;
    @FXML
    private TableColumn<?, ?> cl_circuit;
    @FXML
    private JFXButton btn_participate;
    @FXML
    private JFXTextField tf_filter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Sortie> listu  = FXCollections.observableArrayList();
     try {
        
         ServiceSortie ss = new ServiceSortie();
         for(Sortie s: ss.readAll()) {
             listu.add(s);
         }
     
     } catch (SQLException ex) {
         Logger.getLogger(ServiceSortie.class.getName()).log(Level.SEVERE, null, ex);
     }

         //mettre les données dans la table view:    
         cl_title.setCellValueFactory(new PropertyValueFactory<>("titre"));
         cl_date.setCellValueFactory(new PropertyValueFactory<>("date"));
         cl_time.setCellValueFactory(new PropertyValueFactory<>("heure"));
         cl_circuit.setCellValueFactory(new PropertyValueFactory<>("circuit"));
         
        //load dummy data
        table.setItems(listu);
        
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
        
        ObservableList<Sortie> SelectedRows, allpeople;
     
     allpeople = table.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =table.getSelectionModel().getSelectedItems();
     
     for(Sortie s:SelectedRows){
//       allpeople.add(s);
         ServiceParticipant sp = new ServiceParticipant();
       sp.ajouterParticipant2(s);
    }
    }

    @FXML
    
         private void chercher(ActionEvent event) {
        String text = tf_filter.getText();
              ObservableList<Sortie> listu  = FXCollections.observableArrayList();
     try {
        
         ServiceSortie ss = new ServiceSortie();
         for(Sortie s: ss.RechercheSortie(text))
             listu.add(s);
            table.setItems(listu); 
     
     } catch (SQLException ex) {
         Logger.getLogger(ListSortieController.class.getName()).log(Level.SEVERE, null, ex);
     }   
    }
        
    }
    

