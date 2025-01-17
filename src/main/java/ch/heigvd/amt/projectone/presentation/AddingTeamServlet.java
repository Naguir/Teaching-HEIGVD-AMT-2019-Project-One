package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.DAO.ITeamDAO;
import ch.heigvd.amt.projectone.model.Coach;
import ch.heigvd.amt.projectone.model.Team;

import javax.ejb.DuplicateKeyException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;


@WebServlet(name = "AddingTeamServlet", urlPatterns = {"/addingTeam"})
public class AddingTeamServlet extends HttpServlet {
    @EJB
    ITeamDAO td;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ajout d'une team par le coach admin


        Coach coach = (Coach) request.getSession().getAttribute("coach");
        request.setAttribute("coach", coach);
        String context = request.getContextPath();

        response.setContentType("text/html");

        String name =request.getParameter("name");
        Date creationDate  = Date.valueOf(request.getParameter("creationDate"));
        String location  =request.getParameter("location");


        Team t = Team.builder().name(name)
                .dateCreation(creationDate)
                .location(location)
                .build();

        try {
            td.create(t);
            response.getWriter().println("team created");
            response.sendRedirect(context + "/tableTeamPage/allTeams?currentPage=1");
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/registrationTeamPlayer.jsp").forward(request, response);
    }
}
