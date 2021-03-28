import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

public class Tree {
    private final Vertex root;
    private final Map<Integer, Vertex> vertices;
    private final Collection<Edge> edges;

    final int minNodeDistance = 100;
    final int nodeDistanceIncrement = 100;

    Tree(Vertex root, Map<Integer, Vertex> vertices, Collection<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.root = root;
        setInitialCoordinates(root);
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

    private void setFinalCoordinates() {
        Vertex deepestVertex = vertices
                .values()
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
                v.setX(v.getParent().getX() + level * nodeDistanceIncrement + minNodeDistance);
                v.setY(v.getParent().getY());
            } else {
                v.setX(v.getParent().getX());
                v.setY(v.getParent().getY() + level * nodeDistanceIncrement + minNodeDistance);
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

        for (Map.Entry<Integer, Vertex> entry: vertices.entrySet()) {
            Vertex v = entry.getValue();
            int newX = (int) (v.getX()*cos45 - v.getY()*sin45);
            int newY = (int) (v.getX()*sin45 + v.getY()*cos45);
            v.setX(newX);
            v.setY(newY);
        }
    }
}
