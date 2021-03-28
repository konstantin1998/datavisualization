public class Vertex {
    private int id;
    private int x;
    private int y;
    private int level;
    private Vertex right;
    private Vertex left;
    private Vertex parent;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Vertex getRight() {
        return right;
    }
    public void setRight(Vertex v) {
        right = v;
    }
    public Vertex getLeft() {
        return left;
    }
    public void setLeft(Vertex v) {
        left = v;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vertex: id = " + id;
    }
}
