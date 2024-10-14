import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<String, List<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addNode(String node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String source, String destination) {
        adjacencyList.get(source).add(destination);
    }

    public void parseGraph(String filepath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filepath));
            for (String line : lines) {
                if (line.contains("->")) {
                    String[] parts = line.trim().replace(";", "").split("->");
                    String src = parts[0].trim();
                    String dest = parts[1].trim();
                    addNode(src);
                    addNode(dest);
                    addEdge(src, dest);
                }
            }
        } catch (IOException e) {
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
        try (PrintWriter writer = new PrintWriter(filepath)) {
            writer.println("digraph {");
            for (String node : adjacencyList.keySet()) {
                for (String edge : adjacencyList.get(node)) {
                    writer.println("    " + node + " -> " + edge + ";");
                }
            }
            writer.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
