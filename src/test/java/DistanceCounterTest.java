import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DistanceCounterTest {
    @Test
    public void countDistanceBetweenHorizontalLineAndPoint() {
        int expectedDistance = 100;
        double distance = DistanceCounter
                .getDistanceBetweenLineAndPoint(
                        new Point(0, 0),
                        new Point(1, 0),
                        new Point(0, expectedDistance));

        assertTrue(Math.abs(expectedDistance - distance) < 0.01);
    }

    @Test
    public void countDistanceBetweenVerticalLineAndPoint() {
        int expectedDistance = 100;
        double distance = DistanceCounter.getDistanceBetweenLineAndPoint(
                new Point(0, 0),
                new Point(0, 1),
                new Point(expectedDistance, 0));

        assertTrue(Math.abs(expectedDistance - distance) < 0.01);
    }

    @Test
    public void countDistanceBetweenLineAndPointOnThisLine() {
        int expectedDistance = 0;
        double distance = DistanceCounter.getDistanceBetweenLineAndPoint(
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, expectedDistance));

        assertTrue(Math.abs(expectedDistance - distance) < 0.01);
    }

    @Test
    public void countDistanceBetweenLineAndPoint() {
        double expectedDistance = 0.707;
        double distance = DistanceCounter.getDistanceBetweenLineAndPoint(
                new Point(0, 0),
                new Point(1, 1),
                new Point(0, 1));
        System.out.println(distance);
        assertTrue(Math.abs(expectedDistance - distance) < 0.01);
    }
}
