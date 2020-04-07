/**
 * NBody
 */
public class NBody {

    public static double readRadius(String fileName){
        double radius = 0.0;
        In in = new In(fileName);

        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();// radius

        return secondItemInFile;
    }

    public static Body[] readBodies(String fileName){
        In in = new In(fileName);
        int planetNum = in.readInt();
        // Create an array with planetsNum of Body.
        Body[] allBodys = new Body[planetNum];
        double radius = in.readDouble();
        int i = 0;
        while (i < planetNum){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            allBodys[i] = new Body(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
            i++;
        }
        return allBodys;
    }

    public static void main(String[] args) {
        // Collecting All Needed Input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] allBodies = NBody.readBodies(filename);
        double universeRadius = NBody.readRadius(filename);

        StdDraw.enableDoubleBuffering();
        double time = 0.0;
        while(time <= T){
            double[] xForces = new double[allBodies.length];
            double[] yForces = new double[allBodies.length];
            for(int i = 0; i<allBodies.length;i++){
                xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies);
                yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies);
            }
            for(int i = 0; i < allBodies.length;i++){
                allBodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.setScale(-universeRadius,universeRadius);
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg");

            for(Body myBody : allBodies){
                myBody.draw();
            }
            StdDraw.show();
            time = time + dt;
        }
        System.out.println(allBodies.length);
        StdOut.printf("%.2e\n",universeRadius);
        for(int i = 0;i<allBodies.length;i++){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",allBodies[i].xxPos,allBodies[i].yyPos,allBodies[i].xxVel,allBodies[i].yyVel,allBodies[i].mass,allBodies[i].imgFileName);
        }
    }
}