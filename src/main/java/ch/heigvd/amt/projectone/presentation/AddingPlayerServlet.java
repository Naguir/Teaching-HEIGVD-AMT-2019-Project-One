package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.DAO.IPlayerDAO;
import ch.heigvd.amt.projectone.DAO.ITeamDAO;
import ch.heigvd.amt.projectone.model.Coach;
import ch.heigvd.amt.projectone.model.Player;
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


@WebServlet(name = "AddingPlayerServlet", urlPatterns = {"/addingPlayer"})
public class AddingPlayerServlet extends HttpServlet {
    @EJB
    IPlayerDAO pd;
    @EJB
    ITeamDAO td;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

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
            RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/pages/registrationTeam.jsp");
            rd.forward(request,response);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Coach coach = (Coach) request.getSession().getAttribute("coach");
        request.setAttribute("coach", coach);
        request.setAttribute("teams", td.findMyTeamByCoach(coach.getUsername()));
        request.getRequestDispatcher("/WEB-INF/pages/registrationTeam.jsp").forward(request, response);
    }
}
