package ch.heigvd.amt.projectone.presentation;

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

@WebServlet(name = "DeleteServlet",urlPatterns = {"/deleteTeam"})
public class DeleteServlet extends HttpServlet {
    @EJB
    ITeamDAO td;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("deletename");

        System.out.println(name);

        td.deleteById(name);


        response.getWriter().println("team deleted");
        RequestDispatcher rd = request.getRequestDispatcher("../tableTeamPage/allTeams");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
