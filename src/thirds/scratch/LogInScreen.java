package thirds.scratch;

import thirds.application.MapApplication;
import thirds.application.SignUpMenu;
import thirds.swing.MoveableComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


////Testing for branch commit java documentation
public class LogInScreen
{
    public static MoveableComponent panel;
    
    static
    {
        initializeComponent();

        JLabel welcomeText = createWelcomeText();
        panel.add(welcomeText);

        JLabel logInText = createLogInText();
        JLabel SignUpText = createSignUpText();
        panel.add(logInText);
        panel.add(SignUpText);
        panel.add(createUserTextFieldWithPlaceholder(50, 154));
        panel.add(createPassTextFieldWithPlaceholder(50, 204));

    }

    private static JLabel createLogInText()
    {
        JLabel label = new JLabel();
        label.setText("Log in");

        label.setFont(new Font("Ebrima", Font.PLAIN, 18)); // ing ani ang pag change sa font guys
        label.setForeground(Color.WHITE);

        FontMetrics fm = label.getFontMetrics(label.getFont()); // need ug font metrics para ma base ang text sa text
        int width = fm.stringWidth(label.getText()) + label.getText().length(); // width sa text niya.
        int height = fm.getHeight();

        for(char s : label.getText().toCharArray())
            if(s == '\n')
                height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

        label.setSize(width, height); // width and height.
        label.setLocation(67, 270); // x and y.

        label.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                panel.move(-500, 0, 250);
                LoggedInScreen.getPanel().move(0, 0, 250);
            }
        });
        return label;
    }

    private static JLabel createSignUpText()
    {
        JLabel label = new JLabel();
        label.setText("Sign Up");

        label.setFont(new Font("Ebrima", Font.PLAIN, 18)); // ing ani ang pag change sa font guys
        label.setForeground(Color.ORANGE);

        FontMetrics fm = label.getFontMetrics(label.getFont()); // need ug font metrics para ma base ang text sa text
        int width = fm.stringWidth(label.getText()) + label.getText().length(); // width sa text niya.
        int height = fm.getHeight();

        for(char s : label.getText().toCharArray())
            if(s == '\n')
                height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

        label.setSize(width, height); // width and height.
        label.setLocation(151, 270); // x and y.

        label.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                panel.move(-500, 0, 250);
                SignUpScreen.panel.move(0, 0, 250);
            }
        });
        return label;
    }

    private static JLabel createWelcomeText()
    {
        JLabel label = new JLabel();
        label.setText("<html>" +
                        "Welcome\n<br> " +
                        "to Thirds</br>" +
                     "</html>");

        label.setForeground(Color.WHITE);
        label.setFont(new Font("Ebrima", Font.BOLD, 35)); // ing ani ang pag change sa font guys

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

    private static JPanel createUserTextFieldWithPlaceholder(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 271, 29);
        panel.setOpaque(false);

        JTextField textField = new JTextField("Password");
        textField.setBounds(0, 0, 271, 29);
        textField.setOpaque(false);
        textField.setBackground(new Color(0, 0, 0, 0));
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Ebrima", Font.PLAIN, 18));
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

    private static JPanel createPassTextFieldWithPlaceholder(int x, int y) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, 271, 29);
        panel.setOpaque(false);

        JTextField textField = new JTextField("Username");
        textField.setBounds(0, 0, 271, 29);
        textField.setOpaque(false);
        textField.setBackground(new Color(0, 0, 0, 0));
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Ebrima", Font.PLAIN, 18));
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

    private static void drawCloseIcon(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(3)); // added stroke guys
        g2d.setColor(Color.ORANGE);
        int padding = 6;
        int x1 = 450 + padding;
        int y1 = 20 + padding;
        int x2 = 450 + 30 - padding;
        int y2 = 20 + 30 - padding;

        g2d.drawLine(x1, y1, x2, y2); // Top-left to bottom-right
        g2d.drawLine(x1, y2, x2, y1); // Bottom-left to top-right
    }

    private static void addCloseIconListener() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int padding = 6;
                int iconX = 450 + padding;
                int iconY = 20 + padding;
                int iconSize = 30 - 2 * padding;

                Rectangle closeIconBounds = new Rectangle(iconX, iconY, iconSize, iconSize);

                // if this points to the exit
                if (closeIconBounds.contains(e.getPoint())) {
                    SignUpMenu.close();
                }
            }
        });
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
        panel.setSize(500, 350);
        panel.setVisible(true);
    }

    private static void drawOrangeLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(255, 165, 0));
        g2d.fillRect(50, 183, 272, 2);
        g2d.fillRect(50, 232, 272, 2);
    }


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
}
