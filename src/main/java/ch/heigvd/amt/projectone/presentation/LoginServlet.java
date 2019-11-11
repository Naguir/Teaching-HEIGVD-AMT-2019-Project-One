package ch.heigvd.amt.projectone.presentation;


import ch.heigvd.amt.projectone.DAO.ICoachDAO;
import ch.heigvd.amt.projectone.DAO.LoginDAO;
import ch.heigvd.amt.projectone.model.Coach;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/loginpage"})
public class LoginServlet extends HttpServlet {

    @EJB
    private LoginDAO ld;

    @EJB
    private ICoachDAO cd;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username=request.getParameter("username");
        String pass=request.getParameter("pass");


        try {
            if(ld.authentificate(username, pass)){
                Coach coach = cd.findById(username);
                request.getSession().setAttribute("coach",coach);
                RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");
                rd.forward(request,response);
            }
            else{
                out.print("Sorry username or password error");
                RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
                rd.include(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.close();
        //request.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/dashboard.jsp").forward(req, resp);
    }
}

