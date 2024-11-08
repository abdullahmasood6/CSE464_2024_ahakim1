import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    private Graph graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph();
    }

    // Tests from Part 1
    @Test
    public void testFeature1() throws IOException {
        graph.loadFromDotFile("src/main/resources/input.dot");
        graph.saveToDotFile("src/test/resources/feature1.dot");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected-feature1.txt")));
        String actual = new String(Files.readAllBytes(Paths.get("src/test/resources/feature1.dot")));
        assertEquals(expected, actual);
    }

    @Test
    public void testFeature2() throws IOException {
        graph.loadFromDotFile("src/main/resources/input.dot");
        graph.addNode("i");
        graph.saveToDotFile("src/test/resources/feature2.dot");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected-feature2.txt")));
        String actual = new String(Files.readAllBytes(Paths.get("src/test/resources/feature2.dot")));
        assertEquals(expected, actual);
    }

    @Test
    public void testFeature3() throws IOException {
        graph.loadFromDotFile("src/main/resources/input.dot");
        graph.addNode("l");
        graph.addEdge("g", "l");
        graph.saveToDotFile("src/test/resources/feature3.dot");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected-feature3.txt")));
        String actual = new String(Files.readAllBytes(Paths.get("src/test/resources/feature3.dot")));
        assertEquals(expected, actual);
    }

    @Test
    public void testFeature4() throws IOException {
        graph.loadFromDotFile("src/main/resources/input.dot");
        graph.addNode("j");
        graph.addEdge("b", "j");
        graph.saveToDotFile("src/test/resources/feature4.dot");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected-feature4.txt")));
        String actual = new String(Files.readAllBytes(Paths.get("src/test/resources/feature4.dot")));
        assertEquals(expected, actual);
    }

    // Tests from Part 2
    @Test
    public void testAddNode() {
        String newNode = "z";
        graph.addNode(newNode);
        assertTrue(graph.getNodes().contains(newNode), "The graph should contain the newly added node.");
    }

    @Test
    public void testRemoveNode() {
        String newNode = "z";
        graph.addNode(newNode);
        graph.removeNode(newNode);
        assertFalse(graph.getNodes().contains(newNode), "The graph should no longer contain the removed node.");
    }

    @Test
    public void testRemoveNonexistentNode() {
        assertThrows(NoSuchElementException.class, () -> graph.removeNode("nonexistent"), "Should throw an exception when trying to remove a non-existent node.");
    }

    @Test
    public void testAddAndRemoveEdge() {
        String source = "a";
        String destination = "z";
        graph.addNode(source);  // Ensuring source exists
        graph.addNode(destination);  // Ensuring destination exists
        graph.addEdge(source, destination);
        assertTrue(graph.getEdges().stream().anyMatch(e -> e[0].equals(source) && e[1].equals(destination)), "The graph should contain the newly added edge.");
        graph.removeEdge(source, destination);
        assertFalse(graph.getEdges().stream().anyMatch(e -> e[0].equals(source) && e[1].equals(destination)), "The graph should no longer contain the removed edge.");
    }

    @Test
    public void testRemoveNonexistentEdge() {
        assertThrows(NoSuchElementException.class, () -> graph.removeEdge("nonexistentSource", "nonexistentDest"), "Should throw an exception when trying to remove a non-existent edge.");
    }

    // BFS Path Search Test
    @Test
    public void testPathGraphSearch() {
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        List<String> path = graph.pathGraphSearch("a", "c");
        assertNotNull(path, "The path should not be null");
        assertEquals(3, path.size(), "Path should include three nodes.");
        assertEquals("a", path.get(0), "Path should start with node 'a'");
        assertEquals("c", path.get(2), "Path should end with node 'c'");
    }
}
