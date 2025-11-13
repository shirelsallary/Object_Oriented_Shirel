
import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

public class Ball {
    private Color color;
    private int radius;
    private Point center;
    private Velocity velocity;
    public Ball(Point center, int r, java.awt.Color color){
        this.center = center;
        this.radius = r;
        this.color = color;
    }
    public Ball(Point center, int r, java.awt.Color color, Velocity velocity){
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = velocity;
    }

    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }
    // accessors
    public int getX(){return (int)center.getX();}
    public int getY(){return (int)center.getY();}
    public double getSize(){return radius;}
    public java.awt.Color getColor(){return color;}

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface){
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), radius);
    }

    public void setVelocity(Velocity v){
        velocity = v;
    }
    public void setVelocity(double dx, double dy){
        velocity =Velocity.fromAngleAndSpeed(dx, dy);
    }
    public Velocity getVelocity(){
        return velocity;
    }

    public void moveOneStep() {
        double nextX = this.center.getX() + this.velocity.getX();
        double nextY = this.center.getY() + this.velocity.getY();

        if (nextX + this.radius > 200) {
            this.velocity.setX(-Math.abs(this.velocity.getX()));
        }
        if (nextX - this.radius < 0) {
            this.velocity.setX(Math.abs(this.velocity.getX()));
        }
        if (nextY + this.radius > 200) {
            this.velocity.setY(-Math.abs(this.velocity.getY()));
        }
        if (nextY - this.radius < 0) {
            this.velocity.setY(Math.abs(this.velocity.getY()));
        }

        this.center = this.velocity.applyToPoint(this.center);
    }

    public void moveOneStep(double startWidth, double startLength,
                            double endWidth, double endLength) {

        double nextX = this.center.getX() + this.velocity.getX();
        double nextY = this.center.getY() + this.velocity.getY();

        if (nextX + this.radius > endWidth) {
            this.velocity.setX(-Math.abs(this.velocity.getX()));
        }
        if (nextX - this.radius < startWidth) {
            this.velocity.setX(Math.abs(this.velocity.getX()));
        }

        if (nextY + this.radius > endLength) {
            this.velocity.setY(-Math.abs(this.velocity.getY()));
        }
        if (nextY - this.radius < startLength) {   // ← כאן התיקון!!!
            this.velocity.setY(Math.abs(this.velocity.getY()));
        }

        this.center = this.velocity.applyToPoint(this.center);
    }




}