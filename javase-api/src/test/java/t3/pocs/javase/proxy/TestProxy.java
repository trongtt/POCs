package t3.pocs.javase.proxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class TestProxy {

  @Test
  public void testProxy() {
    A t = (A) Proxy.newProxyInstance(A.class.getClassLoader(),
                                               new Class<?>[] { A.class, B.class },
                                               new MyInvocationHandler(new Foo()));
    t.hello("Duke");
    t.toString();
    t.hashCode();
    t.equals(t);
    t.equals(new Object());
    t.equals(null);

    ((B)t).doSomething("twitting");
  }

}
