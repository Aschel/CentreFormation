/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Personne;
import org.apache.taglibs.standard.functions.Functions;

/**
 *
 * @author Utilisateur
 */
@WebServlet(name = "ConnectionServlet", urlPatterns = {"/connection"})
public class ConnectionServlet extends HttpServlet {

    private static final String VUE_FORM = "WEB-INF/connection.jsp";
    private static final String VUE_OK = "index.jsp";

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
        request.getRequestDispatcher("WEB-INF/connection.jsp").forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isConnected = false;
        System.out.println(request.getHeader("referer"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        String vue = VUE_FORM;
        try {
            if ("deconnecter".equals(request.getParameter("action"))) {
                session.removeAttribute("user");
                vue = VUE_FORM;
            } else {
                boolean isValid = true;
                if (login == null || login.trim().equals("")) {
                    request.setAttribute("erreurlogin", "Veuillez renseigner un login");
                    isValid = false;
                }
                if (password == null || password.trim().equals("")) {
                    request.setAttribute("erreurpassword", "Veuillez renseigner un mot de passe");
                    isValid = false;
                }
                if (isValid) {
                    Personne user = Personne.getByLoginPassword(login, password);
                    if (user == null) {
                        request.setAttribute("erreurconnection", "Identifiant ou mot de passe incorrect");
                    } else {
                        request.setAttribute("okconnection", "Vous êtes connecté");
                        session.setAttribute("user", user);
                        vue = VUE_OK;
                        isConnected = true;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erreurconnection", ex.getMessage());
        }
        String url = (String) session.getAttribute("url");
        if (isConnected && url != null) {
            response.sendRedirect(url);
        } else {
            request.getRequestDispatcher(vue).forward(request, response);
        }
    }
}
