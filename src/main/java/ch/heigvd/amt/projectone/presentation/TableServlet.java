package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.DAO.ICoachDAO;
import ch.heigvd.amt.projectone.DAO.IPlayerDAO;
import ch.heigvd.amt.projectone.DAO.ITeamDAO;
import ch.heigvd.amt.projectone.model.Coach;
import ch.heigvd.amt.projectone.model.Player;
import ch.heigvd.amt.projectone.model.Team;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TableServlet", urlPatterns = {"/tablePlayerPage/allPlayers","/tablePlayerPage/myPlayers","/tableTeamPage/myTeams","/tableTeamPage/allTeams","/tableCoachPage"})
public class TableServlet extends HttpServlet {

    @EJB
    private IPlayerDAO pd;
    @EJB
    private ITeamDAO td;
    @EJB
    private ICoachDAO cd;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/tablePlayer.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Coach coach = (Coach) request.getSession().getAttribute("coach");

        System.out.println(request.getServletPath());
        String path = request.getServletPath();
        request.setAttribute("coach", coach);


        if(path.contains("/tablePlayerPage/")) {

            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            int recordsPerPage = 10;
            int rows = 0;
            int nOfPages =0;

            List<Player> allPlayers = pd.findPlayers(currentPage,recordsPerPage);
            List<Player> myPlayers = pd.findMyTeamPlayers(currentPage,recordsPerPage,coach);


            if (path.equals("/tablePlayerPage/myPlayers")) {
                System.out.println(path);
                rows = pd.getNumberOfRowsForMyTeam(coach);
                request.setAttribute("players", myPlayers);
            }

            if (path.equals("/tablePlayerPage/allPlayers")) {
                rows = pd.getNumberOfRows();
                request.setAttribute("players", allPlayers);
            }

            nOfPages = rows / recordsPerPage;
            if (nOfPages % recordsPerPage > 0) {
                nOfPages++;
            }
            request.setAttribute("noOfPages", nOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", recordsPerPage);

            request.getRequestDispatcher("/WEB-INF/pages/tablePlayer.jsp").forward(request, response);
        }

        if(path.contains("/tableTeamPage/")) {
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            int recordsPerPage = 10;
            int rows = 0;
            int nOfPages =0;

            List<Team> allTeam = td.findAllTeam(currentPage,recordsPerPage);
            List<Team> myTeam = td.findMyTeam(currentPage,recordsPerPage,coach.getUsername());

            if (path.equals("/tableTeamPage/myTeams")) {
                rows = td.getNumberOfRowsForMyTeam(coach.getUsername());
                request.setAttribute("teams", myTeam);
            }

            if (path.equals("/tableTeamPage/allTeams")) {
                rows = td.getNumberOfRows();
                request.setAttribute("myTeams",myTeam);
                request.setAttribute("teams", allTeam);
            }

            nOfPages = rows / recordsPerPage;
            if (nOfPages % recordsPerPage > 0) {
                nOfPages++;
            }
            request.setAttribute("noOfPages", nOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", recordsPerPage);

            request.getRequestDispatcher("/WEB-INF/pages/tableTeam.jsp").forward(request, response);
        }

        if(path.equals("/tableCoachPage")) {
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            int recordsPerPage = 10;
            int rows = 0;
            int nOfPages =0;

            List<Coach> allCoach = cd.findAllCoach(currentPage,recordsPerPage);
            request.setAttribute("coaches", allCoach);

            rows = td.getNumberOfRows();
            nOfPages = rows / recordsPerPage;
            if (nOfPages % recordsPerPage > 0) {
                nOfPages++;
            }
            request.setAttribute("noOfPages", nOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", recordsPerPage);

            request.getRequestDispatcher("/WEB-INF/pages/tableCoach.jsp").forward(request, response);
        }

    }
}
