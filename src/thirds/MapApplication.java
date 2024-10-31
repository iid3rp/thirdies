package thirds;

import thirds.io.Resources;
import thirds.swing.ScrollPane;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MapApplication extends JFrame
{
    private JPanel panel;
    private ScrollPane scrollPane;

    public MapApplication()
    {
        super();
        initializeComponent();

        panel = createPanel();
        add(panel);

        scrollPane = createScrollPane();
        panel.add(scrollPane);

        setVisible(true);
    }

    private ScrollPane createScrollPane()
    {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setSize(1420, 1200);

        BufferedImage image;
        try{
            image = ImageIO.read(Resources.getResourceAsStream("demoMap.png"));
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
        BufferedImage finalImage = image;
        JPanel panel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                g.drawImage(finalImage, 0, 0, null);
            }
        };
        panel.setSize(image.getWidth(), image.getHeight());

        scrollPane.setContainerPanel(panel);
        return scrollPane;
    }

    private JPanel createPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(1920, 1200);
        return panel;
    }

    private void initializeComponent()
    {
        setSize(1920, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0));
    }

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(MapApplication::new);
    }
}
