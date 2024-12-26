package thirds.interfaces;

import thirds.io.Resources;
import thirds.swing.MoveableComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountInfo2 {
    private static BufferedImage background;
    private static MoveableComponent panel;

    static {
        initializeComponent();
        addLabel("Place Name", 18, 119);
        addLabel("500 Meters Away", 18, 136);
        addLabel("4.8", 296, 131);

        addLabel("Place Name", 18, 188);
        addLabel("500 Meters Away", 18, 205);
        addLabel("4.8", 296, 200);

        addLabel("Place Name", 18, 256);
        addLabel("500 Meters Away", 18, 273);
        addLabel("4.8", 296, 268);




        panel.add(createSearchTextFieldWithPlaceholder(41, 36));

    }

    private AccountInfo2()
    {
        throw new IllegalStateException("Utility classes should not be instantiated.");
    }

    private static void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Ebrima", Font.PLAIN, 20));
        label.setForeground(Color.BLACK);

        FontMetrics fm = label.getFontMetrics(label.getFont());
        int width = fm.stringWidth(text) + text.length();
        int height = fm.getHeight();

        label.setSize(width, height);
        label.setLocation(x, y);
        panel.add(label);
    }

    private static JPanel createSearchTextFieldWithPlaceholder(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 163, 29);
        panel.setOpaque(false);

        JTextField textField = new JTextField("Search");
        textField.setBounds(0, 0, 271, 29);
        textField.setOpaque(false);
        textField.setBackground(new Color(0, 0, 0, 0));
        textField.setForeground(Color.BLACK);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setHorizontalAlignment(SwingConstants.LEFT);
        textField.setFont(new Font("Ebrima", Font.PLAIN, 16));


        textField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().equals("Search")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().trim().isEmpty()) {
                    textField.setText("Search");
                    textField.setFont(new Font("Ebrima", Font.PLAIN, 16));
                    textField.setForeground(Color.BLACK);
                }
            }
        });

        panel.add(textField);
        return panel;
    }

    private static void addXIcon() {
        try {
            BufferedImage settingsIcon = ImageIO.read(Resources.getResourceAsStream("x.png"));
            JLabel iconLabel = new JLabel(new ImageIcon(settingsIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            iconLabel.setSize(40, 40);
            iconLabel.setLocation(0, 0);
            panel.add(iconLabel);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load x icon", e);
        }
    }

    private static void addSearchIcon() {
        try {
            BufferedImage exitIcon = ImageIO.read(Resources.getResourceAsStream("search.png"));
            JLabel iconLabel = new JLabel(new ImageIcon(exitIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            iconLabel.setSize(40, 40);
            iconLabel.setLocation(0, 0);
            panel.add(iconLabel);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load exit icon", e);
        }
    }

    private static void addLocationIcon() {
        try {
            BufferedImage editIcon = ImageIO.read(Resources.getResourceAsStream("location.png"));
            JLabel iconLabel = new JLabel(new ImageIcon(editIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            iconLabel.setSize(40, 40);
            iconLabel.setLocation(0, 0);
            panel.add(iconLabel);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load edit icon", e);
        }
    }

    private static void addProfileIcon() {
        try {
            BufferedImage profileIcon = ImageIO.read(Resources.getResourceAsStream("profile.png"));
            JLabel iconLabel = new JLabel(new ImageIcon(profileIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            iconLabel.setSize(40, 40);
            iconLabel.setLocation(0, 0);
            panel.add(iconLabel);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load profile icon", e);
        }
    }

    private static void initializeComponent() {
        panel = new MoveableComponent()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                g.drawImage(background, 0, 0, panel.getWidth(), panel.getHeight(), null);
            }
        };
        try {
            background = ImageIO.read(Resources.getResourceAsStream("AccountInfoTwo.png"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load background image", e);
        }
        panel.setLayout(null); // Set null layout for absolute positioning
        panel.setSize(new Dimension(330, 720));
        panel.setBackground(new Color(184, 141, 29));
    }

    public static MoveableComponent getPanel()
    {
        return panel;
    }

    private static void addEmptyMouseListener(JLabel label) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Empty implementation
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Empty implementation
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Empty implementation
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Empty implementation
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Empty implementation
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(330, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);

        frame.add(getPanel());
        frame.setVisible(true);
    }
}