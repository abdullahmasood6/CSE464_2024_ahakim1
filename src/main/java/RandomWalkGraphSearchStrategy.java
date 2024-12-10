import java.util.*;

public class RandomWalkGraphSearchStrategy implements GraphSearchStrategy {
    private static final int MAX_ITERATIONS = 100; // Limit to prevent infinite loops

    @Override
    public Path search(String src, String dst, Graph graph) {
        List<String> path = new ArrayList<>();
        path.add(src); // Start with the source node
        Random random = new Random();
        String current = src;

        for (int i = 0; i < MAX_ITERATIONS; i++) {
            // Check if we have reached the destination
            if (current.equals(dst)) {
                return new Path(path);
            }

            // Get neighbors of the current node
            List<String> neighbors = getNeighbors(current, graph);
            if (neighbors.isEmpty()) {
                break; // No more nodes to visit
            }

            // Choose a random neighbor to visit next
            current = neighbors.get(random.nextInt(neighbors.size()));
            path.add(current);
        }

        // Return null if destination is not reached
        return null;
    }

    private List<String> getNeighbors(String node, Graph graph) {
        List<String> neighbors = new ArrayList<>();
        for (String[] edge : graph.getEdges()) {
            if (edge[0].equals(node)) {
                neighbors.add(edge[1]);
            }
        }
        return neighbors;
    }
}
