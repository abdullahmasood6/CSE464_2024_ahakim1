import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class GraphTest {

    @Test
    public void testParseGraph() {
        Graph graph = new Graph();    
        graph.parseGraph("src/test/resources/testGraph.dot");
        assertTrue(graph.toString().contains("a -> [b]"));
        assertTrue(graph.toString().contains("b -> [c]"));
        assertTrue(graph.toString().contains("c -> [d]"));
        assertTrue(graph.toString().contains("d -> []"));
    }
}
