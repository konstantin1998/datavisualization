public class DistanceCounter {
    public static double getDistanceBetweenLineAndPoint(Point linePoint1, Point linePoint2, Point point) {
        int x1 = linePoint1.getX();
        int x2 = linePoint2.getX();
        int y1 = linePoint1.getY();
        int y2 = linePoint2.getY();

        if (x2 - x1 == 0) {
            double c = -x2;
            return Math.abs( point.getX()  + c) ;
        }

        double k = (double) (y2 - y1) / (x2 - x1);
        double b = y2 - k * x2;

        return Math.abs(-1 * k * point.getX() + point.getY() + -b) / Math.sqrt(k * k + 1);
    }

    public static double getDistanceBetweenPoints(Point p1, Point p2) {
        return Math.sqrt(
                (p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY())
        );
    }
}
