import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_CUSTOM;

public class ImageProcessor {
    public static void main(String[] args) {
        try {

            // Открываем изображение
            int width = 1000;
            int height = 1000;

            BufferedImage result = new BufferedImage(width, height, 1);

            // Делаем двойной цикл, чтобы обработать каждый пиксель
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {

                    // Получаем цвет текущего пикселя
                    Color backgroundColor = new Color(255, 255, 255);

                    Color roundColour = new Color(0, 0, 255);

                    if ((x - 500)*(x - 500) + (y - 200)*(y - 200) < 1600) {
                        result.setRGB(x, y, roundColour.getRGB());
                    } else {
                        result.setRGB(x, y, backgroundColor.getRGB());
                    }

                    // И устанавливаем этот цвет в текущий пиксель результирующего изображения

                }
            }

            // Созраняем результат в новый файл
            File output = new File("src/main/resources/round.jpg");
            ImageIO.write(result, "jpg", output);

        } catch (IOException e) {
            System.out.println("Файл не найден или не удалось сохранить");
        }
    }
}
