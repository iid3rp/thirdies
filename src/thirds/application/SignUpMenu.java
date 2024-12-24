package thirds.application;

import thirds.io.Resources;
import thirds.scratch.LogInScreen;
import thirds.scratch.SignUpScreen;
import thirds.scratch.SignUpScrollable;
import thirds.swing.MoveableComponent;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SignUpMenu
{
    public static JFrame frame;
    private static BufferedImage background;
    private static BufferedImage fade;
    public static boolean isDragging;
    public static Point offset;

    public static void main(String... args)
    {
        setUpImages();
        frame = new JFrame("Thirds");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setSize(500, 350);
        frame.setLocationRelativeTo(null);

        MoveableComponent panel = createMenuPanel();
        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                int padding = 6;
                int iconX = 450 + padding;
                int iconY = 20 + padding;
                int iconSize = 30 - 2 * padding;

                Rectangle closeIconBounds = new Rectangle(iconX, iconY, iconSize, iconSize);

                System.out.println("hello world");
                // if this points to the exit
                if (closeIconBounds.contains(e.getPoint())) {
                    close();
                }
                panel.requestFocusInWindow();
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (SwingUtilities.isLeftMouseButton(e))
                {
                    isDragging = true;
                    offset = e.getPoint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (SwingUtilities.isLeftMouseButton(e))
                {
                    isDragging = false;
                }
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                if (isDragging)
                {
                    Point currentMouse = e.getLocationOnScreen();

                    int deltaX = currentMouse.x - offset.x;
                    int deltaY = currentMouse.y - offset.y;

                    frame.setLocation(deltaX, deltaY);
                }
            }
        });

        frame.add(panel);
        panel.add(LogInScreen.panel);

        SignUpScreen.panel.setLocation(500, 0);
        panel.add(SignUpScreen.panel);

        SignUpScrollable.panel.setLocation(500, 0);
        panel.add(SignUpScrollable.panel);

        frame.setVisible(true);

    }

    private static MoveableComponent createMenuPanel()
    {
        MoveableComponent panel = new MoveableComponent()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                //
                Graphics2D graphics = background.createGraphics();
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.drawImage(background, 0, 0, null);

                Graphics2D anotherGraphics = fade.createGraphics();
                anotherGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 0.2f));
                anotherGraphics.drawRect(0, 0,500,350);

                g.drawImage(background, 0, 0, getWidth(),getHeight(),null);
                g.drawImage(fade, -2, 0, getWidth() + 4,getHeight() + 2,null);

                drawCloseIcon(g);
            }
        };
        panel.setFocusable(true);
        panel.setSize(500, 300);
        return panel;
    }

    private static void setUpImages()
    {
        try {
            background = ImageIO.read(Resources.getResourceAsStream("demoBlurred.png"));
            fade = ImageIO.read(Resources.getResourceAsStream("fadeColor.png"));
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close()
    {
        System.exit(0);
    }

    private static void drawCloseIcon(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(3)); // added stroke guys
        g2d.setColor(Color.ORANGE);
        int padding = 6;
        int x1 = 450 + padding;
        int y1 = 20 + padding;
        int x2 = 450 + 30 - padding;
        int y2 = 20 + 30 - padding;

        g2d.drawLine(x1, y1, x2, y2); // Top-left to bottom-right
        g2d.drawLine(x1, y2, x2, y1); // Bottom-left to top-right
    }
}
