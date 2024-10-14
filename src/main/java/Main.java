public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.parseGraph("src/main/resources/example.dot");
        System.out.println("Graph after parsing DOT file:");
        System.out.println(graph);

        String[] newNodes = {"e", "f", "g"};
        System.out.println("\nAdding nodes:");
        graph.addNodes(newNodes);

        System.out.println("\nGraph after adding nodes:");
        System.out.println(graph);

        graph.outputGraph("src/main/resources/output.dot");
    }
}
