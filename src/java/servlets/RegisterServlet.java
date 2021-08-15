package servlets;

import dataaccess.UserDB;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String addUser = request.getParameter("saveNewUser");
        UserDB userDB = new UserDB();
        if (addUser != null) {
            addNewUser(userDB, request);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

    }

    private void addNewUser(UserDB userDB, HttpServletRequest request) {

        String username = request.getParameter("newUserName");
        String firstName = request.getParameter("newFirstName");
        String lastName = request.getParameter("newLastName");
        String password = request.getParameter("newpassword");
        String email = request.getParameter("newEmail");
        AccountService.addNewUser(username, password, email, firstName, lastName, userDB, request);

    }

}
