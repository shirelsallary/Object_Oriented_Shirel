import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;

public class MultipleFramesBouncingBallsAnimation {

    static private void MultiFrameAnimation(double[] args) {
        GUI gui = new GUI("Multiple Frames Animation", 800, 800);
        Sleeper sleeper = new Sleeper();
        Random rand = new Random();

        Ball[] balls = new Ball[args.length];

        for (int i = 0; i < args.length; i++) {
            int radius = (int) args[i];
            Point point;

            // הגרלת מיקום (nextDouble מחזיר 0-1 ולכן חייבים להכפיל בטווח)
            if (i < balls.length / 2) {
                point = new Point(
                        50 + radius + rand.nextDouble() * (450 - 2 * radius),
                        50 + radius + rand.nextDouble() * (450 - 2 * radius));
            } else {
                point = new Point(
                        450 + radius + rand.nextDouble() * (150 - 2 * radius),
                        450 + radius + rand.nextDouble() * (150 - 2 * radius));
            }

            Velocity velocity;
            if (radius >= 50) {
                velocity = new Velocity(1, 1);
            } else {
                double speed = Math.max(2, (50.0 / radius));
                velocity = new Velocity(speed, speed);
            }

            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

            // שימוש בקונסטרקטור החדש שמתאים לאנימציות
            balls[i] = new Ball(point, radius, randomColor);
            balls[i].setVelocity(velocity);
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();

            // ציור הפריימים
            d.setColor(Color.GRAY);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);

            for (int i = 0; i < balls.length; i++) {
                if (i < balls.length / 2) {
                    balls[i].moveOneStep(50, 50, 500, 500);
                } else {
                    balls[i].moveOneStep(450, 450, 600, 600);
                }
                balls[i].drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(50);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[] {"15", "25", "35"};
        }

        double[] arr = new double[args.length];
        for (int i = 0; i < args.length; i++) {
            arr[i] = Double.parseDouble(args[i]);
        }
        MultiFrameAnimation(arr);
    }
}