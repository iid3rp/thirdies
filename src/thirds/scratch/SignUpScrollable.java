package thirds.scratch;

import thirds.application.SignUpMenu;
import thirds.io.Debug;
import thirds.swing.MoveableComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class SignUpScrollable
{
   public static MoveableComponent panel;
   static
   {
      initializeComponent();

      panel.add(createAccountText());
      panel.add(createDOBText());
      panel.add(createYYYYText());
      panel.add(createMMText());
      panel.add(createDDText());
      panel.add(createAtUserText());
      panel.add(createHandleText());
      panel.add(createAtHuddleUpText());
      panel.add(createFirstNameTextFieldWithPlaceholder(63, 168));
      panel.add(createMiddleNameTextFieldWithPlaceholder(63, 219));
      panel.add(createLastNameTextFieldWithPlaceholder(63, 274));
      panel.add(createYearTextFieldWithPlaceholder(63, 372));
      panel.add(createMonthTextFieldWithPlaceholder(119, 372));
      panel.add(createDayTextFieldWithPlaceholder(156, 372));
      panel.add(createHandleTextFieldWithPlaceholder(63, 444));
      panel.add(createAtUserTextFieldWithPlaceholder(63, 505));

   }

   private static JLabel createAccountText()
   {
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

      for(char s : label.getText().toCharArray())
         if(s == '\n')
            height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

      label.setSize(width, height); // width and height.
      label.setLocation(50, 50); // x and y.
      return label;
   }

   private static JLabel createDOBText() {
      JLabel label = new JLabel("Date of Birth");
      label.setFont(new Font("Ebrima", Font.BOLD, 16));
      label.setForeground(Color.WHITE);
      label.setBounds(57, 347, 109, 21);
      return label;
   }

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

   private static JLabel createAtUserText() {
      JLabel label = new JLabel("your username is good!");
      label.setFont(new Font("Ebrima", Font.PLAIN, 10));
      label.setForeground(Color.WHITE);
      label.setBounds(63, 539, 150, 12);
      return label;
   }

   private static JLabel createAtHuddleUpText() {
      JLabel label = new JLabel("Huddle me up!");
      label.setFont(new Font("Ebrima", Font.BOLD, 16));
      label.setForeground(Color.ORANGE);
      label.setBounds(72, 609, 128, 25);
      return label;
   }

   private static JPanel createFirstNameTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 163, 29);
      panel.setOpaque(false);

      JTextField textField = new JTextField("First Name");
      textField.setBounds(0, 0, 271, 29);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.WHITE);
      textField.setBorder(BorderFactory.createEmptyBorder());
      textField.setHorizontalAlignment(SwingConstants.LEFT);
      textField.setFont(new Font("Ebrima", Font.PLAIN, 16));


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
               textField.setFont(new Font("Ebrima", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private static JPanel createMiddleNameTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 163, 29);
      panel.setOpaque(false);

      JTextField textField = new JTextField("Middle Name");
      textField.setBounds(0, 0, 271, 29);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.WHITE);
      textField.setBorder(BorderFactory.createEmptyBorder());
      textField.setHorizontalAlignment(SwingConstants.LEFT);
      textField.setFont(new Font("Ebrima", Font.PLAIN, 16));


      textField.addFocusListener(new java.awt.event.FocusListener() {
         @Override
         public void focusGained(java.awt.event.FocusEvent e) {
            if (textField.getText().equals("Middle Name")) {
               textField.setText("");
               textField.setForeground(Color.WHITE);
            }
         }

         @Override
         public void focusLost(java.awt.event.FocusEvent e) {
            if (textField.getText().trim().isEmpty()) {
               textField.setText("Middle Name");
               textField.setFont(new Font("Ebrima", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private static JPanel createLastNameTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 163, 29);
      panel.setOpaque(false);

      JTextField textField = new JTextField("Last Name");
      textField.setBounds(0, 0, 271, 29);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.WHITE);
      textField.setBorder(BorderFactory.createEmptyBorder());
      textField.setHorizontalAlignment(SwingConstants.LEFT);
      textField.setFont(new Font("Ebrima", Font.PLAIN, 16));
      textField.setFont(new Font("Ebrima", Font.PLAIN, 16));


      textField.addFocusListener(new java.awt.event.FocusListener() {
         @Override
         public void focusGained(java.awt.event.FocusEvent e) {
            if (textField.getText().equals("Last Name")) {
               textField.setText("");
               textField.setForeground(Color.WHITE);
            }
         }

         @Override
         public void focusLost(java.awt.event.FocusEvent e) {
            if (textField.getText().trim().isEmpty()) {
               textField.setText("Last Name");
               textField.setFont(new Font("Ebrima", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private static JPanel createYearTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 38, 21);
      panel.setOpaque(false);

      JTextField textField = new JTextField("2001");
      textField.setBounds(0, 0, 38, 21);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.WHITE);
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
               textField.setFont(new Font("Ebrima", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private static JPanel createMonthTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 18, 21);
      panel.setOpaque(false);

      JTextField textField = new JTextField("12");
      textField.setBounds(0, 0, 18, 21);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.WHITE);
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
               textField.setFont(new Font("Ebrima", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private static JPanel createDayTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 18, 21);
      panel.setOpaque(false);

      JTextField textField = new JTextField("31");
      textField.setBounds(0, 0, 18, 21);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.WHITE);
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
               textField.setFont(new Font("Ebrima", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private static JPanel createHandleTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 163, 29);
      panel.setOpaque(false);

      JTextField textField = new JTextField("Handle Name");
      textField.setBounds(0, 0, 163, 29);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.WHITE);
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
               textField.setFont(new Font("Ebrima", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private static JPanel createAtUserTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 163, 29);
      panel.setOpaque(false);

      JTextField textField = new JTextField("@username");
      textField.setBounds(0, 0, 163, 29);
      textField.setOpaque(false);
      textField.setBackground(new Color(0, 0, 0, 0));
      textField.setForeground(Color.WHITE);
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
               textField.setText("Handle Name");
               textField.setFont(new Font("Ebrima", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }


   private static void initializeComponent()
   {
      panel = new MoveableComponent()
      {
         @Override
         public void paintComponent(Graphics g)
         {
            drawOrangeLines(g);
         }
      };
      panel.setOpaque(false);
      panel.setLayout(null);
      panel.setSize(450, 675);
      panel.setVisible(true);

      panel.addMouseListener(new MouseAdapter()
      {
         @Override
         public void mousePressed(MouseEvent e)
         {
            if (SwingUtilities.isLeftMouseButton(e))
            {
               SignUpMenu.isDragging = true;
               SignUpMenu.offset = e.getPoint();
               SignUpMenu.offset.translate(0,panel.getY());
            }
         }

         @Override
         public void mouseReleased(MouseEvent e)
         {
            if (SwingUtilities.isLeftMouseButton(e))
            {
               SignUpMenu.isDragging = false;
            }
         }
      });

      panel.addMouseMotionListener(new MouseMotionAdapter()
      {
         @Override
         public void mouseDragged(MouseEvent e)
         {
            if (SignUpMenu.isDragging)
            {
               Point currentMouse = e.getLocationOnScreen();

               int deltaX = currentMouse.x - SignUpMenu.offset.x;
               int deltaY = currentMouse.y - SignUpMenu.offset.y;

               SignUpMenu.frame.setLocation(deltaX, deltaY);
            }
         }
      });

      panel.addMouseWheelListener(e ->
      {
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
   public static void main(String[] args)
   {
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

      g2d.fillRect(57, 470, 272, 2); // x, y, width, height

      g2d.fillRect(57, 533, 272, 2); // x, y, width, height
   }

}
