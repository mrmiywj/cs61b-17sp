public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet other) {
        this.xxPos = other.xxPos;
        this.yyPos = other.yyPos;
        this.xxVel = other.xxVel;
        this.yyVel = other.yyVel;
        this.mass = other.mass;
        this.imgFileName = other.imgFileName;
    }

    public double calcDistance(Planet other) {
        double res = (this.xxPos - other.xxPos) * (this.xxPos - other.xxPos) + (this.yyPos - other.yyPos) * (this.yyPos - other.yyPos);
        return Math.sqrt(res);
    }

    public double calcForceExertedBy(Planet other) {
        double dis = calcDistance(other);
        return G * this.mass * other.mass / (dis * dis);
    }

    public double calcForceExertedByX(Planet other) {
        double f = calcForceExertedBy(other);
        return f * Math.abs(this.xxPos - other.xxPos) / calcDistance(other);
    }

    public double calcForceExertedByY(Planet other) {
        double f = calcForceExertedBy(other);
        return f * Math.abs(this.yyPos - other.yyPos) / calcDistance(other);
    }

    public double calcNetForceExertedByX(Planet[] others) {
        double res = 0.0;
        for (Planet p: others) {
            if (p.equals(this)) {
                continue;
            }
            double f = calcForceExertedByX(p);
            if (p.xxPos - this.xxPos > 0 ) {
                res += f;
            } else {
                res -= f;
            }
        }
        return res;
    }

    public double calcNetForceExertedByY(Planet[] others) {
        double res = 0.0;
        for (Planet p: others) {
            if (p.equals(this)) {
                continue;
            }
            double f = calcForceExertedByY(p);
            if (p.yyPos - this.yyPos > 0 ) {
                res += f;
            } else {
                res -= f;
            }
        }
        return res;
    }

    public void update(double sec, double x_force, double y_force) {
        double a_x = x_force / this.mass;
        double a_y = y_force / this.mass;
        this.xxVel += a_x * sec;
        this.yyVel += a_y * sec;
        this.xxPos += this.xxVel * sec;
        this.yyPos += this.yyVel * sec;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
