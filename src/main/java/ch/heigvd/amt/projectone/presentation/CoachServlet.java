package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.DAO.ICoachDAO;
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

@WebServlet(name = "CoachServlet", urlPatterns = {"/coachProfile"})
public class CoachServlet extends HttpServlet {

    @EJB
    private ITeamDAO td;

    @EJB
    private ICoachDAO cd;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String context = request.getContextPath();

        String uname = request.getParameter("uname");
        String pass  = request.getParameter("pass");
        String fname =request.getParameter("fname");
        String lname  = request.getParameter("lname");

        Coach c = Coach.builder()
                .lastName(lname)
                .firstName(fname)
                .username(uname)
                .password(pass)
                .build();


        cd.update(c);
        response.getWriter().println("Coach uploaded");
        response.sendRedirect(context + "/coachProfile");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Coach coach = (Coach) request.getSession().getAttribute("coach");
        Coach c = cd.findById(coach.getUsername());
        request.setAttribute("coach",c);

        request.getRequestDispatcher("/WEB-INF/pages/coach.jsp").forward(request, response);

    }
}
