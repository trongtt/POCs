package t3.pocs.guice.injection;

import t3.pocs.guice.bean.Banana;
import t3.pocs.guice.bean.Fruit;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import junit.framework.TestCase;

public class FieldTest extends TestCase
{
   public void testMethodInjection()
   {
      Module module = new Module();
      Injector injector = Guice.createInjector(module);
      FruitStore a1 = injector.getInstance(FruitStore.class);
      assertTrue(a1 instanceof FruitStore);
      assertEquals("banana", a1.sell());
   }

   /**************************************************
    * Defines module, bean for testing
    **************************************************/
   private static class FruitStore
   {
      @Inject private Fruit fruit;

      String sell()
      {
         return fruit.getName();
      }
   }

   private class Module extends AbstractModule
   {
      @Override
      protected void configure()
      {
         bind(Fruit.class).to(Banana.class);
         bind(FruitStore.class);
      }
   }
}
