package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.DAO.IPlayerDAO;
import ch.heigvd.amt.projectone.DAO.ITeamDAO;
import ch.heigvd.amt.projectone.model.Coach;
import ch.heigvd.amt.projectone.model.Player;
import ch.heigvd.amt.projectone.model.Team;

import javax.ejb.DuplicateKeyException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddingPlayerServlet", urlPatterns = {"/addingPlayer"})
public class AddingPlayerServlet extends HttpServlet {
    @EJB
    IPlayerDAO pd;
    @EJB
    ITeamDAO td;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ajout d'un joueur dans une équipe
        // l'ajout c est le coach en question qui le fait dans une de ses equipes

        response.setContentType("text/html");
        String context = request.getContextPath();
        String fname =request.getParameter("fname");
        String lname  = request.getParameter("lname");
        String position  =request.getParameter("position");
        int number  =Integer.parseInt(request.getParameter("number"));
        String team  =request.getParameter("team");
        Team t = td.findById(team);

        Player p = Player.builder()
                .firstName(fname)
                .lastName(lname)
                .position(position)
                .number(number)
                .team(t)
                .build();


        try {
            pd.create(p);
            response.getWriter().println("player created");
            response.sendRedirect(context + "/tablePlayerPage/myPlayers?currentPage=1");
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Coach coach = (Coach) request.getSession().getAttribute("coach");
        request.setAttribute("coach", coach);
        request.setAttribute("teams", td.findMyTeamByCoach(coach.getUsername()));
        request.getRequestDispatcher("/WEB-INF/pages/registrationTeamPlayer.jsp").forward(request, response);
    }
}
