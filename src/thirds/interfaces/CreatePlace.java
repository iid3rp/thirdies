package thirds.interfaces;

import thirds.io.Resources;
import thirds.swing.MoveableComponent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * CreatePlace Class
 *
 * This class provides a graphical user interface for creating a "Place" with various attributes, such as name, type, address, and additional information.
 * It includes features for previewing user input details and uploading images.
 *
 * Key Features:
 * - Input fields for Place Name, Type, Address, and Tags.
 * - Image upload and preview functionality.
 * - Real-time updates for previewing entered data.
 * - Flexible fields for "Extra Information," allowing custom entries.
 */
public class CreatePlace {
    private static JLabel previewPlaceName;
    private static JLabel previewImage;
    private static JLabel previewExtraInfo;
    private static JLabel imagePreview;
    private static JTextField[] extraInfoFields;
    private static BufferedImage image;

    private static final MoveableComponent panel = new MoveableComponent() {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawVerticalLine(g, 665);
        }

        private void drawVerticalLine(Graphics g, int x) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(new Color(125, 94, 51));
            g2d.drawLine(x, 0, x, 720);
        }
    };

    static
    {
        initializeComponent();
    }

    private CreatePlace()
    {
        throw new IllegalStateException("Utility class. Not to be instantiated.");
    }

    /**
     * Initializes the main panel and adds all components.
     */

    private static void initializeComponent() {
        panel.setLayout(null);
        panel.setSize(1000, 720);
        panel.setBackground(Color.WHITE);

        panel.add(returnIcon());

        //basic texts only
        panel.add(createTitleLabel());
        panel.add(createPreviewLabel());
        panel.add(createDescriptionField());
        panel.add(createExtraInformationLabel());

        panel.add(createPlaceNameField()); // Add PlaceName field panel
        panel.add(createPlaceTypeField());
        panel.add(createAddressField());
        panel.add(createPlacePinnedLocationButton());
        panel.add(createDoneButton());

        //Bottom sections
        panel.add(createImageSection());
        panel.add(createExtraInfoSection());

        //fields TextArea
        panel.add(createTagsField());
        panel.add(createDescriptionField());

        //star rating
        panel.add(createStarRatingSection());

    }
    /**
     * Creates and returns the "Return" icon component.
     * Handles click events to exit the application.
     */
    private static JLabel returnIcon() 
    {
        try {
            BufferedImage settingsIcon = ImageIO.read(Resources.getResourceAsStream("returnIcon.png"));

            ImageIcon icon = new ImageIcon(settingsIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

            JLabel iconLabel = new JLabel(icon);
            iconLabel.setSize(20, 20);
            iconLabel.setLocation(35, 10);  // Set location

            // handle click events and exit the application
            iconLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    exitApplication();  // exit the application when clicked
                }
            });
            return iconLabel;

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    // exit the application
    private static void exitApplication() {
        System.exit(0);
    }

    // Title Section
    /**
     * Creates the main title label for the panel.
     */
    private static JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Create Place");
        titleLabel.setFont(new Font("Ebrima", Font.BOLD, 24));
        titleLabel.setBounds(35, 35, 300, 30);
        return titleLabel;
    }
    //preview text
    private static JLabel createPreviewLabel() {
        JLabel titleLabel = new JLabel("'Preview'");
        titleLabel.setFont(new Font("Ebrima", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK); // Set the text color to white
        titleLabel.setBounds(785, 665, 300, 30);
        return titleLabel;
    }
    //extra information text
    private static JLabel createExtraInformationLabel() {
        JLabel titleLabel = new JLabel("Extra Information:");
        titleLabel.setFont(new Font("Montserrat", Font.PLAIN, 18));
        titleLabel.setForeground(Color.BLACK); // Set the text color to white
        titleLabel.setBounds(680, 410, 300, 30);
        return titleLabel;
    }

    private static JPanel createDescriptionField() {
        JPanel panel = new JPanel(null);
        panel.setBounds(680, 250, 321, 160);
        panel.setBackground(new Color(125, 94, 51));
        panel.setOpaque(true); // Make the panel transparent

        // Label for the tags section
        JLabel DescriptionLabel = new JLabel("Description");
        DescriptionLabel.setFont(new Font("Montserrat", Font.PLAIN, 19));
        DescriptionLabel.setForeground(new Color(255, 255, 255));
        DescriptionLabel.setBounds(0, 0, 100, 25);
        panel.add(DescriptionLabel);

        //Text area for entering tags
        JTextArea DescriptionArea = createDescriptionArea();

        // Key listener for handling space as a delimiter

        panel.add(DescriptionArea);
        return panel;
    }
    //Text areas for the description of the place
    private static JTextArea createDescriptionArea()
    {
        JTextArea DescriptionArea = new JTextArea();
        DescriptionArea.setBounds(0, 25, 321, 160);
        DescriptionArea.setLineWrap(true); // Enable automatic word wrapping
        DescriptionArea.setWrapStyleWord(true); // Wrap at word boundaries
        DescriptionArea.setOpaque(true);
        DescriptionArea.setForeground(Color.WHITE);
        DescriptionArea.setFont(new Font("Ebrima", Font.PLAIN, 14));
        DescriptionArea.setBorder(BorderFactory.createLineBorder(new Color(125, 94, 51))); // White color border
        DescriptionArea.setBackground(new Color(125, 94, 51));
        return DescriptionArea;
    }

    // Place Name Field

     //Creates the "Place Name" input field with a real-time preview.

    private static JPanel createPlaceNameField() {
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 260, 160);
        panel.setOpaque(false); // Make the panel transparent

        JLabel placeNameLabel = new JLabel("Place Name");
        placeNameLabel.setFont(new Font("Ebrima", Font.PLAIN, 19));
        placeNameLabel.setBounds(35, 90, 150, 20);
        panel.add(placeNameLabel);

        JTextField placeNameField = new JTextField();
        placeNameField.setBounds(35, 115, 220, 40);
        placeNameField.setOpaque(true);
        placeNameField.setForeground(Color.DARK_GRAY);
        placeNameField.setFont(new Font("Ebrima", Font.PLAIN, 18));
        placeNameField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        placeNameField.setBackground(Color.lightGray);
        panel.add(placeNameField);

        // Initialize the previewPlaceName global label with left alignment and white font color
        previewPlaceName = new JLabel("<html><font color='white'>Place Name</font></html>", SwingConstants.LEFT);
        previewPlaceName.setFont(new Font("Ebrima", Font.BOLD, 30));
        previewPlaceName.setHorizontalAlignment(SwingConstants.LEFT); // left alignment
        previewPlaceName.setBounds(690, 108, 260, 100);
        CreatePlace.panel.add(previewPlaceName); // Add to the main panel

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
     //Creates the place type field
    private static JPanel createPlaceTypeField() {
        JPanel panel = new JPanel(null);
        panel.setBounds(35, 170, 230, 70);
        panel.setOpaque(false); // Make the panel transparent

        JLabel placeTypeLabel = new JLabel("Place Type");
        placeTypeLabel.setFont(new Font("Ebrima", Font.PLAIN, 19));
        placeTypeLabel.setBounds(0, 0, 120, 25);
        panel.add(placeTypeLabel);

        JTextField placeTypeField = new JTextField();
        placeTypeField.setBounds(0, 25, 220, 40);
        placeTypeField.setOpaque(true);
        placeTypeField.setForeground(Color.DARK_GRAY);
        placeTypeField.setFont(new Font("Ebrima", Font.PLAIN, 18));
        placeTypeField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        placeTypeField.setBackground(Color.lightGray);
        panel.add(placeTypeField);
        return panel;
    }
    //Creates the address field
    private static JPanel createAddressField() {
        JPanel panel = new JPanel(null);
        panel.setBounds(35, 245, 230, 80);
        panel.setOpaque(false); // Make the panel transparent

        JLabel AddressLabel = new JLabel("Place Address");
        AddressLabel.setFont(new Font("Ebrima", Font.PLAIN, 19));
        AddressLabel.setBounds(0, 10, 130, 20);
        panel.add(AddressLabel);

        JTextField AdressField = new JTextField();
        AdressField.setBounds(0, 35, 220, 40);
        AdressField.setOpaque(true);
        AdressField.setForeground(Color.DARK_GRAY);
        AdressField.setFont(new Font("Ebrima", Font.PLAIN, 18));
        AdressField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        AdressField.setBackground(Color.lightGray);
        panel.add(AdressField);
        return panel;
    }

    private static JButton createPlacePinnedLocationButton() {
        JButton button = new JButton("Place Pinned Location");
        button.setBounds(35, 335, 220, 40);
        button.setFont(new Font("Ebrima", Font.PLAIN, 16));
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
    private static JPanel createImageSection() {
        JPanel panel = new JPanel(null);
        panel.setBounds(35, 390, 250, 300);
        panel.setOpaque(false); // Make the panel transparent

        // Label for the Image Section
        JLabel imageLabel = new JLabel("Image");
        imageLabel.setBounds(0, 0, 60, 25);
        imageLabel.setFont(new Font("Ebrima", Font.PLAIN, 19));
        panel.add(imageLabel);

        // Image preview label
        imagePreview = new JLabel();
        imagePreview.setBounds(0, 30, 175, 175);
        imagePreview.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panel.add(imagePreview);

        // Upload Image Button
        JButton uploadImageButton = new JButton("Upload Image");
        uploadImageButton.setFont(new Font("Ebrima", Font.PLAIN, 16));
        uploadImageButton.setForeground(Color.WHITE);
        uploadImageButton.setBounds(0, 220, 120, 40);
        uploadImageButton.setBackground(new Color(125, 94, 51));
        uploadImageButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        uploadImageButton.addActionListener(e -> handleImageUpload());
        panel.add(uploadImageButton);

        // Delete Image Button
        JButton deleteImageButton = createDeleteImageButton();
        panel.add(deleteImageButton);

        // Initialize the previewImage label with gray background
        previewImage = new JLabel();
        previewImage.setBounds(680, 1, 321, 250); // Set the initial size
        previewImage.setBackground(Color.LIGHT_GRAY); // Set default gray background
        previewImage.setOpaque(true); // Ensure the background color is visible
        previewImage.setBorder(new LineBorder(Color.LIGHT_GRAY));
        CreatePlace.panel.add(previewImage);

        return panel;
    }

    private static JButton createDeleteImageButton()
    {
        JButton deleteImageButton = new JButton("X");
        deleteImageButton.setFont(new Font("Ebrima", Font.BOLD, 16));
        deleteImageButton.setForeground(Color.WHITE);
        deleteImageButton.setBounds(130, 220, 35, 40); // Positioned next to the upload button
        deleteImageButton.setBackground(new Color(125, 94, 51));
        deleteImageButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        deleteImageButton.addActionListener(e -> handleImageDelete()); // Action listener for delete
        return deleteImageButton;
    }

    // Image upload handler method
    private static void handleImageUpload() {
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
                throw new RuntimeException();
            }
        }
    }

    // Image delete handler method
    private static void handleImageDelete() {
        // Reset the image preview in both imagePreview and previewImage
        imagePreview.setIcon(null); // Remove the image from the imagePreview
        previewImage.setIcon(null); // Remove the image from the previewImage

        System.out.println("Image deleted"); // You can perform additional actions here if needed
    }

    private static JPanel createTagsField() {
        JPanel panel = new JPanel(null);
        panel.setBounds(300, 385, 310, 285);
        panel.setOpaque(false); // Make the panel transparent

        // Label for the tags section
        JLabel tagsLabel = new JLabel("Tags");
        tagsLabel.setFont(new Font("Montserrat", Font.PLAIN, 19));
        tagsLabel.setBounds(0, 0, 100, 25);
        panel.add(tagsLabel);

        // Text area for entering tags
        JTextArea tagsArea = createTagsTextArea();

        // Key listener for handling space as a delimiter

        panel.add(tagsArea);
        return panel;
    }

    private static JTextArea createTagsTextArea()
    {
        JTextArea tagsArea = new JTextArea();
        tagsArea.setBounds(0, 25, 280, 225);
        tagsArea.setLineWrap(true); // Enable automatic word wrapping
        tagsArea.setWrapStyleWord(true); // Wrap at word boundaries
        tagsArea.setOpaque(true);
        tagsArea.setForeground(Color.DARK_GRAY);
        tagsArea.setFont(new Font("Ebrima", Font.PLAIN, 18));
        tagsArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        tagsArea.setBackground(Color.LIGHT_GRAY);
        return tagsArea;
    }

    private static JButton createDoneButton() {
        JButton doneButton = new JButton("Done");
        doneButton.setFont(new Font("Ebrima", Font.BOLD, 18));
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
                        "Lighting Level /10", "Ambience Level /10", "Noise Level /10"
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
    private static JPanel createExtraInfoSection() {
        JPanel panel = new JPanel(null);
        panel.setBounds(350, 45, 300, 325);
        panel.setOpaque(false);

        String[] extraInfoLabels = {
                "Seats per table", "Air Conditioning", "Has Restroom", "Wi-Fi Available", "Price Range",
                "Lighting Level  /10", "Ambience Level  /10", "Noise Level  /10"
        };

        extraInfoFields = new JTextField[extraInfoLabels.length];
        previewExtraInfo = new JLabel("<html></html>");
        previewExtraInfo.setBounds(680, 410, 250, 200);
        previewExtraInfo.setFont(new Font("Ebrima", Font.PLAIN, 12));
        CreatePlace.panel.add(previewExtraInfo);

        for (int i = 0; i < extraInfoLabels.length; i++) {
            JLabel label = new JLabel(extraInfoLabels[i]);
            label.setBounds(0, i * 40, 150, 20);
            label.setFont(new Font("Ebrima", Font.PLAIN, 16));
            panel.add(label);

            JTextField field = new JTextField();
            field.setBackground(Color.LIGHT_GRAY);
            field.setForeground(Color.DARK_GRAY);
            field.setFont(new Font("Ebrima", Font.PLAIN, 16));
            field.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            field.setBounds(160, i * 40, 150, 30);
            extraInfoFields[i] = field;
            panel.add(field);
        }

        return panel;
    }

    private static void updateExtraInfoPreview(String[] labels) {
        StringBuilder info = new StringBuilder("<html>");
        for (int i = 0; i < labels.length; i++) {
            String value = extraInfoFields[i].getText();
            if (!value.isEmpty()) {
                info.append(labels[i]).append(":    ").append(value).append("<br>");
            }
        }
        info.append("</html>");
        previewExtraInfo.setText(info.toString());
    }

    private static JPanel createStarRatingSection() {
        JPanel panel = new JPanel(null);
        panel.setBounds(680, 600, 300, 30); // Adjust width if needed
        panel.setOpaque(true);
        panel.setBackground(Color.WHITE);  // Set the background color for visibility

        JLabel label = new JLabel("Rating:");
        label.setBounds(0, 0, 60, 30);
        label.setFont(new Font("Ebrima", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        panel.add(label);

        int starX = 80; // Starting x position for the stars, just after the "Rating:" label

        // JLabel to display rating as n/5
        JLabel ratingDisplay = new JLabel("0/5");
        ratingDisplay.setBounds(235, 0, 60, 30);
        ratingDisplay.setFont(new Font("Ebrima", Font.PLAIN, 14));
        ratingDisplay.setForeground(Color.BLACK);
        panel.add(ratingDisplay);

        // Array to hold the star labels
        JLabel[] stars = new JLabel[5];

        // Add 5 stars and handle clicks
        for (int i = 0; i < 5; i++) {
            final int rating = i + 1; // Rating value (1 to 5)
            JLabel star = new JLabel("★");
            star.setBounds(starX, 0, 30, 30);
            star.setFont(new Font("Ebrima", Font.PLAIN, 25));
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

    public static MoveableComponent getPanel()
    {
        return panel;
    }

    //for the push
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Create Place");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 720);
            frame.setLocationRelativeTo(null);
            frame.add(getPanel());
            frame.setUndecorated(true);
            frame.setVisible(true);
        });
    }
}