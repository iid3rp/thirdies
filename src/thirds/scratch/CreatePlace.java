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
import java.io.File;
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
        JLabel pricerangeText = createPriceRangeText();

        add (pricerangeText);
        add(previewText);
        add(addImageText);
        add(titleText);

        addReturnIcon();
        // Inside your constructor or main setup method
        JLabel previewLabel = createPreviewTextArea(935, 315);//create Place Name preview
        add(previewLabel);

        JLabel tagPreviewLabel = createPreviewTagsArea(900, 750);//createTagPreview
        add(tagPreviewLabel);


        // Create fields and link to the preview
        add(createPlaceNameField(50, 100, previewLabel));

        //Create Tag File and link to the preview
        //add(createTagsField(50, 800));//original tags field//needed
        add(createTagsField(50, 800, tagPreviewLabel));


        add(createPlaceTypeField(50, 180)); // Adjusted y coordinate//needed
        add(createAddressField(50, 260));   // Adjusted y coordinate


        add(createZipCodeField(340, 180));

        add(createLocationButton(50, 340));

        add(createPriceRangeSection(220, 440));

        add(createImageUploadArea(630, 100));
        add(createImagePreviewPanel(935,75));

        add(createDescriptionField(910, 400));
        add(createTagsPreviewText());

        add(createFacilitiesSection(50, 490));
        add(createSlidersSection(50, 610));
        add(createRatingSection(50, 730));

        JLabel extraInfoLabel = createExtraInfoLabel(910, 615, "N/A");  // Default value
        add(extraInfoLabel);  // Add the label to the UI

// Add the seat capacity field and pass the label for updates
        add(createSeatCapacityField(50, 410, extraInfoLabel));
        addCloseIconListener();
    }

    private void initializeComponent() {
        setLayout(null);
        setSize(1280, 720);
        setBackground(new Color(255, 255, 255, 255));
        setVisible(true);
    }


    private void addReturnIcon () {
        try {
            BufferedImage settingsIcon = ImageIO.read(Resources.getResourceAsStream("returnicon.png"));
            JLabel iconLabel = new JLabel(new ImageIcon(settingsIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            iconLabel.setSize(40, 40);
            iconLabel.setLocation(15, 5);
            add(iconLabel);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load settings icon", e);
        }


}
    private JLabel createExtraInfoLabel(int x, int y, String seatCapacity) {

        JLabel extraInfoLabel = new JLabel("<html><strong>Extra Information:</strong>" +
                "<br><br>Seat Capacity: " + seatCapacity + " </html>");
        extraInfoLabel.setBounds(x, y, 250, 80); // Set dimensions for the label
        extraInfoLabel.setFont(new Font("Kantumruy Pro", Font.PLAIN, 14));
        extraInfoLabel.setForeground(Color.WHITE);
        extraInfoLabel.setOpaque(true);
        extraInfoLabel.setBackground(new Color(159, 130, 84));
        extraInfoLabel.setVerticalAlignment(SwingConstants.TOP); // Align text to the top
        return extraInfoLabel;
    }

    private JLabel createTagsPreviewText() {
        JLabel label = new JLabel();
        label.setText("Tags");
        label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 25));
        label.setForeground(Color.BLACK);

        FontMetrics fm = label.getFontMetrics(label.getFont());
        int width = fm.stringWidth(label.getText()) + label.getText().length();
        int height = fm.getHeight();

        label.setSize(width, height);
        label.setLocation(1010, 710);
        return label;
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
        label.setLocation(650, 30);
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
        label.setLocation(975, 30);
        return label;
    }

    private JLabel createPriceRangeText() {
        JLabel label = new JLabel();
        label.setText("Price Range");
        label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        label.setForeground(Color.BLACK);
        FontMetrics fm = label.getFontMetrics(label.getFont());
        int width = fm.stringWidth(label.getText()) + label.getText().length();
        int height = fm.getHeight();

        label.setSize(width, height);
        label.setLocation(220, 410);
        return label;
    }

    // Creates the Place Name Field and binds it to the preview text area
    private JPanel createPlaceNameField(int x, int y, JLabel previewLabel) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 271, 60); // Set dimensions
        panel.setOpaque(false);

        JLabel label = new JLabel("Place Name");
        label.setBounds(0, 0, 271, 20);
        label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        label.setForeground(Color.BLACK);

        JTextField textField = new JTextField();
        textField.setBounds(0, 25, 271, 35); // Set dimensions
        textField.setOpaque(true);
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        textField.setHorizontalAlignment(SwingConstants.LEFT);

        // Add a key listener to update the preview on Enter key press
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = textField.getText();

                    // Check if text is empty and update the label accordingly
                    if (text.isEmpty()) {
                        previewLabel.setText("N/A");
                        previewLabel.setHorizontalAlignment(SwingConstants.LEFT); // Reset alignment if empty
                    } else {
                        previewLabel.setText("<html><div style='text-align: center;'>" + text + "</div></html>");
                        previewLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align when text exists
                    }
                }
            }
        });

        panel.add(label);
        panel.add(textField);
        return panel;
    }


    // Creates the Preview Text Area
    private JLabel createPreviewTextArea(int x, int y) {
        JLabel previewLabel = new JLabel("", SwingConstants.CENTER);
        previewLabel.setBounds(x, y, 200, 50); // Set dimensions
        previewLabel.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        previewLabel.setForeground(Color.BLACK);
        previewLabel.setBackground(new Color(165, 163, 163));
        previewLabel.setOpaque(true); // To apply the background color
        previewLabel.setVerticalAlignment(SwingConstants.CENTER); // Center vertically
        return previewLabel;
    }
  private JLabel createPreviewTagsArea(int x, int y) {
        JLabel previewLabel = new JLabel("", SwingConstants.CENTER);
       previewLabel.setBounds(x, y, 271, 100); // Set dimensions
       previewLabel.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
       previewLabel.setForeground(Color.BLACK);
        previewLabel.setBackground(new Color(165, 163, 163));
        previewLabel.setOpaque(true); // To apply the background color
        previewLabel.setVerticalAlignment(SwingConstants.CENTER); // Center vertically
       return previewLabel;
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
        textField.setBounds(0, 25, 271, 35);
        textField.setOpaque(true);
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

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
        textField.setBounds(0, 25, 271, 35);
        textField.setOpaque(true);
        textField.setBackground(Color.lightGray);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

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
        textField.setBounds(0, 25, 55, 35);
        textField.setOpaque(true);
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

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

    /// ////////SEAT CAPACITY
    private JPanel createSeatCapacityField(int x, int y, JLabel extraInfoLabel) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 250, 90);
        panel.setOpaque(false);

        JLabel label = new JLabel("Seat Capacity");
        label.setBounds(0, 3, 270, 20);
        label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        label.setForeground(Color.BLACK);

        JTextField textField = new JTextField();
        textField.setBounds(0, 30, 90, 30);
        textField.setOpaque(true);
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
        textField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Action listener to update the extraInfoLabel with the seat capacity
        textField.addActionListener(e -> {
            String seatCapacity = textField.getText();
            extraInfoLabel.setText("<html><strong>Extra Information:</strong>" +
                    "<br><br>Seat Capacity: " + seatCapacity + "</html>");
        });

        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private JPanel createPriceRangeSection(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 350, 80); // Adjust the height as needed
        panel.setOpaque(false);

        // Min Price Label and Text Field
        JLabel minLabel = new JLabel("Min");
        minLabel.setBounds(0, 0, 100, 30); // Adjusted bounds for the label
        minLabel.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
        minLabel.setForeground(Color.BLACK);

        JTextField minPriceField = new JTextField();
        minPriceField.setBounds(30, 0, 75, 30); // Adjusted bounds for the text field
        minPriceField.setOpaque(true);
        minPriceField.setBackground(Color.LIGHT_GRAY);
        minPriceField.setForeground(Color.BLACK);
        minPriceField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
        minPriceField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        minPriceField.setHorizontalAlignment(SwingConstants.LEFT);
        minPriceField.setMargin(new Insets(5, 5, 0, 0)); // Set margin for top-left alignment

        // Max Price Label and Text Field
        JLabel maxLabel = new JLabel("—Max");
        maxLabel.setBounds(110, 0, 100, 30); // Adjusted bounds for the label
        maxLabel.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
        maxLabel.setForeground(Color.BLACK);

        JTextField maxPriceField = new JTextField();
        maxPriceField.setBounds(160, 0, 75, 30); // Adjusted bounds for the text field
        maxPriceField.setOpaque(true);
        maxPriceField.setBackground(Color.LIGHT_GRAY);
        maxPriceField.setForeground(Color.BLACK);
        maxPriceField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
        maxPriceField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        maxPriceField.setHorizontalAlignment(SwingConstants.LEFT);
        maxPriceField.setMargin(new Insets(5, 5, 0, 0)); // Set margin for top-left alignment

        // Add components to the panel
        panel.add(minLabel);
        panel.add(minPriceField);
        panel.add(maxLabel);
        panel.add(maxPriceField);

        return panel;
    }
    ///

    private JPanel createImageUploadArea(int x, int y) {
        int panelWidth = 200; // Width of the image upload area
        int panelHeight = 780; // Total height of the image upload area
        int numberOfPanels = 3; // Number of image panels
        int imagePanelHeight = (panelHeight - (40 * (numberOfPanels - 1))) / numberOfPanels; // Calculate height for each panel

        JPanel panel = new JPanel(new GridLayout(numberOfPanels, 1, 10, 40)); // 3 rows, 1 column
        panel.setBounds(x, y, panelWidth, panelHeight);
        panel.setOpaque(false);

        for (int i = 0; i < numberOfPanels; i++) {
            JPanel imagePanel = new JPanel();
            imagePanel.setBackground(new Color(165, 163, 163));
            imagePanel.setPreferredSize(new Dimension(panelWidth, imagePanelHeight)); // Set preferred size for even height
            imagePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Open file chooser to select an image
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Select an Image");
                    int returnValue = fileChooser.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        // Load and display the image
                        displayImage(selectedFile, imagePanel);
                    }
                }
            });
            panel.add(imagePanel);
        }

        return panel;
    }

    private void displayImage(File imageFile, JPanel imagePanel) {
        try {
            // Load the image from the selected file
            BufferedImage originalImage = ImageIO.read(imageFile);

            // Get the dimensions of the panel
            int panelWidth = imagePanel.getWidth();
            int panelHeight = imagePanel.getHeight();

            // Scale the image to fit the panel while maintaining aspect ratio
            Image scaledImage = originalImage.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);

            // Create a JLabel to display the scaled image
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

            // Clear the previous content of the image panel
            imagePanel.removeAll();
            imagePanel.setLayout(new BorderLayout()); // Use BorderLayout to center the image

            // Add the image label to the panel
            imagePanel.add(imageLabel, BorderLayout.CENTER);

            // Revalidate and repaint the panel to show the new image
            imagePanel.revalidate();
            imagePanel.repaint();
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions (e.g., file not found)
        }
    }
    /// ///////////
    private JPanel createImagePreviewPanel(int x, int y) {
        JPanel previewPanel = new JPanel();
        previewPanel.setBounds(x, y, 200, 235); // Width = 200, Height = 780
        previewPanel.setBackground(new Color(165,163,163));
        previewPanel.setLayout(new BorderLayout());

        previewPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select an Image");
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    displayPreviewImage(selectedFile, previewPanel);
                }
            }
        });

        return previewPanel;
    }

    private void displayPreviewImage(File imageFile, JPanel previewPanel) {
        try {
            BufferedImage originalImage = ImageIO.read(imageFile);
            Image scaledImage = originalImage.getScaledInstance(previewPanel.getWidth(), previewPanel.getHeight(), Image.SCALE_SMOOTH);

            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            previewPanel.removeAll();
            previewPanel.add(imageLabel, BorderLayout.CENTER);

            previewPanel.revalidate();
            previewPanel.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            checkBox.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16)); // Increased font size
            checkBox.setForeground(Color.BLACK);
            checkBox.setOpaque(false);
            panel.add(checkBox);
            yOffset += 30; // Increased vertical gap between checkboxes
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

            // Create a label to display the slider value
            JLabel valueLabel = new JLabel("5"); // Default value
            valueLabel.setBounds(120, yOffset, 30, 20); // Position next to the slider
            valueLabel.setFont(new Font("Kantumruy Pro", Font.BOLD, 14));
            valueLabel.setForeground(Color.BLACK);

            JSlider slider = new JSlider(1, 10, 5); // Slider range from 1 to 10, default at 5
            slider.setBounds(160, yOffset, 150, 20);
            slider.setOpaque(false);
            slider.setForeground(Color.BLACK);

            // Add a change listener to update the value label when the slider is moved
            slider.addChangeListener(e -> {
                valueLabel.setText(String.valueOf(slider.getValue()));
            });

            panel.add(label);
            panel.add(valueLabel);
            panel.add(slider);
            yOffset += 35; // Increase yOffset for the next slider
        }

        return panel;
    }

    private JPanel createRatingSection(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 350, 30);
        panel.setOpaque(false);

        JLabel label = new JLabel("Rating:");
        label.setBounds(0, 0, 60, 30);
        label.setFont(new Font("Kantumruy Pro", Font.BOLD, 14));
        label.setForeground(Color.BLACK);
        panel.add(label);

        int starX = 70; // Starting x position for the stars
        int[] starRatings = new int[5]; // Array to hold the star rating values

        for (int i = 0; i < 5; i++) {
            final int rating = i + 1; // Rating value (1 to 5)
            JLabel star = new JLabel("★");
            star.setBounds(starX, 0, 30, 30);
            star.setFont(new Font("Kantumruy Pro", Font.PLAIN, 20));
            star.setForeground(Color.GRAY); // Default color for unselected stars

            // Add mouse listener to handle star clicks
            star.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Update the rating and change star colors
                    for (int j = 0; j < 5; j++) {
                        starRatings[j] = (j < rating) ? 1 : 0; // Set selected stars
                        JLabel currentStar = (JLabel) panel.getComponent(j + 1); // Get the star label
                        currentStar.setForeground((j < rating) ? Color.ORANGE : Color.GRAY); // Change color based on rating
                    }
                }
            });

            panel.add(star);
            starX += 30; // Increment x position for the next star
        }

        return panel;
    }


    private JPanel createDescriptionField(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 700, 200); // Height to accommodate both label and text area
        panel.setOpaque(false);

        JLabel label = new JLabel("Description");
        label.setBounds(80, 0, 271, 20); // Height for the label
        label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));

        label.setForeground(Color.BLACK);

        // Use JTextArea instead of JTextField for better control over text positioning
        JTextArea textArea = new JTextArea();
        textArea.setBounds(0, 25, 250, 200); // Adjusted height for the text area
        textArea.setOpaque(true);
        textArea.setBackground(new Color(159, 130, 84));
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Kantumruy Pro", Font.BOLD, 12));
        textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        textArea.setLineWrap(true); // Allow line wrapping
        textArea.setWrapStyleWord(true); // Wrap at word boundaries
        textArea.setMargin(new Insets(5, 5, 0, 25)); // Set margin to position text at the top left

        panel.add(label);
        panel.add(textArea);
        return panel;
    }



    private JPanel createTagsField(int x, int y, JLabel previewLabel) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 271, 250); // Height to accommodate both label and text area
        panel.setOpaque(false);

        JLabel label = new JLabel("Enter Tags");
        label.setBounds(0, 0, 271, 20); // Height for the label
        label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        label.setForeground(Color.BLACK);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(0, 25, 271, 110); // Adjusted height for the text area
        textArea.setOpaque(true);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        textArea.setLineWrap(true); // Allow line wrapping
        textArea.setWrapStyleWord(true); // Wrap at word boundaries
        textArea.setMargin(new Insets(5, 5, 0, 0)); // Set margin to position text at the top left

        // Add a key listener to update the preview on Enter key press
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String tags = textArea.getText();

                    // Update the preview label
                    if (previewLabel != null) {
                        if (tags.isEmpty()) {
                            previewLabel.setText("N/A");
                        } else {
                            // Format tags for display in the preview
                            previewLabel.setText("<html><div style='text-align: center;'>" + tags.replace("\n", "<br>") + "</div></html>");
                        }
                    }
                }
            }
        });

        panel.add(label);
        panel.add(textArea);
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
        drawRectangle(g, 585, 80, 285, 835);
        drawCloseIcon(g);
    }



    private void drawRectangle(Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GRAY); // Set the color for the rectangle stroke
        g2d.drawRect(x, y, width - 1, height - 1); // Draw the rectangle with stroke
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