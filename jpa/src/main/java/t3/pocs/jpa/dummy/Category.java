package t3.pocs.jpa.dummy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * A dummy Entity which has the same class name with /pocs-jpa/src/main/java/t3/pocs/jpa/Category.java<br>
 * So we need to name it specifically by using <code>name</code> property of Entity annotation.
 *
 * @author <a href="trongtt@gmail.com">Trong Tran</a>
 * @version $Revision$
 */
@Entity(name="dummyCategory")
public class Category {

  @Id
  @GeneratedValue
  private long   id;

  private String name;

  private long   parentId;

  public Category() {
  }

  public Category(String name) {
    this.name = name;
  }

  public Category(String name, long parentId) {
    this.name = name;
    this.parentId = parentId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getParentId() {
    return parentId;
  }

  public void setParentId(long parentId) {
    this.parentId = parentId;
  }
}
