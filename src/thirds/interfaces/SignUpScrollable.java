package thirds.interfaces;

import thirds.application.SignUpMenu;
import thirds.io.Debug;
import thirds.io.ThirdsObjectReader;
import thirds.swing.MoveableComponent;
import thirds.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * The SignUpScrollable class provides a graphical user interface (GUI) for signing up users
 * with fields for username, password, date of birth, and other related information.
 */
public class SignUpScrollable {
   public static MoveableComponent panel;

   // Text field variables for retrieving input
   private static JTextField handleNameField;
   private static JTextField passwordField;
   private static JTextField secondPasswordField;
   private static JTextField yearField;
   private static JTextField monthField;
   private static JTextField dayField;
   private static JTextField handleField;
   private static JTextField atUserField;

   static {
      initializeComponent();

      panel.add(createAccountText());
      panel.add(createDOBText());
      panel.add(createYYYYText());
      panel.add(createMMText());
      panel.add(createDDText());
      panel.add(createHandleText());
      panel.add(createAtUserText());
      panel.add(createHuddleUpText());

      // Initialize text fields and add them to the panel
      handleNameField = createFirstNameTextFieldWithPlaceholder(63, 168);
      passwordField = createPasswordTextFieldWithPlaceholder(63, 219);
      secondPasswordField = createAnotherPasswordTextFieldWithPlaceholder(63, 274);
      yearField = createYearTextFieldWithPlaceholder(63, 372);
      monthField = createMonthTextFieldWithPlaceholder(119, 372);
      dayField = createDayTextFieldWithPlaceholder(156, 372);
      atUserField = createAtUserTextFieldWithPlaceholder(63, 505);

      panel.add(handleNameField);
      panel.add(passwordField);
      panel.add(secondPasswordField);
      panel.add(yearField);
      panel.add(monthField);
      panel.add(dayField);
      panel.add(atUserField);
   }
   //Creates a label that prompts the user to create an account
   private static JLabel createAccountText() {
      JLabel label = new JLabel();
      label.setText("<html>" +
              "Creating\n<br> " +
              "Your Account</br>" +
              "</html>");

      label.setForeground(Color.WHITE);
      label.setFont(new Font("Ebrima", Font.BOLD, 35));

      FontMetrics fm = label.getFontMetrics(label.getFont());
      int width = fm.stringWidth(label.getText()) + label.getText().length();
      int height = fm.getHeight();

      for (char s : label.getText().toCharArray())
         if (s == '\n')
            height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

      label.setSize(width, height); // width and height.
      label.setLocation(50, 50); // x and y.
      return label;
   }

   //Creates a label displaying the text "Date of Birth".
   private static JLabel createDOBText() {
      JLabel label = new JLabel("Date of Birth");
      label.setFont(new Font("Ebrima", Font.BOLD, 16));
      label.setForeground(Color.WHITE);
      label.setBounds(57, 347, 109, 21);
      return label;
   }

   //Creates a label displaying the "yyyy" text for the year field.
   private static JLabel createYYYYText() {
      JLabel label = new JLabel("yyyy");
      label.setFont(new Font("Ebrima", Font.PLAIN, 12));
      label.setForeground(Color.WHITE);
      label.setBounds(70, 396, 30, 18);
      return label;
   }

   private static JLabel createMMText() {
      JLabel label = new JLabel("mm");
      label.setFont(new Font("Ebrima", Font.PLAIN, 12));
      label.setForeground(Color.WHITE);
      label.setBounds(119, 396, 30, 18);
      return label;
   }

   private static JLabel createDDText() {
      JLabel label = new JLabel("dd");
      label.setFont(new Font("Ebrima", Font.PLAIN, 12));
      label.setForeground(Color.WHITE);
      label.setBounds(158, 396, 30, 18);
      return label;
   }

   private static JLabel createHandleText() {
      JLabel label = new JLabel("your handle name is your full name by default.");
      label.setFont(new Font("Ebrima", Font.PLAIN, 10));
      label.setForeground(Color.WHITE);
      label.setBounds(63, 474, 250, 12);
      return label;
   }

 //  Creates a label displaying a message regarding the user's username.
   private static JLabel createAtUserText() {
      JLabel label = new JLabel("your username is good!");
      label.setFont(new Font("Ebrima", Font.PLAIN, 10));
      label.setForeground(Color.WHITE);
      label.setBounds(63, 539, 150, 12);
      return label;
   }


   /**
    * Creates a label with the text "Huddle me up!" that listens for mouse clicks to
    * trigger the user sign-up process.
    *
    * @return JLabel with the "Huddle me up!" text.
    */
   private static JLabel createHuddleUpText() {
      JLabel label = new JLabel("Huddle me up!");
      label.setFont(new Font("Ebrima", Font.BOLD, 16));
      label.setForeground(Color.ORANGE);
      label.setBounds(72, 609, 128, 25);
      label.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            // Retrieve user input from text fields
            String firstName = handleNameField.getText();
            String middleName = passwordField.getText();
            String lastName = secondPasswordField.getText();
            String year = yearField.getText();
            String month = monthField.getText();
            String day = dayField.getText();
            String handleName = handleField.getText();
            String username = atUserField.getText();

            // Construct the full name
            String fullName = firstName + " " + (middleName.isEmpty() ? "" : middleName + " ") + lastName;

            // Parse the date of birth
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOfBirth = null;
            try {
               dateOfBirth = dateFormat.parse(year + "-" + month + "-" + day);
            } catch (ParseException ex) {
               // Handle parsing exception appropriately
               throw new RuntimeException(ex);
            }

            // Create a new User object
            User user = new User();
            user.setHandleName(handleName.isEmpty() ? fullName : handleName);
            user.setUsername(username);
            user.setDateOfBirth(dateOfBirth);
            // Assuming you have a method to set a default password or prompt the user to create one
            user.setPassword("defaultPassword");

            // Serialize the user
            ThirdsObjectReader.serializeUser(user);
         }
      });
      return label;
   }

   /**
    * Creates a text field with a placeholder text at a specific location.
    *
    *  The x-coordinate for the text field.
    *  The y-coordinate for the text field.
    *  The placeholder text.
    * @return The created JTextField.
    */

   private static JTextField createFirstNameTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 163, 29);
      panel.setOpaque(false);

      JTextField textField = new JTextField("Enter Handle Name");
      textField.setBounds(x, y, 271, 29);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.LIGHT_GRAY);
      textField.setBorder(BorderFactory.createEmptyBorder());
      textField.setHorizontalAlignment(SwingConstants.LEFT);
      textField.setFont(new Font("Ebrima", Font.PLAIN, 16));

      // Change text color when user types

      textField.addFocusListener(new java.awt.event.FocusListener() {
         @Override
         public void focusGained(java.awt.event.FocusEvent e) {
            if (textField.getText().equals("First Name")) {
               textField.setText("");
               textField.setForeground(Color.WHITE);
            }
         }

         @Override
         public void focusLost(java.awt.event.FocusEvent e) {
            if (textField.getText().trim().isEmpty()) {
               textField.setText("First Name");
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return textField; // Return the actual JTextField
   }

   private static JTextField createPasswordTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 163, 29);
      panel.setOpaque(false);

      JTextField textField = new JTextField("Enter Password");
      textField.setBounds(x, y, 271, 29);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.LIGHT_GRAY);
      textField.setBorder(BorderFactory.createEmptyBorder());
      textField.setHorizontalAlignment(SwingConstants.LEFT);
      textField.setFont(new Font("Ebrima", Font.PLAIN, 16));

      textField.addFocusListener(new java.awt.event.FocusListener() {
         @Override
         public void focusGained(java.awt.event.FocusEvent e) {
            if (textField.getText().equals("Enter Password")) {
               textField.setText("");
               textField.setForeground(Color.WHITE);
            }
         }

         @Override
         public void focusLost(java.awt.event.FocusEvent e) {
            if (textField.getText().trim().isEmpty()) {
               textField.setText("Enter Password");
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return textField; // Return the actual JTextField
   }
//Creates a JTextField for confirming the password entry, with a placeholder text that disappears when the user focuses on the field.
   private static JTextField createAnotherPasswordTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 163, 29);
      panel.setOpaque(false);

      JTextField textField = new JTextField("Enter Password Again");
      textField.setBounds(x, y, 271, 29);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.LIGHT_GRAY);
      textField.setBorder(BorderFactory.createEmptyBorder());
      textField.setHorizontalAlignment(SwingConstants.LEFT);
      textField.setFont(new Font("Ebrima", Font.PLAIN, 16));

      textField.addFocusListener(new java.awt.event.FocusListener() {
         @Override
         public void focusGained(java.awt.event.FocusEvent e) {
            if (textField.getText().equals("Enter Password Again")) {
               textField.setText("");
               textField.setForeground(Color.WHITE);
            }
         }

         @Override
         public void focusLost(java.awt.event.FocusEvent e) {
            if (textField.getText().trim().isEmpty()) {
               textField.setText("Enter Password Again");
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return textField; // Return the actual JTextField
   }

   private static JTextField createYearTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 38, 21);
      panel.setOpaque(false);

      JTextField textField = new JTextField("2001");
      textField.setBounds(x, y, 38, 21);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.LIGHT_GRAY);
      textField.setBorder(BorderFactory.createEmptyBorder());
      textField.setHorizontalAlignment(SwingConstants.LEFT);
      textField.setFont(new Font("Ebrima", Font.PLAIN, 16));

      textField.addFocusListener(new java.awt.event.FocusListener() {
         @Override
         public void focusGained(java.awt.event.FocusEvent e) {
            if (textField.getText().equals("2001")) {
               textField.setText("");
               textField.setForeground(Color.WHITE);
            }
         }

         @Override
         public void focusLost(java.awt.event.FocusEvent e) {
            if (textField.getText().trim().isEmpty()) {
               textField.setText("2001");
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return textField; // Return the actual JTextField
   }

   private static JTextField createMonthTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 18, 21);
      panel.setOpaque(false);

      JTextField textField = new JTextField("12");
      textField.setBounds(x, y, 18, 21);
      textField.setOpaque(false);
      textField.setBackground(new Color(0,0, 0, 0));
      textField.setForeground(Color.LIGHT_GRAY);
      textField.setBorder(BorderFactory.createEmptyBorder());
      textField.setHorizontalAlignment(SwingConstants.LEFT);
      textField.setFont(new Font("Ebrima", Font.PLAIN, 16));

      textField.addFocusListener(new java.awt.event.FocusListener() {
         @Override
         public void focusGained(java.awt.event.FocusEvent e) {
            if (textField.getText().equals("12")) {
               textField.setText("");
               textField.setForeground(Color.WHITE);
            }
         }

         @Override
         public void focusLost(java.awt.event.FocusEvent e) {
            if (textField.getText().trim().isEmpty()) {
               textField.setText("12");
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return textField; // Return the actual JTextField
   }

   private static JTextField createDayTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 18, 21);
      panel.setOpaque(false);

      JTextField textField = new JTextField("31");
      textField.setBounds(x, y, 18, 21);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.LIGHT_GRAY);
      textField.setBorder(BorderFactory.createEmptyBorder());
      textField.setHorizontalAlignment(SwingConstants.LEFT);
      textField.setFont(new Font("Ebrima", Font.PLAIN, 16));

      textField.addFocusListener(new java.awt.event.FocusListener() {
         @Override
         public void focusGained(java.awt.event.FocusEvent e) {
            if (textField.getText().equals("31")) {
               textField.setText("");
               textField.setForeground(Color.WHITE);
            }
         }

         @Override
         public void focusLost(java.awt.event.FocusEvent e) {
            if (textField.getText().trim().isEmpty()) {
               textField.setText("31");
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return textField; // Return the actual JTextField
   }

   private static JTextField createHandleTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 163, 29);
      panel.setOpaque(false);

      JTextField textField = new JTextField("Handle Name");
      textField.setBounds(x, y, 163, 29);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.LIGHT_GRAY);
      textField.setBorder(BorderFactory.createEmptyBorder());
      textField.setHorizontalAlignment(SwingConstants.LEFT);
      textField.setFont(new Font("Ebrima", Font.PLAIN, 16));

      textField.addFocusListener(new java.awt.event.FocusListener() {
         @Override
         public void focusGained(java.awt.event.FocusEvent e) {
            if (textField.getText().equals("Handle Name")) {
               textField.setText("");
               textField.setForeground(Color.WHITE);
            }
         }

         @Override
         public void focusLost(java.awt.event.FocusEvent e) {
            if (textField.getText().trim().isEmpty()) {
               textField.setText("Handle Name");
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return textField; // Return the actual JTextField
   }

   private static JTextField createAtUserTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 163, 29);
      panel.setOpaque(false);

      JTextField textField = new JTextField("@username");
      textField.setBounds(x, y, 163, 29);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.LIGHT_GRAY);
      textField.setBorder(BorderFactory.createEmptyBorder());
      textField.setHorizontalAlignment(SwingConstants.LEFT);
      textField.setFont(new Font("Ebrima", Font.PLAIN, 16));

      textField.addFocusListener(new java.awt.event.FocusListener() {
         @Override
         public void focusGained(java.awt.event.FocusEvent e) {
            if (textField.getText().equals("@username")) {
               textField.setText("");
               textField.setForeground(Color.WHITE);
            }
         }

         @Override
         public void focusLost(java.awt.event.FocusEvent e) {
            if (textField.getText().trim().isEmpty()) {
               textField.setText("@username");
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return textField; // Return the actual JTextField
   }

   private static void initializeComponent() {
      panel = new MoveableComponent() {
         @Override
         public void paintComponent(Graphics g) {
            drawOrangeLines(g);
         }
      };
      panel.setOpaque(false);
      panel.setLayout(null);
      panel.setSize(450, 675);
      panel.setVisible(true);

      panel.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
               SignUpMenu.isDragging = true;
               SignUpMenu.offset = e.getPoint();
               SignUpMenu.offset.translate(0, panel.getY());
            }
         }

         @Override
         public void mouseReleased(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
               SignUpMenu.isDragging = false;
            }
         }
      });

      panel.addMouseMotionListener(new MouseMotionAdapter() {
         @Override
         public void mouseDragged(MouseEvent e) {
            if (SignUpMenu.isDragging) {
               Point currentMouse = e.getLocationOnScreen();

               int deltaX = currentMouse.x - SignUpMenu.offset.x;
               int deltaY = currentMouse.y - SignUpMenu.offset.y;

               SignUpMenu.frame.setLocation(deltaX, deltaY);
            }
         }
      });

      panel.addMouseWheelListener(e -> {
         // Current location of the container panel
         Point currentPoint = panel.getLocation();

         // Apply sensitivity scaling for trackpad
         double scrollSensitivity = 20; // Adjust this value as needed

         int newY = (int) (currentPoint.y - e.getPreciseWheelRotation() * scrollSensitivity * (e.isShiftDown() ? 0 : 1));
         int minY = -panel.getHeight() + panel.getParent().getHeight();
         newY = Math.min(0, Math.max(minY, newY));

         panel.setLocation(panel.getX(), newY);
         panel.repaint();
      });
   }

   @Debug
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      frame.setSize(500, 350);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setUndecorated(true);

      frame.add(panel);
      frame.setVisible(true);
   }

   private void drawCloseIcon(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      g2d.setColor(Color.ORANGE);
      int padding = 6;
      int x1 = 450 + padding;
      int y1 = 20 + padding;
      int x2 = 450 + 30 - padding;
      int y2 = 20 + 30 - padding;

      g2d.drawLine(x1, y1, x2, y2); // Top-left to bottom-right
      g2d.drawLine(x1, y2, x2, y1); // Bottom-left to top-right
   }
  //drawing more ui components
   private static void drawOrangeLines(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      g2d.setColor(new Color(255, 165, 0));

      g2d.fillRect(57, 195, 272, 2); // x, y, width, height

      g2d.fillRect(57, 244, 272, 2); // x, y, width, height

      g2d.fillRect(57, 300, 272, 2); // x, y, width, height

      g2d.fillRect(57, 396, 50, 2); // x, y, width, height

      g2d.fillRect(117, 396, 24, 2); // x, y, width, height

      g2d.fillRect(154, 396, 24, 2); // x, y, width, height

      g2d.fillRect(57, 533, 272, 2); // x, y, width, height
   }

}