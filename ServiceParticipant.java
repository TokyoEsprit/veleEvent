/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.Service;

import com.velo.Entitie.Competition;
import com.velo.Entitie.Sortie;
import com.velo.Utils.DataBase;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dell
 */
public class ServiceParticipant {
    private Connection con;
    private Statement ste;

    public ServiceParticipant() {
        con = DataBase.getInstance().getConnection();

    }

    public int ajouterParticipant(Competition c) throws SQLException {
       
        ste = con.createStatement();
        int i = c.getNbr_participant() +1;
        if (c.getMax_participant() > i) {
        String requeteInsert = "UPDATE  `velotn`.`competition` set nbr_participant='"+i+"' where comp_id=c.getComp_id()" ;
        ste.executeUpdate(requeteInsert);
        return 1;
        }
        else {
            return 0;
        }
        
    }
     public void ajouterParticipant2(Sortie s) throws SQLException {
    
        ste = con.createStatement();
        int i = s.getNbr_participant() +1;
       
        String requeteInsert = "UPDATE  `velotn`.`sortie` set nbr_participant='"+i+"' where sort_id=s.getSort_id()" ;
        ste.executeUpdate(requeteInsert);
        
        }
       
        }
        
    

