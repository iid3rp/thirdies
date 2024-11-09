package thirds.swing;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Image
{
    private BufferedImage image;
    private Color average;
    public Image() {}

    public BufferedImage getImage()
    {
        return image;
    }

    public Color getAverage()
    {
        return average;
    }

    public void setImage(BufferedImage image)
    {
        this.image = image;
    }
}
