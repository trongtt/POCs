package t3.pocs.jaas.module;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public abstract class AbstractLoginModule implements LoginModule
{

   protected Subject subject;
   protected CallbackHandler callbackHandler;
   protected Map sharedState;
   protected Map options;

   @SuppressWarnings("rawtypes")
   protected void doInitialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options)
   {
      this.subject = subject;
      this.callbackHandler = callbackHandler;
      this.sharedState = sharedState;
      this.options = options;
      System.out.println("Initialize : " + this.getClass().getName());
   }

   protected void doLogin()
   {
      System.out.println("Login : " + this.getClass().getName());
   }

   protected void doCommit()
   {
      System.out.println("Commit : " + this.getClass().getName());
      System.out.println(this.subject);
      System.out.println(this.callbackHandler);
      System.out.println(this.sharedState);
      System.out.println(this.options);
   }

   protected void doAbort()
   {
      System.out.println("Abort : " + this.getClass().getName());
   }

   protected void doLogout()
   {
      System.out.println("Logout : " + this.getClass().getName());
   }

   public boolean abort() throws LoginException
   {
      doAbort();
      return true;
   }

   public boolean logout() throws LoginException
   {
      doLogout();
      return true;
   }
}
