import java.awt.*;
import java.awt.image.BufferedImage;

public class EdgeArtist {
    private final Color color;
    private final int thickness;
    private BufferedImage image;
    private final int radius;

    EdgeArtist(Color color, int thickness, int radius) {
        this.color = color;
        this.thickness = thickness;
        this.radius = radius;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void draw(Point start, Point finish) {

        for (int i = Math.min(start.getX(), finish.getX()); i <= Math.max(start.getX(), finish.getX()); i++) {
            for (int j = Math.min(start.getY(), finish.getY()); j <= Math.max(start.getY(), finish.getY()); j++) {
                Point currentPixel = new Point(i, j);
                double distanceFromCurrentPointToLine = DistanceCounter.getDistanceBetweenLineAndPoint(
                        start,
                        finish,
                        currentPixel);
                double distanceFromCurrentPointToStartPoint = DistanceCounter.getDistanceBetweenPoints(
                        start,
                        currentPixel);
                double distanceFromCurrentPointToFinishPoint = DistanceCounter.getDistanceBetweenPoints(
                        finish,
                        currentPixel);
                if (distanceFromCurrentPointToLine < thickness / 2.0
                        && distanceFromCurrentPointToStartPoint > radius
                        && distanceFromCurrentPointToFinishPoint > radius) {
                    image.setRGB(i, j, color.getRGB());
                }
            }
        }
    }
}
