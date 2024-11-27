import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    private Graph graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph();
    }

    // Tests for Part 1 Features
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

    // Tests for Part 2 Features
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

    // Test for DFS Path Search
    @Test
    public void testBFS() {
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("A", "D");
        graph.addEdge("D", "E");

        Path result = graph.graphSearch("A", "C", Algorithm.BFS);
        assertEquals(Arrays.asList("A", "B", "C"), result.getNodes());
    }

    @Test
    public void testDFS() {
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("A", "D");
        graph.addEdge("D", "E");

        Path result = graph.graphSearch("A", "C", Algorithm.DFS);
        assertEquals(Arrays.asList("A", "B", "C"), result.getNodes());
    }

    @Test
    public void testRandomWalk() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("a", "e");
        graph.addEdge("e", "g");
        graph.addEdge("g", "h");
        graph.addEdge("b", "c");
        graph.addEdge("e", "f");

        // Run random walk multiple times to observe randomness
        for (int i = 0; i < 5; i++) {
            Path result = graph.graphSearch("a", "c", Algorithm.RANDOM_WALK);
            System.out.println("Random Walk Path: " + result);
        }
    }

}
