/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import domain.Category;
import domain.Item;
import domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import servlets.AdminServlet;
import servlets.InventoryServlet;

/**
 *
 * @author 844568
 */
public class InventoryService {

    //delete an inventory
    public static void deleteItem(Integer itemID, UserDB userDB) {
        try {

            userDB.deleteItem(itemID);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void generateItems(UserDB userDB, HttpServletRequest request, String username) {
        try {
            List<Item> items = userDB.getAllItems(username);

            request.setAttribute("listOfItems", items);
            //items.forEach(e -> System.out.println(e));
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void generateCategories(UserDB userDB, HttpServletRequest request) {
        try {
            List<Category> categories = userDB.getAllCategories();
            //categories.forEach(e -> System.out.println(e));
            request.setAttribute("category", categories);

        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertNewCategory(Category category, UserDB userDB, HttpServletRequest request) {
        try {
            userDB.insertCategory(category);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void saveUserNewItem(int categoryID, double itemPrice, String itemName, UserDB userDB, HttpServletRequest request) {
        try {

            Category category = new Category(categoryID);

            Item item = new Item(category, itemName, itemPrice);

            // user.setAnItem(item);
            userDB.insert(item, request);
            //userDB.update(user);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void editUserItem(HttpServletRequest request, int itemID, UserDB userDB) {
        try {
            request.setAttribute("isClicked", true);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Item itemToEdit = userDB.getUserItem(itemID, user.getUsername());

            request.setAttribute("selectedCategory", itemToEdit.getCategory().getCategoryName());
            request.setAttribute("selectedItemName", itemToEdit.getItemName());
            request.setAttribute("selectedItemPrice", itemToEdit.getPrice());

        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveEditedItem(int itemID, String username, UserDB userDB, Category category, String itemName, double price, HttpServletRequest request) {
        try {
            Item itemToBeUpdated = userDB.getUserItem(itemID, username);

            itemToBeUpdated.setCategory(category);
            itemToBeUpdated.setItemName(itemName);
            itemToBeUpdated.setPrice(price);
            // User userItemToBeUpdated = userDB.get(username);

            userDB.updateItem(itemToBeUpdated);

//            userDB.update(userToBeUpdated);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void editCategory(HttpServletRequest request, int categoryID, UserDB userDB) {
        try {
            //Category category =
            Category category = userDB.get(categoryID);
            request.setAttribute("isClicked", true);
            request.setAttribute("categoryName", category.getCategoryName());
        } catch (Exception ex) {
            Logger.getLogger(InventoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveEditedCategory(UserDB userDB, String newCategoryName, int categoryID) throws Exception {

        Category category = userDB.get(categoryID);
        category.setCategoryName(newCategoryName);

        userDB.updateCategory(category);

    }
}
