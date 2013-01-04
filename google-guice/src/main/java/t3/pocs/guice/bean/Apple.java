package t3.pocs.guice.bean;

public class Apple implements Fruit
{
   private String name;

   public void setName(String name)
   {
      this.name = name;
   }
   
   public void doSomething()
   {
      if (name == null)
      {
         name = this.getClass().getName();
      }
      System.out.println("*** " + name + " is doing something ***");
   }

   public String getName()
   {
      return "apple";
   }
}