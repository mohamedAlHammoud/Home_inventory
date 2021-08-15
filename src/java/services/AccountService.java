/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import domain.User;
import dataaccess.UserDB;
import domain.Category;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import servlets.AdminServlet;

public class AccountService {

    public static void generateUsers(UserDB userDB, HttpServletRequest request) {
        try {
            List<User> users = userDB.getAllUsers();
            users.forEach(e -> System.out.print(e.getLastName()));
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void generateOrderdUsers(UserDB userDB, HttpServletRequest request) {
        try {
            List<User> users = userDB.getAllUsers();
            List<User> orderd = users.stream()
                    .filter(e -> e.getActive())
                    .filter(e -> !e.getIsAdmin())
                    .sorted(Comparator.comparing(User::getUsername))
                    .collect(Collectors.toList());

            request.setAttribute("orderdUsers", orderd);

        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteUser(String username, UserDB userDB) {
        try {
            userDB.delete(username);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void editUserInfo(HttpServletRequest request, String username, UserDB userDB) {
        try {

            request.setAttribute("isClicked", true);
            User userToEdit = userDB.get(username);

            request.setAttribute("username", userToEdit.getUsername());
            request.setAttribute("userFName", userToEdit.getFirstName());
            request.setAttribute("userLName", userToEdit.getLastName());
            request.setAttribute("isActive", userToEdit.getActive());
            request.setAttribute("isAdmin", userToEdit.getIsAdmin());
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveUserNewInfo(String username, UserDB userDB, String firstName, String lastName, boolean isActive, boolean isAdmin, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            User userToBeUpdated = userDB.get(username);

            userToBeUpdated.setFirstName(firstName);
            userToBeUpdated.setLastName(lastName);
            userToBeUpdated.setActive(isActive);
            userToBeUpdated.setIsAdmin(isAdmin);
            userDB.update(userToBeUpdated);

            session.setAttribute("user", userToBeUpdated);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deactivateAccount(HttpServletRequest request, String username, UserDB userDB, ServletResponse response) throws Exception {
        User userToBeDeactivated = userDB.get(username);
        userToBeDeactivated.setActive(false);
        userDB.update(userToBeDeactivated);
        HttpSession session = request.getSession();
        session.invalidate();

        request.setAttribute("logoutMessage", "Logged out successfuly, your account has been deactivated");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        httpRequest.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

    }

    public User logUserIn(String username, String password) {
        UserDB userDB = new UserDB();
        return userDB.login(username, password);
    }

    public static void addNewUser(String username, String password, String email, String firstname, String lastname, UserDB userDB, HttpServletRequest request) {
        User user = null;

        if (username != null && username != null && lastname != null && password != null) {

            user = new User(username, password, email, firstname, lastname, true, false);

        } else {
            request.setAttribute("errorMessage", "Please fill all fields");
        }

        try {

            userDB.insert(user);

        } catch (Exception ex) {
            request.setAttribute("errorMessage", "username already exists");
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
