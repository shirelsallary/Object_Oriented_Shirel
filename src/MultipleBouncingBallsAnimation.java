import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.util.Random;
import java.awt.Color;

/**
 * @author Shirel Sallary
 * Updated version to support Assignment 2 Ball class.
 */
public class MultipleBouncingBallsAnimation {

    static private void MultidrawAnimation(double[] args) {
        // גודל החלון כפי שהיה בחלק 1
        int width = 200;
        int height = 200;

        GUI gui = new GUI("Multiple Bouncing Balls", width, height);
        Sleeper sleeper = new Sleeper();
        Random rand = new Random();

        Ball[] balls = new Ball[args.length];

        for (int i = 0; i < args.length; i++) {
            int radius = (int) args[i];

            // מיקום רנדומלי בתוך גבולות ה-200
            Point point = new Point(
                    rand.nextDouble() * (width - 2 * radius) + radius,
                    rand.nextDouble() * (height - 2 * radius) + radius
            );

            // חישוב מהירות לפי גודל הכדור
            Velocity velocity;
            if (radius >= 50) {
                velocity = new Velocity(1, 1);
            } else {
                double speed = Math.max(2, (50.0 / radius));
                velocity = new Velocity(speed, speed);
            }

            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

            // תיקון שורה 41:
            // 1. יצירת כדור עם null כי אין סביבת משחק באנימציה פשוטה
            balls[i] = new Ball(point, radius, randomColor, null);
            // 2. קביעת המהירות בנפרד (כי הורדנו אותה מהקונסטרקטור)
            balls[i].setVelocity(velocity);
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();

            for (Ball b : balls) {
                // שימי לב: מכיוון שהסביבה היא null, הכדור ישתמש בלוגיקה הישנה
                // אם עדכנת את Ball.java לפי הקוד שנתתי לך, הוספתי שם הגנה ל-null
                b.moveOneStep();
                b.drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(50);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide ball radii as command line arguments.");
            return;
        }

        double[] arr = new double[args.length];
        for (int i = 0; i < args.length; i++) {
            try {
                arr[i] = Double.parseDouble(args[i]);
            } catch (NumberFormatException e) {
                arr[i] = 10; // ברירת מחדל אם הקלט לא תקין
            }
        }
        MultidrawAnimation(arr);
    }
}