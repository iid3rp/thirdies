package thirds.scratch;

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
        setSize(1000, 720);
        setBackground(Color.WHITE);

        //basic texts only
        add(createTitleLabel());
        add(createPreviewLabel());
        add(createDescriptionField());
        add(createExtraInformationLabel());


        add(createPlaceNameField()); // Add PlaceName field panel
        add(createPlaceTypeField());
        add(createAddressField());
        add(createPlacePinnedLocationButton());
        add(createDoneButton());

        //Bottom sections
        add(createImageSection());
        add(createExtraInfoSection());

        //fields TextArea
        add(createTagsField());
        add(createDescriptionField());

        //star rating
        add(createStarRatingSection());

    }

    // Title Section
    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Create Place");
        titleLabel.setFont(new Font("Kantumruy Pro", Font.BOLD, 24));
        titleLabel.setBounds(35, 35, 300, 30);
        return titleLabel;
    }

    private JLabel createPreviewLabel() {
        JLabel titleLabel = new JLabel("'Preview'");
        titleLabel.setFont(new Font("Kantumruy Pro", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK); // Set the text color to white
        titleLabel.setBounds(785, 635, 300, 30);
        return titleLabel;
    }

    private JLabel createExtraInformationLabel() {
        JLabel titleLabel = new JLabel("Extra Information:");
        titleLabel.setFont(new Font("Montserrat", Font.PLAIN, 18));
        titleLabel.setForeground(Color.BLACK); // Set the text color to white
        titleLabel.setBounds(680, 410, 300, 30);
        return titleLabel;
    }

    private JPanel createDescriptionField() {
        JPanel panel = new JPanel(null);
        panel.setBounds(680, 250, 321, 160);
        panel.setBackground(new Color(125, 94, 51));
        panel.setOpaque(false); // Make the panel transparent

        // Label for the tags section
        JLabel DescriptionLabel = new JLabel("Description");
        DescriptionLabel.setFont(new Font("Montserrat", Font.PLAIN, 19));
        DescriptionLabel.setForeground(new Color(125, 94, 51));
        DescriptionLabel.setBounds(0, 0, 100, 25);
        panel.add(DescriptionLabel);


        //Text area for entering tags
        JTextArea DescriptionArea = new JTextArea();
        DescriptionArea.setBounds(0, 25, 321, 160);
        DescriptionArea.setLineWrap(true); // Enable automatic word wrapping
        DescriptionArea.setWrapStyleWord(true); // Wrap at word boundaries
        DescriptionArea.setOpaque(true);
        DescriptionArea.setForeground(Color.WHITE);
        DescriptionArea.setFont(new Font("Kantumruy Pro", Font.PLAIN, 14));
        DescriptionArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        DescriptionArea.setBackground(new Color(125, 94, 51));

        // Key listener for handling space as a delimiter

        panel.add(DescriptionArea);
        return panel;
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
        previewPlaceName.setBounds(690, 108, 260, 100);
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

    private JPanel createPlaceTypeField() {
        JPanel panel = new JPanel(null);
        panel.setBounds(35, 170, 230, 70);
        panel.setOpaque(false); // Make the panel transparent

        JLabel placeTypeLabel = new JLabel("Place Type");
        placeTypeLabel.setFont(new Font("Kantumruy Pro", Font.PLAIN, 19));
        placeTypeLabel.setBounds(0, 0, 120, 25);
        panel.add(placeTypeLabel);

        JTextField placeTypeField = new JTextField();
        placeTypeField.setBounds(0, 25, 220, 40);
        placeTypeField.setOpaque(true);
        placeTypeField.setForeground(Color.DARK_GRAY);
        placeTypeField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        placeTypeField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        placeTypeField.setBackground(Color.lightGray);
        panel.add(placeTypeField);
        return panel;
    }

    private JPanel createAddressField() {
        JPanel panel = new JPanel(null);
        panel.setBounds(35, 245, 230, 80);
        panel.setOpaque(false); // Make the panel transparent

        JLabel AddressLabel = new JLabel("Place Address");
        AddressLabel.setFont(new Font("Kantumruy Pro", Font.PLAIN, 19));
        AddressLabel.setBounds(0, 10, 130, 20);
        panel.add(AddressLabel);

        JTextField AdressField = new JTextField();
        AdressField.setBounds(0, 35, 220, 40);
        AdressField.setOpaque(true);
        AdressField.setForeground(Color.DARK_GRAY);
        AdressField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        AdressField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        AdressField.setBackground(Color.lightGray);
        panel.add(AdressField);
        return panel;
    }

    private JButton createPlacePinnedLocationButton() {
        JButton button = new JButton("Place Pinned Location");
        button.setBounds(35, 335, 210, 40);
        button.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
        button.setForeground(Color.BLACK);
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        // Add MouseListener for hover effect or other interactions
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(200, 200, 200)); // Slightly darker gray for hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.LIGHT_GRAY); // Reset to original color
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Action to be performed when the button is clicked
                //System.out.println("Place Pinned Location button clicked");
            }
        });

        return button;
    }



    // Image Upload Section
    private JPanel createImageSection() {
        JPanel panel = new JPanel(null);
        panel.setBounds(35, 390, 250, 300);
        panel.setOpaque(false); // Make the panel transparent

        // Label for the Image Section
        JLabel imageLabel = new JLabel("Image");
        imageLabel.setBounds(0, 0, 60, 25);
        imageLabel.setFont(new Font("Kantumruy Pro", Font.PLAIN, 19));
        panel.add(imageLabel);

        // Image preview label
        imagePreview = new JLabel();
        imagePreview.setBounds(0, 30, 175, 175);
        imagePreview.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panel.add(imagePreview);

        // Upload Image Button
        JButton uploadImageButton = new JButton("Upload Image");
        uploadImageButton.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
        uploadImageButton.setForeground(Color.WHITE);
        uploadImageButton.setBounds(0, 220, 120, 40);
        uploadImageButton.setBackground(new Color(125, 94, 51));
        uploadImageButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        uploadImageButton.addActionListener(e -> handleImageUpload());
        panel.add(uploadImageButton);

        // Delete Image Button
        JButton deleteImageButton = new JButton("X");
        deleteImageButton.setFont(new Font("Kantumruy Pro", Font.BOLD, 16));
        deleteImageButton.setForeground(Color.WHITE);
        deleteImageButton.setBounds(130, 220, 35, 40); // Positioned next to the upload button
        deleteImageButton.setBackground(new Color(125, 94, 51));
        deleteImageButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        deleteImageButton.addActionListener(e -> handleImageDelete()); // Action listener for delete
        panel.add(deleteImageButton);

        // Initialize the previewImage label with gray background
        previewImage = new JLabel();
        previewImage.setBounds(680, 1, 321, 250); // Set the initial size
        previewImage.setBackground(Color.LIGHT_GRAY); // Set default gray background
        previewImage.setOpaque(true); // Ensure the background color is visible
        previewImage.setBorder(new LineBorder(Color.LIGHT_GRAY));
        add(previewImage);

        return panel;
    }

    // Image upload handler method
    private void handleImageUpload() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(selectedFile);

                // Scale image to fit within the 300x250 size while maintaining the aspect ratio
                Image scaledImg = img.getScaledInstance(321, 250, Image.SCALE_SMOOTH);

                // Set the icon for both previewImage and imagePreview labels
                previewImage.setIcon(new ImageIcon(scaledImg));
                imagePreview.setIcon(new ImageIcon(scaledImg));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Image delete handler method
    private void handleImageDelete() {
        // Reset the image preview in both imagePreview and previewImage
        imagePreview.setIcon(null); // Remove the image from the imagePreview
        previewImage.setIcon(null); // Remove the image from the previewImage

        System.out.println("Image deleted"); // You can perform additional actions here if needed
    }


    private JPanel createTagsField() {
        JPanel panel = new JPanel(null);
        panel.setBounds(300, 385, 310, 285);
        panel.setOpaque(false); // Make the panel transparent

        // Label for the tags section
        JLabel tagsLabel = new JLabel("Tags");
        tagsLabel.setFont(new Font("Montserrat", Font.PLAIN, 19));
        tagsLabel.setBounds(0, 0, 100, 25);
        panel.add(tagsLabel);

        // Text area for entering tags
        JTextArea tagsArea = new JTextArea();
        tagsArea.setBounds(0, 25, 280, 225);
        tagsArea.setLineWrap(true); // Enable automatic word wrapping
        tagsArea.setWrapStyleWord(true); // Wrap at word boundaries
        tagsArea.setOpaque(true);
        tagsArea.setForeground(Color.DARK_GRAY);
        tagsArea.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        tagsArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        tagsArea.setBackground(Color.LIGHT_GRAY);

        // Key listener for handling space as a delimiter

        panel.add(tagsArea);
        return panel;
    }

    private JButton createDoneButton() {
        JButton doneButton = new JButton("Done");
        doneButton.setFont(new Font("Kantumruy Pro", Font.BOLD, 18));
        doneButton.setForeground(Color.GREEN);
        doneButton.setBounds(555, 0, 50, 40); // Use the original bounds
        doneButton.setBackground(new Color(255, 255, 255)); // Light brown color
        doneButton.setBorder(new LineBorder(Color.WHITE));

        // Add Mouse Listener to handle the Done button's click event
        doneButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] extraInfoLabels = {
                        "Seats per table", "Air Conditioning", "Has Restroom", "Wi-Fi Available", "Price Range",
                        "Lighting Level", "Ambience Level", "Noise Level"
                };

                // Call the updateExtraInfoPreview method when the button is pressed
                updateExtraInfoPreview(extraInfoLabels);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                doneButton.setBackground(new Color(200, 200, 200)); // Light gray hover effect
            }

            @Override
            public void mouseExited(MouseEvent e) {
                doneButton.setBackground(new Color(255, 255, 255)); // Restore white background
            }
        });

        return doneButton;
    }

    // Extra Information Section
    private JPanel createExtraInfoSection() {
        JPanel panel = new JPanel(null);
        panel.setBounds(350, 45, 300, 325);
        panel.setOpaque(false);

        String[] extraInfoLabels = {
                "Seats per table", "Air Conditioning", "Has Restroom", "Wi-Fi Available", "Price Range",
                "Lighting Level", "Ambience Level", "Noise Level"
        };

        extraInfoFields = new JTextField[extraInfoLabels.length];
        previewExtraInfo = new JLabel("<html></html>");
        previewExtraInfo.setBounds(680, 410, 250, 200);
        previewExtraInfo.setFont(new Font("Kantumruy Pro", Font.PLAIN, 12));
        add(previewExtraInfo);

        for (int i = 0; i < extraInfoLabels.length; i++) {
            JLabel label = new JLabel(extraInfoLabels[i]);
            label.setBounds(0, i * 40, 150, 20);
            label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
            panel.add(label);

            JTextField field = new JTextField();
            field.setBackground(Color.LIGHT_GRAY);
            field.setForeground(Color.DARK_GRAY);
            field.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
            field.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            field.setBounds(160, i * 40, 150, 30);
            extraInfoFields[i] = field;
            panel.add(field);
        }

        return panel;
    }

    private void updateExtraInfoPreview(String[] labels) {
        StringBuilder info = new StringBuilder("<html>");
        for (int i = 0; i < labels.length; i++) {
            String value = extraInfoFields[i].getText();
            if (!value.isEmpty()) {
                info.append(labels[i]).append(":&nbsp;&nbsp;&nbsp;&nbsp;").append(value).append("<br>");
            }
        }
        info.append("</html>");
        previewExtraInfo.setText(info.toString());
    }

    private JPanel createStarRatingSection() {
        JPanel panel = new JPanel(null);
        panel.setBounds(680, 600, 300, 30); // Adjust width if needed
        panel.setOpaque(true);
        panel.setBackground(Color.WHITE);  // Set the background color for visibility

        JLabel label = new JLabel("Rating:");
        label.setBounds(0, 0, 60, 30);
        label.setFont(new Font("Kantumruy Pro", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        panel.add(label);

        int starX = 60; // Starting x position for the stars, just after the "Rating:" label

        // JLabel to display rating as n/5
        JLabel ratingDisplay = new JLabel("0/5");
        ratingDisplay.setBounds(210, 0, 60, 30);
        ratingDisplay.setFont(new Font("Kantumruy Pro", Font.PLAIN, 14));
        ratingDisplay.setForeground(Color.BLACK);
        panel.add(ratingDisplay);

        // Array to hold the star labels
        JLabel[] stars = new JLabel[5];

        // Add 5 stars and handle clicks
        for (int i = 0; i < 5; i++) {
            final int rating = i + 1; // Rating value (1 to 5)
            JLabel star = new JLabel("★");
            star.setBounds(starX, 0, 30, 30);
            star.setFont(new Font("Kantumruy Pro", Font.PLAIN, 25));
            star.setForeground(Color.GRAY); // Default color for unselected stars
            stars[i] = star; // Store each star label in the array

            // Add mouse listener to handle star clicks
            star.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Update the rating and change star colors
                    for (int j = 0; j < 5; j++) {
                        if (j < rating) {
                            stars[j].setForeground(Color.ORANGE); // Highlight selected stars
                        } else {
                            stars[j].setForeground(Color.GRAY); // Unhighlight unselected stars
                        }
                    }

                    // Update the rating display label (n/5 format)
                    ratingDisplay.setText(rating + "/5");
                }
            });

            panel.add(star);
            starX += 30; // Increment x position for the next star
        }

        return panel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawVerticalLine(g, 665);
    }

    private void drawVerticalLine(Graphics g, int x) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(125, 94, 51));
        g2d.drawLine(x, 0, x, 720);
    }



    //for the push
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Create Place");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 720);
            frame.setLocationRelativeTo(null);
            frame.add(new CreatePlace());
            frame.setUndecorated(true);
            frame.setVisible(true);
        });
    }
}
