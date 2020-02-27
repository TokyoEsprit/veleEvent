/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.Service;

import com.velo.Entitie.Sortie;
import com.velo.IService.IService;
import com.velo.Utils.DataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author dell
 */
public class ServiceSortie implements IService<Sortie> {

    private Connection con;
    private Statement ste;

    public ServiceSortie() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouter(Sortie s) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `velotn`.`sortie` ( `titre`,"
                + " `description`, `date`, `heure`, `circuit`, "
                + " `organisateur`, `guide`)"
                + " VALUES ('" + s.getTitre()+ "', '" + s.getDescription()+
                "', '" + s.getDate()+ "', '" + s.getHeure()+
                "', '" + s.getCircuit()+  "', '"
                + s.getOrganisateur()+ "', '" + s.getGuide()+ "');";
        ste.executeUpdate(requeteInsert);
    }
//    public void ajouter1(Sortie n) throws SQLException
//    {
//    PreparedStatement pre=con.prepareStatement("INSERT INTO `velotn`.`sortie` "
//            + "( `titre`, `description`, `date`, `heure`, `circuit`,  "
//            + "`organisateur`, `guide` ) VALUES ( ?, ?, ?, ?, ?, ?, ?);");
//    pre.setString(1, n.getTitre());
//    pre.setString(2, n.getDescription());
//    pre.setString(3, n.getDate());
//    pre.setString(4, n.getHeure());
//    pre.setString(5, n.getCircuit());
//    pre.setString(6, n.getOragnisateur());
//    pre.setString(7, n.getGuide());
//    pre.executeUpdate();
//    }
            

    public boolean delete(Sortie s) throws SQLException {
        
        String sql = "DELETE FROM sortie WHERE sort_id=?";
 
PreparedStatement statement = con.prepareStatement(sql);
statement.setInt(1, s.getSort_id());
 
int rowsDeleted = statement.executeUpdate();
if (rowsDeleted > 0) {
    System.out.println("A user was deleted successfully!");
}
return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(int sort_id, String titre, String description, java.sql.Date date,
            String heure, String circuit, String organisateur,
            String guide)  {
       try {
     String query ="UPDATE Produit SET titre='"+titre+"',description='"+description+"',date='"+date+"',heure='"+heure+"',circuit='"+circuit+"', organisateur="+organisateur+"', guide="+guide+" WHERE idVelo='"+sort_id+"'";
     Statement st=con.createStatement();
      st.executeUpdate(query) ;
      System.out.println("Client bien modifié");
} catch (SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
    


    }
    public List<Sortie> readAll() throws SQLException {
    List<Sortie> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from sortie");
     while (rs.next()) {                
               int sort_id=rs.getInt("sort_id");
               String titre=rs.getString("titre");
               String description=rs.getString("description");
               java.sql.Date date=rs.getDate("date");
               String heure=rs.getString("heure");
               String circuit=rs.getString("circuit");
               String organisateur=rs.getString("organisateur");
               String guide=rs.getString("guide");
               int nbr_participant=rs.getInt("nbr_participant");
               Sortie s=new Sortie(sort_id, titre, description, date, heure, circuit, organisateur, guide, nbr_participant);
     arr.add(s);
     }
    return arr;
    }

    @Override
    public boolean update(Sortie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
       public boolean validField(String field) throws SQLException {
            if((field.length()>=0)){return true;}
        
           
          return false;}
     
       
public List<Sortie> RechercheSortie(String titre) throws SQLException{

     List<Sortie> listrecherche = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM sortie WHERE titre='"+titre+"'";
Statement stt=con.createStatement() ; 
ResultSet rs=stt.executeQuery(req);
            while(rs.next())
            {    
            Sortie s = new Sortie();
          s.setTitre(rs.getString(2));
     s.setDescription(rs.getString(3));
     s.setHeure(rs.getString(5));
       s.setCircuit(rs.getString(6));
     s.setOrganisateur(rs.getString(7));
     s.setGuide(rs.getString(8));
         
                listrecherche.add(s);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSortie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrecherche;
    }
}

