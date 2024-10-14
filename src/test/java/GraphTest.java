import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTest {

    private Graph graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph();
    }

   
    @Test
    public void testParseGraph() {
        graph.parseGraph("src/test/resources/testGraph.dot");
        assertTrue(graph.toString().contains("a -> [b]"));
        assertTrue(graph.toString().contains("b -> [c]"));
        assertTrue(graph.toString().contains("c -> [d]"));
        assertTrue(graph.toString().contains("d -> []"));
        assertEquals(4, graph.getNodeCount(), "Node count should be 4");
        assertEquals(3, graph.getEdgeCount(), "Edge count should be 3");
    }

    
    @Test
    public void testAddNodeAndAddNodes() {
       
        graph.addNode("x");
        assertTrue(graph.toString().contains("x -> []"));
        graph.addNode("x");
        assertEquals(1, graph.getNodeCount(), "Node 'x' should not be duplicated");

        String[] nodes = {"y", "z", "x"};
        graph.addNodes(nodes);
        assertTrue(graph.toString().contains("y -> []"));
        assertTrue(graph.toString().contains("z -> []"));
        assertEquals(3, graph.getNodeCount(), "Total nodes should be 3 (without duplicates)");
    }
}
