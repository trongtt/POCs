package t3.pocs.guice;

import t3.pocs.guice.bean.Animal;
import t3.pocs.guice.bean.Apple;
import t3.pocs.guice.bean.Fruit;

import com.google.inject.AbstractModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;

public class DefaultGuiceTest extends TestCase
{
   public void testGuiceContainer()
   {
      DefaultModule module = new DefaultModule();
      Injector injector = Guice.createInjector(module);
      Fruit a = injector.getInstance(Fruit.class);
      assertTrue(a instanceof Apple);
   }

   // Define module for testing
   private class DefaultModule extends AbstractModule
   {
      @Override
      protected void configure()
      {
         bind(Fruit.class);
         bind(Animal.class);
      }
   }
}
