package servlets;

import dataaccess.UserDB;
import domain.Category;
import domain.User;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import services.AccountService;
import services.InventoryService;

public class InventoryServlet extends HttpServlet {

    int itemToEditAndSave = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        refreshPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDB userDB = new UserDB();
        String deleteButton = request.getParameter("deleteItem");
        String saveNewItem = request.getParameter("saveNewItem");
        String editItem = request.getParameter("editItem");
        String itemToBeEdited = request.getParameter("saveUserEditedItem");

        if (deleteButton != null) {
            deleteItem(request, userDB, response);
        } else if (saveNewItem != null) {
            saveNewItem(request, userDB, response);
        } else if (editItem != null) {
            editItem(request, userDB);

        } else if (itemToBeEdited != null) {

            saveEditedItem(userDB, request);
        }

        refreshPage(request, response);

    }

    private void deleteItem(HttpServletRequest request, UserDB userDB, HttpServletResponse response) throws ServletException, IOException {
        Integer itemID;
        itemID = Integer.valueOf(request.getParameter("deleteItem"));

        InventoryService.deleteItem(itemID, userDB);

        //refreshPage(request, response);
    }

    private void generateItems(HttpServletRequest request) {
        UserDB userDB = new UserDB();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        InventoryService.generateItems(userDB, request, username);

    }

    private void generateCategories(HttpServletRequest request) {
        UserDB userDB = new UserDB();
        InventoryService.generateCategories(userDB, request);
    }

    private void refreshPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        generateItems(request);
        generateCategories(request);
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }

    private void saveNewItem(HttpServletRequest request, UserDB userDB, HttpServletResponse response) throws IOException, ServletException {
        int categoryID = Integer.valueOf(request.getParameter("item_category"));

        String itemName = request.getParameter("item_name");
        double itemPrice = Double.valueOf(request.getParameter("item_price"));

        InventoryService.saveUserNewItem(categoryID, itemPrice, itemName, userDB, request);
        refreshPage(request, response);
    }

    private void editItem(HttpServletRequest request, UserDB userDB) {
        int itemIDToEdit;
        itemIDToEdit = Integer.valueOf(request.getParameter("editItem"));
        itemToEditAndSave = itemIDToEdit;
        InventoryService.editUserItem(request, itemIDToEdit, userDB);
    }

    private void saveEditedItem(UserDB userDB, HttpServletRequest request) {

        int itemIDToEdit;
        itemIDToEdit = itemToEditAndSave;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();

        int categoryID = Integer.valueOf(request.getParameter("itemNewCategory"));
        Category category = new Category(categoryID);
        String itemName = request.getParameter("itemNewName");
        double price = Double.valueOf(request.getParameter("itemNewPrice"));

        InventoryService.saveEditedItem(itemIDToEdit, username, userDB, category, itemName, price, request);

    }

}
