import java.util.HashMap;
import java.util.Map;

public class EvictionPolicy {

    private DoubleLinkedList dll;
    private Map<String, Node> mapper;

    public EvictionPolicy() {
        this.dll = new DoubleLinkedList();
        this.mapper = new HashMap<>();
    }

    public void keyAccessed(String key) {
        remove(mapper.get(key));
        insert(mapper.get(key));
    }

    public void evictKey() {

    }

    private void remove(Node node) {
        mapper.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insert(Node node) {

    }
}
