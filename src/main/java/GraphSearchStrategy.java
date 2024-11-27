public interface GraphSearchStrategy {
    /**
     * Search for a path between source and destination in the given graph.
     * @param src the source node
     * @param dst the destination node
     * @param graph the graph to search
     * @return the path from src to dst, or null if no path exists
     */
    Path search(String src, String dst, Graph graph);
}
