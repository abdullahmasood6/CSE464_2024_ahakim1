import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        try {
            // Load the graph data from a DOT file. Ensure the path is correct relative to your project structure.
            graph.loadFromDotFile("src/main/resources/input.dot");

            // Save the graph data to another DOT file with node and edge counts. Adjust the path as necessary.
            graph.saveToDotFile("src/main/resources/feature1.dot");

            // Print a success message to the console to indicate that the operations were successful.
            System.out.println("DOT file with node and edge count generated successfully!");
        } catch (IOException e) {
            // Handle possible I/O errors during file operations. This block catches any IOExceptions thrown during reading or writing files.
            System.err.println("Error processing graph due to an I/O exception: " + e.getMessage());
            e.printStackTrace();  // This will print the stack trace to help identify where the error occurred.
        } catch (Exception e) {
            // Handle any other exceptions that might be thrown by the Graph methods. This could include runtime exceptions like IllegalArgumentException.
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();  // This will also print the stack trace for more detailed error information.
        }
    }
}
