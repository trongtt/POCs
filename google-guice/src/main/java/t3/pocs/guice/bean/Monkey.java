package t3.pocs.guice.bean;

public class Monkey implements Animal
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
}