// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double x;
    private double y;
    // constructor
    public Velocity(double dx, double dy){
        x = dx;
        y = dy;
    }
     public double getX(){ return x; }
    public double getY(){ return y; }
    public void setX(double x){ this.x = x; }
    public void setY(double y){ this.y = y; }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p){
        return new Point(p.getX()+this.getX(), p.getY()+this.getY() );
    }


    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(angle);
        double dy = speed * Math.sin(angle);
        return new Velocity(dx, dy);
    }

}