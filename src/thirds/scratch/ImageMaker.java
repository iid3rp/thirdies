package thirds.scratch;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageMaker
{
    public static void main(String... args)
    {
        BufferedImage image = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        for(int i = 0; i < 100; i++) {
            g.setColor(new Color(0, 0, 0,100 - i));
            g.fillRect(0, i, 300, 1);
        }
        g.dispose();

        try {
            File file = new File(
                    System.getProperty("user.home") + File.separator + "Pictures" + File.separator + "fadeUp.png");
            boolean bool = ImageIO.write(image, "PNG", file);
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
