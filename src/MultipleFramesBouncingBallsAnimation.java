import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.Random;

public class MultipleFramesBouncingBallsAnimation {


static private void MultiFrameAnimation(double[] args) {

    GUI gui = new GUI("title",800,800);

    Sleeper sleeper = new Sleeper();
    Random rand = new Random();
    Color randomColor ;


    int radius;
    Point point;
    double xVelocity,yVelocity;
    Velocity velocity;

    Ball [] ball=new Ball[args.length];

    for (int i = 0; i < args.length; i++) {
        radius = (int) args[i];
        //point
        if (i < ball.length / 2) {
            point = new Point(
                    50 + radius + rand.nextDouble(450 - 2*radius),
                    50 + radius + rand.nextDouble(450 - 2*radius));
        } else {
            point = new Point(
                    450 + radius + rand.nextDouble(150 - 2*radius),
                    450 + radius + rand.nextDouble(150 - 2*radius));
        }
        //velocity
        if (radius >= 50)
        {velocity = new Velocity(1, 1);}
        else
        {velocity = new Velocity(Math.max(2, (50.0 / radius)), Math.max(2, (50.0 / radius)));}

        randomColor = new Color(
                rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

        ball[i] = new Ball(point, radius,  randomColor, velocity);
    }

    while (true) {

        DrawSurface d = gui.getDrawSurface();


        d.setColor(Color.GRAY);
        d.fillRectangle(50, 50, 450, 450);


        d.setColor(Color.YELLOW);
        d.fillRectangle(450, 450, 150, 150);

        for (int i = 0; i < ball.length; i++) {

            Ball b = ball[i];

            if (i < ball.length / 2) {
                // move inside first frame (50,50) to (500,500)
                b.moveOneStep(50, 50, 500, 500);
            } else {
                // move inside second frame (450,450) to (600,600)
                b.moveOneStep(450, 450, 600, 600);
            }

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
        MultiFrameAnimation(arr);
    }
}
