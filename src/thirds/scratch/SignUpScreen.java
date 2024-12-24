package thirds.scratch;

import thirds.swing.MoveableComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignUpScreen
{
   public static MoveableComponent panel;

   static
   {
      initializeComponent();

      // Add components to the panel
      panel.add(createWelcomeText());
      panel.add(createInstructText());
      panel.add(createInstrucText1());
      panel.add(createInstrucText2());
      panel.add(createEmailTextFieldWithPlaceholder(51, 197));
   }

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

   private static JLabel createInstructText() {
      return createTextLabel("Start with your email. We", 50, 116);
   }

   private static JLabel createInstrucText1() {
      return createTextLabel("will send a code for you to", 50, 137);
   }

   private static JLabel createInstrucText2() {
      return createTextLabel("get started.", 50, 161);
   }

   private static JLabel createWelcomeText() {
      JLabel label = new JLabel("Sign Up");
      label.setFont(new Font("Kantumruy Pro", Font.BOLD, 35));
      label.setForeground(Color.WHITE);
      label.setBounds(50, 50, 400, 40);
      return label;
   }

   private static JLabel createTextLabel(String text, int x, int y) {
      JLabel label = new JLabel(text);
      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 15));
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
