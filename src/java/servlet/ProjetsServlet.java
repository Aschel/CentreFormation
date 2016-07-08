/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Projet;

/**
 *
 * @author Utilisateur
 */
@WebServlet(name = "ProjetsServlet", urlPatterns = {"/projets"})
public class ProjetsServlet extends HttpServlet {

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
        try {
            List<Projet> projets = Projet.getProjets();
            request.setAttribute("projets", projets);
        }
        catch (SQLException ex){
            request.setAttribute("message", ex.getMessage());
        }
        request.getRequestDispatcher("WEB-INF/projets.jsp").forward(request, response);
    }
}
