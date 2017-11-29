package control;

import java.awt.Color;
import utils.Consts;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //instanciando a nossa tela de menu
                //TelaInicial screenMenu = new TelaInicial();
                //mudando a cor do fundo da nossa tela de menu
                Consts.screenMenu.getContentPane().setBackground(Color.BLACK);
                //tornando a nossa tela de menu visivel
                Consts.screenMenu.setVisible(true);
                //Deixa o tamanho da tela fixo
                Consts.screenMenu.setResizable(false);
                Consts.screenMenu.createBufferStrategy(2);
                //Stage screen = new Stage();
                //screen.setVisible(true);
                //screen.createBufferStrategy(2);
                //screen.go();
            }
        });
    }
}

