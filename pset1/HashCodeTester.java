package pset1;
import static org.junit.Assert.*;
import org.junit.Test;
public class HashCodeTester {
/*
* P5: If two objects are equal according to the equals(Object)
* method, then calling the hashCode method on each of
* the two objects must produce the same integer result.
*/
// your test methods go here
@Test public void t0() {
    Object o = new Object();
    C c = new C(3);
    if(c.equals(o)) assertTrue(c.hashCode() == o.hashCode());
}

@Test public void t1() {
    Object o = new Object();
    D d = new D(3, 4);
    if(d.equals(o)) assertTrue(d.hashCode() == o.hashCode());
}

@Test public void t2() {
    Object o1 = new Object();
    Object o2 = new Object();
    if(o2.equals(o1)) assertTrue(o2.hashCode() == o1.hashCode());
}

@Test public void t3() {
    C c = new C(4);
    Object o = new Object();
    if(o.equals(c)) assertTrue(o.hashCode() == c.hashCode());
}

@Test public void t4() {
    C c = new C(5);
    D d = new D(5, 6);
    if(d.equals(c)) assertTrue(d.hashCode() == c.hashCode());
}

@Test public void t6() {
    C c = new C(6);
    C c2 = new C(7);
    if(c2.equals(c)) assertTrue(c2.hashCode() == c.hashCode());
}

@Test public void t7() {
    D d = new D(4, 5);
    Object o = new Object();
    if(o.equals(d)) assertTrue(o.hashCode() == d.hashCode());
}

@Test public void t8() {
    D d = new D(5, 6);
    C c = new C(5);
    if(c.equals(d)) assertTrue(c.hashCode() == d.hashCode());
}

@Test public void t9() {
    D d = new D(6, 7);
    D d2 = new D(7, 8);
    if(d2.equals(d)) assertTrue(d2.hashCode() == d.hashCode());
}
}