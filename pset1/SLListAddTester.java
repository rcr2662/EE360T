package pset1;

import static org.junit.Assert.*;
import org.junit.Test;

public class SLListAddTester {
    @Test public void test0() {
        SLList l = new SLList();
        assertTrue(l.repOk());
        l.add(true);

        // write a sequence of assertTrue method invocations that
        // perform checks on the values for all the declared fields
        // of list and node objects reachable from l
        assertTrue(l.first != null);
        // your code goes here
        assertTrue(l.last != null);
        SLList.Node node = l.first;
        while(node != null){
            assertTrue(node.elem);
            assertTrue(node.next != null);
            node = node.next;
        }
    }

    @Test public void test1() {
        SLList l = new SLList();
        assertTrue(l.repOk());
        l.add(true);
        assertTrue(l.repOk());
        l.add(false);
        assertTrue(l.repOk());
        // write a sequence of assertTrue method invocations that
        // perform checks on the values for all the declared fields
        // of list and node objects reachable from l
        assertTrue(l.first != null);
        // your code goes here
        assertTrue(l.last != null);
        SLList.Node node = l.first;
        while(node != null){
            assertTrue(node.elem);
            assertTrue(node.next != null);
            node = node.next;
        }
    }
}
