package imageViewer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

class ImageMapper {
    
    static final Exception NO_NEXT_IMAGE,
            NO_PREVIOUS_IMAGE;
    private static final String[] EXTENSION;
    private static final ArrayList<BufferedImage> IMAGE_LIST;
    private static int imageIndex;
    
    static {
        IMAGE_LIST = new ArrayList<>();
        NO_NEXT_IMAGE = new Exception();
        NO_PREVIOUS_IMAGE = new Exception();
        EXTENSION = new String[] {
           "png",
           "jpg",
           "gif",
           "jpeg",
           "tif"
        };
    }
    
   static void setImageFile(File imageFile) {
        IMAGE_LIST.clear();
        imageIndex = -1;
        initImageList(imageFile);
    }
    
   static BufferedImage getNext() throws Exception {
       if (imageIndex == IMAGE_LIST.size() - 1) {
           throw NO_NEXT_IMAGE;
       }
        return IMAGE_LIST.get(++imageIndex);
    }
    
   static BufferedImage getprevious() throws Exception {
        if (imageIndex == 0) {
           throw NO_PREVIOUS_IMAGE;
        }
        return IMAGE_LIST.get(--imageIndex);
    }
    
    private static void initImageList(File imageFile) {
        IMAGE_LIST.add(toBuffer(imageFile));
        
        if (imageFile != null) {
            new Thread(() -> {
                for (File file : imageFile.getParentFile().listFiles()) {
                    if (file.equals(imageFile)) {
                        continue;
                    }
                    if (isImageFile(file)) {
                        IMAGE_LIST.add(toBuffer(file));
                    }
                }
            }).start();
        }
    }

    private static BufferedImage toBuffer(File imageFile) {
        try {
            return ImageIO.read(imageFile);
        } catch (Exception ex) {
            return null;
        }
    }

    private static boolean isImageFile(File file) {
        for (String ext : EXTENSION) {
            if (file.getPath().toLowerCase().endsWith("." + ext)) {
                return true;
            }
        }
        return false;
    }
    
}
