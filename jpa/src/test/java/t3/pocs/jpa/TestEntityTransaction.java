package t3.pocs.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestEntityTransaction extends AbstractTest {

  @BeforeClass
  public static void initData() {
    // Create a EntityManager instance to perform CRUD operations on database
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    // Start a transaction
    entityManager.getTransaction().begin();

    // Add 5 Tasks in DB
    entityManager.persist(new Category("Income"));
    entityManager.persist(new Category("Outcome"));
    entityManager.persist(new Category("Investment"));

    // Commit the transaction
    entityManager.getTransaction().commit();

    // Close the EntityManager instance
    entityManager.close();
  }

  @Test
  public void testEntityManagerFactoryNotNull() {
    Assert.assertNotNull(entityManagerFactory);
  }

  @Test
  public void testCreationTask() {

    // Create a EntityManager instance to perform CRUD operations on database
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    // Queries all categories from DB
    List<Category> result = entityManager.createQuery("from Category", Category.class)
                                         .getResultList();

    Assert.assertEquals(3, result.size());

    // Query a Category with name is "Income"
    result = entityManager.createQuery("select c from Category c where c.name like :name", Category.class)
        .setParameter("name", "Investment")
        .getResultList();
    
    Assert.assertEquals(1, result.size());
    Category c = result.get(0);
    Assert.assertEquals("Investment", c.getName());
    c.setParentId(1);

    entityManager.getTransaction().begin();
    entityManager.persist(c);
    entityManager.getTransaction().commit();

    // Close the EntityManager instance
    entityManager.close();
  }

  @Test
  public void testFlush() {
    EntityManager em1 = entityManagerFactory.createEntityManager();
    em1.getTransaction().begin();
    List<Category> result = em1.createQuery("select c from Category c where c.name like :name", Category.class)
        .setParameter("name", "Investment")
        .getResultList();

    Category c = result.get(0);
    c.setParentId(10);
    em1.flush();
    em1.getTransaction().commit();

    EntityManager em2 = entityManagerFactory.createEntityManager();
    // Query a Category with name is "Income"
    result = em2.createQuery("select c from Category c where c.name like :name", Category.class)
        .setParameter("name", "Investment")
        .getResultList();
    
    Assert.assertEquals(1, result.size());
    c = result.get(0);
    Assert.assertEquals("Investment", c.getName());
    Assert.assertEquals(10, c.getParentId());

    // End all EntityManagers
    em2.close();
    em1.close();
  }
}
