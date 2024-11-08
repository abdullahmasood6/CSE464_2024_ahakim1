import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

 public class Path {
        private final List<String> nodes;

        public Path(List<String> nodes) {
            this.nodes = nodes;
        }

        @Override
        public String toString() {
            return String.join(" -> ", nodes);
        }

        public List<String> getNodes() {
            return Collections.unmodifiableList(nodes);
        }
    }
