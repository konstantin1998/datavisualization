import java.awt.*;
import java.awt.image.BufferedImage;

public class VertexArtist {
    private final Color color;
    private final int radius;
    private BufferedImage image;

    VertexArtist(Color color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void draw(int x, int y) {
        for (int i = Math.max(0, x - radius); i <= Math.min(x + radius, image.getWidth() - 1); i++) {
            for (int j = Math.max(0, y - radius); j <= Math.min(y + radius, image.getHeight() - 1); j++) {
                if ((i - x)*(i - x) + (j - y)*(j - y) <= radius * radius) {
                    image.setRGB(i, j, color.getRGB());
                }
            }
        }
    }
}
