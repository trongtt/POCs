package t3.pocs.guice.binding;

import t3.pocs.guice.bean.Fruit;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;

public class InstanceTest extends TestCase
{
   private Injector injector;

   @Override
   protected void setUp() throws Exception
   {
      Module module = new Module();
      injector = Guice.createInjector(module);
   }

   public void testInstance()
   {
      Fruit a = injector.getInstance(Fruit.class);
      assertTrue(a instanceof Grape);
   }

   // Define module and service for testing
   private class Module extends AbstractModule
   {
      @Override
      protected void configure()
      {
         Grape a = new Grape();
         bind(Fruit.class).toInstance(a);
      }
   }

   private class Grape implements Fruit
   {
      public String getName()
      {
         return "grape";
      }
   }
}
