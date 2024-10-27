package thirds.lang;

public class Vec2
{
    double x, y;

    public Vec2()
    {
        x = y = 0;
    }

    public Vec2(double xy)
    {
        x = y = xy;
    }

    public Vec2(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double length()
    {
        return Math.sqrt(x * x + y * y);
    }
}
