package View;

import IO.DataWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Clock;
import java.time.LocalDate;

public class ImageFileChooser extends JFileChooser {
    private String path;
    BaseFrame frame;
    public ImageFileChooser(BaseFrame frame){
        this.frame = frame;
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif" ,"png");
        this.setFileFilter(filter);
    }

    public String getPath(){
        int returnVal = this.showOpenDialog(frame);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                Path imgpath = this.getSelectedFile().toPath();

                BufferedImage image = ImageIO.read(new File(imgpath.toString()));
                int count =0;
                String finalPath = "./assets/"+imgpath.getFileName() ;
                ImageIO.write(image , "png"  ,new File(finalPath));
                return finalPath;
            } catch (IOException e) {
                DataWriter.writeErrors(e);
            }
        }
     return null;
    }
}
