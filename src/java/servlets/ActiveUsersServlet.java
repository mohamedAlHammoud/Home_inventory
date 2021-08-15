package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;

public class ActiveUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=activeusers.xls");
        refreshPage(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void refreshPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDB userDB = new UserDB();
        generateUsers(userDB, request);
        getServletContext().getRequestDispatcher("/WEB-INF/activeUsers.jsp").forward(request, response);
    }

    private void generateUsers(UserDB userDB, HttpServletRequest request) {
        AccountService.generateOrderdUsers(userDB, request);

    }
}
