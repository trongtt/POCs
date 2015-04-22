package t3.pocs.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
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
