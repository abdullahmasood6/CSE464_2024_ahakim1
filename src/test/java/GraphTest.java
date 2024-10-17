import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphTest {

    // Test case for Feature 1: Load and save the graph without modifications
    @Test
    public void testFeature1() throws IOException {
        // Create a graph object and load the DOT file (input.dot)
        Graph graph = new Graph();
        graph.loadFromDotFile("src/main/resources/input.dot");

        // Save the output to feature1.dot
        graph.saveToDotFile("src/test/resources/feature1.dot");

        // Read the expected output from expected-feature1.txt
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected-feature1.txt")));

        // Read the actual output from feature1.dot
        String actual = new String(Files.readAllBytes(Paths.get("src/test/resources/feature1.dot")));

        // Assert that the actual output matches the expected output
        assertEquals(expected.trim(), actual.trim());
    }

    // Test case for Feature 2: Add a new node "i" to the graph and save it
    @Test
    public void testFeature2() throws IOException {
        // Create a graph object and load the DOT file (input.dot)
        Graph graph = new Graph();
        graph.loadFromDotFile("src/main/resources/input.dot");

        // Feature 2: Add a new node "i" to the graph
        graph.addNode("i");

        // Save the output to feature2.dot
        graph.saveToDotFile("src/test/resources/feature2.dot");

        // Read the expected output from expected-feature2.txt
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected-feature2.txt")));

        // Read the actual output from feature2.dot
        String actual = new String(Files.readAllBytes(Paths.get("src/test/resources/feature2.dot")));

        // Assert that the actual output matches the expected output
        assertEquals(expected.trim(), actual.trim());
    }
}
