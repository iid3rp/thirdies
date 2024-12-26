package thirds.application;

import thirds.interfaces.PlaceInfo;
import thirds.io.Resources;
import thirds.interfaces.AccountInfo;
import thirds.io.ThirdsObjectReader;
import thirds.swing.Label;
import thirds.swing.MoveableComponent;
import thirds.swing.ScrollPane;
import thirds.util.ThirdsUtil;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

public class MapApplication {
    private static JPanel panel;
    private static ScrollPane scrollPane;

    public static final JFrame frame = new JFrame();

    static {
        ThirdsObjectReader.readAllPlaces();
        initializeComponent();

        panel = createPanel();
        frame.add(panel);

        scrollPane = createScrollableMap();
        panel.add(scrollPane);


        PlaceInfo.panel.setLocation(1280, 0);
        scrollPane.glassPane.add(PlaceInfo.panel);

        AccountInfo.getPanel().setLocation(-330, 0);
        scrollPane.glassPane.add(AccountInfo.getPanel());

        frame.setVisible(true);
    }

    private MapApplication() {
        throw new IllegalStateException("Utility classes should not be instantiated.");
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
                System.out.println("kdfjdkj");
                if (bool.get() && PlaceInfo.panel.getX() != 1280) {
                    PlaceInfo.panel.move(1280, 0, 200);
                    bool.set(false);
                } else {
                    PlaceInfo.panel.move(930, 0, 200);
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
                    PlaceInfo.panel.move(1280, 0, 200);
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
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ThirdsObjectReader.serializeAllPlaces(ThirdsUtil.getPlaceList());
                ThirdsObjectReader.serializeAllUsers(ThirdsUtil.getUserMap());
            }
        });
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