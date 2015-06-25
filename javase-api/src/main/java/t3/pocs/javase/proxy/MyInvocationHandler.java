package t3.pocs.javase.proxy;

import java.lang.reflect.*;

public class MyInvocationHandler implements InvocationHandler {

  private A a;

  public MyInvocationHandler(A a) {
    this.a = a;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("Invoking method: " + method.getName() + " on class " + proxy.getClass().getName());
    return method.invoke(a, args);
  }
}
