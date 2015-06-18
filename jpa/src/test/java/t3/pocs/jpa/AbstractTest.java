package t3.pocs.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author <a href="trongtt@gmail.com">Trong Tran</a>
 * @version $Revision$
 */
public abstract class AbstractTest {
  protected static EntityManagerFactory entityManagerFactory;

  @BeforeClass
  public static void createFactory() {
    entityManagerFactory = createEntityManagerFactory();
  }

  @AfterClass
  public static void closeEntityManagerFactory() {
    entityManagerFactory.close();
  }

  public static EntityManagerFactory createEntityManagerFactory() {
    return Persistence.createEntityManagerFactory("t3.pocs.jpa.hsql");
//    return Persistence.createEntityManagerFactory("t3.pocs.jpa.h2");
//    return Persistence.createEntityManagerFactory("t3.pocs.jpa.mysql");
  }
}
