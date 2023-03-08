package pset1;

import static org.junit.Assert.*;
import org.junit.Test;

public class EqualsTester {
/*
* P1: For any non-null reference value x, x.equals(null) should return false.
*/
    @Test public void t0() {
        assertFalse(new Object().equals(null));
    }
// your test methods for P1 go here
    @Test public void t1() {
        assertFalse(new C(1).equals(null));
    }

    @Test public void t2() {
        assertFalse(new D(1, 2).equals(null));
        }
/*
* P2: It is reflexive: for any non-null reference value x, x.equals(x)
* should return true.
*/
// your test methods for P2 go here
    @Test public void t3() {
        Object o = new Object();
        assertTrue(o.equals(o));
    }

    @Test public void t4() {
        C c = new C(2);
        assertTrue(c.equals(c));
    }

    @Test public void t5() {
        D d = new D(2, 3);
        assertTrue(d.equals(d));
    }
/*
* P3: It is symmetric: for any non-null reference values x and y, x.equals(y)
* should return true if and only if y.equals(x) returns true.
*/
// your test methods for P3 go here
    @Test public void t6() {
        Object o = new Object();
        C c = new C(3);
        if(c.equals(o)) assertTrue(o.equals(c));
    }

    @Test public void t7() {
        Object o = new Object();
        D d = new D(3, 4);
        if(d.equals(o)) assertTrue(o.equals(d));
    }

    @Test public void t8() {
        Object o1 = new Object();
        Object o2 = new Object();
        if(o1.equals(o2)) assertTrue(o2.equals(o1));
    }

    @Test public void t9() {
        C c = new C(4);
        Object o = new Object();
        if(o.equals(c)) assertTrue(c.equals(o));
    }

    @Test public void t10() {
        C c = new C(5);
        D d = new D(5, 6);
        if(d.equals(c)) assertTrue(c.equals(d));
    }

    @Test public void t11() {
        C c = new C(6);
        C c2 = new C(7);
        if(c2.equals(c)) assertTrue(c.equals(c2));
    }

    @Test public void t12() {
        D d = new D(4, 5);
        Object o = new Object();
        if(o.equals(d)) assertTrue(d.equals(o));
    }

    @Test public void t13() {
        D d = new D(5, 6);
        C c = new C(5);
        if(c.equals(d)) assertTrue(d.equals(c));
    }

    @Test public void t14() {
        D d = new D(6, 7);
        D d2 = new D(7, 8);
        if(d2.equals(d)) assertTrue(d.equals(d2));
    }
    



/*
* P4: It is transitive: for any non-null reference values x, y, and z,
* if x.equals(y) returns true and y.equals(z) returns true, then
* x.equals(z) should return true.
*/
// you do not need to write tests for P4
}