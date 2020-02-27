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
import com.velo.Entitie.Sortie;
import com.velo.Service.ServiceSortie;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ListSortieController implements Initializable {

    @FXML
    private JFXButton btn_event;
    @FXML
    private Label name;
    @FXML
    private Pane pn_request;
    @FXML
    private TableView<Sortie> table;
    @FXML
    private TableColumn<?, ?> cl_date;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private JFXButton btn_edit;
    @FXML
    private JFXTextField tf_filter;
    @FXML
    private JFXButton btn_add;
    @FXML
    private JFXButton btn_home;
    @FXML
    private TableColumn<?, ?> cl_title;
    @FXML
    private TableColumn<?, ?> cl_time;
    @FXML
    private TableColumn<?, ?> cl_circuit;
    @FXML
    private JFXButton btn_pdf;
     



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
        
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
    private void deleteSortie(ActionEvent event) throws SQLException {
         ObservableList<Sortie> SelectedRows, allpeople;
     
     allpeople = table.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =table.getSelectionModel().getSelectedItems();
     
     for(Sortie s:SelectedRows){
       allpeople.remove(s);
       ServiceSortie ss = new ServiceSortie();
       ss.delete(s);
    }
    }
    
    

    @FXML
    private void openEditSortie(ActionEvent event) throws IOException {
         Sortie s = table.getSelectionModel().getSelectedItem();
     FXMLLoader loader = new FXMLLoader(getClass().getResource("EditSortie.fxml"));
            Parent root = loader.load();
           EditSortieController scene2Controller = loader.getController();
               
            //Get controller of scene2
            //Pass whatever data you want. You can have multiple method calls here
//                        scene2Controller.setSort_id(s.getSort_id()+"");
              scene2Controller.setTitre(s.getTitre());
              scene2Controller.setDescription(s.getDescription());
//               scene2Controller.setDate(s.getDate()); 
              scene2Controller.setHeure(s.getHeure()); 
              scene2Controller.setCircuit(s.getCircuit()); 
              scene2Controller.setOrgnisateur(s.getOrganisateur());
              scene2Controller.setGuide(s.getGuide());

                Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Hello World");
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void addSortie(ActionEvent event) {
        try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AddSortie.fxml"));
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
   
//     @FXML
//    void impression(ActionEvent event) {
//        System.out.println("To Printer!");
//         PrinterJob job = PrinterJob.createPrinterJob();
//           if(job != null){
//    Window primaryStage = null;
//           job.showPrintDialog(primaryStage); 
//            
//    Node root = this.listeView;
//           job.printPage(root);
//           job.endJob();
//
//    }
//    }
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
        PdfWriter.getInstance(doc, new FileOutputStream(path+"ListeSortie.pdf"));
        doc.open();
        PdfPTable tbl = new PdfPTable(4);
        
        tbl.addCell("Hangout Title");
        tbl.addCell("Date");
        tbl.addCell("Time");
        tbl.addCell("Circuit");
        
        doc.add(tbl);
        doc.close();

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
        
      

