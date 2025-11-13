public class Point {
   private double x;
   private double y;
    private static final double EPSILON = 0.00001;

    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));

    }

    // equals -- return true is the points are equal, false otherwise
    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }

        boolean x_equal = Math.abs(this.x - other.x) < EPSILON;
        boolean y_equal = Math.abs(this.y - other.y) < EPSILON;
        return x_equal && y_equal;
    }
    // Return the x and y values of this point
    public double getX() { return x; }
    public double getY() {return y; }

    public boolean inRange(Point start,Point end) {
         double x = this.x;
         double y = this.y;
         double x1 = start.x;
         double y1 = start.y;
         double x2 = end.x;
         double y2 = end.y;
         if (x1<x2)
            {if (x<x1 || x>x2) return false;}
         else
              { if (x>x1 || x<x2) return false;}
         if(y1<y2)
             {  if (y<y1 || y>y2) return false;}
         else
             { if (y>y1 || y<y2) return false;}
         return true;

    }

}


