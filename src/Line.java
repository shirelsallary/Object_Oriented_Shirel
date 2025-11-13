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
       Point empty=null;
        if(this.equals(other)) {return this.start;}//same line
        if( this.start.getX()==this.end.getX()
                && other.start.getX()==this.end.getX()) {return empty;}// both parallel to Y
        if( this.start.getX()!=this.end.getX()
                && other.start.getX()!=this.end.getX())//both are not parallel to Y
        {
            double m1=this.gradient();
            double m2=other.gradient();
            double b1 =(this.start.getY()) -m1*this.start.getX();
            double b2 =(other.start.getY()) -m2*other.start.getX();
            double xCut=(b2-b1)/(m1-m2);
            double yCut=m1*xCut+b1;
            Point cutPoint=new Point(xCut,yCut);
            if ( ( cutPoint.inRange(this.start,this.end) )&& (cutPoint.inRange(other.start,other.end)))
                return cutPoint;
            return empty;
        }
        //one of them is parallel to Y
        if (this.start.getX()==this.end.getX())//this.line is parallel
        {
            Point cutPoint=this.oneLineParallel(other);

            if ( ( cutPoint.inRange(this.start,this.end) )&& (cutPoint.inRange(other.start,other.end)))
                return cutPoint;
            return empty;

        }
        else// other.line os parallel
        {
            Point cutPoint=other.oneLineParallel(this);

            if ( ( cutPoint.inRange(this.start,this.end) )&& (cutPoint.inRange(other.start,other.end)))
                return cutPoint;
            return empty;
        }

    }



}