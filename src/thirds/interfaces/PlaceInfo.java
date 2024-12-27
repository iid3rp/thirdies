package thirds.interfaces;

import thirds.io.Resources;
import thirds.place.Place;
import thirds.swing.Label;
import thirds.swing.MoveableComponent;
import thirds.swing.ScrollPane;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * <h1>PlaceInfo</h1>
 * This class provides the user interface for displaying information about a place.
 * It includes a scrollable panel that shows images, labels, and sample descriptions
 * of a location. The class also handles the visual effects of the page.
 */
public class PlaceInfo {
    public static ScrollPane panel; // The scrollable panel displaying the place information

    static {
        panel = createPlaceInformation();
    }

    /**
     * Initializes and sets up the place information panel.
     *
     * @return ScrollPane - The scrollable panel containing the place information.
     */
    private static ScrollPane createPlaceInformation() {
        BufferedImage image, fadeDown, fadeUp;

        // Load images required for the place display
        try (InputStream stream = Resources.getResourceAsStream("demoPlace.jpg");
             InputStream stream2 = Resources.getResourceAsStream("fadeColor.png");
             InputStream stream3 = Resources.getResourceAsStream("fadeUp.png")) {
            image = ImageIO.read(stream);
            fadeDown = ImageIO.read(stream2);
            fadeUp = ImageIO.read(stream3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create a scrollable pane for displaying the information
        ScrollPane sc = scrollPlaceInfo(fadeDown);

        // Create the panel containing the place's images and information
        MoveableComponent panel = getPlaceInfoPanel(fadeDown, image, fadeUp);

        // Create labels for the place's title and description
        JLabel label = createSampleLabel();
        JLabel loremLabel = createLoremLabel();

        // Add the label and description to the panel and scroll pane
        panel.add(label);
        sc.addComponentToContainer(panel);
        sc.addComponentToContainer(loremLabel);

        // Create and add the fade effect to the scroll pane
        MoveableComponent mv = getFadeEffect(fadeDown);
        sc.glassPane.add(mv);

        return sc;
    }

    /**
     * Creates a fade effect for the place information page.
     *
     * @param fadeDown The image to use for the fade effect.
     * @return MoveableComponent - The component containing the fade effect.
     */
    private static MoveableComponent getFadeEffect(BufferedImage fadeDown) {
        MoveableComponent mv = new MoveableComponent() {
            @Override
            public void paintComponent(Graphics g) {
                // Apply a fade effect to the graphics
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

    /**
     * Creates the scrollable pane for displaying the place information.
     *
     * @param fadeDown The image used for the background fade effect.
     * @return ScrollPane - The scrollable pane with the necessary configuration.
     */
    private static ScrollPane scrollPlaceInfo(BufferedImage fadeDown) {
        ScrollPane sc = new ScrollPane();
        sc.setSize(350, 720);
        sc.setContainerSize(350, 2000);
        sc.setLocation(1280, 0);
        sc.setContainerBackground(new Color(177, 106, 0));
        return sc;
    }

    /**
     * Creates the main panel containing the place information with images.
     *
     * @param fadeDown The image for the background fade effect.
     * @param image The main image of the place.
     * @param fadeUp The image for the top fade effect.
     * @return MoveableComponent - The panel with images and fade effects.
     */
    private static MoveableComponent getPlaceInfoPanel(BufferedImage fadeDown, BufferedImage image, BufferedImage fadeUp) {
        MoveableComponent panel = new MoveableComponent() {
            @Override
            public void paintComponent(Graphics g) {
                // Apply fade effects to the panel
                Graphics2D g2d = fadeDown.createGraphics();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1f));
                g2d.setColor(new Color(177, 106, 0));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();

                // Draw the images on the panel
                g.drawImage(image, 0, 0, 350, 350, null);
                g.drawImage(fadeDown, 0, 0, 350, 350, null);
                g.drawImage(fadeUp, 0, 0, 350, 350, null);
            }
        };
        panel.setSize(350, 350);
        panel.setLocation(0, 0);
        return panel;
    }

    /**
     * Creates a sample label displaying the title of the place.
     *
     * @return JLabel - The label displaying the place title.
     */
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

        label.setBounds(20, 350 - height - 20, width, height);
        return label;
    }

    /**
     * Creates a label containing a lorem ipsum description for the place.
     *
     * @return JLabel - The label displaying the lorem ipsum text.
     */
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

    /**
     * Refreshes the place information displayed in the UI.
     *
     * @param place The place object containing the updated information to be displayed.
     */
    private static void refreshPlaceInformation(Place place) {
        // Placeholder for refreshing the place information.
        if (place == null)
            return;

        int i = 0;
        // Implement functionality to update the information displayed based on the `place` object.
    }
}
