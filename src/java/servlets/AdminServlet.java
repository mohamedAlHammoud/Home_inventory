package servlets;

import dataaccess.UserDB;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import services.AccountService;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String logout = request.getParameter("logout");
        if (logout != null) {
            session.invalidate();

            request.setAttribute("logoutMessage", "Logged out successfuly");

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

        } else {

            if (session.getAttribute("user") != null) {
                UserDB userDB = new UserDB();
                generateUsers(userDB, request);

                refreshPage(request, response);

            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDB userDB = new UserDB();
        String deleteButton = request.getParameter("deleteUser");
        String saveNewUserInfo = request.getParameter("saveUserNewInfo");
        String editUser = request.getParameter("editButton");
        String addUser = request.getParameter("saveNewUser");

        if (deleteButton != null) {
            deleteUser(userDB, request);
        } else if (editUser != null) {
            editUser(userDB, request);
        } else if (saveNewUserInfo != null) {
            saveUserNewInfo(userDB, request);
        } else if (addUser != null) {
            addNewUser(userDB, request);
        }
        refreshPage(request, response);
    }

    private void refreshPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDB userDB = new UserDB();
        generateUsers(userDB, request);
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    private void generateUsers(UserDB userDB, HttpServletRequest request) {
        AccountService.generateUsers(userDB, request);

    }

    private void deleteUser(UserDB userDB, HttpServletRequest request) throws ServletException, IOException {
        String username = request.getParameter("deleteUser");
        AccountService.deleteUser(username, userDB);

    }

    private void editUser(UserDB userDB, HttpServletRequest request) {
        String usernameToEdit;
        usernameToEdit = request.getParameter("editButton").toString();
        AccountService.editUserInfo(request, usernameToEdit, userDB);

    }

    private void saveUserNewInfo(UserDB userDB, HttpServletRequest request) {

        String usernameToUpdate;
        usernameToUpdate = request.getParameter("saveUserNewInfo").toString();

        String newFirstName = request.getParameter("firstName");
        String newLastName = request.getParameter("lastName");
        boolean isActive = Boolean.valueOf(request.getParameter("isUserActive"));
        boolean isAdmin = Boolean.valueOf(request.getParameter("isUserAdmin"));

        AccountService.saveUserNewInfo(usernameToUpdate, userDB, newFirstName, newLastName, isActive,isAdmin, request);

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
