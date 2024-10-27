package thirds;

import thirds.swing.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main
{
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

        Panel panel1 = new Panel();
        panel1.setLayout(null);
        panel1.setSize(200, 200);
        panel1.setLocation(200, 200);
        panel1.setBackground(Color.black);
        panel1.addMouseListener(new MouseAdapter()
        {
            AtomicBoolean bool = new AtomicBoolean(true);
            public void mouseClicked(MouseEvent e)
            {
                if(bool.get())
                {
                    panel1.scale(300, 300, 200, 200, 500);
                    bool.set(false);
                }
                else
                {
                    panel1.scale(200, 200, 200, 200, 200);
                    bool.set(true);
                }

            }
        });

        panel.add(panel1);
        frame.setVisible(true);

    }
}