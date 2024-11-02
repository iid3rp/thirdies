package thirds;

import thirds.io.Resources;
import thirds.swing.MoveableComponent;
import thirds.swing.ScrollPane;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

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
        scrollPane.setSize(780, 720);

        BufferedImage image;
        try{
            image = ImageIO.read(Resources.getResourceAsStream("demoMap.png"));
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
        BufferedImage finalImage = image;
        MoveableComponent panel = new MoveableComponent()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                g.drawImage(finalImage, 0, 0, null);
            }
        };
        panel.setSize(image.getWidth(), image.getHeight());

        scrollPane.setContainerPanel(panel);
        scrollPane.addMouseListener(new MouseAdapter()
        {
            AtomicBoolean bool = new AtomicBoolean(true);
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(bool.get()) {
                    scrollPane.scale(0, 0,1280,720, 200);
                    bool.set(false);
                }
                else
                {
                    scrollPane.scale(0, 0,780,720, 200);
                    bool.set(true);
                }
            }
        });
        return scrollPane;
    }

    private JPanel createPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(1280, 720);
        return panel;
    }

    private void initializeComponent()
    {
        setSize(1280, 720);
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
