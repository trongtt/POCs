package t3.pocs.jaas;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class SimpleCallbackHandler implements CallbackHandler
{
   private String username = null;

   private String password = null;

   public SimpleCallbackHandler(String pUsername, String pPassword)
   {
      username = pUsername;
      password = pPassword;
   }

   public void handle(Callback[] callbacks) throws java.io.IOException, UnsupportedCallbackException
   {
      for (int i = 0; i < callbacks.length; i++)
      {
         if (callbacks[i] instanceof NameCallback)
         {
            NameCallback nc = (NameCallback)callbacks[i];
            nc.setName(username);
         }
         else if (callbacks[i] instanceof PasswordCallback)
         {
            PasswordCallback pc = (PasswordCallback)callbacks[i];
            pc.setPassword(password.toCharArray());
         }
         else
         {
            throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
         }
      }
   }
}