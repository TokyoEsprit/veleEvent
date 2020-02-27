/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.velo.Entitie.Competition;
import com.velo.Entitie.Sortie;
import com.velo.Service.ServiceCompetition;
import com.velo.Service.ServiceSortie;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ListCompetitionController implements Initializable {

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
    private JFXButton btn_delete;
    @FXML
    private JFXButton btn_edit;
    @FXML
    private JFXTextField tf_filter;
    @FXML
    private JFXButton btn_add;
    @FXML
    private JFXButton btn_pdf;

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
    private void deleteCompetition(ActionEvent event) throws SQLException{ 
            
            ObservableList<Competition> SelectedRows, allpeople;
     
     allpeople = table.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =table.getSelectionModel().getSelectedItems();
     
     for(Competition c:SelectedRows){
       allpeople.remove(c);
       ServiceCompetition sc = new ServiceCompetition();
       sc.delete(c);
    }
    }

    @FXML
    private void openEditCompetition(ActionEvent event) throws IOException {
        
          Competition c = table.getSelectionModel().getSelectedItem();
     FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCompetition.fxml"));
            Parent root = loader.load();
           EditCompetitionController scene2Controller = loader.getController();
               
            //Get controller of scene2
            //Pass whatever data you want. You can have multiple method calls here
//                        scene2Controller.setSort_id(s.getSort_id()+"");
              scene2Controller.setNom(c.getNom());
              scene2Controller.setDescription(c.getDescription());
//               scene2Controller.setDate(s.getDate()); 
              scene2Controller.setHeure(c.getHeure()); 
              scene2Controller.setCircuit(c.getCircuit()); 
              scene2Controller.setLong_circuit(Integer.toString(c.getLong_circuit()));
              scene2Controller.setOrgnisateur(c.getOrganisateur());
              scene2Controller.setRecompense(c.getRecompense());
              scene2Controller.setMax_participant(Integer.toString(c.getMax_participant()));

                Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Hello World");
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void addCompetition(ActionEvent event) {
        try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AddCompetition.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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

    @FXML
    
        private void exportPdf(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException {
        String path="";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
            
    Component primaryStage = null;
        int x =  j.showSaveDialog(primaryStage); 
     
        if ( x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(path+"ListeCompetition.pdf"));
        doc.open();
        PdfPTable tbl = new PdfPTable(6);
        
        tbl.addCell("Competition Name");
        tbl.addCell("Date");
        tbl.addCell("Time");
        tbl.addCell("Circuit");
        tbl.addCell("Length");
        tbl.addCell("Prize");
        
        doc.add(tbl);
        doc.close();

    }

    }
    

