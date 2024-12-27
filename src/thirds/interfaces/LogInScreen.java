package thirds.interfaces;

import thirds.application.SignUpMenu;
import thirds.swing.MoveableComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <h1>LogInScreen</h1>
 * This class defines the Login Screen interface with interactive elements such as
 * user input fields, labels for login and sign-up options, and a custom-designed
 * moveable component for the screen. It also handles user interactions for
 * navigating between the login and sign-up screens.
 */
public class LogInScreen {

    public static MoveableComponent panel;

    static {
        initializeComponent();

        // Adding text and input fields to the panel
        JLabel welcomeText = createWelcomeText();
        panel.add(welcomeText);

        JLabel logInText = createLogInText();
        JLabel signUpText = createSignUpText();
        panel.add(logInText);
        panel.add(signUpText);
        panel.add(createUserTextFieldWithPlaceholder(50, 154));
        panel.add(createPassTextFieldWithPlaceholder(50, 204));

    }

    private static JLabel createLogInText() {
        /**
         * Creates the "Log In" label with specific font and position.
         * The label has a mouse click listener to transition to the logged-in screen.
         *
         * @return JLabel - The "Log In" label.
         */
        JLabel label = new JLabel();
        label.setText("Log in");
        label.setFont(new Font("Ebrima", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);

        FontMetrics fm = label.getFontMetrics(label.getFont());
        int width = fm.stringWidth(label.getText()) + label.getText().length();
        int height = fm.getHeight();

        for (char s : label.getText().toCharArray()) {
            if (s == '\n') height += fm.getHeight() + 1;
        }

        label.setSize(width, height);
        label.setLocation(67, 270);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Transition to LoggedInScreen
                panel.move(-500, 0, 250);
                LoggedInScreen.getPanel().move(0, 0, 250);
            }
        });
        return label;
    }

    private static JLabel createSignUpText() {
        /**
         * Creates the "Sign Up" label with specific font and position.
         * The label has a mouse click listener to transition to the SignUpScreen.
         *
         * @return JLabel - The "Sign Up" label.
         */
        JLabel label = new JLabel();
        label.setText("Sign Up");
        label.setFont(new Font("Ebrima", Font.PLAIN, 18));
        label.setForeground(Color.ORANGE);

        FontMetrics fm = label.getFontMetrics(label.getFont());
        int width = fm.stringWidth(label.getText()) + label.getText().length();
        int height = fm.getHeight();

        for (char s : label.getText().toCharArray()) {
            if (s == '\n') height += fm.getHeight() + 1;
        }

        label.setSize(width, height);
        label.setLocation(151, 270);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Transition to SignUpScreen
                panel.move(-500, 0, 250);
                SignUpScreen.panel.move(0, 0, 250);
            }
        });
        return label;
    }

    private static JLabel createWelcomeText() {
        /**
         * Creates the welcome message label with HTML content for multiple lines.
         *
         * @return JLabel - The welcome text label.
         */
        JLabel label = new JLabel();
        label.setText("<html>" +
                "Welcome\n<br> " +
                "to Thirds</br>" +
                "</html>");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Ebrima", Font.BOLD, 35));

        FontMetrics fm = label.getFontMetrics(label.getFont());
        int width = fm.stringWidth(label.getText()) + label.getText().length();
        int height = fm.getHeight();

        for (char s : label.getText().toCharArray()) {
            if (s == '\n') height += fm.getHeight() + 1;
        }

        label.setSize(width, height);
        label.setLocation(50, 50);
        return label;
    }

    private static JPanel createUserTextFieldWithPlaceholder(int x, int y) {
        /**
         * Creates a user text field with a placeholder "Password".
         *
         * @param x X-coordinate for position.
         * @param y Y-coordinate for position.
         * @return JPanel - A panel containing the user input field.
         */
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
        /**
         * Creates a password text field with a placeholder "Username".
         *
         * @param x X-coordinate for position.
         * @param y Y-coordinate for position.
         * @return JPanel - A panel containing the password input field.
         */
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
        /**
         * Draws the close icon in the top-right corner of the screen.
         *
         * @param g The Graphics object used to draw the icon.
         */
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(3));
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
        /**
         * Adds a mouse listener to the close icon to allow window closing when clicked.
         */
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int padding = 6;
                int iconX = 450 + padding;
                int iconY = 20 + padding;
                int iconSize = 30 - 2 * padding;

                Rectangle closeIconBounds = new Rectangle(iconX, iconY, iconSize, iconSize);

                if (closeIconBounds.contains(e.getPoint())) {
                    SignUpMenu.close(); // Close the sign-up menu on click
                }
            }
        });
    }

    private static void initializeComponent() {
        /**
         * Initializes the moveable component and sets up the panel with its size and properties.
         */
        panel = new MoveableComponent() {
            @Override
            public void paintComponent(Graphics g) {
                drawOrangeLines(g); // Draw custom orange lines on the panel
            }
        };
        panel.setOpaque(false);
        panel.setSize(500, 350);
        panel.setVisible(true);
    }

    private static void drawOrangeLines(Graphics g) {
        /**
         * Draws two horizontal orange lines for visual design on the panel.
         *
         * @param g The Graphics object used to draw the lines.
         */
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(255, 165, 0));
        g2d.fillRect(50, 183, 272, 2); // First line
        g2d.fillRect(50, 232, 272, 2); // Second line
    }

    public static void main(String[] args) {
        /**
         * Main method to initialize the JFrame and display the Login screen.
         */
        JFrame frame = new JFrame();
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);

        frame.add(panel);
        frame.setVisible(true);
    }
}
