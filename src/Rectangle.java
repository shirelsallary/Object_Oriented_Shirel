import java.util.ArrayList;
import java.util.List;

/**
 * @author Shirel Sallary
 * Represents a rectangle in 2D space, aligned with the axes.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft The top-left corner of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line The line to check for intersections.
     * @return A list of intersection points.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionList = new ArrayList<>();

        // Define the 4 boundaries of the rectangle as lines
        Line top = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
        Line bottom = new Line(upperLeft.getX(), upperLeft.getY() + height, upperLeft.getX() + width, upperLeft.getY() + height);
        Line left = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
        Line right = new Line(upperLeft.getX() + width, upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY() + height);

        Line[] boundaries = {top, bottom, left, right};

        for (Line boundary : boundaries) {
            Point intersection = line.intersectionWith(boundary);
            if (intersection != null) {
                intersectionList.add(intersection);
            }
        }
        return intersectionList;
    }

    // --- Accessors ---

    public double getWidth() { return this.width; }
    public double getHeight() { return this.height; }
    public Point getUpperLeft() { return this.upperLeft; }
}