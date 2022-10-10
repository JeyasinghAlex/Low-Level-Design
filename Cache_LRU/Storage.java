import java.util.HashMap;
import java.util.Map;

public class Storage {

    private int capacity;
    private Map<String, String> map;

    public Storage(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    public String get(String key) {
        return map.get(key);
    }

    public void add(String key, String value) {

        if (map.size() == capacity) {
            // some logic
        }
        map.put(key, value);
    }

    public void remove(String key) {
        if (!map.containsKey(key)) {
            return;
        }
        map.remove(key);
    }
}
