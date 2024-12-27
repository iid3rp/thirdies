package thirds.interfaces;

import thirds.application.MapApplication;
import thirds.application.SignUpMenu;
import thirds.io.Resources;
import thirds.swing.MoveableComponent;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <h1>LoggedInScreen</h1>
 * This class creates the logged-in screen with a welcome message, instructions,
 * and a background image. The screen is interactive and transitions to the map
 * application when clicked.
 */
public class LoggedInScreen {

   private static BufferedImage background;
   private static MoveableComponent panel;

   static {
      initializeComponent(); // Initialize the components

      // Add the welcome text and instruction labels to the panel
      panel.add(createWelcomeText());
      panel.add(createInstructText("Click Anywhere to", 226));
      panel.add(createInstructText("start you huddling", 247));
      panel.add(createInstructText("journey!", 268));
   }

   private LoggedInScreen() {
      throw new IllegalStateException("Utility class should not be instantiated");
   }

   /**
    * Creates a JLabel for instructional text with given text and vertical position.
    *
    * @param text The text to display on the label.
    * @param yPosition The vertical position for the label.
    * @return A JLabel containing the instruction text.
    */
   private static JLabel createInstructText(String text, int yPosition) {
      JLabel label = new JLabel();
      label.setText(text);
      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 15));
      label.setForeground(Color.WHITE);

      // Calculate label size and adjust for multi-line text if necessary
      FontMetrics fm = label.getFontMetrics(label.getFont());
      int width = fm.stringWidth(label.getText()) + label.getText().length();
      int height = fm.getHeight();
      for (char s : label.getText().toCharArray())
         if (s == '\n')
            height += fm.getHeight() + 1;

      label.setSize(width, height);
      label.setLocation(50, yPosition);
      return label;
   }

   /**
    * Creates the welcome text with handler name for the screen.
    *
    * @return A JLabel containing the welcome message.
    */
   private static JLabel createWelcomeText() {
      JLabel label = new JLabel();
      label.setText("<html>Welcome Back!\n<br><html>" + "[Handler Name]<br>");
      label.setForeground(Color.WHITE);
      label.setFont(new Font("Kantumruy Pro", Font.BOLD, 35));

      // Calculate label size and adjust for multi-line text
      FontMetrics fm = label.getFontMetrics(label.getFont());
      int width = fm.stringWidth(label.getText()) + label.getText().length();
      int height = fm.getHeight();
      for (char s : label.getText().toCharArray())
         if (s == '\n')
            height += fm.getHeight() + 1;

      label.setSize(width, height);
      label.setLocation(50, 50);
      return label;
   }

   /**
    * Initializes the moveable component, background image, and mouse listener.
    */
   private static void initializeComponent() {
      panel = new MoveableComponent();
      try {
         background = ImageIO.read(Resources.getResourceAsStream("demoBlurred.png"));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
      panel.setOpaque(false);
      panel.setLayout(null);
      panel.setSize(500, 350);

      // Mouse click event to transition to MapApplication
      panel.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            SignUpMenu.frame.setVisible(false);
            MapApplication.frame.setVisible(true);
         }
      });
   }

   /**
    * Returns the moveable component panel.
    *
    * @return The moveable component panel.
    */
   public static MoveableComponent getPanel() {
      return panel;
   }

   /**
    * Main method to launch the LoggedInScreen in a JFrame window.
    *
    * @param args Command-line arguments.
    */
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      frame.setSize(500, 350);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setUndecorated(true);

      frame.add(getPanel());
      frame.setVisible(true);
   }
}
