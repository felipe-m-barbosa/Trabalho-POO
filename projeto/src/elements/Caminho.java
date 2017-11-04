package elements;

//import utils.Drawing;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class Caminho extends Element implements Serializable{
    private boolean hasPowerPellet;
    private int i, j;
    
    public Caminho(String imageName) {
        super(imageName);
        this.hasPowerPellet = true;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    public boolean getHasPowerPellet() {
        return hasPowerPellet;
    }

    public void setHasPowerPellet(boolean isPowerPellet) {
        this.hasPowerPellet = isPowerPellet;
    }
    
    @Override
    public void autoDraw(Graphics g) {
        //Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
        /*if(!this.moveRight())
            Drawing.getGameScreen().removeElement(this);*/
    }
    
}
