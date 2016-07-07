/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class Projet {

    private int id;
    private int idPromotion;
    private int idCreateur;
    private String sujet;
    private String titre;
    private Date dateCreation;
    private Date dateLimite;

    public Projet(int id, int idPromotion, int idCreateur, String sujet, String titre, Date dateCreation, Date dateLimite) {
        this.id = id;
        this.idPromotion = idPromotion;
        this.idCreateur = idCreateur;
        this.sujet = sujet;
        this.titre = titre;
        this.dateCreation = dateCreation;
        this.dateLimite = dateLimite;
    }

    public static Projet getById(int id) throws SQLException {
        Projet result = null;
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM produit WHERE id_projet=" + id);
        if (rs.next()) {
            result = new Projet(id, rs.getInt("no_produit"), rs.getString("nom"), rs.getDouble("prix"));
        }
        rs.close();
        stmt.close();
        connection.close();
        return result;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    public int getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(int idCreateur) {
        this.idCreateur = idCreateur;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public void insert() throws SQLException {

    }

    
}
