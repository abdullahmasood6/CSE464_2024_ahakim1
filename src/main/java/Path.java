import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<String> nodes;

    public Path(List<String> nodes) {
        this.nodes = nodes;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public String getNode(int index) {
        return nodes.get(index);
    }

    public int size() {
        return nodes.size();
    }
}