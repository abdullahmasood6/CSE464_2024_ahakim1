import java.util.*;

public class DFSGraphSearchStrategy implements GraphSearchStrategy {
    @Override
    public Path search(String src, String dst, Graph graph) {
        Map<String, String> parent = new HashMap<>();
        Stack<String> stack = new Stack<>();
        stack.push(src);
        parent.put(src, null);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (current.equals(dst)) {
                return new Path(constructPath(parent, dst));
            }
            for (String[] edge : graph.getEdges()) {
                if (edge[0].equals(current) && !parent.containsKey(edge[1])) {
                    parent.put(edge[1], current);
                    stack.push(edge[1]);
                }
            }
        }
        return null;
    }

    private List<String> constructPath(Map<String, String> parent, String dst) {
        List<String> path = new ArrayList<>();
        for (String at = dst; at != null; at = parent.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
