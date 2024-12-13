package thirds.scratch;

import thirds.io.Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LogInScreen extends JPanel
{
    private BufferedImage background;
    public LogInScreen()
    {
        super();
        initializeComponent();

        JLabel welcomeText = createWelcomeText();
        add(welcomeText);

        JLabel logInText = createLogInText();
        JLabel SignUpText = createSignUpText();
        add(logInText);
        add(SignUpText);
        add(createUserTextFieldWithPlaceholder(50, 154));
        add(createPassTextFieldWithPlaceholder(50, 204));
        addCloseIconListener();

    }

    private JLabel createLogInText()
    {
        JLabel label = new JLabel();
        label.setText("Log in");

        label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18)); // ing ani ang pag change sa font guys
        label.setForeground(Color.WHITE);

        FontMetrics fm = label.getFontMetrics(label.getFont()); // need ug font metrics para ma base ang text sa text
        int width = fm.stringWidth(label.getText()) + label.getText().length(); // width sa text niya.
        int height = fm.getHeight();

        for(char s : label.getText().toCharArray())
            if(s == '\n')
                height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

        label.setSize(width, height); // width and height.
        label.setLocation(67, 270); // x and y.
        return label;
    }

    private JLabel createSignUpText()
    {
        JLabel label = new JLabel();
        label.setText("Sign Up");

        label.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18)); // ing ani ang pag change sa font guys
        label.setForeground(Color.ORANGE);

        FontMetrics fm = label.getFontMetrics(label.getFont()); // need ug font metrics para ma base ang text sa text
        int width = fm.stringWidth(label.getText()) + label.getText().length(); // width sa text niya.
        int height = fm.getHeight();

        for(char s : label.getText().toCharArray())
            if(s == '\n')
                height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

        label.setSize(width, height); // width and height.
        label.setLocation(151, 270); // x and y.
        return label;
    }

    private JLabel createWelcomeText()
    {
        JLabel label = new JLabel();
        label.setText("<html>" +
                        "Welcome\n<br> " +
                        "to Thirds</br>" +
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

//   label.setLocation(56, 159); // x and y.

    private JPanel createUserTextFieldWithPlaceholder(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 271, 29);
        panel.setOpaque(false);

        JTextField textField = new JTextField("Password");
        textField.setBounds(0, 0, 271, 29);
        textField.setOpaque(false);
        textField.setBackground(new Color(0, 0, 0, 0));
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setHorizontalAlignment(SwingConstants.LEFT);

        textField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().equals("Password")) {
                    textField.setText("");
                    textField.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().trim().isEmpty()) {
                    textField.setText("Password");
                    textField.setForeground(Color.WHITE);
                }
            }
        });

        panel.add(textField);
        return panel;
    }

    private JPanel createPassTextFieldWithPlaceholder(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 271, 29);
        panel.setOpaque(false);

        JTextField textField = new JTextField("Username");
        textField.setBounds(0, 0, 271, 29);
        textField.setOpaque(false);
        textField.setBackground(new Color(0, 0, 0, 0));
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Kantumruy Pro", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setHorizontalAlignment(SwingConstants.LEFT);

        textField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().equals("Username")) {
                    textField.setText("");
                    textField.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().trim().isEmpty()) {
                    textField.setText("Username");
                    textField.setForeground(Color.WHITE);
                }
            }
        });

        panel.add(textField);
        return panel;
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
        g2d.fillRect(50, 183, 272, 2); // x, y, width, height

        // Draw the second line
        g2d.fillRect(50, 232, 272, 2); // x, y, width, height
    }


    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);

        frame.add(new LogInScreen());
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //
        Graphics2D graphics = background.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.drawImage(background, 0, 0, null);

        g.drawImage(background, 0, 0, getWidth(),getHeight(),null);

        drawOrangeLines(g);
        drawCloseIcon(g);
    }
}
