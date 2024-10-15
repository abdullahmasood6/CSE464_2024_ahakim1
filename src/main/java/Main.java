public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.parseGraph("src/main/resources/example.dot");
        System.out.println("Graph after parsing DOT file:");
        System.out.println(graph);

        String[] newNodes = {"e", "f", "g"};
        System.out.println("\nAdding nodes:");
        graph.addNodes(newNodes);

        String[][] newEdges = {{"e", "f"}, {"f", "g"}, {"g", "e"}};
        System.out.println("\nAdding edges:");
        graph.addEdges(newEdges);

        System.out.println("\nGraph after adding nodes and edges:");
        System.out.println(graph);

        System.out.println("\nRemoving edge 'e -> f':");
        graph.removeEdge("e", "f");
        System.out.println(graph);

        System.out.println("\nRemoving node 'g':");
        graph.removeNode("g");
        System.out.println(graph);

        graph.outputGraph("src/main/resources/output.dot");
    }
}
