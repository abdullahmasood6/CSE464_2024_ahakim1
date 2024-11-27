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
                processDotFileLine(line.trim());
            }
        }
    }

    private void processDotFileLine(String line) {
        if (line.contains("->")) {
            String[] parts = line.replace(";", "").split("->");
            addEdge(parts[0].trim(), parts[1].trim());
        } else if (!line.isEmpty() && !line.startsWith("digraph") && !line.equals("{") && !line.equals("}")) {
            addNode(line.replace(";", "").trim());
        }
    }


    public void addNode(String node) {
        if (node == null || node.isEmpty()) {
            throw new IllegalArgumentException("Node label cannot be null or empty.");
        }
        nodes.add(node);
    }

    public void addEdge(String source, String destination) {
        validateNodeInput(source, destination);
        edges.add(new String[]{source, destination});
        addNode(source);
        addNode(destination);
    }

    private void validateNodeInput(String... nodes) {
        for (String node : nodes) {
            if (node == null || node.isEmpty()) {
                throw new IllegalArgumentException("Node input cannot be null or empty: " + Arrays.toString(nodes));
            }
        }
    }


    public void removeNode(String label) {
        if (!nodes.contains(label)) {
            throw new NoSuchElementException("Node '" + label + "' does not exist.");
        }
        nodes.remove(label);
        removeEdgesForNode(label);
    }

    private void removeEdgesForNode(String node) {
        edges.removeIf(edge -> edge[0].equals(node) || edge[1].equals(node));
    }


    public void removeNodes(String[] labels) {
        for (String label : labels) {
            removeNode(label);
        }
    }

    public void removeEdge(String srcLabel, String dstLabel) {
        boolean removed = edges.removeIf(edge -> edge[0].equals(srcLabel) && edge[1].equals(dstLabel));
        if (!removed) {
            throw new NoSuchElementException("Edge from '" + srcLabel + "' to '" + dstLabel + "' does not exist.");
        }
    }

    public Set<String> getNodes() {
        return Collections.unmodifiableSet(nodes);
    }

    public List<String[]> getEdges() {
        return Collections.unmodifiableList(new ArrayList<>(edges));
    }

    public void saveToDotFile(String filepath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("digraph {\n");
            for (String[] edge : edges) {
                writer.write("\t" + edge[0] + " -> " + edge[1] + ";\n");
            }
            for (String node : nodes) {
                if (isIsolatedNode(node)) {
                    writer.write("\t" + node + ";\n");
                }
            }
            writer.write("// Number of nodes: " + nodes.size() + "\n");
            writer.write("// Number of edges: " + edges.size());
            writer.write("\n}");
        }
    }

    private boolean isIsolatedNode(String node) {
    for (String[] edge : edges) {
        if (edge[0].equals(node) || edge[1].equals(node)) {
            return false;
        }
    }
    return true;
}
    // Part 3: BFS Path Search API
    public Path graphSearch(String src, String dst, Algorithm algo) {
    GraphSearchStrategy strategy;

    if (algo == Algorithm.BFS) {
        strategy = new BFSGraphSearchStrategy();
    } else if (algo == Algorithm.DFS) {
        strategy = new DFSGraphSearchStrategy();
    } else {
        throw new IllegalArgumentException("Unsupported algorithm: " + algo);
    }

    return strategy.search(src, dst, this);
}



    private List<String> constructPath(Map<String, String> parent, String target) {
    List<String> path = new ArrayList<>();
    while (target != null) {
        path.add(target); // Adds to the end
        target = parent.get(target);
    }
    Collections.reverse(path); // Reverses the list at the end
    return path;
}

}
