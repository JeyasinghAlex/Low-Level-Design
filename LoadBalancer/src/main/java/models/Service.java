package models;

import algorithms.Router;

import java.util.Arrays;

public class Service {

    private final Router router;
    private final String id ;
    private final String[] methods;

    public Service(String id, Router router, String[] methods) {
        this.id = id;
        this.router = router;
        this.methods = methods;
    }

    public Router getRouter() {
        return router;
    }

    public String getId() {
        return id;
    }

    public String[] getMethods() {
        return methods;
    }

    @Override
    public String toString() {
        return "Service{" +
                "router=" + router +
                ", id='" + id + '\'' +
                ", methods=" + Arrays.toString(methods) +
                '}';
    }
}
