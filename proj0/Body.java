/**
 * Body
 */
public class Body {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }
    public double calcDistance(Body b){
        double distance = 0;
        double x = this.xxPos - b.xxPos;
        double y = this.yyPos - b.yyPos;
        distance = Math.sqrt(x * x + y * y);
        return distance;
    }
    
    public double calcForceExertedBy(Body b){
        double constantG = 6.67e-11;
        double force = constantG * this.mass * b.mass / Math.pow(this.calcDistance(b), 2);
        return force;
    }

    public double calcForceExertedByX(Body b){
        double dx = b.xxPos - this.xxPos;
        double force_x = this.calcForceExertedBy(b) * dx / this.calcDistance(b);
        return force_x;
    }

    public double calcForceExertedByY(Body b){
        double dy = b.yyPos - this.yyPos;
        double force_y = this.calcForceExertedBy(b) * dy / this.calcDistance(b);
        return force_y;
    }

    public double calcNetForceExertedByX(Body[] allBodys){
        double netForceX = 0;
        for(Body myBody : allBodys){
            if (this.equals(myBody)){
                continue;
            }else{
                netForceX += this.calcForceExertedByX(myBody);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Body[] allBodys){
        double netForceY = 0;
        for(Body myBody : allBodys){
            if (this.equals(myBody)){
                continue;
            }else{
                netForceY += this.calcForceExertedByY(myBody);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        // Calculate acceleration of X and Y direction.
        double a_X = fX/this.mass;
        double a_Y = fY/this.mass;

        // Calculate new velocity of X and Y direction.
        this.xxVel += dt * a_X;
        this.yyVel += dt * a_Y;

        // Calcalate new position of X and Y direction.
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;       
    }

    public void draw(){
        double xxPos = this.xxPos;
        double yyPos = this.yyPos;
        String imgName = this.imgFileName;

        // StdDraw.enableDoubleBuffering();
        // StdDraw.setScale(-1e-11, 1e-11);
        StdDraw.picture(xxPos,yyPos,"images/" + imgName);
        // StdDraw.show();
        // StdDraw.pause(2000);
    }
}