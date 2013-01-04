package t3.pocs.jaas.module;

import java.security.Principal;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

public class LoginSucceeded extends AbstractLoginModule
{

   @SuppressWarnings("rawtypes")
   public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options)
   {
      doInitialize(subject, callbackHandler, options, options);
   }

   public boolean login() throws LoginException
   {
      doLogin();
      this.subject.getPrincipals().add(new Principal()
      {
         public String getName()
         {
            return "LoginSucceeded";
         }
      });
      return true;
   }

   public boolean commit() throws LoginException
   {
      doCommit();
      return true;
   }
}
