package thirds.scratch;

import thirds.swing.MoveableComponent;
import thirds.swing.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.concurrent.atomic.AtomicBoolean;

public class UITesting
{
    public static Point panelOffset;
    public static boolean isDragging;
    public static Point currentPanelPoint;
    public static void main(String[] args)
    {

        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0x8ace00));
        frame.add(panel);

        MoveableComponent blackBox = new MoveableComponent();
        blackBox.setLayout(null);
        blackBox.setSize(200, 200);
        blackBox.setLocation(200, 200);
        blackBox.setBackground(Color.black);

        blackBox.addMouseListener(new MouseAdapter()
        {
            AtomicBoolean bool = new AtomicBoolean(true);

            @Override
            public void mousePressed(MouseEvent e)
            {
                isDragging = true;
                blackBox.setMotion(e.getXOnScreen(), e.getYOnScreen(), System.currentTimeMillis());
                blackBox.hold();
            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(bool.get()) {
                    blackBox.scale(300, 300, 300, 300, 250);
                    bool.set(false);
                }
                else {
                    blackBox.scale(200, 200, 200, 200, 250);
                    bool.set(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                isDragging = false;
                blackBox.slipMotion();
            }
        });

        blackBox.addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                if (isDragging)
                {
                    // Calculate the offset based on initial position
                    int currentX = e.getXOnScreen();
                    int currentY = e.getYOnScreen();
                    long currentTime = System.currentTimeMillis();

                    blackBox.setMotion(currentX - blackBox.getCurrentX(),currentY - blackBox.getCurrentY(), blackBox.getLastTime());

                    blackBox.setLocation(blackBox.getX() + blackBox.getCurrentX(), blackBox.getY() + blackBox.getCurrentY());

                    // Calculate velocity based on change in position and time
                    long deltaTime = currentTime - blackBox.getLastTime();
                    if (deltaTime > 0) {
                        blackBox.velocityX = blackBox.getCurrentX() / (double) deltaTime * 10; // Scaling factor for effect
                        blackBox.velocityY = blackBox.getCurrentY() / (double) deltaTime * 10;
                    }

                    blackBox.setMotion(currentX, currentY, currentTime);

                }
            }


        });

        //panel.add(blackBox);

        JLabel brat = getjLabel();


        //panel.add(brat);

        ScrollPane pane = new ScrollPane();
        pane.setSize(500, 500);
        pane.setBackground(new Color(0, 0,0, 0));
        pane.setLocation(0, 0);
        pane.setContainerBackground(new Color(255, 255, 255, 255));
        pane.setContainerSize(1000, 2000);
        pane.setContainerLocation(0, 0);

        JLabel labe = new JLabel();
        labe.setLayout(null);
        labe.setLocation(0, 0);
        labe.setText("Hello World!");
        labe.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        labe.setForeground(Color.black);
        labe.setSize(200, 40);
        labe.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.out.println("Hello World has been clicked!");
            }
        });

        pane.glassPane.add(labe);

        for(int i = 0; i < 50; i++)
        {
            JLabel label = new JLabel();
            label.setText("this is a long label with a text number with " + i + " " + i + " " + i + " " + i + " " + i + " " + i + " " + i + " " + i + " " + i + " " + i + " " + i + " " + i);
            label.setLayout(null);
            label.setLocation(0, i * 40);
            label.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            label.setSize(1000, 40);
            label.setForeground(new Color(135, 135, 135));
            pane.addComponentToContainer(label);
        }

        panel.add(pane);
        // last
        frame.setVisible(true);
    }

    private static JLabel getjLabel()
    {
        // example sa label na atoang gamiton...
        JLabel brat = new JLabel();
        brat.setLayout(null);
        brat.setText("<html>" +
                         "<body style='line-height:1;'>" +
                              "<div style='text-align:justify;'>" +
                                  "brat and its the same but it has three more songs so its not" +
                              "</div>" +
                         "</body>" +
                     "</html>");
        brat.setSize(200, 200);
        brat.setFont(new Font("Comic Sans MS", Font.PLAIN, 27));
        brat.setOpaque(true);
        brat.setBackground(Color.white);
        brat.setLocation(50, 50);
        return brat;
    }
}