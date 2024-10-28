package thirds;

import thirds.swing.MoveableComponent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main
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

        MoveableComponent panel1 = new MoveableComponent();
        panel1.setLayout(null);
        panel1.setSize(200, 200);
        panel1.setLocation(200, 200);
        panel1.setBackground(Color.black);

        panel1.addMouseListener(new MouseAdapter()
        {
            AtomicBoolean bool = new AtomicBoolean(true);

            @Override
            public void mousePressed(MouseEvent e)
            {
                isDragging = true;
                offset = e.getPoint();
                panel1.hold();
            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(bool.get()) {
                    panel1.scale(300, 300, 300, 300, 250);
                    bool.set(false);
                }
                else {
                    panel1.scale(200, 200, 200, 200, 250);
                    bool.set(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                isDragging = false;
            }
        });

        panel1.addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                if (isDragging)
                {
                    currentPanelPoint = panel1.getLocation();
                    panelOffset = panel1.getParent().getLocationOnScreen();
                    Point mouseLocation = e.getLocationOnScreen();
                    int newX = mouseLocation.x - panelOffset.x - offset.x;
                    int newY = mouseLocation.y - panelOffset.y - offset.y;

                    panel1.setLocation(newX, newY);
                    System.out.println(newX + " " + newY);

                }
            }


        });

        panel.add(panel1);
        frame.setVisible(true);

    }
}