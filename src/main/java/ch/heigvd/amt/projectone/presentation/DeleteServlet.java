package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.DAO.IPlayerDAO;
import ch.heigvd.amt.projectone.DAO.ITeamDAO;
import ch.heigvd.amt.projectone.model.Coach;
import ch.heigvd.amt.projectone.model.Team;

import javax.ejb.DuplicateKeyException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet(name = "DeleteServlet",urlPatterns = {"/deleteTeam","/deletePlayer"})
public class DeleteServlet extends HttpServlet {
    @EJB
    ITeamDAO td;

    @EJB
    IPlayerDAO pd;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("deletename");
        String path = request.getServletPath();

        if(path.contains("/deleteTeam")){
            td.deleteById(id);
            response.getWriter().println("team deleted");
            RequestDispatcher rd = request.getRequestDispatcher("../tableTeamPage/allTeams");
            rd.forward(request, response);
        }


        if(path.contains("/deletePlayer")){
            pd.deleteById(Integer.parseInt(id));
            response.getWriter().println("player deleted");
            RequestDispatcher rd = request.getRequestDispatcher("/tablePlayerPage/myPlayers");
            rd.forward(request, response);
        }

        System.out.println(id);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
