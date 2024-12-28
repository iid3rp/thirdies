/**
 * SignUpScreen Class
 * ------------------
 * This class provides a graphical user interface for a Sign-Up screen. It includes interactive components to guide the user
 * through the process of entering an email address and proceeding to the next step. The panel is styled with a modern design
 * and supports smooth transitions between screens.
 *
 * Key Features:
 * - Displays a sign-up title and instructions.
 * - Includes a placeholder-enabled email text field.
 * - Interactive labels trigger panel transitions upon clicking.
 * - Custom orange line is drawn as a design element.
 * - The main panel is movable for smooth navigation.
 */
package thirds.interfaces;

import thirds.swing.MoveableComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignUpScreen
{
   // Main movable panel for the Sign-Up screen.

   public static MoveableComponent panel;

   static
   {
      initializeComponent();

      // Add components to the panel
      panel.add(createWelcomeText());
      panel.add(createInstructText());
      panel.add(createInstructText1());
      panel.add(createInstructText2());
      panel.add(createEmailTextFieldWithPlaceholder(51, 197));
   }
   /**
    * Creates an email text field with placeholder functionality.
    *
    * @param x X-coordinate of the text field.
    * @param y Y-coordinate of the text field.
    * @return A JPanel containing the text field.
    */
   private static JPanel createEmailTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 271, 29);
      panel.setOpaque(false);

      JTextField textField = new JTextField("Email Address");
      textField.setBounds(0, 0, 271, 29);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.LIGHT_GRAY);
      textField.setBorder(BorderFactory.createEmptyBorder());
      textField.setHorizontalAlignment(SwingConstants.LEFT);


      // Placeholder behavior on focus events.
      textField.addFocusListener(new java.awt.event.FocusListener() {
         @Override
         public void focusGained(java.awt.event.FocusEvent e) {
            if (textField.getText().equals("Email Address")) {
               textField.setText("");
               textField.setForeground(Color.WHITE);
            }
         }

         @Override
         public void focusLost(java.awt.event.FocusEvent e) {
            if (textField.getText().trim().isEmpty()) {
               textField.setText("Email Address");
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   /**
    * Creates the first line of instruction text.
    *
    * @return A JLabel containing the text.
    */

   //lines of instruction test
   private static JLabel createInstructText() {
      return createTextLabel("Start with your email. We", 50, 116);
   }

   private static JLabel createInstructText1() {
      return createTextLabel("will send a code for you to", 50, 137);
   }

   private static JLabel createInstructText2() {
      return createTextLabel("get started.", 50, 161);
   }


   //Creates the "Sign Up" title label.
   private static JLabel createWelcomeText() {
      JLabel label = new JLabel("Sign Up");
      label.setFont(new Font("Ebrima", Font.BOLD, 35));
      label.setForeground(Color.WHITE);
      label.setBounds(50, 50, 400, 40);
      return label;
   }

   /**
    * Creates a generic text label with specified text and position.
    *
    * @param text The text to display.
    * @param x    X-coordinate of the label.
    * @param y    Y-coordinate of the label.
    * @return A JLabel containing the specified text.
    */

   private static JLabel createTextLabel(String text, int x, int y) {
      JLabel label = new JLabel(text);
      label.setFont(new Font("Ebrima", Font.PLAIN, 15));
      label.setForeground(Color.WHITE);
      label.setBounds(x, y, 300, 20);

      label.addMouseListener(new MouseAdapter()
      {
         @Override
         public void mouseClicked(MouseEvent e)
         {
            panel.move(-500, 0, 250);
            SignUpScrollable.panel.move(0, 0, 250);
         }
      });
      return label;
   }

   /**
    * Initializes the main panel and sets its size and properties.
    */
   private static void initializeComponent() {
      panel = new MoveableComponent()
      {
         @Override
         protected void paintComponent(Graphics g) {

            drawOrangeLine(g); // Draw custom orange line
         }
      };
      panel.setOpaque(false);
      panel.setSize(new Dimension(500, 350));
   }

   private static void drawOrangeLine(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2d.setColor(new Color(255, 165, 0)); // Orange color
      g2d.fillRect(50, 224, 272, 2); // Line coordinates and size
   }

 //  Main method to run the Sign-Up screen.

   public static void main(String[] args) {
      JFrame frame = new JFrame("Sign Up Screen");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(500, 350);
      frame.setLocationRelativeTo(null);
      frame.setUndecorated(true);

      frame.add(panel);
      frame.setVisible(true);
   }
}
