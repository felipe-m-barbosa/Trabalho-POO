package elements;

import utils.Consts;
import utils.Position;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * Projeto de POO 2017
 * 
 * @author Felipe
 * Baseado em material do Prof. Luiz Eduardo
 */
public abstract class Element implements Serializable{
    private String nomeImagem;
    public ImageIcon imageIcon;
    public Position pos;
    protected boolean isTransposable; // Pode passar por cima?
    protected boolean isMortal;       // Se encostar, morre?

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    //Construtor, define as caracteristicas iniciais do elemento
    protected Element(String imageName) {
        this.pos = new Position(1, 1);
        this.isTransposable = true;
        this.isMortal = false;
        
        this.setNomeImagem(imageName);
        
        if (this instanceof Pacman || this instanceof Fantasma)
        {
            try {
                imageIcon = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + imageName);
                Image img = imageIcon.getImage();
                BufferedImage bi = new BufferedImage(Consts.CELL_SIZE, Consts.CELL_SIZE, BufferedImage.TYPE_INT_ARGB);
                Graphics g = bi.createGraphics();
                g.drawImage(img, 0, 0, Consts.CELL_SIZE, Consts.CELL_SIZE, null);
                imageIcon = new ImageIcon(bi);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    //Possivel construtor para outros tipos de elemntos ???
    public Element()
    {
        
    }
    
    //Verifica se existem elementos sobrepostos ??
    //Ou seja, se esta "encostando" no elemento recebido como parametro
    public boolean overlap(Element elem) {
        double tolerancia;
        double xDist = Math.abs(elem.pos.getX() - this.pos.getX());
        double yDist = Math.abs(elem.pos.getY() - this.pos.getY());
        
        if (!(elem instanceof Pacman) || ((this instanceof BackgroundElement) && ((BackgroundElement)this).getTipo().equals("caminho")))
        {
            tolerancia = 0.7;
        }
        else
        {
            tolerancia = 0.87;
        }
        
        if (elem instanceof Fantasma)
        {
            tolerancia = 1;
        }
        
        if (xDist < tolerancia && yDist < tolerancia)
                return true;
            else
                return false;
    }

    public String getStringPosition() {
        return ("(" + pos.getX() + ", " + pos.getY() + ")");
    }
    
    public boolean setPosition(double x, double y) {
        return pos.setPosition(x, y);
    }

    public boolean isTransposable() {
        return isTransposable;
    }

    public void setTransposable(boolean isTransposable) {
        this.isTransposable = isTransposable;
    }

    abstract public void autoDraw(Graphics g);

    public boolean moveUp() {
        return this.pos.moveUp();
    }

    public boolean moveDown() {
        return this.pos.moveDown();
    }

    public boolean moveRight() {
        return this.pos.moveRight();
    }

    public boolean moveLeft() {
        return this.pos.moveLeft();
    }
}
