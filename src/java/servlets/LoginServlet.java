package servlets;

import domain.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import services.AccountService;

public class LoginServlet extends HttpServlet {

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
                User user = (User) session.getAttribute("user");
                String username = user.getUsername();
                request.setAttribute("username", username);
                response.sendRedirect("inventory");

            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean usernamePasswordNotEmpty = isUsernamePasswordNotEmpty(username, password);

        if (usernamePasswordNotEmpty) {
            AccountService service = new AccountService();
            User user = null;
            try {
                user = service.logUserIn(username, password);
            } catch (Exception ex) {
                request.setAttribute("errorMessage", "Username does not exist");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }

            if (user == null) {
                request.setAttribute("errorMessage", "Username or password is not right");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            } else if (user.getIsAdmin()) {
                if (user.getActive()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect("admin");
                } else {
                    request.setAttribute("errorMessage", "Account has been deactivated! contact a system admin to activate again");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }

            } else if (user.getActive()) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("inventory");
            } else {
                request.setAttribute("errorMessage", "Account has been deactivated! contact a system admin to activate again");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        }

    }

    private boolean isUsernamePasswordNotEmpty(String username, String password) {
        boolean valid = false;
        if (username.equals("") || username == null || password.equals("") || password == null) {
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }
}
