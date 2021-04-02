package ru.mipt.entities;

public class Edge {
    private int source;
    private int target;
    private String direction;

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

    public String getDirection() {
        return direction;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
