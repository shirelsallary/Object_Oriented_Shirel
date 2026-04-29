import biuoop.GUI;
import biuoop.DrawSurface;


public class BallsTest1 {
    public static void main(String[] args) {
        GUI gui = new GUI("Balls Test 1", 400, 400);
        DrawSurface d = gui.getDrawSurface();

        // יצירת נקודות עבור מרכז הכדורים
        Point p1 = new Point(100, 100);
        Point p2 = new Point(100, 150);
        Point p3 = new Point(80, 249);

        // יצירת הכדורים באמצעות הנקודות (ושימוש בקונסטרקטור ללא environment עבור הטסט)
        Ball b1 = new Ball(p1, 30, java.awt.Color.RED);
        Ball b2 = new Ball(p2, 10, java.awt.Color.BLUE);
        Ball b3 = new Ball(p3, 50, java.awt.Color.GREEN);

        b1.drawOn(d);
        b2.drawOn(d);
        b3.drawOn(d);

        gui.show(d);
    }
}