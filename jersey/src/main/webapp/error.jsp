<!DOCTYPE html 
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
           "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>   
  </head>
  <body>
    <div>Login failed !!!</div>
    <form name="loginForm" action="j_security_check" method="post">
    		<table> 
          <tr>
            <td>Username </td>
            <td><input name="j_username" value=""/></td>
          </tr>
          <tr>
            <td>Password </td>
            <td><input type="password" name="j_password" value=""/></td>
          </tr>
        </table>
        <div>
          <table>
          	<tr>
              <td>
              	<input type="submit" name="signIn" value="Login"></input>
              </td>
             </tr>
          </table>
        </div>
      </form>
  </body>
</html>
