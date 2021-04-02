import org.testng.annotations.Test;
import ru.mipt.drawing.VertexArtist;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class VertexArtistTest {
    @Test
    public void shouldRunWithoutExceptions() throws IOException {
        int width = 100;
        int height = 100;
        BufferedImage image = new BufferedImage(width, height, 1);
        Color vertexColour = new Color(0, 0, 255);

        int radius = 50;
        VertexArtist artist = new VertexArtist(vertexColour, radius);
        artist.setImage(image);

        assertDoesNotThrow(() -> artist.draw(50, 50));
        File output = new File("src/test/resources/vertex.jpg");
        ImageIO.write(image, "jpg", output);
    }

}
