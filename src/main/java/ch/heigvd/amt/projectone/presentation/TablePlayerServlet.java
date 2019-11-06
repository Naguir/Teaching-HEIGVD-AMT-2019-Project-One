package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.DAO.IPlayerDAO;
import ch.heigvd.amt.projectone.model.Coach;
import ch.heigvd.amt.projectone.model.Player;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TablePlayerServlet", urlPatterns = {"/tablePlayerPage"})
public class TablePlayerServlet extends HttpServlet {

    @EJB
    private IPlayerDAO pd;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/tablePlayer.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Coach coach = (Coach) request.getSession().getAttribute("coach");
        request.setAttribute("players", pd.findAllPlayers());
        request.setAttribute("coach", coach);
        request.getRequestDispatcher("/WEB-INF/pages/tablePlayer.jsp").forward(request, response);

    }
}
