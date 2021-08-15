/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.UserDB;
import domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;

/**
 *
 * @author 844568
 */
public class EditUserInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDB userDB = new UserDB();
        editUser(userDB, request);
        getServletContext().getRequestDispatcher("/WEB-INF/manageMyAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String saveNewUserInfo = request.getParameter("saveUserNewInfo");
        String deactivateAccount = request.getParameter("deactivateAccount");

        UserDB userDB = new UserDB();

        try {
            if (saveNewUserInfo != null) {

                saveUserNewInfo(userDB, request);

//                getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
                response.sendRedirect("inventory");

            } else if (deactivateAccount != null) {

                deactivateAccount(userDB, request, response);

            }

        } catch (Exception ex) {
            Logger.getLogger(EditUserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void editUser(UserDB userDB, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User userToBeEdited = (User) session.getAttribute("user");

        AccountService.editUserInfo(request, userToBeEdited.getUsername(), userDB);

    }

    private void saveUserNewInfo(UserDB userDB, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User userToBeEdited = (User) session.getAttribute("user");

        String newFirstName = request.getParameter("firstName");
        String newLastName = request.getParameter("lastName");
        boolean isAdmin = userToBeEdited.getIsAdmin();

        AccountService.saveUserNewInfo(userToBeEdited.getUsername(), userDB, newFirstName, newLastName, true, isAdmin, request);
        request.setAttribute("nameChanged", true);

    }

    private void deactivateAccount(UserDB userDB, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User userToBeActivated = (User) session.getAttribute("user");

        AccountService.deactivateAccount(request, userToBeActivated.getUsername(), userDB, response);
    }
}
