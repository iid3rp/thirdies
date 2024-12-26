package thirds.application;

import thirds.io.Resources;
import thirds.place.Place;
import thirds.interfaces.AccountInfo;
import thirds.swing.Label;
import thirds.swing.MoveableComponent;
import thirds.swing.ScrollPane;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class MapApplication {
    private static JPanel panel;
    private static ScrollPane scrollPane;
    private static ScrollPane placeInformation;

    public static final JFrame frame = new JFrame();

    static {
        initializeComponent();

        panel = createPanel();
        frame.add(panel);

        scrollPane = createScrollableMap();
        panel.add(scrollPane);

        placeInformation = createPlaceInformation();
        panel.add(placeInformation);

        scrollPane.glassPane.add(placeInformation);

        AccountInfo.getPanel().setLocation(-330, 0);
        scrollPane.glassPane.add(AccountInfo.getPanel());

        frame.setVisible(true);
    }

    private MapApplication() {
        throw new IllegalStateException("Utility classes should not be instantiated.");
    }

    private static ScrollPane createPlaceInformation() {
        BufferedImage image, fadeDown, fadeUp;
        try (InputStream stream = Resources.getResourceAsStream("demoPlace.jpg");
             InputStream stream2 = Resources.getResourceAsStream("fadeColor.png");
             InputStream stream3 = Resources.getResourceAsStream("fadeUp.png")) {
            image = ImageIO.read(stream);
            fadeDown = ImageIO.read(stream2);
            fadeUp = ImageIO.read(stream3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ScrollPane sc = scrollPlaceInfo(fadeDown);

        MoveableComponent panel = getPlaceInfoPanel(fadeDown, image, fadeUp);

        JLabel label = createSampleLabel();
        JLabel loremLabel = createLoremLabel();

        panel.add(label);
        sc.addComponentToContainer(panel);
        sc.addComponentToContainer(loremLabel);

        MoveableComponent mv = getFadeEffect(fadeDown);
        sc.glassPane.add(mv);

        return sc;
    }

    private static MoveableComponent getFadeEffect(BufferedImage fadeDown) {
        MoveableComponent mv = new MoveableComponent() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2d = fadeDown.createGraphics();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1f));
                g2d.setColor(new Color(177, 106, 0));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
                g.drawImage(fadeDown, 0, 0, 350, 350, null);
            }
        };
        mv.setSize(350, 350);
        mv.setLocation(0, 720 - mv.getHeight());
        return mv;
    }

    private static JLabel createLoremLabel() {
        Label l = new Label();
        l.setOpaque(false);
        l.setSize(310, 20);
        l.setFont(new Font("Franklin Gothic", Font.PLAIN, 18));
        l.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus vel quam ac sapien pharetra lobortis non in ligula. Aenean id orci ut eros ultricies lobortis. Donec sed sodales velit. Duis ac lobortis purus. Quisque nec rutrum sapien, a lobortis enim. Integer in urna ac neque suscipit dignissim eu at elit. Cras dignissim lacus accumsan lectus consequat vulputate. Proin nec ultrices nisi, non sodales elit.\n" +
                "Praesent efficitur iaculis scelerisque. Cras ut laoreet nisi. Cras libero sapien, facilisis id sapien a, eleifend pellentesque dui. Etiam non iaculis tellus, eu rutrum est. Quisque et dignissim tortor. Donec aliquet sollicitudin orci ut aliquam. Quisque ut purus molestie, aliquet metus nec, placerat nulla. Quisque sit amet lorem sapien. Etiam euismod dapibus eros, eget ultricies arcu pulvinar ut. Sed ac lorem urna. Nulla ullamcorper tristique erat. Nam faucibus aliquet vulputate");
        l.setLocation(20, 350);
        l.setForeground(Color.WHITE);
        return l;
    }

    private static ScrollPane scrollPlaceInfo(BufferedImage fadeDown) {
        ScrollPane sc = new ScrollPane();
        sc.setSize(350, 720);
        sc.setContainerSize(350, 2000);
        sc.setLocation(1280, 0);
        sc.setContainerBackground(new Color(177, 106, 0));

        return sc;
    }

    private static MoveableComponent getPlaceInfoPanel(BufferedImage fadeDown, BufferedImage image, BufferedImage fadeUp) {
        MoveableComponent panel = new MoveableComponent() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2d = fadeDown.createGraphics();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1f));
                g2d.setColor(new Color(177, 106, 0));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();

                g.drawImage(image, 0, 0, 350, 350, null);
                g.drawImage(fadeDown, 0, 0, 350, 350, null);
                g.drawImage(fadeUp, 0, 0, 350, 350, null);
            }
        };

        panel.setSize(350, 350);
        panel.setLocation(0, 0);
        return panel;
    }

    private static JLabel createSampleLabel() {
        JLabel label = new JLabel();
        label.setText("<html>" +
                "Sample\n<br> " +
                "Coffee Shop </br>" +
                "</html>");
        label.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 50));
        label.setForeground(Color.white);

        FontMetrics fm = label.getFontMetrics(label.getFont());
        int width = fm.stringWidth(label.getText()) + label.getText().length();
        int height = fm.getHeight();

        for (char s : label.getText().toCharArray())
            if (s == '\n')
                height += fm.getHeight() + 1;

        label.setBounds(20, 350 - height - 20,
                width, height);
        return label;
    }

    private static void refreshPlaceInformation(Place place) {
        if (place == null)
            return;

        int i = 0;
    }

    private static ScrollPane createScrollableMap() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setSize(1280, 720);

        BufferedImage image;
        try {
            image = ImageIO.read(Resources.getResourceAsStream("demoMap.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        BufferedImage finalImage = image;
        MoveableComponent panel = new MoveableComponent() {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(finalImage, 0, 0, null);
            }
        };
        panel.setSize(image.getWidth(), image.getHeight());

        scrollPane.setContainerPanel(panel);
        scrollPane.addMouseListener(new MouseAdapter() {
            AtomicBoolean bool = new AtomicBoolean(true);

            @Override
            public void mouseClicked(MouseEvent e) {
                if (bool.get() && placeInformation.getX() != 1280) {
                    placeInformation.move(1280, 0, 200);
                    bool.set(false);
                } else {
                    placeInformation.move(930, 0, 200);
                    bool.set(true);
                }
            }
        });

        Label label = new Label();
        label.setTextOrigin("Info");
        label.setSize(100, 40);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        label.setLocation(20, 0);

        label.addMouseListener(new MouseAdapter() {
            AtomicBoolean bool = new AtomicBoolean(true);

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("clickies");
                if (bool.get()) {
                    placeInformation.move(1280, 0, 200);
                    AccountInfo.getPanel().move(0, 0, 200);
                    label.move(350, 0, 200);
                    bool.set(false);
                } else {
                    AccountInfo.getPanel().move(-330, 0, 200);
                    label.move(20, 0, 200);
                    bool.set(true);
                }
            }
        });

        scrollPane.glassPane.add(label);

        return scrollPane;
    }

    private static JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(1280, 720);
        return panel;
    }

    private static void initializeComponent() {
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0));
    }

    public static void main(String... args) {
        System.out.println("Hello World!");
        SwingUtilities.invokeLater(() -> {
            // Initialization of static members and components is already done in the static block
            // No need to do anything further here, just ensure the frame is visible
            frame.setVisible(true);
        });
    }
}