package elements;

import java.awt.Graphics;
import utils.Drawing;

/**
 *
 * @author Felipe
 */
public class BackgroundElement extends Element{
    private String tipo;
    private boolean hasPowerPellet;
    private int i, j;

    public BackgroundElement(String tipo, String imageName) {
        super(imageName);
        this.tipo = tipo;
        if (tipo.equals("parede"))
            this.isTransposable = false;
        if (tipo.equals("caminho"))
            this.hasPowerPellet = true;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        if (this.tipo.equals("parede"))
            Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
        //Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
        /*if(!this.moveRight())
            Drawing.getGameScreen().removeElement(this);*/
    }
}
