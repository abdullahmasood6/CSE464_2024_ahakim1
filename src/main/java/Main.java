import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();

        try {
            
            graph.loadFromDotFile("src/main/resources/input.dot");
            graph.saveToDotFile("src/main/resources/feature1.dot");

            System.out.println("DOT files generated successfully!");

        } catch (IOException e) {
            System.err.println("Error processing graph: " + e.getMessage());
        }
    }
}
