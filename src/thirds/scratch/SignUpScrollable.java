package thirds.scratch;

import thirds.io.Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SignUpScrollable extends JPanel {

   public SignUpScrollable() {
      initializeComponent();

      addCloseIconListener();
      add(createAccountText());
      add(createDOBText());
      add(createYYYYText());
      add(createMMText());
      add(createDDText());
      add(createAtUserText());
      add(createHandleText());
      add(createAtHudddleUpText());
      add(createFirstNameTextFieldWithPlaceholder(63, 168));
      add(createMiddleNameTextFieldWithPlaceholder(63, 219));
      add(createLastNameTextFieldWithPlaceholder(63, 274));
      add(createYearTextFieldWithPlaceholder(63, 372));
      add(createMonthTextFieldWithPlaceholder(119, 372));
      add(createDayTextFieldWithPlaceholder(156, 372));
      add(createHandleTextFieldWithPlaceholder(63, 444));
      add(createAtUserTextFieldWithPlaceholder(63, 505));

   }

   private BufferedImage background;

   private JLabel createAccountText()
   {
      JLabel label = new JLabel();
      label.setText("<html>" +
              "Creating\n<br> " +
              "Your Account</br>" +
              "</html>");

      label.setForeground(Color.WHITE);
      label.setFont(new Font("Kantumruy Pro", Font.BOLD, 35)); // ing ani ang pag change sa font guys

      FontMetrics fm = label.getFontMetrics(label.getFont()); // need ug font metrics para ma base ang text sa text
      int width = fm.stringWidth(label.getText()) + label.getText().length(); // width sa text niya.
      int height = fm.getHeight();

      for(char s : label.getText().toCharArray())
         if(s == '\n')
            height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

      label.setSize(width, height); // width and height.
      label.setLocation(50, 50); // x and y.
      return label;
   }

   private JLabel createDOBText() {
      JLabel label = new JLabel("Date of Birth");
      label.setFont(new Font("Kantumruy Pro", Font.BOLD, 16));
      label.setForeground(Color.WHITE);
      label.setBounds(57, 347, 109, 21);
      return label;
   }

   private JLabel createYYYYText() {
      JLabel label = new JLabel("yyyy");
      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 12));
      label.setForeground(Color.WHITE);
      label.setBounds(70, 396, 30, 18);
      return label;
   }

   private JLabel createMMText() {
      JLabel label = new JLabel("mm");
      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 12));
      label.setForeground(Color.WHITE);
      label.setBounds(119, 396, 30, 18);
      return label;
   }

   private JLabel createDDText() {
      JLabel label = new JLabel("dd");
      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 12));
      label.setForeground(Color.WHITE);
      label.setBounds(158, 396, 30, 18);
      return label;
   }

   private JLabel createHandleText() {
      JLabel label = new JLabel("your handle name is your full name by default.");
      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 10));
      label.setForeground(Color.WHITE);
      label.setBounds(63, 474, 250, 12);
      return label;
   }

   private JLabel createAtUserText() {
      JLabel label = new JLabel("your username is good!");
      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 10));
      label.setForeground(Color.WHITE);
      label.setBounds(63, 539, 150, 12);
      return label;
   }

   private JLabel createAtHudddleUpText() {
      JLabel label = new JLabel("Huddle me up!");
      label.setFont(new Font("Kantumruy Pro", Font.BOLD, 16));
      label.setForeground(Color.ORANGE);
      label.setBounds(72, 609, 128, 25);
      return label;
   }

   private JPanel createFirstNameTextFieldWithPlaceholder(int x, int y) {
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
      textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));


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
               textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private JPanel createMiddleNameTextFieldWithPlaceholder(int x, int y) {
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
      textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));


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
               textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private JPanel createLastNameTextFieldWithPlaceholder(int x, int y) {
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
      textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
      textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));


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
               textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private JPanel createYearTextFieldWithPlaceholder(int x, int y) {
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
      textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));


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
               textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private JPanel createMonthTextFieldWithPlaceholder(int x, int y) {
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
      textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));


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
               textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private JPanel createDayTextFieldWithPlaceholder(int x, int y) {
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
      textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));


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
               textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private JPanel createHandleTextFieldWithPlaceholder(int x, int y) {
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
      textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));


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
               textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private JPanel createAtUserTextFieldWithPlaceholder(int x, int y) {
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
      textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));


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
               textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }


   private void initializeComponent()
   {
      try {
         background = ImageIO.read(Resources.getResourceAsStream("demoBlurredS.png"));
      }
      catch(IOException e) {
         throw new RuntimeException(e);
      }
      setLayout(null);
      setSize(500, 675);
      setBackground(new Color(184, 141, 29));
      setVisible(true);
   }

   public static void main(String[] args)
   {
      JFrame frame = new JFrame();
      frame.setSize(500, 675);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setUndecorated(true);

      frame.add(new SignUpScrollable());
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

   private void addCloseIconListener() {
      addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            int padding = 6;
            int iconX = 450 + padding;
            int iconY = 20 + padding;
            int iconSize = 30 - 2 * padding;

            Rectangle closeIconBounds = new Rectangle(iconX, iconY, iconSize, iconSize);

            if (closeIconBounds.contains(e.getPoint())) {
               System.exit(0); // Exit the program
            }
         }
      });
   }

   private void drawOrangeLines(Graphics g) {
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

   @Override
   public void paintComponent(Graphics g)
   {
      //
      Graphics2D graphics = background.createGraphics();
      graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      graphics.drawImage(background, 0, 0, null);

      g.drawImage(background, 0, 0, getWidth(),getHeight(),null);

      drawOrangeLines(g);
      drawCloseIcon(g);

   }

}
