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
import java.util.Objects;

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
        ResultSet rs = stmt.executeQuery("SELECT * FROM projet WHERE id_projet=" + id);
        if (rs.next()) {
            result = new Projet(id, rs.getInt("id_promotion"), rs.getInt("id_createur"), rs.getString("sujet"), rs.getString("titre"), rs.getDate("date_creation"), rs.getDate("date_limite"));
        }
        rs.close();
        stmt.close();
        connection.close();
        return result;
    }

    public Projet() {
     //   return Project();
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
        Connection connection = Database.getConnection();
        // Commencer une transaction
        connection.setAutoCommit(false);
        try {
            // Inserer le projet
            String sql = "INSERT INTO projet(id_promotion, id_createur, sujet, titre) VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, Integer.toString(idPromotion));
            stmt.setString(2, Integer.toString(idCreateur));
            stmt.setString(3, sujet);
            stmt.setString(4, titre);
            stmt.executeUpdate();
            stmt.close();
            // Recuperer l'id
            Statement maxStmt = connection.createStatement();
            ResultSet rs = maxStmt.executeQuery("SELECT MAX(id_projet) AS id FROM projet");
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        hash = 97 * hash + this.idPromotion;
        hash = 97 * hash + this.idCreateur;
        hash = 97 * hash + Objects.hashCode(this.sujet);
        hash = 97 * hash + Objects.hashCode(this.titre);
        hash = 97 * hash + Objects.hashCode(this.dateCreation);
        hash = 97 * hash + Objects.hashCode(this.dateLimite);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Projet other = (Projet) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idPromotion != other.idPromotion) {
            return false;
        }
        if (this.idCreateur != other.idCreateur) {
            return false;
        }
        if (!Objects.equals(this.sujet, other.sujet)) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.dateCreation, other.dateCreation)) {
            return false;
        }
        if (!Objects.equals(this.dateLimite, other.dateLimite)) {
            return false;
        }
        return true;
    }
}
