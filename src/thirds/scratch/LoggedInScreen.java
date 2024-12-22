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

public class LoggedInScreen extends JPanel
{
   private BufferedImage background;
   public LoggedInScreen()
   {
      super();
      initializeComponent();

      JLabel welcomeText = createWelcomeText();
      add(welcomeText);

      JLabel Text = createInstrucText();
      add(Text);

      JLabel Text1 = createInstrucText1();
      add(Text1);

      JLabel Text2 = createInstrucText2();
      add(Text2);

   }



   private JLabel createInstrucText()
   {
      JLabel label = new JLabel();

      label.setText("The application will be");

      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 15)); // ing ani ang pag change sa font guys
      label.setForeground(Color.WHITE);

      FontMetrics fm = label.getFontMetrics(label.getFont()); // need ug font metrics para ma base ang text sa text
      int width = fm.stringWidth(label.getText()) + label.getText().length(); // width sa text niya.
      int height = fm.getHeight();

      for(char s : label.getText().toCharArray())
         if(s == '\n')
            height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

      label.setSize(width, height); // width and height.
      label.setLocation(50, 226); // x and y.
      return label;

   }

   private JLabel createInstrucText1()
   {
      JLabel label = new JLabel();

      label.setText("open in a couple of");

      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 15)); // ing ani ang pag change sa font guys
      label.setForeground(Color.WHITE);

      FontMetrics fm = label.getFontMetrics(label.getFont()); // need ug font metrics para ma base ang text sa text
      int width = fm.stringWidth(label.getText()) + label.getText().length(); // width sa text niya.
      int height = fm.getHeight();

      for(char s : label.getText().toCharArray())
         if(s == '\n')
            height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

      label.setSize(width, height); // width and height.
      label.setLocation(50, 247); // x and y.
      return label;

   }

   private JLabel createInstrucText2()
   {
      JLabel label = new JLabel();

      label.setText("seconds...");

      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 15)); // ing ani ang pag change sa font guys
      label.setForeground(Color.WHITE);

      FontMetrics fm = label.getFontMetrics(label.getFont()); // need ug font metrics para ma base ang text sa text
      int width = fm.stringWidth(label.getText()) + label.getText().length(); // width sa text niya.
      int height = fm.getHeight();


      for(char s : label.getText().toCharArray())
         if(s == '\n')
            height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

      label.setSize(width, height); // width and height.
      label.setLocation(50, 268); // x and y.
      return label;

   }


   private JLabel createWelcomeText()
   {
      JLabel label = new JLabel();
      label.setText("<html>" +
              "Welcome Back!\n<br>" +
              "<html>" + "[Handler Name]<br>");


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

   public static void main(String[] args)
   {
      JFrame frame = new JFrame();
      frame.setSize(500, 350);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setUndecorated(true);

      frame.add(new LoggedInScreen());
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
   }
}

