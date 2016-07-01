/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author user
 */
public class Promotion {
   Integer id;
   String nom;

    public Promotion(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Integer getId_promotion() {
        return id;
    }

    public void setId_promotion(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
   
   
}
