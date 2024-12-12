package thirds.scratch;

import thirds.io.Resources;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SignUpScreen extends JPanel
{
   private BufferedImage background;
   public SignUpScreen()
   {
      super();
      initializeComponent();

      JLabel welcomeText = createWelcomeText();
      add(welcomeText);

      JLabel logInText = createLogInText();
      add(logInText);

      JLabel InstructionText = createInstrucText();
      add(InstructionText);

      JLabel InstructionText1 = createInstrucText1();
      add(InstructionText1);

      JLabel InstructionText2 = createInstrucText2();
      add(InstructionText2);

      JLabel EmailText = createEmailText();
      add(EmailText);
   }

   private JLabel createEmailText()
   {
      JLabel label = new JLabel();
      label.setText("Email Address");

      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16)); // ing ani ang pag change sa font guys
      label.setForeground(Color.WHITE);

      FontMetrics fm = label.getFontMetrics(label.getFont()); // need ug font metrics para ma base ang text sa text
      int width = fm.stringWidth(label.getText()) + label.getText().length(); // width sa text niya.
      int height = fm.getHeight();

      for(char s : label.getText().toCharArray())
         if(s == '\n')
            height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

      label.setSize(width, height); // width and height.
      label.setLocation(65, 200); // x and y.
      return label;
   }

   private JLabel createLogInText()
   {
      JLabel label = new JLabel();
      label.setText("Log in");

      label.setFont(new Font("Kantumruy Pro", Font.BOLD, 18)); // ing ani ang pag change sa font guys
      label.setForeground(Color.ORANGE);

      FontMetrics fm = label.getFontMetrics(label.getFont()); // need ug font metrics para ma base ang text sa text
      int width = fm.stringWidth(label.getText()) + label.getText().length(); // width sa text niya.
      int height = fm.getHeight();

      for(char s : label.getText().toCharArray())
         if(s == '\n')
            height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

      label.setSize(width, height); // width and height.
      label.setLocation(65, 268); // x and y.
      return label;
   }

   private JLabel createInstrucText()
   {
      JLabel label = new JLabel();

      label.setText("Start with your email. We");

      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 15)); // ing ani ang pag change sa font guys
      label.setForeground(Color.WHITE);

      FontMetrics fm = label.getFontMetrics(label.getFont()); // need ug font metrics para ma base ang text sa text
      int width = fm.stringWidth(label.getText()) + label.getText().length(); // width sa text niya.
      int height = fm.getHeight();

      for(char s : label.getText().toCharArray())
         if(s == '\n')
            height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

      label.setSize(width, height); // width and height.
      label.setLocation(50, 116); // x and y.
      return label;

   }

   private JLabel createInstrucText1()
   {
      JLabel label = new JLabel();

      label.setText("will send a code for you to");

      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 15)); // ing ani ang pag change sa font guys
      label.setForeground(Color.WHITE);

      FontMetrics fm = label.getFontMetrics(label.getFont()); // need ug font metrics para ma base ang text sa text
      int width = fm.stringWidth(label.getText()) + label.getText().length(); // width sa text niya.
      int height = fm.getHeight();

      for(char s : label.getText().toCharArray())
         if(s == '\n')
            height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

      label.setSize(width, height); // width and height.
      label.setLocation(50, 137); // x and y.
      return label;

   }

   private JLabel createInstrucText2()
   {
      JLabel label = new JLabel();

      label.setText("get started.");

      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 15)); // ing ani ang pag change sa font guys
      label.setForeground(Color.WHITE);

      FontMetrics fm = label.getFontMetrics(label.getFont()); // need ug font metrics para ma base ang text sa text
      int width = fm.stringWidth(label.getText()) + label.getText().length(); // width sa text niya.
      int height = fm.getHeight();


      for(char s : label.getText().toCharArray())
         if(s == '\n')
            height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

      label.setSize(width, height); // width and height.
      label.setLocation(50, 161); // x and y.
      return label;

   }

   private void drawCloseIcon(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      // Set the color to orange
      g2d.setColor(new Color(255, 165, 0));
      g2d.setStroke(new java.awt.BasicStroke(3));

      // Set the color to white for the "X"
      g2d.setColor(Color.ORANGE);
      int padding = 6;
      int x1 = 450 + padding;
      int y1 = 20 + padding;
      int x2 = 450 + 30 - padding;
      int y2 = 20 + 30 - padding;

      // Draw the "X" lines
      g2d.drawLine(x1, y1, x2, y2); // Top-left to bottom-right
      g2d.drawLine(x1, y2, x2, y1); // Bottom-left to top-right
   }

   private JLabel createWelcomeText()
   {
      JLabel label = new JLabel();
      label.setText("<html>" +
              "Sign Up\n<br>");

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

   private void initializeComponent()
   {
      try {
         background = ImageIO.read(Resources.getResourceAsStream("demoBlurred.png"));
      }
      catch(IOException e) {
         throw new RuntimeException(e);
      }
      setLayout(null);
      setSize(500, 350);
      setBackground(new Color(184, 141, 29));
      setVisible(true);
   }

   private void drawOrangeLines(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      // Set the color to orange
      g2d.setColor(new Color(255, 165, 0));

      // Draw the first line
      g2d.fillRect(50, 224, 272, 2); // x, y, width, height

   }

   public static void main(String[] args)
   {
      JFrame frame = new JFrame();
      frame.setSize(500, 350);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setUndecorated(true);

      frame.add(new SignUpScreen());
      frame.setVisible(true);
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

