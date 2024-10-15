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
        assertEquals(4, graph.getNodeCount());
        assertEquals(3, graph.getEdgeCount());
    }

    @Test
    public void testAddNodeAndAddNodes() {
        graph.addNode("x");
        assertTrue(graph.toString().contains("x -> []"));
        graph.addNode("x");
        assertEquals(1, graph.getNodeCount());

        String[] nodes = {"y", "z", "x"};
        graph.addNodes(nodes);
        assertTrue(graph.toString().contains("y -> []"));
        assertTrue(graph.toString().contains("z -> []"));
        assertEquals(3, graph.getNodeCount());
    }

    @Test
    public void testAddEdgeAndAddEdges() {
        graph.addNode("x");
        graph.addNode("y");
        graph.addEdge("x", "y");
        assertTrue(graph.toString().contains("x -> [y]"));

        String[][] edges = {{"y", "z"}, {"z", "x"}};
        graph.addEdges(edges);
        assertTrue(graph.toString().contains("y -> [z]"));
        assertTrue(graph.toString().contains("z -> [x]"));
    }
}
