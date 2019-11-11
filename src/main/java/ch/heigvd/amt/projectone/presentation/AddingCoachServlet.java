package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.DAO.ITeamDAO;
import ch.heigvd.amt.projectone.model.Coach;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddingCoachServlet", urlPatterns = {"/addingCoach"})
public class AddingCoachServlet extends HttpServlet {
    @EJB
    ITeamDAO td;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // on créer le lien entre coach et equipe

        response.setContentType("text/html");
        Coach coach = (Coach) request.getSession().getAttribute("coach");
        String context = request.getContextPath();
        String name = request.getParameter("tname");

        td.addCoach(coach.getUsername(), name);

        response.getWriter().println("coach train team");
        response.sendRedirect(context + "/tableTeamPage/myTeams?currentPage=1");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
