package t3.pocs.guice.bean;

import com.google.inject.ImplementedBy;

@ImplementedBy(Monkey.class)
public interface Animal
{
   public void doSomething();
}
