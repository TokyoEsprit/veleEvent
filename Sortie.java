/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velo.Entitie;

import java.time.LocalDate;

/**
 *
 * @author dell
 */
public class Sortie {
    private int sort_id;
    private String titre;
    private String description;
    private java.sql.Date date;
    private String heure;
    private String circuit;
    private String organisateur;
    private String guide;
    private int nbr_participant;
    
    
    public Sortie(){
    
    }
    
    public Sortie(int sort_id, String titre, String description, java.sql.Date date,
            String heure, String circuit, String organisateur,
            String guide, int nbr_participant) {
        this.sort_id = sort_id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.heure = heure;
        this.circuit = circuit;
        this.organisateur = organisateur;
        this.guide = guide;
        this.nbr_participant = nbr_participant;
        
    }

    public Sortie(String titre, String description, java.sql.Date date, String heure,
            String circuit, String organisateur, String guide ) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.heure = heure;
        this.circuit = circuit;
        this.organisateur = organisateur;
        this.guide = guide;

    }

    
    public int getNbr_participant() {
        return nbr_participant;
    }

    public void setNbr_participant(int nbr_participant) {
        this.nbr_participant = nbr_participant;
    }

    public int getSort_id() {
        return sort_id;
    }

    public void setSort_id(int sort_id) {
        this.sort_id = sort_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }


    public String getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }


    @Override
    public String toString() {
        return "Sortie{" + "id=" + sort_id + ", titre=" + titre + ", description=" 
                + description + ", date=" + date + ", heure=" + heure + ", circuit=" 
                + circuit +  ", organisateur=" + organisateur + ", guide=" + guide +", nbr participant="+ nbr_participant+  '}';
    }

}
