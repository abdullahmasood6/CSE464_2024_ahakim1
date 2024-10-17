import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();

        try {
            // Feature 1: Load the graph from input.dot
            graph.loadFromDotFile("src/main/resources/input.dot");
            graph.saveToDotFile("src/main/resources/feature1.dot");

            // Feature 2: Add a node 'i'
            graph.addNode("i");
            graph.saveToDotFile("src/main/resources/feature2.dot");

            // Feature 3: Add an edge from 'a' to 'i'
            graph.addEdge("a", "i");
            graph.saveToDotFile("src/main/resources/feature3.dot");

            // Feature 4: Save the final graph as feature4.dot
            graph.saveToDotFile("src/main/resources/feature4.dot");

            System.out.println("DOT files generated successfully!");

        } catch (IOException e) {
            System.err.println("Error processing graph: " + e.getMessage());
        }
    }
}
