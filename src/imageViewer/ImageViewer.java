package imageViewer;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

public class ImageViewer extends Frame {

    
    public ImageViewer(File imageFile) {
        ImageMapper.setImageFile(imageFile);
        setNext();
    }

    public void start() {
        super.setVisible();
    }

    private void setNext() {
        try {
            super.setImage(ImageMapper.getNext());
        } catch (Exception ex) {
            if (ex.equals(ImageMapper.NO_NEXT_IMAGE)) {
                super.moveLeft();
            }
        }
    }

    private void setPrevious() {
        try {
            super.setImage(ImageMapper.getprevious());
        } catch (Exception ex) {
            if (ex.equals(ImageMapper.NO_PREVIOUS_IMAGE)) {
                super.moveRight();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON3 :
        {
            setNext();
        }
                break;
            case MouseEvent.BUTTON1 :
                setPrevious();
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.setFocused(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.setFocused(false);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT :
                setNext();
                break;
            case KeyEvent.VK_LEFT :
                setPrevious();
                break;
            case KeyEvent.VK_ESCAPE :
                super.close();
//                System.exit(0);
                break;
        }
    }
}
