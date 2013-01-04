package t3.pocs.jaas;

import java.net.URI;
import java.net.URL;

import com.sun.security.auth.login.ConfigFile;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import junit.framework.TestCase;

public class TestJAAS extends TestCase
{
   private LoginContext loginContext;

   @Override
   protected void setUp() throws Exception
   {
      System.setProperty("java.security.auth.login.config", "jaas.config");

   }

   private boolean authenticate(String config)
   {
      System.out.println("======== Begin " + config + " authentication ========");

      boolean bln = false;
      try
      {
         URL url = TestJAAS.class.getResource("jaas.config");
         URI uri = new URI(url.toString());
         ConfigFile configuration = new ConfigFile(uri);
         loginContext = new LoginContext(config, null, new SimpleCallbackHandler("root", "gtn"), configuration);
      }
      catch (Exception e)
      {
         // Can get a SecurityException or a LoginException
         e.printStackTrace();
         System.exit(-1);
      }

      try
      {
         loginContext.login();
         bln = true;
      }
      catch (LoginException e)
      {
         System.out.println(e);
         bln = false;
      }

      System.out.println("======== End authentication ========");
      return bln;
   }

   
   
   public void testLoginModules()
   {
      loginSuccessful("login-successful-1");
      loginSuccessful("login-successful-2");
      loginSuccessful("login-successful-3");
      loginFailed("login-failed-1");
      loginFailed("login-failed-2");
      loginFailed("login-failed-3");
      loginFailed("login-failed-4");
      loginFailed("login-failed-5");
      loginFailed("login-failed-6");
      loginFailed("login-failed-7");
   }

   private void loginSuccessful(String config)
   {
      assertTrue("The " + config + " login must be successful", authenticate(config));
   }

   private void loginFailed(String config)
   {
      assertFalse("The " + config + " login must be failed", authenticate(config));
   }
}