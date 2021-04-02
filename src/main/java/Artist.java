import java.awt.*;
import java.awt.image.BufferedImage;

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

    Artist(Tree tree) {
        this.tree = tree;
        vertexArtist = new VertexArtist(vertexColour, vertexRadiusPixels);
    }

    public void draw() {
        setTreeCoordinates();
        initializeImage();
        moveTreeToImageCenter();
        drawVertices();
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

        int treeCenterX = (rightestCoord - leftestCoord) / 2;
        int treeCenterY = (lowestCoord - highestCoord) / 2;
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


}
