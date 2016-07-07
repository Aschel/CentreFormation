/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Projet;
import org.omg.CORBA.AnySeqHelper;

/**
 *
 * @author Utilisateur
 */
@WebServlet(name = "ProjetServlet", urlPatterns = {"/projet"})
public class ProjetServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sId = request.getParameter("id");
        try {
            int id = Integer.parseInt(sId);
            Projet projet = Projet.getById(id);
            request.setAttribute("projet", projet);            
        }
        catch (SQLException ex){
            System.out.println("Erreur SQL");
            request.setAttribute("message", ex.getMessage());
        }
        catch (NumberFormatException ex){
            System.out.println("Erreur Format");
            request.setAttribute("message", "L'Id doit être un entier");
        }
        request.getRequestDispatcher("WEB-INF/projet.jsp").forward(request, response);
    }
}
