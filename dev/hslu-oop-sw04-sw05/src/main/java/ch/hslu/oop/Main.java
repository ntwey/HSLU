package ch.hslu.oop;

import ch.hslu.oop.sw04.*;
import ch.hslu.oop.sw05.*;

public final class Main {
    public static void main(final String[] args) {
        System.out.println("=== SW04 Demo ===");
        Switchable engine = new Motor("EC-2000");
        Vehicle car = new Vehicle("MyCar", (Motor) engine, new Headlight("LeftLight"), new Headlight("RightLight"));
        car.switchOn();
        car.switchOff();
        car.switchOn();
        System.out.printf("Car switched on? %s, engine rpm=%d, car switch count=%d%n",
                car.isSwitchedOn(), ((Motor) engine).getRpm(), car.getSwitchCount());

        // Polymorphie mit Switchable
        Switchable light = new Headlight("FogLight");
        light.switchOn();
        System.out.printf("Foglight on? %s%n", light.isSwitchedOn());

        // Datenkapselung: Line mit defensiver Kopie
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Line line = new Line(p1, p2);
        Point startRef = line.getStart(); // Defensive Kopie!
        startRef.setX(999); // Darf die Linie NICHT beeinflussen
        System.out.printf("Line start expected (0,0): (%d,%d)%n", line.getStart().getX(), line.getStart().getY());

        System.out.println("=== SW05 Demo ===");
        Shape r = new Rectangle(0, 0, 20, 10);
        Shape c = new Circle(0, 0, 5);
        Shape s = new Square(0, 0, 8);
        System.out.printf("Rectangle perimeter=%d, area=%d%n", r.getPerimeter(), r.getArea());
        System.out.printf("Circle perimeter=%d, area=%d%n", c.getPerimeter(), c.getArea());
        System.out.printf("Square perimeter=%d, area=%d%n", s.getPerimeter(), s.getArea());
    }
}
