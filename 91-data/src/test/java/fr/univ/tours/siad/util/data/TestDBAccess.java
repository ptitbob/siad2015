package fr.univ.tours.siad.util.data;

import fr.univ.tours.siad.util.data.bean.Person;
import fr.univ.tours.siad.util.data.bean.Region;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by francois on 28/08/15.
 */
public class TestDBAccess {

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager entityManager;

    @BeforeClass
    public static void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("siadTest");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterClass
    public static void tearDown() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    protected <E> E persist(E entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(entity);
        entityTransaction.commit();
        return entity;
    }

    @Test
    public void test() {
        Person person = new Person("bat", "batman");
        persist(person);
    }

    @Test
    public void regionTest() {
        Region region = new Region();
        region.setInseeId("94");
        region.setChefLieuId("12345");
        region.setName("test");
        region.setUpperName("TEST");
        persist(region);
    }

}
