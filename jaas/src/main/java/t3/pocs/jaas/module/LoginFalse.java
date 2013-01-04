package t3.pocs.jaas.module;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

public class LoginFalse extends AbstractLoginModule
{

   @SuppressWarnings("rawtypes")
   public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options)
   {
      doInitialize(subject, callbackHandler, options, options);
   }

   public boolean login() throws LoginException
   {
      doLogin();
      return false;
   }

   public boolean commit() throws LoginException
   {
      doCommit();
      return true;
   }
}
