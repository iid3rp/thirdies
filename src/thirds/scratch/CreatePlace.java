package thirds.scratch;

import thirds.io.Resources;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CreatePlace extends JPanel {
    private JLabel previewPlaceName; // Global reference for preview label
    private JLabel previewImage;
    private JLabel previewExtraInfo;
    private JLabel imagePreview;
    private JTextField[] extraInfoFields;

    public CreatePlace() {
        super();
        initializeComponent();
    }

    private void initializeComponent() {
        setLayout(null);
        setSize(1000, 780);
        setBackground(Color.WHITE);

        add(createTitleLabel());
        add(createPreviewLabel());
        add(createPlaceNameField()); // Add PlaceName field panel
        add(createImageSection());
        add(createExtraInfoSection());
    }

    // Title Section
    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Create Place");
        titleLabel.setFont(new Font("Kantumruy Pro", Font.BOLD, 24));
        titleLabel.setBounds(35, 35, 300, 30);
        return titleLabel;
    }
    private JLabel createPreviewLabel() {
        JLabel titleLabel = new JLabel("Preview");
        titleLabel.setFont(new Font("Kantumruy Pro", Font.BOLD, 24));
        titleLabel.setBounds(785, 260, 300, 30);
        return titleLabel;
    }

    // Place Name Field
    private JPanel createPlaceNameField() {
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 260, 160);
        panel.setOpaque(false); // Make the panel transparent

        JLabel placeNameLabel = new JLabel("Place Name");
        placeNameLabel.setFont(new Font("Kantumruy Pro", Font.PLAIN, 19));
        placeNameLabel.setBounds(35, 90, 150, 20);
        panel.add(placeNameLabel);

        JTextField placeNameField = new JTextField();
        placeNameField.setBounds(35, 115, 220, 40);
        placeNameField.setOpaque(true);
        placeNameField.setForeground(Color.DARK_GRAY);
        placeNameField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        placeNameField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        placeNameField.setBackground(Color.lightGray);
        panel.add(placeNameField);

        // Initialize the previewPlaceName global label with left alignment and white font color
        previewPlaceName = new JLabel("<html><font color='white'>Place Name</font></html>", SwingConstants.LEFT);
        previewPlaceName.setFont(new Font("Kantumruy Pro", Font.BOLD, 30));
        previewPlaceName.setHorizontalAlignment(SwingConstants.LEFT); // Ensure left alignment
        previewPlaceName.setBounds(720, 108, 260, 100);
        add(previewPlaceName); // Add to the main panel

        // Add KeyListener to update the preview label
        placeNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = placeNameField.getText().trim();
                if (text.isEmpty()) {
                    previewPlaceName.setText("<html><font color='white'>Place Name</font></html>"); // Default preview text with white font

                } else {
                    // Use HTML to update with line breaks if text exceeds width
                    previewPlaceName.setText("<html><font color='white'>" + text.replaceAll("(.{20})", "$1<br>") + "</font></html>");
                }
            }
        });

        return panel;
    }



    // Image Upload Section
    private JPanel createImageSection() {
        JPanel panel = new JPanel(null);
        panel.setBounds(35, 390, 250, 300);
        panel.setOpaque(false); // Make the panel transparent

        JLabel imageLabel = new JLabel("Image");
        imageLabel.setBounds(0, 0, 51, 21);
        panel.add(imageLabel);

        imagePreview = new JLabel();
        imagePreview.setBounds(0, 30, 175, 175);
        imagePreview.setBorder(new LineBorder(Color.BLACK));
        panel.add(imagePreview);

        JButton uploadImageButton = new JButton("Upload Image");
        uploadImageButton.setBounds(0, 220, 140, 40);
        uploadImageButton.addActionListener(e -> handleImageUpload());
        panel.add(uploadImageButton);

        // Initialize the previewImage label with gray background
        previewImage = new JLabel();
        previewImage.setBounds(680, 1, 300, 250); // Set the initial size
        previewImage.setBackground(Color.LIGHT_GRAY); // Set default gray background
        previewImage.setOpaque(true); // Ensure the background color is visible
        previewImage.setBorder(new LineBorder(Color.BLACK));
        add(previewImage);

        return panel;
    }

    // Example handleImageUpload method
    private void handleImageUpload() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(selectedFile);

                // Scale image to fit within the 300x250 size while maintaining the aspect ratio
                Image scaledImg = img.getScaledInstance(300, 250, Image.SCALE_SMOOTH);

                // Set the icon for both previewImage and imagePreview labels
                previewImage.setIcon(new ImageIcon(scaledImg));
                imagePreview.setIcon(new ImageIcon(scaledImg));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    // Extra Information Section
    private JPanel createExtraInfoSection() {
        JPanel panel = new JPanel(null);
        panel.setBounds(350, 45, 300, 325);
        panel.setOpaque(false); // Make the panel transparent

        String[] extraInfoLabels = {
                "Seats per table", "Air Conditioning", "Has Restroom", "Wi-Fi Available", "Price Range",
                "Lighting Level", "Ambience Level", "Noise Level"

        };

        extraInfoFields = new JTextField[extraInfoLabels.length];
        previewExtraInfo = new JLabel("<html></html>");
        previewExtraInfo.setBounds(720, 480, 200, 200);
        add(previewExtraInfo);

        for (int i = 0; i < extraInfoLabels.length; i++) {
            JLabel label = new JLabel(extraInfoLabels[i]);
            label.setBounds(0, i * 40, 150, 20);
            label.setFont(new Font("Arial", Font.PLAIN, 18));
            panel.add(label);

            JTextField field = new JTextField();
            field.setBounds(160, i * 40, 100, 30);
            extraInfoFields[i] = field;
            panel.add(field);

            field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    updateExtraInfoPreview(extraInfoLabels);
                }
            });
        }

        return panel;
    }

    private void updateExtraInfoPreview(String[] labels) {
        StringBuilder info = new StringBuilder("<html>");
        for (int i = 0; i < labels.length; i++) {
            String value = extraInfoFields[i].getText();
            if (!value.isEmpty()) {
                info.append(labels[i]).append(": ").append(value).append("<br>");
            }
        }
        info.append("</html>");
        previewExtraInfo.setText(info.toString());
    }
    //for the push
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Create Place");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 780);
            frame.setLocationRelativeTo(null);
            frame.add(new CreatePlace());
            frame.setVisible(true);
        });
    }
}
