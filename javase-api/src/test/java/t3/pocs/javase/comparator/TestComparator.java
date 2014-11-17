package t3.pocs.javase.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import junit.framework.TestCase;


public class TestComparator extends TestCase {

    public void testComparator_1() {
        List<Number> list = new ArrayList<Number>();
        list.add(new Number(5));
        list.add(new Number(7));
        list.add(new Number(1));
        list.add(new Number(4));
        list.add(new Number(5));
        list.add(new Number(0));

        Collections.sort(list, new Comparator<Number>() {
            public int compare(Number arg0, Number arg1) {
                return arg0.getNumber() - arg1.getNumber();
            }
        });
    }

    class Number {
        private int n;
        
        public Number(int n) {
            this.n = n;
        }

        public int getNumber() {
            return n;
        }
    }
}
