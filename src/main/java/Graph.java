import java.io.*;
import java.util.*;

public class Graph {
    private List<String[]> edges; 
    private Set<String> nodes;    

    public Graph() {
        edges = new ArrayList<>();
        nodes = new HashSet<>();
    }

    
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

    
    public void addNode(String node) {
        nodes.add(node);
    }

   
    public void addEdge(String source, String destination) {
        edges.add(new String[]{source, destination});
        addNode(source);
        addNode(destination);
    }

   
    public void saveFeature1ToDotFile(String filepath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("digraph {\n");

            
            for (String[] edge : edges) {
                writer.write("    " + edge[0] + " -> " + edge[1] + ";\n");
            }

            
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

           
            writer.write("// Number of nodes: " + nodes.size() + "\n");
            writer.write("// Number of edges: " + edges.size() + "\n");
        }
    }

    public void saveToDotFile(String filepath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("digraph {\n");

            for (String[] edge : edges) {
                writer.write("    " + edge[0] + " -> " + edge[1] + ";\n");
            }

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

    public String toDotFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n");

        for (String[] edge : edges) {
            sb.append("    ").append(edge[0]).append(" -> ").append(edge[1]).append(";\n");
        }

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
