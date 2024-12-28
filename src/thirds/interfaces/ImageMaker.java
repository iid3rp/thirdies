package thirds.interfaces;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The {@code ImageMaker} class demonstrates how to create a simple image
 * using Java's {@code BufferedImage} and {@code Graphics} classes.
 * This example creates an image with a gradient fade effect and saves it as a PNG file.
 */
public class ImageMaker {

    /**
     * The entry point of the program.
     *
     * @param args Command-line arguments passed during the execution.
     * This program does not utilize any command-line arguments.
     */
    public static void main(String... args) {
        // Create a blank BufferedImage of size 300x300 with alpha channel support
        BufferedImage image = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);

        // Obtain the Graphics object to draw on the image
        Graphics g = image.getGraphics();

        // Draw a vertical gradient effect
        for (int i = 0; i < 100; i++) {
            // Set a semi-transparent black color with decreasing opacity
            g.setColor(new Color(0, 0, 0, 100 - i));

            // Fill a horizontal line at the current position
            g.fillRect(0, i, 300, 1);
        }

        // Dispose of the Graphics object to release resources
        g.dispose();

        // Save the generated image to a file
        saveImage(image, "fadeUp.png");
    }

    /**
     * Saves a {@code BufferedImage} to the user's Pictures directory as a PNG file.
     *
     * @param image The {@code BufferedImage} to be saved.
     * @param fileName The name of the file to save the image as.
     * @throws RuntimeException if an {@code IOException} occurs during saving.
     */
    private static void saveImage(BufferedImage image, String fileName) {
        try {
            // Define the file path in the user's Pictures directory
            File file = new File(System.getProperty("user.home") + File.separator + "Pictures" + File.separator + fileName);

            // Write the image as a PNG file
            boolean isSaved = ImageIO.write(image, "PNG", file);

            // Check if the image was successfully saved
            if (isSaved) {
                System.out.println("Image saved successfully at: " + file.getAbsolutePath());
            } else {
                System.out.println("Failed to save the image.");
            }
        } catch (IOException e) {
            // Handle potential I/O exceptions
            throw new RuntimeException("Error while saving the image: " + e.getMessage(), e);
        }
    }
}
