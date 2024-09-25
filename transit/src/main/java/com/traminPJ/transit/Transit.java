package com.traminPJ.transit;

public class Transit {
    private String id;
    private String status;
    private String origin;
    private String destination;
    private double cost;

    public Transit(String id, String status, String origin, String destination, double cost){
        this.id=id;
        this.status = status;
        this.origin = origin;
        this.destination= destination;
        this.cost = cost;

    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String type) {
        this.status = type;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
