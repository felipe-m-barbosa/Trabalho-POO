package control;

import elements.*;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import utils.Consts;
import utils.Som;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class GameController {
    
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
    
    public void drawDynamicElements(ArrayList<Element> elemArray, Graphics g){
        
        for(int i=0; i<elemArray.size(); i++){
            if (elemArray.get(i) instanceof Pacman || elemArray.get(i) instanceof Fantasma){
                elemArray.get(i).autoDraw(g);
            }
        }
    }
    
    public void processAllElements(ArrayList<Element> e, Pacman pacman, Fantasma vermelho, Fantasma rosa, Fantasma ciano, Fantasma laranja, Som som){
        
        if(e.isEmpty()){
            return;
        }
        
        //variavel temporaria de Element para fazer os testes
        Element eTemp;
        int i;
        
        //Pegando os fantasmas
        for(i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if(eTemp instanceof Fantasma){
                break;
            }
        }
        
        if (!isValidPosition(e, pacman)) {
            pacman.backToLastPosition();
            pacman.setMovDirection(Pacman.STOP);
            som.parar();
            return;
        }
        
        
        if (!isValidPosition(e, vermelho)) {
            vermelho.backToLastPosition();
            vermelho.setMovDirection(Fantasma.STOP);
            return;
        }
        /*
        if (!isValidPosition(e, rosa)) {
            rosa.backToLastPosition();
            rosa.setMovDirection(Fantasma.STOP);
            return;
        }
        if (!isValidPosition(e, ciano)) {
            ciano.backToLastPosition();
            ciano.setMovDirection(Fantasma.STOP);
            return;
        }
        if (!isValidPosition(e, laranja)) {
            laranja.backToLastPosition();
            laranja.setMovDirection(Fantasma.STOP);
            return;
        }
        */
        
        
        
        BackgroundElement pacDot;  //acho que é interessante mudar o nome dessa variavel
        
        for(i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if(pacman.overlap(eTemp)){
                if(eTemp.isTransposable()){
                    if (eTemp instanceof BackgroundElement){
                        if (((BackgroundElement)eTemp).getTipo().equals("caminho")){
                            pacDot = (BackgroundElement)eTemp;
                            if (pacDot.getTemPacDot()){
                                pacDot.setTemPacDot(false);
                                pacDot.setNomeImagem("caminho_vinho.png");
                                //pacman ganha pontos
                                pacman.pontuacao = pacman.pontuacao + 10; 
                                //Trocou a imagem. Logo, o pacman comeu a pacdot e diminui o número de pacdots.
                                Stage.numPacDots--;
                            }
                        }
                    }
                    else{
                        if(eTemp instanceof Fantasma){
                            //Gasta um vida
                            pacman.vidas = pacman.vidas - 1;
                            pacman.setPosition(3, 1);
                        }
                    }
                }
            }
        }
        
        pacman.move();
        vermelho.moveFantasma();
        //Aqui entra os move dos fantasma !!!!?????
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
