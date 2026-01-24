


import Controller.MainController;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;



public class Main {

    public static void main(String[] args)  {

        FlatLightLaf.setup();
        UIManager.put("Button.arc", 12);
        UIManager.put("Component.arc", 12);
        UIManager.put("ProgressBar.arc", 12);
        UIManager.put("TextComponent.arc", 10);
        UIManager.put("Button.pressedBackground", new Color(210, 210, 210));
        UIManager.put("Component.focusWidth", 1);
        UIManager.put("Component.innerFocusWidth", 0);

        MainController.getInstance();


    }
}