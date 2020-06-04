public class Body
{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G = 6.67e-11;

    public Body(double x_p, double y_p, double x_v, double y_v, double m, String img)
    {
        xxPos = x_p;
        yyPos = y_p;
        xxVel = x_v;
        yyVel = y_v;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b)
    {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /** Calculate the distance between two bodys */
    public double calcDistance(Body b)
    {
        double dx = xxPos - b.xxPos;
        double dy = yyPos - b.yyPos;

        return Math.sqrt(dx * dx + dy * dy);
    }

    /** Calculate the force exerted by another body */
    public double calcForceExertedBy(Body b)
    {
        double r = calcDistance(b);
        return (G * mass * b.mass) / (r * r);
    }

    /** Calculate the x-axis force exerted by another body */
    public double calcForceExertedByX(Body b)
    {
        double f = calcForceExertedBy(b);
        double r = calcDistance(b);

        return (f * (b.xxPos - xxPos) / r);
    }

    /** Calculate the y-axis force exerted by another body */
    public double calcForceExertedByY(Body b)
    {
        double f = calcForceExertedBy(b);
        double r = calcDistance(b);

        return (f * (b.yyPos - yyPos) / r);
    }

    /** Calculate the x-axis force exerted by other bodies */
    public double calcNetForceExertedByX(Body[] bodies)
    {
        double net_force_x = 0;
        for (int i = 0; i < bodies.length; i++)
        {
            if (bodies[i].equals(this))
            {
                continue;
            }
            net_force_x += calcForceExertedByX(bodies[i]);
        }

        return net_force_x;
    }

    /** Calculate the y-axis force exerted by other bodies */
    public double calcNetForceExertedByY(Body[] bodies)
    {
        double net_force_y = 0;
        for (int i = 0; i < bodies.length; i++)
        {
            if (bodies[i].equals(this))
            {
                continue;
            }
            net_force_y += calcForceExertedByY(bodies[i]);
        }

        return net_force_y;
    }

    /** Update the current position on x and y given the force and the time */
    public void update(double dt, double fx, double fy)
    {
        double ax = fx / mass;
        double ay = fy / mass;

        // update the x / y velocity
        xxVel += (ax * dt);
        yyVel += (ay * dt);

        // update the x / y position given dt
        xxPos += (xxVel * dt);
        yyPos += (yyVel * dt);
    }
    
    /** Draw the body at the current position */
    public void draw()
    {
        String img_path = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, img_path);
    }
}