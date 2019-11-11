package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.DAO.ICoachDAO;
import ch.heigvd.amt.projectone.DAO.IPlayerDAO;
import ch.heigvd.amt.projectone.DAO.ITeamDAO;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteServlet",urlPatterns = {"/deleteTeam","/deletePlayer","/deleteCoach"})
public class DeleteServlet extends HttpServlet {
    @EJB
    ITeamDAO td;

    @EJB
    IPlayerDAO pd;

    @EJB
    ICoachDAO cd;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // on regarde d'abord le path pour ensuite pouvoir supprimer soit une equipe, soit un joueur ou soit un coach

        String id = request.getParameter("deletename");
        String path = request.getServletPath();
        String context = request.getContextPath();

        if(path.contains("/deleteTeam")){
            td.deleteById(id);
            response.getWriter().println("team deleted");
            response.sendRedirect(context + "/tableTeamPage/allTeams?currentPage=1");
        }


        if(path.contains("/deletePlayer")){
            pd.deleteById(Integer.parseInt(id));
            response.getWriter().println("player deleted");
            response.sendRedirect(context + "/tablePlayerPage/myPlayers?currentPage=1");
        }

        if(path.contains("/deleteCoach")){
            cd.deleteById(id);
            response.getWriter().println("coach deleted");
            response.sendRedirect(context + "/tableCoachPage?currentPage=1");
        }

        System.out.println(id);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
