package servlets;

import dataaccess.UserDB;
import domain.Category;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;

import services.InventoryService;

public class ManageCategories extends HttpServlet {

    int categoryToEdit;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        refreshPage(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String editCategory = request.getParameter("editButton");
        String saveNewCategory = request.getParameter("saveNewCategory");
        String saveEditedCategory = request.getParameter("saveEditedCategory");

        UserDB userDB = new UserDB();

        try {

            if (saveNewCategory != null) {

                saveNewCategory(userDB, request);

            } else if (editCategory != null) {

                editCategory(userDB, request);

            } else if (saveEditedCategory != null) {

                saveEditedCategory(userDB, request);

            }
        } catch (Exception ex) {
            Logger.getLogger(ManageCategories.class.getName()).log(Level.SEVERE, null, ex);
        }

        refreshPage(request, response);
    }

    private void refreshPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDB userDB = new UserDB();
        generateListOfCategories(userDB, request);
        getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
    }

    private void generateListOfCategories(UserDB userDB, HttpServletRequest request) {
        InventoryService.generateCategories(userDB, request);

    }

    private void saveNewCategory(UserDB userDB, HttpServletRequest request) {
        String categoryName = request.getParameter("newCategoryName");
        Category category = new Category();
        category.setCategoryName(categoryName);
        InventoryService.insertNewCategory(category, userDB, request);

    }

    private void editCategory(UserDB userDB, HttpServletRequest request) {
        int categoryID;
        categoryID = Integer.valueOf(request.getParameter("editButton"));
        categoryToEdit = categoryID;

        InventoryService.editCategory(request, categoryID, userDB);
    }

    private void saveEditedCategory(UserDB userDB, HttpServletRequest request) throws Exception {

        String newCategoryName = request.getParameter("editedCategoryName");
        InventoryService.saveEditedCategory(userDB, newCategoryName, categoryToEdit);
    }
}
