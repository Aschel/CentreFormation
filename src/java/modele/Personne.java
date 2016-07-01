/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Personne {

    Integer id;
    String nom;
    String prenom;
    String email;
    String password;

    public Personne(Integer id_personne, String nom, String prenom, String email, String login, String password) {
        this.id = id_personne;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Personne(Integer id_personne, String email, String password) {
        this.id = id_personne;
        this.email = email;
        this.password = password;
    }

    public Integer getId_personne() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId_personne(Integer id_personne) {
        this.id = id_personne;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return @throws SQLException
     */
    public static Personne getByLoginPassword(String login, String password)
            throws SQLException {
        Personne result = null;
        if ((login.trim().equals("") == false) && (password.trim().equals("") == false)) {
            Connection connection = Database.getConnection();
            String sql = "SELECT * FROM personne WHERE login = ? and password = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = new Personne(rs.getInt("id_personne"), rs.getString("login"), rs.getString("password"));
            }
            rs.close();
            stmt.close();
            connection.close();
            return result;
        }
        return null;
    }
}
