package pset1;

import static org.junit.Assert.*;
import org.junit.Test;
import pset1.SLList.Node;

public class SLListRepOkTester {
    @Test public void t0() {
        SLList l = new SLList();
        assertTrue(l.repOk());
    }

    @Test public void t1() {
        SLList l = new SLList();
        Node n = new Node();
        // your code goes here
        l.first = n;
        assertFalse(l.repOk());
    }
// your code goes here

    @Test public void t2() {
        SLList l = new SLList();
        Node n = new Node();
        // your code goes here
        l.last = n;
        assertFalse(l.repOk());
    }

    @Test public void t3() {
        SLList l = new SLList();
        Node n = new Node();
        // your code goes here
        l.first = n;
        l.last = n;
        assertTrue(l.repOk());
    }

    @Test public void t4() {
        SLList l = new SLList();
        Node n = new Node();
        // your code goes here
        n.next = n;
        l.first = n;
        l.last = n;
        assertFalse(l.repOk());
    }
}
