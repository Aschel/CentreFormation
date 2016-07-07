/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
            result = new Projet(id, rs.getInt("id_promotion"), rs.getInt("id_createur"), rs.getString("sujet"), rs.getString("titre"), rs.getDate("date_creation"), rs.getDate("date_limite"));
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
        //assert nom != null && !nom.matches("/^ \t\n\r$");
        Connection connection = Database.getConnection();
        // Commencer une transaction
        connection.setAutoCommit(false);
        try {
            // Inserer le projet
            String sql = "INSERT INTO projet(sujet, titre) VALUES(?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, sujet);
            stmt.setString(2, titre);
            stmt.executeUpdate();
            stmt.close();
            // Recuperer le id
            Statement maxStmt = connection.createStatement();
            ResultSet rs = maxStmt.executeQuery("SELECT MAX(no_produit) AS id FROM produit");
            rs.next();
            id = rs.getInt("id");
            rs.close();
            maxStmt.close();
            // Valider
            connection.commit();
        } catch (SQLException exc) {
            connection.rollback();
            exc.printStackTrace();
            throw exc;
        } finally {
            connection.close();
        }

    }
