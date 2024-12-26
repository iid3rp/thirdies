package thirds.scratch;

import thirds.io.Resources;
import thirds.swing.MoveableComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AccountInfo {
   private static BufferedImage background;
   private static MoveableComponent panel;

   static {
      initializeComponent();
      addLabel("HandleName", 30, 208);
      addLabel("Date of Birth", 30, 328);
      addLabel("Email", 30, 438);
      addLabelGray("@username", 30, 246);
      addLabelGray("January 1, 2000", 30, 361);
      addLabelGray("emailaddress@website.com", 30, 471);
      addLabelRed("Logout as @username", 65, 680);
      addSettingsIcon();
      addExitIcon();
      addEditIcon();
      addLogOutIcon();
      addProfileIcon();
   }

   private AccountInfo()
   {
      throw new IllegalStateException("Utility classes should not be instantiated.");
   }

   private static void addLabel(String text, int x, int y) {
      JLabel label = new JLabel(text);
      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 20));
      label.setForeground(Color.BLACK);

      FontMetrics fm = label.getFontMetrics(label.getFont());
      int width = fm.stringWidth(text) + text.length();
      int height = fm.getHeight();

      label.setSize(width, height);
      label.setLocation(x, y);
      panel.add(label);
   }

   private static void addLabelGray(String text, int x, int y) {
      JLabel label = new JLabel(text);
      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
      label.setForeground(Color.LIGHT_GRAY);
      FontMetrics fm = label.getFontMetrics(label.getFont());
      int width = fm.stringWidth(text) + text.length();
      int height = fm.getHeight();

      label.setSize(width, height);
      label.setLocation(x, y);
      panel.add(label);
   }

   private static void addLabelRed(String text, int x, int y) {
      JLabel label = new JLabel(text);
      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
      label.setForeground(Color.RED);
      FontMetrics fm = label.getFontMetrics(label.getFont());
      int width = fm.stringWidth(text) + text.length();
      int height = fm.getHeight();

      label.setSize(width, height);
      label.setLocation(x, y);
      panel.add(label);
   }

   private static void addSettingsIcon() {
      try {
         BufferedImage settingsIcon = ImageIO.read(Resources.getResourceAsStream("settings.png"));
         JLabel iconLabel = new JLabel(new ImageIcon(settingsIcon.getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
         iconLabel.setSize(40, 40);
         iconLabel.setLocation(210, 15);
         panel.add(iconLabel);
      } catch (IOException e) {
         throw new RuntimeException("Failed to load settings icon", e);
      }
   }

   private static void addExitIcon() {
      try {
         BufferedImage exitIcon = ImageIO.read(Resources.getResourceAsStream("exit.png"));
         JLabel iconLabel = new JLabel(new ImageIcon(exitIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
         iconLabel.setSize(20, 20);
         iconLabel.setLocation(349, 33);
         panel.add(iconLabel);
      } catch (IOException e) {
         throw new RuntimeException("Failed to load exit icon", e);
      }
   }

   private static void addEditIcon() {
      try {
         BufferedImage editIcon = ImageIO.read(Resources.getResourceAsStream("edit.png"));
         JLabel iconLabel = new JLabel(new ImageIcon(editIcon.getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
         iconLabel.setSize(25, 25);
         iconLabel.setLocation(272, 23);
         panel.add(iconLabel);
      } catch (IOException e) {
         throw new RuntimeException("Failed to load edit icon", e);
      }
   }

   private static void addProfileIcon() {
      try {
         BufferedImage profileIcon = ImageIO.read(Resources.getResourceAsStream("circle.png"));
         JLabel iconLabel = new JLabel(new ImageIcon(profileIcon.getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
         iconLabel.setSize(125, 125);
         iconLabel.setLocation(30, 30);
         panel.add(iconLabel);
      } catch (IOException e) {
         throw new RuntimeException("Failed to load profile icon", e);
      }
   }

   private static void addLogOutIcon() {
      try {
         BufferedImage logoutIcon = ImageIO.read(Resources.getResourceAsStream("logout.png"));
         JLabel iconLabel = new JLabel(new ImageIcon(logoutIcon.getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
         iconLabel.setSize(25, 25);
         iconLabel.setLocation(31, 680);
         panel.add(iconLabel);
      } catch (IOException e) {
         throw new RuntimeException("Failed to load logout icon", e);
      }
   }

   private static void initializeComponent() {
      panel = new MoveableComponent()
      {
         @Override
         protected void paintComponent(Graphics g)
         {
            g.drawImage(background, 0, 0, panel.getWidth(), panel.getHeight(), null);
         }
      };
      try {
         background = ImageIO.read(Resources.getResourceAsStream("AccountInfo.png"));
      } catch (IOException e) {
         throw new RuntimeException("Failed to load background image", e);
      }
      panel.setLayout(null); // Set null layout for absolute positioning
      panel.setSize(new Dimension(330, 720));
      panel.setBackground(new Color(184, 141, 29));
   }

   public static MoveableComponent getPanel()
   {
      return panel;
   }

   public static void main(String[] args) {
      JFrame frame = new JFrame();
      frame.setSize(320, 720);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setUndecorated(true);

      frame.add(getPanel());
      frame.setVisible(true);
   }
}