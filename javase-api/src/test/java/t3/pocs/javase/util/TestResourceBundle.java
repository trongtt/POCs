package t3.pocs.javase.util;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

import junit.framework.Assert;

public class TestResourceBundle {

  /**
   * Conclusions:
   * - Space character needs to be escaped in the key
   */
  @Test
  public void testResourceBundle() {
    ResourceBundle rb = ResourceBundle.getBundle("t3.pocs.rb");
    Assert.assertEquals("default language", rb.getString("t3.pocs.javase.language"));
    Assert.assertEquals("Tran The Trong", rb.getString("t3.pocs.javase.full name"));

    rb = ResourceBundle.getBundle("t3.pocs.rb", new Locale("vi"));
    Assert.assertEquals("tieng viet", rb.getString("t3.pocs.javase.language"));
  }
}
