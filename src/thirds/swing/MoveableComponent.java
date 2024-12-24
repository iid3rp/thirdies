package thirds.swing;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.concurrent.atomic.AtomicInteger;

public class MoveableComponent extends JPanel
{
    private Timer timer;
    private int currentX;
    private int currentY;
    private long lastTime;
    public double velocityX;
    public double velocityY;

    public MoveableComponent()
    {
        super(null);
        setBackground(new Color(0, 0, 0, 0));
    }

    public synchronized void setMotion(int x, int y, long currentTime)
    {
        currentX = x;
        currentY = y;
        lastTime = currentTime;
    }

    public synchronized void slipMotion()
    {
        final double deceleration = 0.95;

        timer = new Timer(5, e -> {

            int newX = getX() + (int) velocityX;
            int newY = getY() + (int) velocityY;

            Dimension d = getParent().getSize();
            if (newX < 0) {
                newX = 0;
                velocityX = -velocityX * deceleration;
            } else if (newX + getWidth() > d.getWidth()) {
                newX = (int) (d.getWidth() - getWidth());
                velocityX = -velocityX * deceleration;
            }

            if (newY < 0) {
                newY = 0;
                velocityY = -velocityY * deceleration;
            } else if (newY + getHeight() > getParent().getHeight()) {
                newY = (int) (d.getHeight() - getHeight());
                velocityY = -velocityY * deceleration;
            }
            // Apply velocity to position
            setLocation(newX, newY);

            // Apply deceleration to slow down over time
            velocityX *= deceleration;
            velocityY *= deceleration;

            // Stop when the velocity is very low
            if (Math.abs(velocityX) < 0.5 && Math.abs(velocityY) < 0.5) {
                timer.stop();
            }

        });
        timer.start();
    }

    public synchronized void move(double x, double y, long millis)
    {
        scale(x, y, getWidth(), getHeight(), millis);
    }

    public synchronized void scale(double x, double y, double width, double height, long millis)
    {
        Point startPoint = getLocation();
        Dimension startDimension = getSize();

        int totalSteps = (int) (millis / 10);

        int deltaX = (int) (x - startPoint.x);
        int deltaY = (int) (y - startPoint.y);

        int deltaWidth = (int) (width - startDimension.width);
        int deltaHeight = (int) (height - startDimension.height);

        double widthScaleFactor = deltaWidth / startDimension.getWidth();
        double heightScaleFactor = deltaHeight / startDimension.getHeight();

        double stepX = (double) deltaX / totalSteps;
        double stepY = (double) deltaY / totalSteps;

        double stepWidth = widthScaleFactor / totalSteps;
        double stepHeight = heightScaleFactor / totalSteps;

        AtomicInteger currentStep = new AtomicInteger();

        if(timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(5, e ->
        {
            if(currentStep.get() < totalSteps) {
                currentStep.getAndIncrement();

                // Calculate the progress as a fraction of total steps
                double progress = (double) currentStep.get() / totalSteps;
                double exponentialFactor = 1 - Math.pow(1 - progress, 2); // Exponential out effect

                // Calculate new position and size
                int newX = (int) (startPoint.getX() + deltaX * exponentialFactor);
                int newY = (int) (startPoint.getY() + deltaY * exponentialFactor);

                int newWidth = (int) (startDimension.getWidth() + deltaWidth * exponentialFactor);
                int newHeight = (int) (startDimension.getHeight() + deltaHeight * exponentialFactor);

                setSize(newWidth, newHeight);
                setLocation(newX, newY);

                if(getParent() != null)
                    getParent().repaint();
                repaint();
                //System.out.println(newX + " " + newY + " " + newWidth + " " + newHeight);
            }
            else {
                ((Timer) e.getSource()).stop(); // Stop the timer when scaling is complete
                if(getParent() != null)
                    getParent().repaint();
                repaint();
            }
        });

        timer.start();
    }

    public void hold()
    {
        if(timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    public int getCurrentX()
    {
        return currentX;
    }

    public int getCurrentY()
    {
        return currentY;
    }

    public long getLastTime()
    {
        return lastTime;
    }
}
