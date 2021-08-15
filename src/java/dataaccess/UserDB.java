package dataaccess;

import domain.Category;
import domain.Item;
import domain.User;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserDB {

    public List<User> getAllUsers() throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        } finally {
            em.close();
        }
    }

    public User get(String username) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            User user = em.find(User.class, username);
            return user;
        } finally {
            em.close();
        }

    }

    public Category get(int categoryID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Category category = em.find(Category.class, categoryID);
            return category;
        } finally {
            em.close();
        }

    }

    public void insert(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(user);
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void insertCategory(Category category) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(category);
            em.merge(category);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void insert(Item item, HttpServletRequest request) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            item.setOwner(user);

            user.getItemList().add(item);

            trans.begin();
            em.persist(item);
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(user);
            trans.commit();

        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void updateItem(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(item);
            trans.commit();

        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void updateCategory(Category category) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(category);
            trans.commit();

        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void delete(String username) throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        User user = em.find(User.class, username);

        try {

            trans.begin();
            em.remove(em.merge(user));

            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void deleteItem(Integer itemID) throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Item item = em.find(Item.class, itemID);
        User user = item.getOwner();

        try {
            user.getItemList().remove(item);
            trans.begin();

            em.remove(em.merge(item));
            em.merge(user);

            trans.commit();

        } catch (Exception ex) {

            trans.rollback();
        } finally {
            em.close();
        }
    }

    public User login(String username, String password) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            User user = em.find(User.class, username);
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                return null;
            }

        } finally {
            em.close();
        }
    }

    public List<Item> getAllItems(String username) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User user = em.find(User.class, username);

            List<Item> items = user.getItemList();

            return items;
        } finally {
            em.close();
        }
    }

    public Item getUserItem(int itemID, String username) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            //Item item = em.find(Item.class, itemID);
//            Item item = em.createNamedQuery("Item.findByItemID", Item.class).getSingleResult();
            User user = em.find(User.class, username);
            List<Item> items = user.getItemList();

            Item foundItem = null;
            for (Item item : items) {
                if (item.getItemID() == itemID) {
                    foundItem = item;
                }
            }
            return foundItem;
        } finally {
            em.close();
        }
    }

    public List<Category> getAllCategories() {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {

            List<Category> categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();

            categories.forEach(e -> System.out.println(e));
            return categories;
        } finally {
            em.close();
        }
    }

}
