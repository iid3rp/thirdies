package thirds.lang;

public class SmoothDouble extends Number
{
    private volatile double value;
    private volatile double target;
    private volatile double delta;
    private volatile double offset;

    public SmoothDouble(double value, double delta)
    {
        this.value = value;
        this.target = value;
        this.delta = delta;
    }

    public synchronized void set(double value)
    {
        this.value = value;
    }

    public synchronized void smooth(double target, double increment)
    {
        target += this.target;

        double offset = target - value;
        double change = offset * (1 / delta);
        value += change;
    }

    @Override
    public synchronized int intValue()
    {
        return (int) value;
    }

    @Override
    public synchronized long longValue()
    {
        return (long) value;
    }

    @Override
    public synchronized float floatValue()
    {
        return (float) value;
    }

    @Override
    public synchronized double doubleValue()
    {
        return value;
    }
}
