package thirds;

import thirds.swing.MoveableComponent;

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
    public static Point offset;
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
                offset = e.getPoint();
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

//                    currentPanelPoint = blackBox.getLocation();
//                    panelOffset = blackBox.getParent().getLocationOnScreen();
//                    Point mouseLocation = e.getLocationOnScreen();
//                    int newX = mouseLocation.x - panelOffset.x - offset.x;
//                    int newY = mouseLocation.y - panelOffset.y - offset.y;
//
//                    blackBox.setLocation(newX, newY);
//                    System.out.println(newX + " " + newY);

                }
            }


        });

        panel.add(blackBox);

        JLabel brat = getjLabel();


        //panel.add(brat);

        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(null);
        scrollPanel.setBackground(Color.white);
        scrollPanel.setLocation(0, 0);
        scrollPanel.setSize(200, 200);

        JPanel scroller = new JPanel();
        scroller.setLayout(null);
        scroller.setLocation(0, 0);
        scroller.setSize(200, 2000);

        for(int i = 0; i < 50; i++)
        {
            JLabel label = new JLabel();
            label.setText("text #" + i);
            label.setLayout(null);
            label.setLocation(0, i * 40);
            label.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            label.setSize(200, 40);
            label.setForeground(Color.black);
            scroller.add(label);
        }

        scrollPanel.add(scroller);

        panel.add(scrollPanel);

        scrollPanel.addMouseWheelListener(e ->
        {
            Point currentPoint = scroller.getLocation();
            double wheelRotation = e.getPreciseWheelRotation();
            int newY = (int) (currentPoint.y - (wheelRotation * 10));

            int minY = -scroller.getHeight() + scroller.getParent().getHeight();
            int maxY = 0;

            newY = Math.min(maxY, Math.max(minY, newY));

            scroller.setLocation(currentPoint.x, newY);
        });

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