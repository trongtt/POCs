package t3.pocs.guice.bean;

import com.google.inject.ImplementedBy;

@ImplementedBy(Apple.class)
public interface Fruit
{
   String getName();
}