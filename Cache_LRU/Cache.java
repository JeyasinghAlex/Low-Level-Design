public class Cache {

    private final EvictionPolicy evictionPolicy;
    private final Storage storage;

    public Cache (EvictionPolicy evictionPolicy, Storage storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public void put(String key, String value) {
        this.storage.add(key, value);
        this.evictionPolicy.keyAccessed(key);
    }

    public String get(String key) {
        String value = this.storage.get(key);
        this.evictionPolicy.keyAccessed(key);
       return value;
    }
}
