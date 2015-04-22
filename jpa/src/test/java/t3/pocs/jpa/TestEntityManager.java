package t3.pocs.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * The EntityManager instance represents a connection to the database.
 * An EntityManager is an inexpensive, non-threadsafe object that should be used
 * once, for a single business process, a single unit of work, and then
 * discarded.
 * <p>
 * Don't use the entitymanager-per-operation antipattern, that is, don't
 * open and close an EntityManager for every simple database call in a single
 * thread!
 * 
 * @author <a href="trongtt@gmail.com">Trong Tran</a>
 * @version $Revision$
 */
public class TestEntityManager extends AbstractTest {
  private EntityManager entityManager;

  @Before
  public void setUp() throws Exception {
    entityManager = entityManagerFactory.createEntityManager();
  }

  @After
  public void tearDown() throws Exception {
    entityManager.close();
  }

  /**
   * <p>
   * The transaction commit() will close also close the transaction.
   * If we want to make further persistence action, we need to begin transaction again.
   */
  @Test
  public void testEntityManagerLifecycle() {
    Assert.assertTrue(entityManager.isOpen());

    //
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    Assert.assertTrue(transaction.isActive());

    entityManager.persist(new Category("Income"));
    entityManager.getTransaction().commit();

    Assert.assertFalse(transaction.isActive()); // Transaction will be inactive/closed

    //
    entityManager.getTransaction().begin();
    entityManager.persist(new Category("Outcome"));
    entityManager.getTransaction().commit();

    List<Category> result = entityManager.createQuery("from Category", Category.class)
                                         .getResultList();

    Assert.assertEquals(2, result.size());
  }

  /**
   * 
   */
  @Test
  public void transactionManagement() {
    //
    EntityTransaction t1 = entityManager.getTransaction();
    Assert.assertFalse(t1.isActive());

    //
    t1.begin();
    Assert.assertTrue(t1.isActive());

    EntityTransaction t2 = entityManager.getTransaction();
    Assert.assertEquals(t2, t1);
    Assert.assertTrue(t2.isActive());

    //
    t1.commit();
    Assert.assertFalse(t1.isActive());

    t2 = entityManager.getTransaction();
    Assert.assertEquals(t2, t1);
    Assert.assertFalse(t2.isActive());

    //
    EntityManager entityManager2 = entityManagerFactory.createEntityManager();
    EntityTransaction t3 = entityManager2.getTransaction();
    Assert.assertNotSame(t1, t3);
  }

  /**
   * Calling {@link EntityManager#flush()} doesn't deactivate the associated transaction.
   */
  @Test
  public void testFlush() {
    EntityTransaction t = entityManager.getTransaction();
    
    //
    t.begin();
    Assert.assertTrue(t.isActive());

    // calling flush() doesn't deactivate the associated transaction
    entityManager.flush();
    Assert.assertTrue(t.isActive());

    t.commit();
  }
}
