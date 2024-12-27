package thirds.application;

import thirds.interfaces.CreatePlace;
import thirds.interfaces.PlaceInfo;
import thirds.interfaces.SearchPlaces;
import thirds.io.Debug;
import thirds.io.Resources;
import thirds.interfaces.AccountInfo;
import thirds.io.ThirdsObjectReader;
import thirds.swing.Label;
import thirds.swing.MoveableComponent;
import thirds.swing.ScrollPane;
import thirds.util.ThirdsUtil;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MapApplication {
    private static JPanel panel;
    private static ScrollPane scrollPane;

    private static Label exit;
    private static Label profile;
    private static Label search;
    private static Label addPlace;

    public static final JFrame frame = new JFrame();

    static {
        ThirdsObjectReader.readAllPlaces();
        initializeComponent();

        panel = createPanel();
        frame.add(panel);

        scrollPane = createScrollableMap();
        panel.add(scrollPane);

        exit = addXIcon();
        profile = addProfileIcon();
        search = addSearchIcon();
        addPlace = addLocationIcon();

        PlaceInfo.panel.setLocation(1280, 0);
        scrollPane.glassPane.add(PlaceInfo.panel);

        AccountInfo.getPanel().setLocation(-330, 0);
        scrollPane.glassPane.add(AccountInfo.getPanel());

        SearchPlaces.getPanel().setLocation(-330, 0);
        scrollPane.glassPane.add(SearchPlaces.getPanel());

        CreatePlace.getPanel().setLocation(-1000, 0);
        scrollPane.glassPane.add(CreatePlace.getPanel());

        scrollPane.glassPane.add(exit);
        scrollPane.glassPane.add(profile);
        scrollPane.glassPane.add(search);
        scrollPane.glassPane.add(addPlace);

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
                    PlaceInfo.panel.setLocation(1280, 0);
                    bool.set(false);
                } else {
                    PlaceInfo.panel.setLocation(930, 0);
                    bool.set(true);
                }
            }
        });

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

    private static Label addXIcon()
    {
        try {
            BufferedImage exitIcon = ImageIO.read(Resources.getResourceAsStream("x.png"));
            Label iconLabel = new Label(new ImageIcon(exitIcon.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            iconLabel.setSize(60, 60);
            iconLabel.setLocation(20, 20);

            iconLabel.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    close();
                }
            });
            return iconLabel;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load exit icon", e);
        }
    }

    private static void close()
    {
        ThirdsObjectReader.serializeAllPlaces(ThirdsUtil.getPlaceList());
        ThirdsObjectReader.serializeAllUsers(ThirdsUtil.getUserMap());
        System.exit(0);
    }

    private static Label addSearchIcon() {
        try {
            BufferedImage exitIcon = ImageIO.read(Resources.getResourceAsStream("search.png"));
            Label iconLabel = new Label(new ImageIcon(exitIcon.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            iconLabel.setSize(60, 60);
            iconLabel.setLocation(20, 140);
            iconLabel.addMouseListener(new MouseAdapter()
            {
                AtomicBoolean bool = new AtomicBoolean(true);

                @Override
                public void mouseClicked(MouseEvent e)
                {
                    System.out.println("clickies");
                    if(SearchPlaces.getPanel().getX() != 0) {
                        PlaceInfo.panel.setLocation(1280, 0);
                        AccountInfo.getPanel().setLocation(-330, 0);
                        CreatePlace.getPanel().setLocation(-1000, 0);
                        SearchPlaces.getPanel().setLocation(0, 0);
                        exit.setLocation(350, 20);
                        profile.setLocation(350, 80);
                        search.setLocation(350, 140);
                        addPlace.setLocation(350, 200);
                        bool.set(false);
                    }
                    else {
                        if(AccountInfo.getPanel().getX() == 0)
                            AccountInfo.getPanel().setLocation(0, 0);
                        if(CreatePlace.getPanel().getX() == 0)
                            CreatePlace.getPanel().setLocation(0, 0);
                        SearchPlaces.getPanel().setLocation(-330, 0);
                        exit.setLocation(20, 20);
                        profile.setLocation(20, 80);
                        search.setLocation(20, 140);
                        addPlace.setLocation(20, 200);
                        bool.set(true);
                    }
                }
            });
            return iconLabel;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load exit icon", e);
        }
    }

    private static Label addLocationIcon() {
        try {
            BufferedImage exitIcon = ImageIO.read(Resources.getResourceAsStream("location.png"));
            Label iconLabel = new Label(new ImageIcon(exitIcon.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            iconLabel.setSize(60, 60);
            iconLabel.setLocation(20, 200);
            iconLabel.addMouseListener(new MouseAdapter()
            {
                AtomicBoolean bool = new AtomicBoolean(true);

                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("clickies");
                    if (CreatePlace.getPanel().getX() != 0) {
                        PlaceInfo.panel.setLocation(1280, 0);
                        CreatePlace.getPanel().setLocation(-1000, 0);
                        AccountInfo.getPanel().setLocation(-330, 0);
                        SearchPlaces.getPanel().setLocation(-330, 0);
                        exit.setLocation(1020, 20);
                        profile.setLocation(1020, 80);
                        search.setLocation(1020, 140);
                        addPlace.setLocation(1020, 200);
                        CreatePlace.getPanel().setLocation(0, 0);
                        bool.set(false);
                    } else {
                        CreatePlace.getPanel().setLocation(-1000, 0);
                        exit.setLocation(20, 20);
                        profile.setLocation(20, 80);
                        search.setLocation(20, 140);
                        addPlace.setLocation(20, 200);
                        bool.set(true);
                    }
                }
            });
            return iconLabel;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load exit icon", e);
        }
    }

    private static Label addProfileIcon() {
        try {
            BufferedImage exitIcon = ImageIO.read(Resources.getResourceAsStream("profile.png"));
            Label iconLabel = new Label(new ImageIcon(exitIcon.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            iconLabel.setSize(60, 60);
            iconLabel.setLocation(20, 80);
            iconLabel.addMouseListener(new MouseAdapter()
            {
                AtomicBoolean bool = new AtomicBoolean(true);

                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("clickies");
                    if (AccountInfo.getPanel().getX() != 0) {
                        PlaceInfo.panel.setLocation(1280, 0);
                        SearchPlaces.getPanel().setLocation(-330, 0);
                        CreatePlace.getPanel().setLocation(-1000, 0);
                        AccountInfo.getPanel().setLocation(0, 0);
                        exit.setLocation(350, 20);
                        profile.setLocation(350, 80);
                        search.setLocation(350, 140);
                        addPlace.setLocation(350, 200);
                        bool.set(false);
                    } else {
                        if(SearchPlaces.getPanel().getX() == 0)
                            SearchPlaces.getPanel().setLocation(0, 0);
                        if(CreatePlace.getPanel().getX() == 0)
                            CreatePlace.getPanel().setLocation(0, 0);
                        AccountInfo.getPanel().setLocation(-330, 0);
                        exit.setLocation(20, 20);
                        profile.setLocation(20, 80);
                        search.setLocation(20, 140);
                        addPlace.setLocation(20, 200);
                        bool.set(true);
                    }
                }
            });
            return iconLabel;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load exit icon", e);
        }
    }

    @Debug
    @Deprecated
    public static void main(String... args) {
        System.out.println("Hello World!");
        SwingUtilities.invokeLater(() -> {
            // Initialization of static members and components is already done in the static block
            // No need to do anything further here, just ensure the frame is visible
            frame.setVisible(true);
        });
    }
}