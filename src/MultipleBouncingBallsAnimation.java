import java.util.Random;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.util.Random;
import java.awt.Color;

public class MultipleBouncingBallsAnimation {



    static private void MultidrawAnimation(double[] args)
    {
        GUI gui = new GUI("title",200,200);
        Sleeper sleeper = new Sleeper();
        Random rand = new Random();
        Color randomColor;

        int radius;
        Point point;
        double xVelocity,yVelocity;
        Velocity velocity;

        Ball [] ball=new Ball[args.length];

        for (int i = 0; i < args.length; i++) {
            radius = (int) args[i];

            point = new Point(rand.nextDouble(200 - 2 * radius) + radius,
                    rand.nextDouble(200 - 2 * radius) + radius);



            if (radius >= 50)
                {velocity = new Velocity(1, 1);}
            else
                {velocity = new Velocity(Math.max(2, (50.0 / radius)), Math.max(2, (50.0 / radius)));}

            randomColor = new Color(
                    rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            ball[i] = new Ball(point, radius, randomColor, velocity);
        }


        while (true) {
            DrawSurface d = gui.getDrawSurface();

            for (Ball b : ball) {
                b.moveOneStep();
                b.drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(50);
        }

    }


    public static void main(String[] args) {
        double [] arr=new double[args.length];
          for (int i = 0; i < args.length; i++) {
              arr[i] = Double.parseDouble(args[i]);
          }
          MultidrawAnimation(arr);
    }
}
