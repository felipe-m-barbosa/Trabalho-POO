package control;

import elements.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import utils.Consts;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class GameController {
    
    private Graphics tela;
    ImageIcon imageIcon;
    
    public GameController()
    {
        try
        {
            imageIcon = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + "bricks.png");
        }catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public void drawAllElements(ArrayList<Element> elemArray, Graphics g){
        this.tela = g;
        
        for(int i=0; i<elemArray.size(); i++){
            elemArray.get(i).autoDraw(g);
        }
    }
    public void processAllElements(ArrayList<Element> e){
        if(e.isEmpty())
            return;
        
        Pacman pacman = (Pacman)e.get(e.size()-1);
        if (!isValidPosition(e, pacman)) {
            pacman.backToLastPosition();
            pacman.setMovDirection(Pacman.STOP);
            return;
        }
        
        Element eTemp;
        Caminho powerPellet;
        
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if(pacman.overlap(eTemp))
            {
                if(eTemp.isTransposable())
                {
                    if (eTemp instanceof Caminho)
                    {
                        powerPellet = (Caminho)eTemp;
                        if (powerPellet.getHasPowerPellet())
                        {
                            powerPellet.setHasPowerPellet(false);
                            ((Caminho) eTemp).imageIcon = this.imageIcon;
                            eTemp.autoDraw(tela);
                            /*try {
                                newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "bricks.png");
                                tela.drawImage(newImage, (int)powerPellet.pos.getY() * Consts.CELL_SIZE, (int)powerPellet.pos.getX() * Consts.CELL_SIZE, Consts.CELL_SIZE, Consts.CELL_SIZE, null);
                            } catch (IOException ex) {
                                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                            }*/
                            
                            
                        }
                    }
                }
            }
        }
        
        pacman.move();
    }
    
    //Testa validade da posição. Usado para testar colisões com paredes e fantasmas no modo normal.
    public boolean isValidPosition(ArrayList<Element> elemArray, Element elem){
        Element elemAux;
        for(int i = 1; i < elemArray.size(); i++){
            elemAux = elemArray.get(i);            
            if(!elemAux.isTransposable())
                if(elemAux.overlap(elem))
                    return false;
        }        
        return true;
    }
}
