package thirds.scratch;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

/**
 * kani ang paghimo sa atoang mga files nato pag mag UI nata guys
 */
@SuppressWarnings("ALL")
public class Swing extends JFrame
{
    public Swing()
    {
        super(); // superclass

        initializeComponent(); // NAA JUD NI SIYA EVERYTIME. para ni sa jframe.

        JPanel panel = createPanel(); // dapat naa juy "create" sa method...

        add(panel); // ani lang pag add sa window... since ang frame kay ma add na siya

        JLabel label = createLabel(); // para sa mga text ni siya...

        panel.add(label); // dapat i add ang mga labels sa panel. mura siyag heirarchy...

        setVisible(true); // lastly, i add ni siya as maopen na ang window...
    }

    private JLabel createLabel()
    {
        JLabel label = new JLabel(); // new object
        label.setLayout(null); // always jud ni siya
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 20)); // ing ani ang pag change sa font guys
        label.setText("Data Structures Learning Evidence"); // mao ni ang text
        label.setForeground(Color.WHITE); // ing ani ang color sa font text.

        FontMetrics fm = label.getFontMetrics(label.getFont()); // need ug font metrics para ma base ang text sa text
        int width = fm.stringWidth(label.getText()) + label.getText().length(); // width sa text niya.
        int height = fm.getHeight();

        for(char s : label.getText().toCharArray())
            if(s == '\n')
                height += fm.getHeight() + 1; // para ma height lang siya based sa amount of lines

        // like ing ani:

        /*

        hello  }
        world! }--- ang height sa ani na text sa label
        123    }

         */

        label.setBounds(200, 200, // x and y
                width, height); // width and height.
        return label;
    }

    private JPanel createPanel() // ing ani ang structure
    {
        JPanel panel = new JPanel(); // new object
        panel.setLayout(null); // include jud ni siya guys.
        panel.setBackground(new Color(102, 237, 24)); // optional. red, green, blue, alpha na siya.
        panel.setSize(1280, 720); // size
        panel.setVisible(true);
        return panel; // mao ang panel na ireturn.
    }

    private void initializeComponent()
    {
        setSize(1280, 720); // width and height sa window.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // dapat maexit ang application pag iclose nimo and window
        setLocationRelativeTo(null); // para sa tunga mabutang ang window pag maopen siya
        setUndecorated(false); // kani, para wala siyay borders, kato gung - [] X
                                // na window gud kung true, pero false man siya so dili sa..
        setBackground(new Color(0, 0, 0)); // optional ra ni... para sa background color

    }

    public static void main(String... args)
    {
        new Swing(); // ani ra pag main method;
    }
}
