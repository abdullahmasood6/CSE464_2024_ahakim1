import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        try {
            // Feature 1: Load the graph from input.dot
            graph.loadFromDotFile("src/main/resources/input.dot");

            // Feature 2: Add a new node "i" to the graph
            graph.addNode("i");

            // Save the updated graph to feature2.dot
            graph.saveToDotFile("src/main/resources/feature2.dot");

            System.out.println("Feature 1 and Feature 2: Graph has been loaded, updated, and saved successfully.");
        } catch (IOException e) {
            System.err.println("Error processing the graph: " + e.getMessage());
        }
    }
}
