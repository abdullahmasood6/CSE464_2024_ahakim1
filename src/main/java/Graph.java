import java.io.*;
import java.util.*;

public class Graph {
    private List<String[]> edges; // List to store edges in the order they are added
    private Set<String> nodes;    // Set to store nodes

    public Graph() {
        edges = new ArrayList<>();
        nodes = new HashSet<>();
    }

    // Feature 1: Load graph from a DOT file
    public void loadFromDotFile(String filepath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.contains("->")) {
                    // Parse edges in the DOT file
                    String[] edgeParts = line.replace(";", "").split("->");
                    String source = edgeParts[0].trim();
                    String destination = edgeParts[1].trim();
                    addEdge(source, destination);
                } else if (!line.contains("digraph") && !line.contains("{") && !line.contains("}")) {
                    // Parse standalone nodes in the DOT file
                    String node = line.replace(";", "").trim();
                    addNode(node);
                }
            }
        }
    }

    // Feature 2: Add a new node to the graph
    public void addNode(String node) {
        nodes.add(node);
    }

    // Add an edge between two nodes
    public void addEdge(String source, String destination) {
        edges.add(new String[]{source, destination});
        addNode(source);
        addNode(destination);
    }

    // Output the graph to a DOT file
    public void saveToDotFile(String filepath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("digraph {\n");

            // Write the edges
            for (String[] edge : edges) {
                writer.write("    " + edge[0] + " -> " + edge[1] + ";\n");
            }

            // Write only truly isolated nodes (those without any incoming or outgoing edges)
            Set<String> nodesInEdges = new HashSet<>();
            for (String[] edge : edges) {
                nodesInEdges.add(edge[0]); // source node
                nodesInEdges.add(edge[1]); // destination node
            }

            // Find nodes that are not part of any edges (neither source nor destination)
            for (String node : nodes) {
                if (!nodesInEdges.contains(node)) {
                    writer.write("    " + node + ";\n");
                }
            }

            writer.write("}\n");
        }
    }

    // Print the graph structure in DOT format (used for debugging or console output)
    public String toDotFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n");

        // Append edges
        for (String[] edge : edges) {
            sb.append("    ").append(edge[0]).append(" -> ").append(edge[1]).append(";\n");
        }

        // Append only truly isolated nodes
        Set<String> nodesInEdges = new HashSet<>();
        for (String[] edge : edges) {
            nodesInEdges.add(edge[0]); // source node
            nodesInEdges.add(edge[1]); // destination node
        }

        for (String node : nodes) {
            if (!nodesInEdges.contains(node)) {
                sb.append("    ").append(node).append(";\n");
            }
        }

        sb.append("}\n");
        return sb.toString();
    }
}
