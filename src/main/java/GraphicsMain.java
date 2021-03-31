import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.JFrame;

public class GraphicsMain extends JFrame {

    public GraphicsMain() {
        super("simpleApp");
        setSize(700, 600);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D gr2d = (Graphics2D) g;
        gr2d.setBackground(Color.green);





        gr2d.setPaint(Color.YELLOW);
        gr2d.drawRoundRect(200, 50, 200, 300, 200, 400);
        gr2d.setPaint(Color.DARK_GRAY);
        //Прямоугольник с закругленными краями
        gr2d.drawRoundRect(500, 500, 70, 40, 10, 10);
        // Фигура овал
        gr2d.drawOval(300, 50, 300, 300);
        // Заполненный овал
        gr2d.fillOval(100, 50, 200, 300);
        gr2d.setPaint(Color.pink);
        gr2d.drawArc(100, 200, 300, 300, ABORT, ABORT);
        
        //Овал с толстой линией
        gr2d.drawOval(100,100,300,300);
        gr2d.setPaint(Color.red);
        //Получаем цветной треугольник
        for (int i = 0; i < 30; i++) {
            gr2d.setPaint(Color.getHSBColor(5+i*350, 5+i*100, 5+i*100));
            gr2d.drawLine(400 + i*5, 400- i*6, 400 + i*4, 400 + i*3);
        }


        //clearRect очищает указанную область(координаты левого верхнего                      //угла прямоугольника;
        //width — ширина прямоугольника;
        //height — высота прямоугольника, вырезанная область зеленого цвета                 // фона
        gr2d.clearRect(50, 40, 200, 200);


    }

    public static void main(String args[]) {
        GraphicsMain app = new GraphicsMain();
    }
}
