package control;

import elements.*;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
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
    public void processAllElements(ArrayList<Element> e, Pacman pacman){
        if(e.isEmpty())
            return;
        
        if (!isValidPosition(e, pacman)) {
            pacman.backToLastPosition();
            pacman.setMovDirection(Pacman.STOP);
            return;
        }
        
        Element eTemp;
        BackgroundElement powerPellet;
        
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if(pacman.overlap(eTemp))
            {
                if(eTemp.isTransposable())
                {
                    if (eTemp instanceof BackgroundElement)
                    {
                        if (((BackgroundElement)eTemp).getTipo().equals("caminho"))
                        {
                            powerPellet = (BackgroundElement)eTemp;
                            if (powerPellet.getHasPowerPellet())
                            {
                                powerPellet.setHasPowerPellet(false);
                                ((BackgroundElement) eTemp).imageIcon = this.imageIcon;
                                eTemp.autoDraw(tela);
                            }
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
            if(!(elemArray.get(i) instanceof Pacman))
            {
                elemAux = elemArray.get(i);
                
                if(!elemAux.isTransposable())
                    if(elemAux.overlap(elem))
                        return false;
            }
        }        
        return true;
    }
}
