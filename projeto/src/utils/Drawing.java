package utils;

import control.Stage;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class Drawing {
    static Stage screen;

    public static Stage getGameScreen() {
        return screen;
    }

    public static void setGameScreen(Stage newScreen) {
        screen = newScreen;
    }    
    
    public static void draw(Graphics g, ImageIcon imageIcon, double y, double x) {
        imageIcon.paintIcon(screen, g, (int)Math.round(y * Consts.CELL_SIZE),(int)Math.round(x * Consts.CELL_SIZE));
    }
}
