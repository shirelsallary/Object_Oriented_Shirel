import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
import java.util.ArrayList;



public class AbstractArtDrawing {
    private static Line generateRandomLine(){
        Random rand = new Random();
        Line line = new Line(
                rand.nextInt(380) + 10,
                rand.nextInt(280) + 10,
                rand.nextInt(380) + 10,
                rand.nextInt(280) + 10 );
        return line;
    }
    public static void main(String[] args) {

        GUI gui = new GUI("Random Circles Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line [] lines = new Line[10];
        Point [] middlePoints = new Point[lines.length];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = generateRandomLine();
            middlePoints [i]= new Point(lines[i].middle().getX(), lines[i].middle().getY());
            d.setColor(Color.BLACK);
            d.drawLine(
                    (int) lines[i].start().getX(), (int) lines[i].start().getY(),
                    (int) lines[i].end().getX(), (int) lines[i].end().getY()
            );
            d.setColor(Color.blue);
            d.fillCircle((int) middlePoints[i].getX(), (int) middlePoints[i].getY(), 3);
        }
        ArrayList<Point> crossPoints = new ArrayList<Point>();
        for (int i = 0; i < lines.length; i++) {
            for (int j = i+1; j < lines.length; j++) {
                if (lines[i].isIntersecting(lines[j])) {
                    crossPoints.add(lines[i].intersectionWith(lines[j]));}
            }}
        for (Point p : crossPoints) {
            d.setColor(Color.RED);
            d.fillCircle((int)p.getX(), (int)p.getY(), 3);
        }


        gui.show(d);

    }
}
