package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
import modele.Projet;

/**
 *
 * @author Utilisateur
 */
@WebServlet(name = "creerProjetServlet", urlPatterns = {"/creerprojet"})
public class CreerProjetServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Personne user = (Personne) session.getAttribute("user");
        if (user == null) {
            //memories où on se trouve
            session.setAttribute("url", request.getRequestURI());
            // afficher la connexion
            response.sendRedirect("connection");
        } else {
            request.getRequestDispatcher("WEB-INF/formProjet.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Personne user = (Personne) session.getAttribute("user");
        if (user == null) {
            //memories où on se trouve
            session.setAttribute("url", request.getRequestURI());
            // afficher la connexion
            response.sendRedirect("connection");
        } else {
            try {
                if (request.getParameter("promotion") == "") {
                    request.setAttribute("erreurPromo", "Veuillez entrer votre promotion");
                    request.getRequestDispatcher("WEB-INF/formProjet.jsp").forward(request, response);
                } else if (request.getParameter("titre") == "") {
                    request.setAttribute("erreurTitre", "Veuillez entrer un titre pour votre projet");
                    request.getRequestDispatcher("WEB-INF/formProjet.jsp").forward(request, response);
                } else if (request.getParameter("sujet") == "") {
                    request.setAttribute("erreurSujet", "Veuillez entre un sujet pour le projet");
                    request.getRequestDispatcher("WEB-INF/formProjet.jsp").forward(request, response);
                } else if (request.getParameter("dateLimite") == "") {
                    request.setAttribute("erreurDate", "Vous devez choisir une date limite pour votre projet");
                    request.getRequestDispatcher("WEB-INF/formProjet.jsp").forward(request, response);
                } else {
                    Integer idPromotion = Integer.parseInt(request.getParameter("promotion"));
                    Integer idCreateur = user.getId_personne();
                    String titre = request.getParameter("titre");
                    String sujet = request.getParameter("sujet");
                    Date dateLimite = Date.valueOf(request.getParameter("dateLimite"));
                    Projet projet = new Projet(-1, idPromotion, idCreateur, sujet, titre, null, dateLimite);
                    projet.insert();
                    response.sendRedirect("projets");
                }
            } catch (NumberFormatException ex) {
                request.setAttribute("erreurPromo2", "Format invalide");
                request.getRequestDispatcher("WEB-INF/formProjet.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(CreerProjetServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("erreurPromo2", ex.getMessage());
                request.getRequestDispatcher("WEB-INF/formProjet.jsp").forward(request, response);
            }
        }
    }
}
