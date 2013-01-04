package t3.pocs.guice.binding;

import t3.pocs.guice.bean.Apple;
import t3.pocs.guice.bean.Fruit;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.Provides;
import junit.framework.TestCase;

public class ProviderTest extends TestCase
{
   public void testProvider()
   {
      Module module = new Module();
      Injector injector = Guice.createInjector(module);
      Fruit a1 = injector.getInstance(Fruit.class);
      assertTrue(a1 instanceof Apple);
   }

   public void testProvideMethod()
   {
      Module module = new Module();
      Injector injector = Guice.createInjector(module);
      Lemon lemon = injector.getInstance(Lemon.class);
      assertEquals("lemon", lemon.getName());
   }

   /**************************************************
    * Defines module, bean and provider for testing
    **************************************************/
   private class Module extends AbstractModule
   {
      @Override
      protected void configure()
      {
         bind(Fruit.class).toProvider(ProviderImpl.class);
      }

      @Provides
      Lemon provideString()
      {
         return new Lemon();
      }
   }

   private class Lemon implements Fruit
   {
      public String getName()
      {
         return "lemon";
      }
   }

   private static class ProviderImpl implements Provider<Fruit>
   {
      public Fruit get()
      {
         Apple a1 = new Apple();
         a1.setName("ServiceA_1 provider");
         return a1;
      }
   }
}
