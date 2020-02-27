/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.Service;

import com.velo.Entitie.Competition;
import com.velo.Entitie.Sortie;
import com.velo.IService.IService;
import com.velo.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class ServiceCompetition implements IService<Competition> {

    private Connection con;
    private Statement ste;

    public ServiceCompetition() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouter(Competition c) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `velotn`.`competition` ( `nom`,"
                + " `description`, `date`, `heure`, `circuit`, `long_circuit`,"
                + " `organisateur`, `recompense`, `max_participant`)"
                + " VALUES ( '" + c.getNom() + "', '" + c.getDescription()+
                "', '" + c.getDate()+ "', '" + c.getHeure()+
                "', '" + c.getCircuit()+ "', '" + c.getLong_circuit()+ "', '"
                + c.getOrganisateur()+ "', '" + c.getRecompense()+ "', '" +
                c.getMax_participant()+ "');";
        ste.executeUpdate(requeteInsert);
    }
//    public void ajouter1(Competition m) throws SQLException
//    {
//    PreparedStatement pre=con.prepareStatement("INSERT INTO `velotn`.`competition` "
//            + "( `nom`, `description`, `date`, `heure`, `circuit`, `long_circuit`, "
//            + "`organisateur`, `recompense`, `nbr_participant` ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);");
//    pre.setString(1, m.getNom());
//    pre.setString(2, m.getDescription());
//    pre.setString(3, m.getDate());
//    pre.setString(4, m.getHeure());
//    pre.setString(5, m.getCircuit());
//    pre.setInt(6, m.getLong_circuit());
//    pre.setString(7, m.getOragnisateur());
//    pre.setString(8, m.getRecompense());
//    pre.setInt(9, m.getNbr_participant());
//    pre.executeUpdate();
//    }
            

    public boolean delete(Competition c) throws SQLException {
         String sql = "DELETE FROM competition WHERE comp_id=?";
 
PreparedStatement statement = con.prepareStatement(sql);
statement.setInt(1, c.getComp_id());
 
int rowsDeleted = statement.executeUpdate();
if (rowsDeleted > 0) {
    System.out.println("une competition a été supprimée avec succés!");
}
return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   public void update(int comp_id, String nom, String description, java.sql.Date date,
            String heure, String circuit, int long_circuit, String organisateur,
            String recompense, int max_participant)  {
       try {
     String query ="UPDATE Produit SET nom='"+nom+"',description='"+description+"',date='"+date+"',heure='"+heure+"',circuit='"+circuit+"',long_circuit='"+long_circuit+"', organisateur="+organisateur+"', recompense="+recompense+"',max_participant='"+max_participant+" WHERE idVelo='"+comp_id+"'";
     Statement st=con.createStatement();
      st.executeUpdate(query) ;
      System.out.println("Client bien modifié");
} catch (SQLException ex){
            System.out.println(ex.getMessage()) ; 
            
        }
   }
    public List<Competition> readAll() throws SQLException {
    List<Competition> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from competition");
     while (rs.next()) {                
               int comp_id=rs.getInt("comp_id");
               String nom=rs.getString("nom");
               String description=rs.getString("description");
               java.sql.Date date=rs.getDate("date");
               String heure=rs.getString("heure");
               String circuit=rs.getString("circuit");
               int long_circuit=rs.getInt("long_circuit");
               String organisateur=rs.getString("organisateur");
               String recompense=rs.getString("recompense");
               int nbr_participant=rs.getInt("nbr_participant");
               int max_participant=rs.getInt("max_participant");
               Competition C = new Competition(comp_id, nom, description, date, heure, circuit, long_circuit, organisateur, recompense,nbr_participant, max_participant);
     arr.add(C);
     }
    return arr;
    }

    @Override
    public boolean update(Competition t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Competition> RechercheCompetition(String nom) throws SQLException{

     List<Competition> listrecherche = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM competition WHERE nom='"+nom+"'";
Statement stt=con.createStatement() ; 
ResultSet rs=stt.executeQuery(req);
            while(rs.next())
            {    
            Competition c = new Competition();
          c.setNom(rs.getString(2));
     c.setDescription(rs.getString(3));
     c.setHeure(rs.getString(5));
       c.setCircuit(rs.getString(6));
     c.setOrganisateur(rs.getString(7));
     c.setRecompense(rs.getString(8));
         
                listrecherche.add(c);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCompetition.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrecherche;
    }
}
