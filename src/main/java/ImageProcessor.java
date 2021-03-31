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
                    Color backgroundColor = new Color(0, 1, 0);

                    Color roundColour = new Color(130, 220, 125);

                    if ((x - 500)*(x - 500) + (y - 500)*(y - 500) < 10000) {
                        result.setRGB(x, y, roundColour.getRGB());
                    } else {
                        result.setRGB(x, y, backgroundColor.getRGB());
                    }

                    // И устанавливаем этот цвет в текущий пиксель результирующего изображения

                }
            }

            // Созраняем результат в новый файл
            File output = new File("src/main/resources/round.jpj");
            ImageIO.write(result, "jpg", output);

        } catch (IOException e) {
            System.out.println("Файл не найден или не удалось сохранить");
        }
    }
}
