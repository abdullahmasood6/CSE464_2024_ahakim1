import java.io.*;
import java.util.*;

public class Graph {
    private List<String[]> edges; // List to store edges in the order they are added
    private Set<String> nodes;    // Set to store nodes (order doesn't matter for nodes)

    public Graph() {
        edges = new ArrayList<>();
        nodes = new HashSet<>();
    }

    // Load graph from a DOT file (Feature 1)
    public void loadFromDotFile(String filepath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.contains("->")) {
                    String[] nodes = line.replace(";", "").split("->");
                    String source = nodes[0].trim();
                    String dest = nodes[1].trim();
                    addEdge(source, dest);
                } else if (!line.contains("digraph") && !line.contains("{") && !line.contains("}")) {
                    addNode(line.replace(";", "").trim());
                }
            }
        }
    }

    // Add a node to the graph (Feature 2)
    public void addNode(String node) {
        nodes.add(node);
    }

    // Add an edge between two nodes (Feature 3)
    public void addEdge(String source, String destination) {
        edges.add(new String[]{source, destination});
        addNode(source);
        addNode(destination);
    }

    // Output the graph to a DOT file (Feature 4)
    public void saveToDotFile(String filepath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("digraph {\n");

            // Write the edges in the order they were added
            for (String[] edge : edges) {
                writer.write("    " + edge[0] + " -> " + edge[1] + ";\n");
            }

            // Write isolated nodes (those not involved in any edge)
            for (String node : nodes) {
                boolean isIsolated = true;
                for (String[] edge : edges) {
                    if (edge[0].equals(node) || edge[1].equals(node)) {
                        isIsolated = false;
                        break;
                    }
                }
                if (isIsolated) {
                    writer.write("    " + node + ";\n");
                }
            }

            writer.write("}\n");
        }
    }

    // Print the graph structure in DOT format
    public String toDotFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n");

        // Append edges
        for (String[] edge : edges) {
            sb.append("    ").append(edge[0]).append(" -> ").append(edge[1]).append(";\n");
        }

        // Append isolated nodes
        for (String node : nodes) {
            boolean isIsolated = true;
            for (String[] edge : edges) {
                if (edge[0].equals(node) || edge[1].equals(node)) {
                    isIsolated = false;
                    break;
                }
            }
            if (isIsolated) {
                sb.append("    ").append(node).append(";\n");
            }
        }

        sb.append("}\n");
        return sb.toString();
    }
}
