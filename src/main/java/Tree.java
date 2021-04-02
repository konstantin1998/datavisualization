import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Tree {
    private final Vertex root;
    private Collection<Vertex> vertices;
    private final Collection<Edge> edges;

    private int minVertexDistance = 100;
    private int nodeDistanceIncrement = 100;

    Tree(Vertex root, Map<Integer, Vertex> vertices, Collection<Edge> edges) {
        this.vertices = vertices.values();
        this.edges = edges;
        this.root = root;
        //setInitialCoordinates(root);
    }

    public void setMinVertexDistance(int distance) {
        minVertexDistance = distance;
    }

    public void setNodeDistanceIncrement(int increment) {
        nodeDistanceIncrement = increment;
    }

    public void setCoordinates() {
        setInitialCoordinates(root);
        setExpandedCoordinates();
        turnBy45Degrees();
    }

    private void setInitialCoordinates(Vertex v) {
        if (v.getParent() == null) {
            v.setX(0);
            v.setY(0);
        }
        if (v.getRight() != null) {
            v.getRight().setX(v.getX() + 1);
            v.getRight().setY(v.getY());
            setInitialCoordinates(v.getRight());
        }
        if (v.getLeft() != null) {
            v.getLeft().setX(v.getX());
            v.getLeft().setY(v.getY() + 1);
            setInitialCoordinates(v.getLeft());
        }
    }

    private void setExpandedCoordinates() {
        Vertex deepestVertex = vertices
                .stream()
                .max(Comparator.comparingInt((Vertex v) -> (v.getX() + v.getY()))).get();
        int maxHeight = deepestVertex.getX() + deepestVertex.getY();
        expandTree(root, maxHeight);
    }

    private void expandTree(Vertex v, int maxHeight) {
        if (v.getParent() == null) {
            v.setX(0);
            v.setY(0);
        } else {
            int level = maxHeight - (v.getY() + v.getX());
            boolean isRightChild = v.getParent().getRight() == v;
            if (isRightChild) {
                v.setX(v.getParent().getX() + level * nodeDistanceIncrement + minVertexDistance);
                v.setY(v.getParent().getY());
            } else {
                v.setX(v.getParent().getX());
                v.setY(v.getParent().getY() + level * nodeDistanceIncrement + minVertexDistance);
            }
        }

        if (v.getRight() != null) {
            expandTree(v.getRight(), maxHeight);
        }
        if (v.getLeft() != null) {
            expandTree(v.getLeft(), maxHeight);
        }
    }

    private void turnBy45Degrees() {
        double cos45 = 0.707;
        double sin45 = 0.707;

        for (Vertex v: vertices) {
            int newX = (int) (v.getX()*cos45 - v.getY()*sin45);
            int newY = (int) (v.getX()*sin45 + v.getY()*cos45);
            v.setX(newX);
            v.setY(newY);
        }
    }

    public void shift(int x, int y) {
        vertices = vertices.stream().peek((Vertex v) -> {
            int newX = v.getX() + x;
            int newY = v.getY() + y;
            v.setX(newX);
            v.setY(newY);
        }).collect(Collectors.toList());
    }

    public Collection<Vertex> getVertices() {
        return this.vertices;
    }

    public Collection<Edge> getEdges() {
        return this.edges;
    }

    public int getLeftestCoord() {
        Vertex leftestVertex = vertices
                .stream()
                .min(Comparator.comparingInt(Vertex::getX)).get();
        return leftestVertex.getX();
    }

    public int getRightestCoord() {
        Vertex rithtestVertex = vertices
                .stream()
                .max(Comparator.comparingInt(Vertex::getX)).get();
        return rithtestVertex.getX();
    }

    public int getHighestCoord() {
        return root.getY();
    }

    public int getLowestCoord() {
        Vertex lowestVertex = vertices
                .stream()
                .max(Comparator.comparingInt(Vertex::getY)).get();
        return lowestVertex.getY();
    }
}
