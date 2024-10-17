import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();

        try {
            graph.loadFromDotFile("src/main/resources/input.dot");
            graph.saveFeature1ToDotFile("src/main/resources/feature1.dot");

            System.out.println("DOT file with node and edge count generated successfully!");

        } catch (IOException e) {
            System.err.println("Error processing graph: " + e.getMessage());
        }
    }
}
