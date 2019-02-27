package com.company.mst.prim.eager;

public class Edge {
    private double weight;
    private Vertex u;
    private Vertex v;

    public Edge(double weight, Vertex u, Vertex v) {
        this.weight = weight;
        this.u = u;
        this.v = v;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vertex getU() {
        return u;
    }

    public void setU(Vertex u) {
        this.u = u;
    }

    public Vertex getV() {
        return v;
    }

    public void setV(Vertex v) {
        this.v = v;
    }

}