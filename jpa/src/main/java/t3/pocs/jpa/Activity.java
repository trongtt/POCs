package t3.pocs.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Activity {

  @Id
  @GeneratedValue
  private long   id;

  private String name;

  private String value;

  public Activity() {
  }

  public Activity(String name) {
    this.name = name;
  }

  public Activity(String name, String value) {
    this.name = name;
    this.value = value;
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

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
