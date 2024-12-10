import java.util.*;

public class BFSGraphSearchStrategy implements GraphSearchStrategy {
    @Override
    public Path search(String src, String dst, Graph graph) {
        Map<String, String> parent = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(src);
        parent.put(src, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(dst)) {
                return new Path(constructPath(parent, dst));
            }
            for (String[] edge : graph.getEdges()) {
                if (edge[0].equals(current) && !parent.containsKey(edge[1])) {
                    parent.put(edge[1], current);
                    queue.add(edge[1]);
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
