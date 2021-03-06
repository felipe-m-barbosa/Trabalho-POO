package elements;

//import java.awt.Color;
import utils.Drawing;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import utils.Consts;

/**
 * Projeto de POO 2017
 * 
 * @author Felipe
 * Baseado em material do Prof. Luiz Eduardo
 */
public class Fantasma extends Element  implements Serializable{
    
    public boolean testeNois = false;
    public static final int STOP = 0;
    public static final int MOVE_LEFT = 1;
    public static final int MOVE_RIGHT = 2;
    public static final int MOVE_UP = 3;
    public static final int MOVE_DOWN = 4;
    
    private int movDirection = STOP;
    
    //Construtor de Pacman que usa o construtor de Element mas o que diz respeito ao pacman
    public Fantasma(String imageName) {
        super(imageName);
        this.isMortal = true;
        this.isTransposable = true;
    }
    
    @Override
    public void autoDraw(Graphics g){
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }

    public void backToLastPosition(){
        this.pos.comeBack();
    }
    
    public void setMovDirection(int direction) {
        movDirection = direction;
    }

    public int getMovDirection() {
        return movDirection;
    }
    
    public void move() {
        switch (movDirection) {
            case MOVE_LEFT:
                this.moveLeft();
                break;
            case MOVE_RIGHT:
                this.moveRight();
                break;
            case MOVE_UP:
                this.moveUp();
                break;
            case MOVE_DOWN:
                this.moveDown();
                break;
        }
    }
    
    public void go() {
        TimerTask task = new TimerTask() {
            
            public void run() {
                
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.DELAY);
    }
    
    public void trocaImagem()
    {
        if(testeNois)
        {
            this.novaImagem("pacman_direita.png");
        }
        else
        {
            this.novaImagem("pacman_esquerda.png");
        }
    }

    //Método usado na atualização da imagem do personagem de acordo com a direção de seu movimento
    public void novaImagem(String nomeImagem) {
        try {
            ImageIcon imageIcon = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + nomeImagem);
            Image img = imageIcon.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIZE, Consts.CELL_SIZE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIZE, Consts.CELL_SIZE, null);
            this.imageIcon = new ImageIcon(bi);
        } catch (IOException ex) {
            Logger.getLogger(Fantasma.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
