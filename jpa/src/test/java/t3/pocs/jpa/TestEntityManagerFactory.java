package t3.pocs.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * A EntityManagerFactory is an expensive-to-create, threadsafe object intended
 * to be shared by all application threads. It is created once, usually on
 * application startup.
 * 
 * @author <a href="trongtt@gmail.com">Trong Tran</a>
 * @version $Revision$
 */
public class TestEntityManagerFactory {

  /**
   * <p>
   * Each time EntityManagerFactory createEntityManager() is invoked, a new
   * instance of EntityManager is created/returned. The isOpen method will
   * return true on the returned instance.
   * <p>
   * Once an EntityManagerFactory has been closed, all its entity managers are
   * considered to be in the closed state.
   */
  @Test
  public void EntityManagerFactoryLifecycle() {
    //
    EntityManagerFactory entityManagerFactory = AbstractTest.createEntityManagerFactory();
    EntityManager entityManager1 = entityManagerFactory.createEntityManager();
    EntityManager entityManager2 = entityManagerFactory.createEntityManager();

    Assert.assertNotSame(entityManager1, entityManager2);
    Assert.assertTrue(entityManager1.isOpen());
    Assert.assertTrue(entityManager2.isOpen());

    //
    entityManagerFactory.close();
    Assert.assertFalse(entityManager1.isOpen());
    Assert.assertFalse(entityManager2.isOpen());
  }
}
