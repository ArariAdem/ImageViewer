package imageViewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

abstract class Frame implements MouseListener, KeyListener {
    
    private final JFrame frame;
    private final Dimension screenDimension;
    private BufferedImage image;
    
    Frame() {
        this.frame = new JFrame() {
            @Override
            public void paint(Graphics g) {
                try {
                    int x, y, w, h;
                    w = image.getWidth() > frame.getWidth() - 20
                        ? frame.getWidth() - 20
                                : image.getWidth();
                    h = image.getHeight()> frame.getHeight() - 20
                        ? frame.getHeight()- 20
                                : image.getHeight();

                    x = frame.getWidth() / 2 - w / 2;
                    y = frame.getHeight()/ 2 - h / 2;

                    g.drawImage(image, x, y, w, h, this);
                } catch(Exception ex) {
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
                    g.setColor(Color.RED);
                    g.drawString(ex.toString(), 100, 100);
                }
                    
            }
        };
        this.screenDimension = frame.getToolkit().getScreenSize();
        try {
            this.frame.setIconImage(
                    ImageIO.read(
                            this.getClass()
                                    .getResourceAsStream("/img/red-camera.png")));
        } catch (Exception ex) {  }
        this.frame.addMouseListener(this);
        this.frame.addKeyListener(this);
        this.frame.setUndecorated(true);
        this.frame.setLayout(null);
        this.frame.setBackground(new Color(100, 100, 100, 100));
    }
    
    void setVisible() {
        this.frame.setVisible(true);
    }
    
    void close() {
        int w = frame.getWidth();
        int h = frame.getHeight();
        int rw = Math.round((float)w / h);
        int rh = w / h;
        
        while (w > 0) {
            frame.setSize(w -= rw, h -= rh);
        }
        System.exit(0);
    }
    
    void setImage(BufferedImage bImage) {
        this.image = bImage;
        
        this.frame.setSize(screenDimension.width - 100,
                screenDimension.height - 100);
        this.frame.setLocation(
                (int)screenDimension.getWidth() / 2 - frame.getWidth() / 2,
                (int)screenDimension.getHeight()/ 2 - frame.getHeight() / 2);
        frame.setBackground(new Color(100, 100, 100, 100));
        frame.repaint();
    }

    void moveLeft() {
        int x = frame.getX();
        for (int i = x;i <= x + 30;i++) {
            frame.setLocation(i, frame.getY());
        }
        for (int i = x + 30;i >= x;i--) {
            frame.setLocation(i, frame.getY());
        }
        for (int i = x;i <= x + 30;i++) {
            frame.setLocation(i, frame.getY());
        }
        for (int i = x + 30;i >= x;i--) {
            frame.setLocation(i, frame.getY());
        }
    }

    void moveRight() {
        int x = frame.getX();
        for (int i = x;i >= x - 30;i--) {
            frame.setLocation(i, frame.getY());
        }
        for (int i = x - 30;i <= x;i++) {
            frame.setLocation(i, frame.getY());
        }
        for (int i = x;i >= x - 30;i--) {
            frame.setLocation(i, frame.getY());
        }
        for (int i = x - 30;i <= x;i++) {
            frame.setLocation(i, frame.getY());
        }
    }

    void setFocused(boolean b) {
        if (b) {
            frame.setBackground(new Color(100, 100, 100, 100));
        } else {
            frame.setBackground(new Color(70, 70, 70, 70));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
