package algorithms;

import models.Node;
import models.Request;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

public class ConsistentHashing implements Router {

    private final Map<Node, List<Long>> nodePositions;
    private final ConcurrentSkipListMap<Long, Node> nodeMappings;
    private final Function<String, Long> hashFunction;
    private final int pointMultiplier;

    public ConsistentHashing(final Function<String, Long> hashFunction,
                             final int pointMultiplier) {
        if (pointMultiplier == 0) {
            throw new IllegalArgumentException();
        }
        this.pointMultiplier = pointMultiplier;
        this.hashFunction = hashFunction;
        this.nodePositions = new ConcurrentHashMap<>();
        this.nodeMappings = new ConcurrentSkipListMap<>();
    }

    @Override
    public void addNode(Node node) {
        nodePositions.put(node, new CopyOnWriteArrayList<>());
        for (int i = 0; i < pointMultiplier; ++i) {
            for (int j = 0; j < node.getWeight(); ++j) {
                Long point = hashFunction.apply((i * pointMultiplier + j) + node.getId());
                nodePositions.get(node).add(point);
                nodeMappings.put(point, node);
            }
        }
    }

    @Override
    public void removeNode(Node node) {
        for (Long point : nodePositions.remove(node)) {
            nodeMappings.remove(point);
        }
    }

    @Override
    public Node getAssignedNode(Request request) {
        Long key = hashFunction.apply(request.getId());
        Map.Entry<Long, Node> entry = nodeMappings.higherEntry(key);
        if (entry == null) {
            return nodeMappings.firstEntry().getValue();
        }
        return entry.getValue();
    }

    @Override
    public String toString() {
        return "ConsistentHashing{" +
                "nodePositions=" + nodePositions +
                ", nodeMappings=" + nodeMappings +
                ", hashFunction=" + hashFunction +
                ", pointMultiplier=" + pointMultiplier +
                '}';
    }
}