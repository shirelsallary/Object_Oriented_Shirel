public class Line {
    private Point start;
    private Point end;
    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    // Return the length of the line
    public double length() {
       return start.distance(this.end);
    }

    // Returns the middle point of the line
    public Point middle() {
        double x= (end.getX()+start.getX())/2;
        double y= (end.getY()+start.getY())/2;
        return new Point(x,y);
    }

    // Returns the start point of the line
    public Point start() { return start; }

    // Returns the end point of the line
    public Point end() {return end; }

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (start.equals(other.start) && end.equals(other.end)) {return true;}
        if (start.equals(other.end) && end.equals(other.start)) {return true;}
        return false;
    }

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other){
        if (this.intersectionWith(other)!=null) return true;
        return false;
    }
    private double gradient ()
    {
        return (end.getY()-start.getY())/(end.getX()-start.getX());
    }
     private Point oneLineParallel (Line other) {
         double xCut=this.start.getX();
         double m2=other.gradient();
         double b2 =(other.start.getY()) -m2*other.start.getX();
         double yCut=m2*xCut+b2;
        return new Point(xCut,yCut);
     }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX(), y1 = this.start.getY();
        double x2 = this.end.getX(), y2 = this.end.getY();
        double x3 = other.start.getX(), y3 = other.start.getY();
        double x4 = other.end.getX(), y4 = other.end.getY();

        double denominator = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        if (denominator == 0) return null;

        double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denominator;
        double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denominator;

        if (ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1) {
            return new Point(x1 + ua * (x2 - x1), y1 + ua * (y2 - y1));
        }
        return null;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect the rectangle to check intersection with.
     * @return the closest intersection point or null if no intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Get all intersection points using the method we built in Rectangle
        java.util.List<Point> intersections = rect.intersectionPoints(this);

        // If no intersections, return null
        if (intersections.isEmpty()) {
            return null;
        }

        // Find the point closest to the start of the line
        Point closest = null;
        double minDistance = Double.MAX_VALUE;

        for (Point p : intersections) {
            double dist = this.start().distance(p);
            if (dist < minDistance) {
                minDistance = dist;
                closest = p;
            }
        }

        return closest;
    }

}