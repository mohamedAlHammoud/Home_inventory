package dataaccess;

import javax.persistence.*;

public class DBUtil {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Home_Inventory_realDBPU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
