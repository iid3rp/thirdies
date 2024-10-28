package thirds.swing;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Point;
import java.util.concurrent.atomic.AtomicInteger;

public class MoveableComponent extends JPanel
{
    private Timer timer;

    public MoveableComponent()
    {
        super();
    }

    public void move(double x, double y, long millis)
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

        timer = new Timer(10, e ->
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

                //System.out.println(newX + " " + newY + " " + newWidth + " " + newHeight);
            }
            else {
                ((Timer) e.getSource()).stop(); // Stop the timer when scaling is complete
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
}
