import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<String, List<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addNode(String label) {
        if (!adjacencyList.containsKey(label)) {
            adjacencyList.put(label, new ArrayList<>());
            System.out.println("Node '" + label + "' added.");
        } else {
            System.out.println("Node '" + label + "' already exists. Skipping.");
        }
    }

    public void addNodes(String[] labels) {
        for (String label : labels) {
            addNode(label);
        }
    }

    public void parseGraph(String filepath) {
        try {
            List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filepath));
            for (String line : lines) {
                if (line.contains("->")) {
                    String[] parts = line.trim().replace(";", "").split("->");
                    String src = parts[0].trim();
                    String dest = parts[1].trim();
                    addNode(src);
                    addNode(dest);
                    adjacencyList.get(src).add(dest);
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public int getNodeCount() {
        return adjacencyList.size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (List<String> edges : adjacencyList.values()) {
            count += edges.size();
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String node : adjacencyList.keySet()) {
            builder.append(node).append(" -> ").append(adjacencyList.get(node)).append("\n");
        }
        return builder.toString();
    }

    public void outputGraph(String filepath) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(filepath)) {
            writer.println("digraph {");
            for (String node : adjacencyList.keySet()) {
                for (String edge : adjacencyList.get(node)) {
                    writer.println("    " + node + " -> " + edge + ";");
                }
            }
            writer.println("}");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
