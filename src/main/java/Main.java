public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.parseGraph("src/main/resources/example.dot");
        System.out.println(graph);
        System.out.println("Number of nodes: " + graph.getNodeCount());
        System.out.println("Number of edges: " + graph.getEdgeCount());
        graph.outputGraph("src/main/resources/output.dot");
    }
}
