package ua.projects.javatests.sandbox;

public class MyFirstProgram {
	
public static void main(String[] args) {

    Point alpha = new Point(2, 5);
    Point beta = new Point(6, 2);

	System.out.println("Расстояние между двумя точками alpha (координаты " + alpha.x + ", " + alpha.y + ") и beta (координаты " + beta.x + ", "
            + beta.y + ") на двумерной плоскости равно " + distance(alpha, beta) + ".");
    System.out.println("Расстояние между двумя точками alpha (координаты " + alpha.x + ", " + alpha.y + ") и beta (координаты " + beta.x + ", "
            + beta.y + ") на двумерной плоскости равно " + alpha.distance(beta) + ".");
	}

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
}
