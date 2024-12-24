package thirds.scratch;

import thirds.io.Resources;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreatePlace extends JPanel {

    public CreatePlace() {
        super();
        initializeComponent();

        // Add components
        JLabel titleText = createTitleText();
        JLabel addImageText = createAddImageText();
        JLabel previewText = createPreviewText();
        add(previewText);
        add(addImageText);
        add(titleText);

        add(createPlaceNameField(50, 100));
        add(createPlaceTypeField(50, 180)); // Adjusted y coordinate
        add(createAddressField(50, 260));   // Adjusted y coordinate

//        add(createPlaceNameField(50, 100));
//        add(createPlaceTypeField(50, 150));
//        add(createAddressField(50, 200));
        add(createZipCodeField(340, 180));

        add(createLocationButton(50, 340));
        add(createImageUploadArea(50, 300));

        add(createFacilitiesSection(50, 450));
        add(createSlidersSection(50, 600));
        add(createRatingSection(50, 730));
        add(createTagsField(50, 800));

        addCloseIconListener();
    }

    private void initializeComponent() {
        setLayout(null);
        setSize(1200, 950);
        setBackground(Color.WHITE);
        setVisible(true);
    }

    private JLabel createTitleText() {
        JLabel label = new JLabel();
        label.setText("Create Place");
        label.setFont(new Font("Kantumruy Pro", Font.BOLD, 35));
        label.setForeground(Color.BLACK);

        FontMetrics fm = label.getFontMetrics(label.getFont());
        int width = fm.stringWidth(label.getText()) + label.getText().length();
        int height = fm.getHeight();

        label.setSize(width, height);
        label.setLocation(50, 30);
        return label;
    }

    private JLabel createAddImageText() {
        JLabel label = new JLabel();
        label.setText("Add Images");
        label.setFont(new Font("Kantumruy Pro", Font.BOLD, 30));
        label.setForeground(Color.BLACK);
        FontMetrics fm = label.getFontMetrics(label.getFont());
        int width = fm.stringWidth(label.getText()) + label.getText().length();
        int height = fm.getHeight();

        label.setSize(width, height);
        label.setLocation(630, 30);
        return label;
    }

    private JLabel createPreviewText() {
        JLabel label = new JLabel();
        label.setText("Preview");
        label.setFont(new Font("Kantumruy Pro", Font.BOLD, 30));
        label.setForeground(Color.BLACK);
        FontMetrics fm = label.getFontMetrics(label.getFont());
        int width = fm.stringWidth(label.getText()) + label.getText().length();
        int height = fm.getHeight();

        label.setSize(width, height);
        label.setLocation(950, 30);
        return label;
    }

    private JPanel createPlaceNameField(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 271, 60); // Increased height to accommodate label
        panel.setOpaque(false);

        JLabel label = new JLabel("Place Name");
        label.setBounds(0, 0, 271, 20);
        label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        label.setForeground(Color.BLACK);

        JTextField textField = new JTextField();
        textField.setBounds(0, 25, 271, 29); // Moved down to make room for label
        textField.setOpaque(true);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textField.setHorizontalAlignment(SwingConstants.LEFT);

        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private JPanel createPlaceTypeField(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 271, 60);
        panel.setOpaque(false);

        JLabel label = new JLabel("Place Type");
        label.setBounds(0, 0, 271, 20);
        label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        label.setForeground(Color.BLACK);

        JTextField textField = new JTextField();
        textField.setBounds(0, 25, 271, 29);
        textField.setOpaque(true);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private JPanel createAddressField(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 271, 60);
        panel.setOpaque(false);

        JLabel label = new JLabel("Place Address");
        label.setBounds(0, 0, 271, 20);
        label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        label.setForeground(Color.BLACK);

        JTextField textField = new JTextField();
        textField.setBounds(0, 25, 271, 29);
        textField.setOpaque(true);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private JPanel createZipCodeField(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 270, 60);
        panel.setOpaque(false);

        JLabel label = new JLabel("Zip Code");
        label.setBounds(0, 0, 270, 20);
        label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        label.setForeground(Color.BLACK);

        JTextField textField = new JTextField();
        textField.setBounds(0, 25, 55, 29);
        textField.setOpaque(true);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private JButton createLocationButton(int x, int y) {
        JButton button = new JButton("Place Pinned Location on Map");
        button.setBounds(x, y, 250, 35);
        button.setFont(new Font("Kantumruy Pro", Font.PLAIN, 15));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(255, 165, 0));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

    private JPanel createImageUploadArea(int x, int y) {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 40));
        panel.setBounds(x, y, 220, 900);
        panel.setLocation(620,100);
        panel.setOpaque(false);

        for (int i = 0; i <3; i++) {
            JPanel imagePanel = new JPanel();
            imagePanel.setBackground(new Color(240, 240, 240));
         //   imagePanel.setBorder(BorderFactory.createLineBorder(new Color(255, 165, 0)));
            imagePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Image selection logic here
                }
            });
            panel.add(imagePanel);
        }

        return panel;
    }

    private JPanel createFacilitiesSection(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 350, 120); // Adjusted height to accommodate larger checkboxes
        panel.setOpaque(false);

        String[] facilities = {"Air Conditioning", "WiFi Availability", "Restroom"};
        int yOffset = 0;

        for (String facility : facilities) {
            JCheckBox checkBox = new JCheckBox(facility);
            checkBox.setBounds(0, yOffset, 250, 30); // Increased height for larger checkbox

            checkBox.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18)); // Increased font size
            checkBox.setForeground(Color.BLACK);
            checkBox.setOpaque(false);
            panel.add(checkBox);
            yOffset += 40; // Increased vertical gap between checkboxes
        }

        return panel;
    }

    private JPanel createSlidersSection(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 350, 120);
        panel.setOpaque(false);

        String[] sliderNames = {"Lighting Level", "Ambience Level", "Noise Level"};
        int yOffset = 0;

        for (String name : sliderNames) {
            JLabel label = new JLabel(name);
            label.setBounds(0, yOffset, 100, 20);
            label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 14));
            label.setForeground(Color.BLACK);

            JSlider slider = new JSlider(1, 10, 5);
            slider.setBounds(110, yOffset, 200, 20);
            slider.setOpaque(false);
            slider.setForeground(Color.BLACK);

            panel.add(label);
            panel.add(slider);
            yOffset += 40;
        }

        return panel;
    }

    private JPanel createRatingSection(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 350, 30);
        panel.setOpaque(false);

        JLabel label = new JLabel("Rating:");
        label.setBounds(0, 0, 60, 30);
        label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 14));
        label.setForeground(Color.BLACK);
        panel.add(label);

        int starX = 70;
        for (int i = 0; i < 5; i++) {
            JLabel star = new JLabel("★");
            star.setBounds(starX, 0, 30, 30);
            star.setFont(new Font("Kantumruy Pro", Font.PLAIN, 20));
            star.setForeground(Color.YELLOW);
            final int rating = i + 1;
            star.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Rating logic here
                }
            });
            panel.add(star);
            starX += 30;
        }

        return panel;
    }

    private JPanel createTagsField(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 271, 29);
        panel.setOpaque(false);

        JTextField textField = new JTextField("Enter Tags");
        textField.setBounds(0, 0, 271, 29);
        textField.setOpaque(false);
        textField.setBackground(new Color(0, 0, 0, 0));
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());

        setupPlaceholderBehavior(textField, "Enter Tags");
        panel.add(textField);
        return panel;
    }

    private void setupPlaceholderBehavior(JTextField textField, String placeholder) {
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().trim().isEmpty()) {
                    textField.setText(placeholder);
                }
            }
        });
    }

    private void drawLines(Graphics g) {

    }

    private void addCloseIconListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Rectangle closeIconBounds = new Rectangle(1130, 15, 30, 30);  // Changed from (750, 20, 30, 30)


                if (closeIconBounds.contains(e.getPoint())) {
                    Window window = SwingUtilities.getWindowAncestor(CreatePlace.this);
                    window.dispose();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCloseIcon(g);
    }

    private void drawCloseIcon(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.ORANGE);

        int padding = 6;
        int x1 = 1130 + padding;  // Changed from 750 to 1120
        int y1 = 15 + padding;    // Changed from 20 to 44
        int x2 = 1130 + 30 - padding;  // Changed x position
        int y2 = 15 + 30 - padding;    // Changed y position

        g2d.drawLine(x1, y1, x2, y2);
        g2d.drawLine(x1, y2, x2, y1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setSize(1200, 950);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setUndecorated(true);

            frame.add(new CreatePlace());
            frame.setVisible(true);
        });
    }
}