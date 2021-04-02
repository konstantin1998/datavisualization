import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class EdgeArtistTest {
    @Test
    public void shouldRunWithoutExceptions() throws IOException {
        int width = 100;
        int height = 100;
        BufferedImage image = new BufferedImage(width, height, 1);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i,j, new Color(255, 255, 255).getRGB());
            }
        }

        Color edgeColour = new Color(0, 0, 0);
        int radius = 10;
        int thickness = 10;
        EdgeArtist artist = new EdgeArtist(edgeColour, thickness, radius);
        artist.setImage(image);

        assertDoesNotThrow(() -> artist.draw(new Point(20, 20), new Point(80, 80)));
        File output = new File("src/test/resources/edge.jpg");
        ImageIO.write(image, "jpg", output);
    }
}
