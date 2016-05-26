package ua.projects.javatests.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance1() {
        Point p1 = new Point(2, 5);
        Point p2 = new Point(6, 2);
        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDistance2() {
        Point p1 = new Point(0, 1);
        Point p2 = new Point(12, 6);
        Assert.assertEquals(p1.distance(p2), 13.0);
    }

}
