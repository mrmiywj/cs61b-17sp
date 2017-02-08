public class NBody {
    public static int N;
    public static double T;
    public static double dt;
    public static String filename;
    public static double radius;
    public static Planet[] planets;

    public static double readRadius(String uri) {
        In in = new In(uri);
        N = in.readInt();
        double r = in.readDouble();
        return r;
    }

    public static Planet[] readPlanets(String uri) {
        In in = new In(uri);
        N = in.readInt();
        in.readDouble();
        Planet[] res = new Planet[N];
        for (int i = 0; i < N; ++i) {
            double x = in.readDouble();
            double y = in.readDouble();
            double x_vel = in.readDouble();
            double y_vel = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            res[i] = new Planet(x, y, x_vel, y_vel, m, img);
        }
        return res;
    }

    public static void main(String[] args) {
        T = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);
        filename = args[2];
        radius = readRadius(filename);
        planets = readPlanets(filename);
        String background = "images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, background, 2 * radius, 2*radius);
        StdDraw.show();
        for (Planet p:planets) {
            p.draw();
        }
        StdDraw.show(10);

        double t = 0;
        while (t < T) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];
            for (int i = 0; i < N; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                xForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < N; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0,0,background,2*radius, 2*radius);
            StdDraw.show();
            for (Planet p:planets) {
                p.draw();
            }
            StdDraw.show(10);
        }
    }
}
