import jdk.internal.jshell.tool.resources.l10n;
import sun.rmi.server.UnicastRef;

public class NBody {
    public static String _background = "images/starfield.jpg";

    public static double readRadius(String filename)
    {
        /* Start reading in filename*/
        In in = new In(filename);
        
        in.readInt();
        double radius = in.readDouble();
        
        return radius;
    }

    public static Body[] readBodies(String filename)
    {
        In in = new In(filename);

        int n_bodies = in.readInt();
        in.readDouble();

        Body[] bodies = new Body[n_bodies];

        for (int i = 0; i < bodies.length; i++)
        {
            double x_p = in.readDouble();
            double y_p = in.readDouble();
            double x_v = in.readDouble();
            double y_v = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();

            bodies[i] = new Body(x_p, y_p, x_v, y_v, mass, img);
        }

        return bodies;
    }

    public static void main(String[] args)
    {
        // Collect all needed input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        Body[] bodies = NBody.readBodies(filename);
        double universe_radius = NBody.readRadius(filename);
        int n_bodies = bodies.length;

        /** Enables double buffering.
		  * A animation technique where all drawing takes place on the offscreen canvas.
		  * Only when you call show() does your drawing get copied from the
		  * offscreen canvas to the onscreen canvas, where it is displayed
		  * in the standard drawing window. */
		StdDraw.enableDoubleBuffering();

		/** Set up the lower / upper boundary of the window */  
		StdDraw.setScale(- universe_radius, universe_radius);

		/* Clears the drawing window. */
        StdDraw.clear();

        // Creating the animation
        for (double t = 0; t <= T; t += dt)
        {
            double[] xForces = new double[n_bodies];
            double[] yForces = new double[n_bodies];
            for (int i = 0; i < n_bodies; i++)
            {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            for (int i = 0; i < n_bodies; i++)
            {
                bodies[i].update(dt, xForces[i], yForces[i]);
                
            }
            // Draw the background
            StdDraw.picture(0, 0, _background);

            // Draw the bodies
            for (int i = 0; i < n_bodies; i++)
            {
                bodies[i].draw();
            }

            /* Shows the drawing to the screen, and waits 2000 milliseconds. */
            StdDraw.show();
            StdDraw.pause(10);
        }

        // Print the universe
        StdOut.println(n_bodies);
        StdOut.println(universe_radius);
        for (int i = 0; i < n_bodies; i++)
        {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName); 
        }
    }
}