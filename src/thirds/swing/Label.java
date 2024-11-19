package thirds.swing;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontMetrics;

public class Label extends JLabel
{
    public Label()
    {
        super();
        setLayout(null);
    }

    public static Font font(String family, boolean bold, int size)
    {
        return new Font(family, bold? Font.BOLD: Font.PLAIN, size);
    }

    @Override
    public void setText(String text)
    {
        if (getWidth() == 0)
            return;

        FontMetrics fm = this.getFontMetrics(this.getFont());
        StringBuilder newText = new StringBuilder("<html>");
        int currentWidth = 0;
        int lineCount = 1; // Start with at least one line

        for (String str : text.split(" ")) {
            int wordWidth = fm.stringWidth(str + " ");

            // If adding this word exceeds the label width, wrap to a new line
            if (currentWidth + wordWidth > getWidth()) {
                newText.append("<br>"); // Add a newline
                lineCount++;          // Increase line count
                currentWidth = 0;     // Reset current width for the new line
            }

            // Add the word and update currentWidth
            newText.append(str).append(" ");
            currentWidth += wordWidth;
        }

        newText.append("</html>");

        // Set the wrapped text
        super.setText(newText.toString().trim());

        // Adjust the height of the label based on the line count and font height
        int lineHeight = fm.getHeight();
        setSize(getWidth(), lineHeight * (lineCount + 1));
    }

    @Override
    public void setFont(Font font)
    {
        super.setFont(font);
        setSize(getWidth(), font.getSize());
        setText(getText());
    }

    public void setWidth(int width)
    {
        setSize(width, getHeight());
        setText(getText());
    }
}
