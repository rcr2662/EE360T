package pset5;
import static org.junit.Assert.*;
import java.util.TreeSet;
import java.util.Set;
import org.junit.Test;

public class GraphTester {
    // tests for method "addEdge" in class "Graph"
    @Test public void tae0() {
        Graph g = new Graph(2);
        g.addEdge(0, 1);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 2\nedges: [[false, true], [false, false]]");
    }

    @Test public void tae1() {
        Graph g = new Graph(3);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 3\nedges: [[false, true, false], [false, false, true], [false, false, false]]");
    }

    @Test public void tae2() {
        Graph g = new Graph(3);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 3\nedges: [[false, true, true], [false, false, false], [false, false, false]]");
    }

    @Test public void tae3() {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(2, 3);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 4\nedges: [[false, true, false, false], [false, false, false, false], [false, false, false, true], [false, false, false, false]]");
    }

    @Test public void tae4() {
        Graph g = new Graph(2);
        g.addEdge(0, 1);
        g.addEdge(1, 0);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 2\nedges: [[false, true], [true, false]]");
    }
    // your tests for method "addEdge" in class "Graph" go here
    // you must provide at least 4 test methods;
    // each test method has at least 1 invocation of addEdge;
    // each test method creates exactly 1 graph
    // each test method creates a unique graph w.r.t. "equals" method
    // each test method has at least 1 test assertion;
    // your test methods provide full statement coverage of your
    // implementation of addEdge and any helper methods
    // no test method directly invokes any method that is not
    // declared in the Graph class as given in this homework
    // ...
    // tests for method "reachable" in class "Graph"
    @Test public void tr0() {
        Graph g = new Graph(1);
        Set<Integer> nodes = new TreeSet<Integer>();
        nodes.add(0);
        assertTrue(g.reachable(nodes, nodes));
    }

    @Test public void tr1() {
        Graph g = new Graph(1);
        Set<Integer> nodes = new TreeSet<Integer>();
        nodes.add(1);
        assertFalse(g.reachable(nodes, nodes));
    }

    @Test public void tr2() {
        Graph g = new Graph(3);
        g.addEdge(1, 2);
        Set<Integer> sources = new TreeSet<Integer>();
        Set<Integer> targets = new TreeSet<Integer>();
        sources.add(1);
        targets.add(2);
        assertTrue(g.reachable(sources, targets));
    }

    @Test public void tr3() {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        Set<Integer> sources = new TreeSet<Integer>();
        Set<Integer> targets = new TreeSet<Integer>();
        sources.add(3);
        targets.add(0);
        assertFalse(g.reachable(sources, targets));
    }

    @Test public void tr4() {
        Graph g = new Graph(25);
        g.addEdge(0, 5);
        g.addEdge(5, 15);
        g.addEdge(15, 24);
        Set<Integer> sources = new TreeSet<Integer>();
        Set<Integer> targets = new TreeSet<Integer>();
        sources.add(5);
        targets.add(24);
        assertTrue(g.reachable(sources, targets));
    }

    @Test public void tr5() {
        Graph g = new Graph(6);
        g.addEdge(0, 5);
        g.addEdge(5, 0);
        Set<Integer> sources = new TreeSet<Integer>();
        Set<Integer> targets = new TreeSet<Integer>();
        sources.add(0);
        targets.add(5);
        assertTrue(g.reachable(sources, targets));
    }

    @Test public void tr6() {
        Graph g = new Graph(20);
        g.addEdge(0, 6);
        g.addEdge(7, 12);
        g.addEdge(13, 15);
        Set<Integer> sources = new TreeSet<Integer>();
        Set<Integer> targets = new TreeSet<Integer>();
        sources.add(5);
        targets.add(21);
        assertFalse(g.reachable(sources, targets));
    }

    // your tests for method "reachable" in class "Graph" go here
    // you must provide at least 6 test methods;
    // each test method must have at least 1 invocation of reachable;
    // each test method must have at least 1 test assertion;
    // at least 2 test methods must have at least 1 invocation of addEdge;
    // your test methods must provide full statement coverage of your
    // implementation of reachable and any helper methods
    // no test method directly invokes any method that is not
    // declared in the Graph class as given in this homework
    // ...
}
