package main;

import imageViewer.ImageViewer;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        ImageViewer iViewer;
        if (args.length > 0) {
            File imageFile = new File(args[0]);
            iViewer = new ImageViewer(imageFile);
        } else {
            iViewer = new ImageViewer(null);
        }
        iViewer.start();
    }
}
