import algorithms.ConsistentHashing;
import algorithms.Router;
import algorithms.WeightedRoundRobin;
import models.Node;
import models.Request;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RouterTester {

    private String ipAddress = "127.0. 0.1", serviceId = "service", method = "method";

    @Test
    public void defaultRoundRobin() {
        final Router router = new WeightedRoundRobin();
        final Node node1 = newNode("node-1"), node2 = newNode("node-2"), node3 = newNode("node-3");
        router.addNode(node1);
        router.addNode(node2);
        router.addNode(node3);

        Assert.assertEquals(node1, router.getAssignedNode(newRequest("r-111")));
        Assert.assertEquals(node2, router.getAssignedNode(newRequest("r-222")));
        Assert.assertEquals(node3, router.getAssignedNode(newRequest("r-333")));

        router.removeNode(node1);

        Assert.assertEquals(node2, router.getAssignedNode(newRequest("r-333")));
        Assert.assertEquals(node3, router.getAssignedNode(newRequest("r-444")));
        Assert.assertEquals(node2, router.getAssignedNode(newRequest("r-555")));
        Assert.assertEquals(node3, router.getAssignedNode(newRequest("r-666")));

        final Node node4 = new Node("node-4", ipAddress, 2);
        router.addNode(node4);
        Assert.assertEquals(node4, router.getAssignedNode(newRequest("r-123")));
        Assert.assertEquals(node4, router.getAssignedNode(newRequest("r-124")));
        Assert.assertEquals(node2, router.getAssignedNode(newRequest("r-125")));
        Assert.assertEquals(node3, router.getAssignedNode(newRequest("r-126")));
        Assert.assertEquals(node4, router.getAssignedNode(newRequest("r-127")));

    }

    @Test
    public void defaultConsistentHashing() throws IllegalAccessException {
        final List<Long> hashes = new ArrayList<>();
        hashes.add(1L);
        hashes.add(11L);
        hashes.add(21L);
        hashes.add(31L);

        final Function<String, Long> hashFunction =  id -> {
            if (id.contains("000000")) {
                return hashes.remove(0);
            } else {
                return Long.parseLong(id);
            }
        };

        final Router router = new ConsistentHashing(hashFunction, 1);
        final Node node1 = newNode("1000000"), node2 = newNode("2000000"), node3 = newNode("3000000");
        router.addNode(node1);
        router.addNode(node2);
        router.addNode(node3);

        Assert.assertEquals(node1, router.getAssignedNode(newRequest("30")));
        Assert.assertEquals(node2, router.getAssignedNode(newRequest("4")));
        Assert.assertEquals(node3, router.getAssignedNode(newRequest("17")));

        router.removeNode(node1);

        Assert.assertEquals(node2, router.getAssignedNode(newRequest("23")));
        Assert.assertEquals(node3, router.getAssignedNode(newRequest("12")));
        Assert.assertEquals(node2, router.getAssignedNode(newRequest("24")));
        Assert.assertEquals(node3, router.getAssignedNode(newRequest("19")));

        final Node node4 = newNode("4000000");
        router.addNode(node4);

        Assert.assertEquals(node4, router.getAssignedNode(newRequest("24")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void consistentHashingConstruction() {
        new ConsistentHashing(Long :: valueOf, 0);
    }

    @Test
    public void consistentHashingWithWeight() {

        final List<Long> hashes = new ArrayList<>();
        hashes.add(1L);
        hashes.add(21L);
        hashes.add(11L);
        hashes.add(41L);
        hashes.add(31L);
        hashes.add(51L);

        final Function<String, Long> hashFunction = id -> {
          if (id.contains("000000")) {
              return hashes.remove(0);
          } else {
              return Long.parseLong(id);
          }
        };

        final Router router = new ConsistentHashing(hashFunction, 2);
        final Node node1 = newNode("1000000"), node2 = newNode("2000000"), node3 = newNode("3000000");
        router.addNode(node1);
        router.addNode(node2);
        router.addNode(node3);

        Assert.assertEquals(node1, router.getAssignedNode(newRequest("55")));
        Assert.assertEquals(node1, router.getAssignedNode(newRequest("15")));
        Assert.assertEquals(node2, router.getAssignedNode(newRequest("8")));
        Assert.assertEquals(node2, router.getAssignedNode(newRequest("33")));
        Assert.assertEquals(node3, router.getAssignedNode(newRequest("28")));
        Assert.assertEquals(node3, router.getAssignedNode(newRequest("47")));

        router.removeNode(node1);

        Assert.assertEquals(node2, router.getAssignedNode(newRequest("58")));
        Assert.assertEquals(node3, router.getAssignedNode(newRequest("12")));
        Assert.assertEquals(node3, router.getAssignedNode(newRequest("23")));
        Assert.assertEquals(node2, router.getAssignedNode(newRequest("54")));

        final Node node4 = newNode("4000000");
        hashes.add(6L);
        hashes.add(26L);
        router.addNode(node4);

        Assert.assertEquals(node4, router.getAssignedNode(newRequest("15")));
        Assert.assertEquals(node4, router.getAssignedNode(newRequest("15")));
        Assert.assertEquals(node4, router.getAssignedNode(newRequest("5")));

    }

    private Request newRequest(String id) {
        return new Request(id, serviceId, method);
    }

    private Node newNode(String id) {
        return new Node(id, ipAddress);
    }

}
