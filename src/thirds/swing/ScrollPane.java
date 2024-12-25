package thirds.swing;

import javax.swing.Timer;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public class ScrollPane extends MoveableComponent
{
    private MoveableComponent container;
    public MoveableComponent glassPane;
    private Timer timer;
    private int currentX;
    private int currentY;
    private long lastTime;
    public double velocityX;
    public double velocityY;
    public boolean isDragging;
    
    private boolean touchscreen;
    private boolean horizontalScroll = true;
    private boolean verticalScroll = true;

    public ScrollPane()
    {
        super();
        container = new MoveableComponent();
        container.setBackground(Color.WHITE);
        glassPane = new MoveableComponent();
        glassPane.setOpaque(false);
        add(glassPane);
        add(container);
        addMouseWheelListener(mwl);
        setTouchscreen(true);
    }
    
    private void setTouchscreen(boolean flag)
    {
        touchscreen = flag;
        addMouseListener(ml);
        addMouseMotionListener(mml);
    }

    private MouseMotionListener mml = new MouseMotionListener()
    {
        @Override
        public void mouseDragged(MouseEvent e)
        {
            if (isDragging)
            {
                // Calculate the new location based on the mouse drag
                int deltaX = e.getXOnScreen() - currentX;
                int deltaY = e.getYOnScreen() - currentY;

                int newX = container.getX() + deltaX;
                int newY = container.getY() + deltaY;

                // Update velocity for smoothness during drag
                long currentTime = System.currentTimeMillis();
                long deltaTime = currentTime - lastTime;
                if (deltaTime > 0)
                {
                    velocityX = deltaX / (double) deltaTime * 10;
                    velocityY = deltaY / (double) deltaTime * 10;
                }

                // Keep container within bounds
                newX = Math.min(0, Math.max(-container.getWidth() + getWidth(), newX));
                newY = Math.min(0, Math.max(-container.getHeight() + getHeight(), newY));

                // Update container position and record the new current position
                container.setLocation(newX, newY);
                setMotion(e.getXOnScreen(), e.getYOnScreen(), currentTime);
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {}
    };

    @Override
    public void setSize(int width, int height)
    {
        super.setSize(width, height);
        glassPane.setSize(width, height);
    }

    public long getLastTime()
    {
        return lastTime;
    }

    public int getCurrentY()
    {
        return currentY;
    }

    public int getCurrentX()
    {
        return currentX;
    }

    private MouseListener ml = new MouseListener()
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            repaint();
            System.out.println("click");
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            repaint();
            isDragging = true;
            setMotion(e.getXOnScreen(), e.getYOnScreen(), System.currentTimeMillis());
            hold();

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            isDragging = false;
            slipMotion();
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    };

    public synchronized void setMotion(int x, int y, long currentTime)
    {
        currentX = x;
        currentY = y;
        lastTime = currentTime;
    }

    public void hold()
    {
        if(timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    private MouseWheelListener mwl = e ->
    {
        // Current location of the container panel
        Point currentPoint = container.getLocation();

        // Apply sensitivity scaling for trackpad
        double scrollSensitivity = 20; // Adjust this value as needed

        int newX = (int) (currentPoint.x - e.getPreciseWheelRotation() * scrollSensitivity * (e.isShiftDown() ? 1 : 0));
        int minX = -container.getWidth() + container.getParent().getWidth();

        int newY = (int) (currentPoint.y - e.getPreciseWheelRotation() * scrollSensitivity * (e.isShiftDown() ? 0 : 1));
        int minY = -container.getHeight() + container.getParent().getHeight();

        // Constrain movement within bounds
        newX = Math.min(0, Math.max(minX, newX));
        newY = Math.min(0, Math.max(minY, newY));

        // Update container location
        newX = horizontalScroll? newX : container.getX();
        newY = verticalScroll? newY : container.getY();

        container.setLocation(newX, newY);
        container.repaint();
        repaint();
    };

    public synchronized void slipMotion()
    {
        final double deceleration = 0.95;

        timer = new Timer(10, e -> {
            // Calculate the next location with applied velocity
            int newX = container.getX() + (int) velocityX;
            int newY = container.getY() + (int) velocityY;

            // Keep container within bounds
            int minX = -container.getWidth() + getWidth();
            int minY = -container.getHeight() + getHeight();

            newX = Math.max(minX, Math.min(0, newX));
            newY = Math.max(minY, Math.min(0, newY));

            // Apply the decelerated position to the container
            container.setLocation(newX, newY);
            container.repaint();

            // Apply deceleration to velocity
            velocityX *= deceleration;
            velocityY *= deceleration;

            // Stop the timer if velocity drops below a threshold
            if (Math.abs(velocityX) < 0.5 && Math.abs(velocityY) < 0.5) {
                timer.stop();
                repaint();
            }
            repaint();
        });
        timer.start();
    }
    
    public void setContainerLocation(int x, int y)
    {
        container.setLocation(x, y);
    }

    public void setContainerPanel(MoveableComponent panel)
    {
        remove(container);
        container = panel;
        add(panel);
    }

    public void setContainerSize(int width, int height)
    {
        container.setSize(width, height);
    }

    public MoveableComponent getContainer()
    {
        return container;
    }

    public void addComponentToContainer(Component component)
    {
        container.add(component);
        container.revalidate();
        container.repaint();
        repaint();
    }

    public void removeComponentFromContainer(Component component)
    {
        container.remove(component);
        container.revalidate();
        container.repaint();
    }

    public void clearContainer()
    {
        container.removeAll();
        container.revalidate();
        container.repaint();
    }

    public void setContainerVisible(boolean visible)
    {
        container.setVisible(visible);
    }

    public boolean isContainerVisible()
    {
        return container.isVisible();
    }

    public void setContainerBackground(Color color)
    {
        container.setBackground(color);
    }

    public void setContainerBorder(Border border)
    {
        container.setBorder(border);
    }

    @Override
    public void repaint()
    {
        super.repaint();
        if(container != null)
            container.repaint();
        if(glassPane != null)
            glassPane.repaint();
    }

    public void setGlassPane(MoveableComponent comp)
    {
        removeAll();
        glassPane = comp;
        comp.setOpaque(false);
        add(container);
        add(glassPane);
    }

    public void setHorizontalScroll(boolean b)
    {
        horizontalScroll = b;
    }

    public void setVerticalScroll(boolean b)
    {
        verticalScroll = b;
    }
}
