// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double x;
    private double y;
    // constructor
    public Velocity(double dx, double dy){
        x = dx;
        y = dy;
    }
     public double getDx(){ return x; }
    public double getDy(){ return y; }
    public void setDx(double x){ this.x = x; }
    public void setDy(double y){ this.y = y; }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p){
        return new Point(p.getX()+this.getDx(), p.getY()+this.getDy() );
    }


    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // המרה למערכת הצירים של Arkanoid (0 למעלה) ורדיאנים
        double radians = Math.toRadians(angle - 90);
        double dx = speed * Math.cos(radians);
        double dy = speed * Math.sin(radians);
        return new Velocity(dx, dy);
    }

}