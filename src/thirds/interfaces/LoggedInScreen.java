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

public class LoggedInScreen {
   private static BufferedImage background;
   private static MoveableComponent panel;

   static
   {
      initializeComponent();

      JLabel welcomeText = createWelcomeText();
      panel.add(welcomeText);

      JLabel Text = createInstructText();
      panel.add(Text);

      JLabel Text1 = createInstructText1();
      panel.add(Text1);

      JLabel Text2 = createInstructText2();
      panel.add(Text2);
   }

   private LoggedInScreen()
   {
      throw new IllegalStateException("Utility class should not be instantiated");
   }

   private static JLabel createInstructText() {
      JLabel label = new JLabel();

      label.setText("Click Anywhere to");

      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 15));
      label.setForeground(Color.WHITE);

      FontMetrics fm = label.getFontMetrics(label.getFont());
      int width = fm.stringWidth(label.getText()) + label.getText().length();
      int height = fm.getHeight();

      for (char s : label.getText().toCharArray())
         if (s == '\n')
            height += fm.getHeight() + 1;

      label.setSize(width, height);
      label.setLocation(50, 226);
      return label;
   }

   private static JLabel createInstructText1() {
      JLabel label = new JLabel();

      label.setText("start you huddling");

      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 15));
      label.setForeground(Color.WHITE);

      FontMetrics fm = label.getFontMetrics(label.getFont());
      int width = fm.stringWidth(label.getText()) + label.getText().length();
      int height = fm.getHeight();

      for (char s : label.getText().toCharArray())
         if (s == '\n')
            height += fm.getHeight() + 1;

      label.setSize(width, height);
      label.setLocation(50, 247);
      return label;
   }

   private static JLabel createInstructText2() {
      JLabel label = new JLabel();

      label.setText("journey!");

      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 15));
      label.setForeground(Color.WHITE);

      FontMetrics fm = label.getFontMetrics(label.getFont());
      int width = fm.stringWidth(label.getText()) + label.getText().length();
      int height = fm.getHeight();

      for (char s : label.getText().toCharArray())
         if (s == '\n')
            height += fm.getHeight() + 1;

      label.setSize(width, height);
      label.setLocation(50, 268);
      return label;
   }

   private static JLabel createWelcomeText() {
      JLabel label = new JLabel();
      label.setText("<html>" +
              "Welcome Back!\n<br>" +
              "<html>" + "[Handler Name]<br>");

      label.setForeground(Color.WHITE);
      label.setFont(new Font("Kantumruy Pro", Font.BOLD, 35));

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

      panel.addMouseListener(new MouseAdapter()
      {
         @Override
         public void mouseClicked(MouseEvent e)
         {
            SignUpMenu.frame.setVisible(false);
            MapApplication.frame.setVisible(true);
         }
      });
   }

   public static MoveableComponent getPanel() {
      return panel;
   }

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