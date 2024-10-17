import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphTest {

    @Test
    public void testFeature1() throws IOException {
        Graph graph = new Graph();
        graph.loadFromDotFile("src/main/resources/input.dot");
        graph.saveToDotFile("src/test/resources/feature1.dot");

        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected-feature1.txt")));
        String actual = new String(Files.readAllBytes(Paths.get("src/test/resources/feature1.dot")));
        assertEquals(expected, actual);
    }

    @Test
    public void testFeature2() throws IOException {
        Graph graph = new Graph();
        graph.loadFromDotFile("src/main/resources/input.dot");
        graph.addNode("i");
        graph.saveToDotFile("src/test/resources/feature2.dot");

        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected-feature2.txt")));
        String actual = new String(Files.readAllBytes(Paths.get("src/test/resources/feature2.dot")));
        assertEquals(expected, actual);
    }

    @Test
    public void testFeature3() throws IOException {
        Graph graph = new Graph();
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
        Graph graph = new Graph();
        graph.loadFromDotFile("src/main/resources/input.dot");
        graph.addNode("j");
        graph.addEdge("b", "j");
        graph.saveToDotFile("src/test/resources/feature4.dot");

        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected-feature4.txt")));
        String actual = new String(Files.readAllBytes(Paths.get("src/test/resources/feature4.dot")));
        assertEquals(expected, actual);
    }
}
