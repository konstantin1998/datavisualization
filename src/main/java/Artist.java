import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Artist {
    private final Tree tree;
    private final int minVertexDistance = 100;
    private final int vertexDistanceIncrement = 100;
    private final int vertexRadiusPixels = 40;
    private final int edgeThicknessPixels = 10;
    private final int paddingPixels = vertexRadiusPixels + edgeThicknessPixels;

    private BufferedImage image;
    private Color backgroundColour = new Color(255, 255, 255);
    private Color vertexColour = new Color(0, 0, 255);
    private Color edgeColour = new Color(0, 0, 0);
    private int width;
    private int height;

    private final VertexArtist vertexArtist;
    private final EdgeArtist edgeArtist;

    Artist(Tree tree) {
        this.tree = tree;
        vertexArtist = new VertexArtist(vertexColour, vertexRadiusPixels);
        edgeArtist = new EdgeArtist(edgeColour, edgeThicknessPixels, vertexRadiusPixels);
    }

    public void draw() {
        setTreeCoordinates();
        initializeImage();
        moveTreeToImageCenter();
        drawVertices();
        drawEdges();
        save();
    }

    private void setTreeCoordinates() {
        tree.setMinVertexDistance(minVertexDistance);
        tree.setVertexDistanceIncrement(vertexDistanceIncrement);
        tree.setCoordinates();
    }

    private void initializeImage() {
        int leftestCoord = tree.getLeftestCoord();
        int rightestCoord = tree.getRightestCoord();
        int highestCoord = tree.getHighestCoord();
        int lowestCoord = tree.getLowestCoord();

        width = rightestCoord - leftestCoord + paddingPixels * 2;
        height = lowestCoord - highestCoord + paddingPixels * 2;

        int imageType = 1;
        image = new BufferedImage(width, height, imageType);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, backgroundColour.getRGB());
            }
        }
    }

    private void moveTreeToImageCenter() {
        int leftestCoord = tree.getLeftestCoord();
        int rightestCoord = tree.getRightestCoord();
        int highestCoord = tree.getHighestCoord();
        int lowestCoord = tree.getLowestCoord();

        int treeCenterX = (rightestCoord + leftestCoord) / 2;
        int treeCenterY = (lowestCoord + highestCoord) / 2;
        int frameCenterX = width / 2;
        int frameCenterY = height / 2;

        tree.shift(frameCenterX - treeCenterX, frameCenterY - treeCenterY);
    }

    private void drawVertices() {
        vertexArtist.setImage(image);
        for (Vertex v : tree.getVertices()) {
            vertexArtist.draw(v.getX(), v.getY());
        }
    }

    private void drawEdges() {
        Map<Integer, Vertex> idToVertex = new HashMap<>();
        for (Vertex v : tree.getVertices()) {
            idToVertex.put(v.getId(), v);
        }

        edgeArtist.setImage(image);
        for (Edge e : tree.getEdges()) {
            Vertex source = idToVertex.get(e.getSource());
            Vertex target = idToVertex.get(e.getTarget());
            Point start = new Point(source.getX(), source.getY());
            Point finish = new Point(target.getX(), target.getY());
            edgeArtist.draw(start, finish);
        }
    }

    private void save() {
        File output = new File("src/main/resources/graph.jpg");
        try {
            ImageIO.write(image, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
