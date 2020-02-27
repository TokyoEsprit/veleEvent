/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.Test;

import com.velo.Entitie.Competition;
import com.velo.Entitie.Sortie;
import com.velo.Service.ServiceCompetition;
import com.velo.Service.ServiceSortie;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dell
 */
public class Test {
    
    public static void main(String[] args) {
        ServiceCompetition com = new ServiceCompetition();
        ServiceSortie sor = new ServiceSortie();
        
//        Competition c1 = new Competition("Ejri", "circuit panoramique", "10/02/2020", "10h00", "bizerte", 10, "cab", "voyage", 50);
//        Sortie s2 = new Sortie("ejri", "panoramique", 15/02/2020, "9h00", "benzart", "aiesec", "dali");
        
        try {
//         
//            com.ajouter(c1);
//            sor.ajouter(s2);
            List<Competition> list = com.readAll();
            List<Sortie> list2 = sor.readAll();
            System.out.println(list);
            System.out.println(list2);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}