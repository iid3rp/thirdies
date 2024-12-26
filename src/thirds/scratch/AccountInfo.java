package thirds.scratch;

import thirds.io.Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AccountInfo extends JPanel {
   private BufferedImage background;

   public AccountInfo() {
      initializeComponent();

      addLabel("HandleName", 30, 208);
      addLabel("Date of Birth", 30, 328);
      addLabel("Email", 30, 438);
      addLabel("Contact No", 30, 540);
      addLabelGray("@username", 30, 246);
      addLabelGray("January 1, 2000", 30, 361);
      addLabelGray("emailaddress@website.com", 30, 471);
      addLabelGray("09088184444", 30, 579);
      addLabelRed("Logout as @username", 65, 680);
      addSettingsIcon();
      addExitIcon();
      addEditIcon();
      addLogOutIcon();
      addProfileIcon();
   }

   private void addLabel(String text, int x, int y) {
      JLabel label = new JLabel(text);
      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 20));
      label.setForeground(Color.BLACK);

      FontMetrics fm = label.getFontMetrics(label.getFont());
      int width = fm.stringWidth(text) + text.length();
      int height = fm.getHeight();

      label.setSize(width, height);
      label.setLocation(x, y);
      this.add(label);
   }

   private void addLabelGray(String text, int x, int y) {
      JLabel label = new JLabel(text);
      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
      label.setForeground(Color.LIGHT_GRAY);
      FontMetrics fm = label.getFontMetrics(label.getFont());
      int width = fm.stringWidth(text) + text.length();
      int height = fm.getHeight();

      label.setSize(width, height);
      label.setLocation(x, y);
      this.add(label);
   }

   private void addLabelRed(String text, int x, int y) {
      JLabel label = new JLabel(text);
      label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 16));
      label.setForeground(Color.RED);
      FontMetrics fm = label.getFontMetrics(label.getFont());
      int width = fm.stringWidth(text) + text.length();
      int height = fm.getHeight();

      label.setSize(width, height);
      label.setLocation(x, y);
      this.add(label);
   }

   private void addSettingsIcon() {
      try {
         BufferedImage settingsIcon = ImageIO.read(Resources.getResourceAsStream("settings.png"));
         JLabel iconLabel = new JLabel(new ImageIcon(settingsIcon.getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
         iconLabel.setSize(40, 40);
         iconLabel.setLocation(210, 15);
         this.add(iconLabel);
      } catch (IOException e) {
         throw new RuntimeException("Failed to load settings icon", e);
      }
   }

   private void addExitIcon() {
      try {
         BufferedImage exitIcon = ImageIO.read(Resources.getResourceAsStream("exit.png"));
         JLabel iconLabel = new JLabel(new ImageIcon(exitIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
         iconLabel.setSize(20, 20);
         iconLabel.setLocation(349, 33);
         this.add(iconLabel);
      } catch (IOException e) {
         throw new RuntimeException("Failed to load exit icon", e);
      }
   }

   private void addEditIcon() {
      try {
         BufferedImage editIcon = ImageIO.read(Resources.getResourceAsStream("edit.png"));
         JLabel iconLabel = new JLabel(new ImageIcon(editIcon.getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
         iconLabel.setSize(25, 25);
         iconLabel.setLocation(272, 23);
         this.add(iconLabel);
      } catch (IOException e) {
         throw new RuntimeException("Failed to load edit icon", e);
      }
   }

   private void addProfileIcon() {
      try {
         BufferedImage profileIcon = ImageIO.read(Resources.getResourceAsStream("circle.png"));
         JLabel iconLabel = new JLabel(new ImageIcon(profileIcon.getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
         iconLabel.setSize(125, 125);
         iconLabel.setLocation(30, 30);
         this.add(iconLabel);
      } catch (IOException e) {
         throw new RuntimeException("Failed to load profile icon", e);
      }
   }

   private void addLogOutIcon() {
      try {
         BufferedImage logoutIcon = ImageIO.read(Resources.getResourceAsStream("logout.png"));
         JLabel iconLabel = new JLabel(new ImageIcon(logoutIcon.getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
         iconLabel.setSize(25, 25);
         iconLabel.setLocation(31, 680);
         this.add(iconLabel);
      } catch (IOException e) {
         throw new RuntimeException("Failed to load logout icon", e);
      }
   }

   private void initializeComponent() {
      try {
         background = ImageIO.read(Resources.getResourceAsStream("AccountInfo.png"));
      } catch (IOException e) {
         throw new RuntimeException("Failed to load background image", e);
      }
      this.setLayout(null); // Set null layout for absolute positioning
      this.setPreferredSize(new Dimension(350, 720));
      this.setBackground(new Color(184, 141, 29));
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
   }

   public static void main(String[] args) {
      JFrame frame = new JFrame();
      frame.setSize(350, 720);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setUndecorated(true);

      frame.add(new AccountInfo());
      frame.setVisible(true);
   }
}
