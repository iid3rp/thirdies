package thirds.swing;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.util.concurrent.atomic.AtomicInteger;

public class Label extends JLabel
{
    private Timer timer;

    public Label()
    {
        super();
        setLayout(null);
    }

    public Label(ImageIcon imageIcon)
    {
        super(imageIcon);
        setLayout(null);
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

                //System.out.println(newX + " " + newY + " " + newWidth + " " + newHeight);
            }
            else {
                timer.stop(); // Stop the timer when scaling is complete
            }
            if(getParent() != null)
                getParent().repaint();
            repaint();
        });

        timer.start();
    }

    public static Font font(String family, boolean bold, int size)
    {
        return new Font(family, bold? Font.BOLD: Font.PLAIN, size);
    }

    public void setTextOrigin(String text)
    {
        super.setText(text);
    }

    @Override
    public void setText(String text)
    {
        if (getWidth() == 0)
            return;

        FontMetrics fm = this.getFontMetrics(this.getFont());
        StringBuilder newText = new StringBuilder("<html>");
        int currentWidth = 0;
        int lineCount = 1; // Start with at least one line

        for (String str : text.split(" ")) {
            int wordWidth = fm.stringWidth(str + " ");

            // If adding this word exceeds the label width, wrap to a new line
            if (currentWidth + wordWidth > getWidth()) {
                newText.append("<br>"); // Add a newline
                lineCount++;          // Increase line count
                currentWidth = 0;     // Reset current width for the new line
            }

            // Add the word and update currentWidth
            newText.append(str).append(" ");
            currentWidth += wordWidth;
        }

        newText.append("</html>");

        // Set the wrapped text
        super.setText(newText.toString().trim());

        // Adjust the height of the label based on the line count and font height
        int lineHeight = fm.getHeight();
        setSize(getWidth(), lineHeight * (lineCount + 1));
    }

    @Override
    public void setFont(Font font)
    {
        super.setFont(font);
        setSize(getWidth(), font.getSize());
        setText(getText());
    }

    public void setWidth(int width)
    {
        setSize(width, getHeight());
        setText(getText());
    }
}
