package t3.pocs.guice.scopes;

import t3.pocs.guice.bean.Animal;
import t3.pocs.guice.bean.Apple;
import t3.pocs.guice.bean.Fruit;
import t3.pocs.guice.bean.Monkey;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;

public class ScopeTest extends TestCase
{
   public void testInstance()
   {
      Module module = new Module();
      Injector injector = Guice.createInjector(module);
      Fruit a1 = injector.getInstance(Fruit.class);
      assertEquals("apple", a1.getName());
      Fruit a2 = injector.getInstance(Fruit.class);
      assertEquals("apple", a2.getName());      

      // But NOT the same instance
      assertNotSame(a1, a2);

      Animal b1 = injector.getInstance(Animal.class);
      Animal b2 = injector.getInstance(Animal.class);

      // The same instance
      assertSame(b1, b2);
   }

   // Define module for testing
   private class Module extends AbstractModule
   {
      @Override
      protected void configure()
      {
         bind(Fruit.class).to(Apple.class);
         bind(Animal.class).to(Monkey.class).in(Singleton.class);
      }
   }
}
