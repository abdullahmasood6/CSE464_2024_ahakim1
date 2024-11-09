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
                    String[] parts = line.replace(";", "").split("->");
                    addEdge(parts[0].trim(), parts[1].trim());
                } else if (!line.isEmpty() && !line.startsWith("digraph") && !line.equals("{") && !line.equals("}")) {
                    addNode(line.replace(";", "").trim());
                }
            }
        }
    }

    public void addNode(String node) {
        if (node == null || node.isEmpty()) {
            throw new IllegalArgumentException("Node label cannot be null or empty.");
        }
        nodes.add(node);
    }

    public void addEdge(String source, String destination) {
        if (source == null || destination == null || source.isEmpty() || destination.isEmpty()) {
            throw new IllegalArgumentException("Source and destination cannot be null or empty.");
        }
        edges.add(new String[]{source, destination});
        addNode(source);
        addNode(destination);
    }

    public void removeNode(String label) {
        if (!nodes.contains(label)) {
            throw new NoSuchElementException("Node '" + label + "' does not exist.");
        }
        nodes.remove(label);
        edges.removeIf(edge -> edge[0].equals(label) || edge[1].equals(label));
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
                boolean isIsolated = true;
                for (String[] edge : edges) {
                    if (edge[0].equals(node) || edge[1].equals(node)) {
                        isIsolated = false;
                        break;
                    }
                }
                if (isIsolated) {
                    writer.write("\t" + node + ";\n");
                }
            }
            writer.write("// Number of nodes: " + nodes.size() + "\n");
            writer.write("// Number of edges: " + edges.size());
            writer.write("\n}");
        }
    }

    // Graph search using specified algorithm
    public Path graphSearch(String src, String dst, Algorithm algo) {
        if (algo == Algorithm.BFS) {
            return bfsPathSearch(src, dst);
        } else if (algo == Algorithm.DFS) {
            return dfsPathSearch(src, dst);
        }
        return null;
    }

    private Path bfsPathSearch(String src, String dst) {
        if (!nodes.contains(src) || !nodes.contains(dst)) {
            return null;
        }
        Map<String, String> parent = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(src);
        parent.put(src, null);
        String current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            if (current.equals(dst)) {
                return new Path(constructPath(parent, dst));
            }
            for (String[] edge : edges) {
                if (edge[0].equals(current) && !parent.containsKey(edge[1])) {
                    parent.put(edge[1], current);
                    queue.add(edge[1]);
                }
            }
        }
        return null;
    }

    private Path dfsPathSearch(String src, String dst) {
        if (!nodes.contains(src) || !nodes.contains(dst)) {
            return null;
        }
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();
        dfs(src, dst, parent, visited);
        return parent.containsKey(dst) ? new Path(constructPath(parent, dst)) : null;
    }

    private void dfs(String current, String dst, Map<String, String> parent, Set<String> visited) {
        visited.add(current);
        for (String[] edge : edges) {
            if (edge[0].equals(current) && !visited.contains(edge[1])) {
                parent.put(edge[1], current);
                if (edge[1].equals(dst)) {
                    return;
                }
                dfs(edge[1], dst, parent, visited);
            }
        }
    }

    private List<String> constructPath(Map<String, String> parent, String target) {
        LinkedList<String> path = new LinkedList<>();
        for (String at = target; at != null; at = parent.get(at)) {
            path.addFirst(at);
        }
        return path;
    }

    // Inner Path class to represent a path from src to dst
    public static class Path {
        private List<String> nodes;

        public Path(List<String> nodes) {
            this.nodes = nodes;
        }

        public List<String> getNodes() {
            return nodes;
        }

        @Override
        public String toString() {
            return String.join(" -> ", nodes);
        }
    }
}
