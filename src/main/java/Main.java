import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        try {
            graph.loadFromDotFile("src/main/resources/input.dot");
            graph.saveToDotFile("src/main/resources/feature1.dot");
            System.out.println("DOT file with node and edge count generated successfully!");
        } catch (IOException e) {
            System.err.println("Error processing graph due to an I/O exception: " + e.getMessage());
            e.printStackTrace();  // This will print the stack trace to help identify where the error occurred.
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();  // This will also print the stack trace for more detailed error information.
        }
    }
}
