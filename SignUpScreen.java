package thirds.scratch;

import thirds.io.Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SignUpScreen extends JPanel {
   private BufferedImage background;

   public SignUpScreen() {
      initializeComponent();

      add(createLetsGoText());
      add(createWelcomeText());
      add(createInstrucText());
      add(createInstrucText1());
      add(createInstrucText2());
      add(createEmailTextFieldWithPlaceholder(51, 197));
      addBackIcon();
      addCloseIconListener();
   }

   private void addBackIcon() {
      try {
         BufferedImage settingsIcon = ImageIO.read(Resources.getResourceAsStream("orange_arrow.png"));
         JLabel iconLabel = new JLabel(new ImageIcon(settingsIcon.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
         iconLabel.setSize(30, 30);
         iconLabel.setLocation(30, 20);
         add(iconLabel);
      } catch (IOException e) {
         throw new RuntimeException("Failed to load settings icon", e);
      }
   }

   private JLabel createLetsGoText()
   {
      JLabel label = new JLabel();
      label.setText("Let's go");

      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
      label.setForeground(Color.WHITE);

      FontMetrics fm = label.getFontMetrics(label.getFont());
      int width = fm.stringWidth(label.getText()) + label.getText().length();
      int height = fm.getHeight();

      for(char s : label.getText().toCharArray())
         if(s == '\n')
            height += fm.getHeight() + 1;

      label.setSize(width, height);
      label.setLocation(65, 268);
      return label;
   }

   private JPanel createEmailTextFieldWithPlaceholder(int x, int y) {
      JPanel panel = new JPanel(null);
      panel.setBounds(x, y, 271, 29);
      panel.setOpaque(false);

      JTextField textField = new JTextField("Email Address");
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
            if (textField.getText().equals("Email Address")) {
               textField.setText("");
               textField.setForeground(Color.WHITE);
            }
         }

         @Override
         public void focusLost(java.awt.event.FocusEvent e) {
            if (textField.getText().trim().isEmpty()) {
               textField.setText("Email Address");
               textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
               textField.setForeground(Color.LIGHT_GRAY);
            }
         }
      });

      panel.add(textField);
      return panel;
   }

   private JLabel createInstrucText() {
      return createTextLabel("Start with your email. We", 50, 116);
   }

   private JLabel createInstrucText1() {
      return createTextLabel("will send a code for you to", 50, 137);
   }

   private JLabel createInstrucText2() {
      return createTextLabel("get started.", 50, 161);
   }

   private JLabel createWelcomeText() {
      JLabel label = new JLabel("Sign Up");
      label.setFont(new Font("Kantumruy Pro", Font.BOLD, 35));
      label.setForeground(Color.WHITE);
      label.setBounds(50, 50, 400, 40);
      return label;
   }

   private JLabel createTextLabel(String text, int x, int y) {
      JLabel label = new JLabel(text);
      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 15));
      label.setForeground(Color.WHITE);
      label.setBounds(x, y, 300, 20);
      return label;
   }

   private void initializeComponent() {
      try {
         background = ImageIO.read(Resources.getResourceAsStream("demoBlurred.png"));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
      setLayout(null);
      setPreferredSize(new Dimension(500, 350));
   }

   private void drawOrangeLine(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2d.setColor(new Color(255, 165, 0)); // Orange color
      g2d.fillRect(50, 224, 272, 2); // Line coordinates and size
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

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (background != null) {
         g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
      }
      drawOrangeLine(g); // Draw custom orange line
      drawCloseIcon(g); // Draw the close icon
   }

   public static void main(String[] args) {
      JFrame frame = new JFrame("Sign Up Screen");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(500, 350);
      frame.setLocationRelativeTo(null);
      frame.setUndecorated(true);

      SignUpScreen signUpScreen = new SignUpScreen();
      frame.add(signUpScreen);
      frame.pack();
      frame.setVisible(true);
   }
}
