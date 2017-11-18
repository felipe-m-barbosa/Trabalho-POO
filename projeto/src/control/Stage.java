package control;

import elements.*;
import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import utils.Som;

/**
 * Projeto de POO 2017
 * 
 * @author Felipe
 * Baseado em material do Prof. Luiz Eduardo
 */
public class Stage extends javax.swing.JFrame implements KeyListener {
    public Som som;
    public ImageIcon imageIcon;
    private Pacman pacman;
    private CabecalhoSalvamento cabecalho;
    private final ArrayList<Element> elementosCenario;
    ArrayList<Element> paredes = null;
    ArrayList<Element> caminho = null;
    private final GameController controller = new GameController();
    private char mapa[][] = {
    {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
    {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
    {'|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'|', ' ', '|', '|', '|', '|', '|', '|', '|', ' ', ' ', '|', '|', '|', '|', '|', '|', '|', ' ', '|'},
    {'|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', ' ', ' ', ' ', '|', '|', ' ', ' ', '|', '|', ' ', ' ', ' ', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', ' ', ' ', ' ', '|', '|', '|', '|', '|', '|', ' ', ' ', ' ', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|'},
    {'|', ' ', '|', '|', '|', '|', '|', '|', '|', ' ', ' ', '|', '|', '|', '|', '|', '|', '|', ' ', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|', '|'},
    };

    public Stage() 
    {        
        som = new Som();
        som.tocar();
        Drawing.setGameScreen(this);
        initComponents();
        
        this.addKeyListener(this);   /*teclado*/
        
        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().left + getInsets().right,
                     Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().top + getInsets().bottom);

        elementosCenario = new ArrayList<Element>();
        
        //Verifica se há algum progresso salvo
        File file = new File("ProgressoPacman.ser");
        if (file.exists())
        {
            carregarProgresso();
        }
        else
        {
            novoJogo();
        }
    }
    
    public final void addElement(Element elem) {
        elementosCenario.add(elem);
    }
    
    public void removeElement(Element elem) {
        elementosCenario.remove(elem);
    }
    
    @Override
    public void paint(Graphics gOld) {
        
        Graphics g = getBufferStrategy().getDrawGraphics();
        
        /*Criamos um contexto grafico*/
        Graphics g2 = g.create(getInsets().right, getInsets().top, getWidth() - getInsets().left, getHeight() - getInsets().bottom);
        
        /* DESENHA CENARIO
           Trocar essa parte por uma estrutura mais bem organizada
           Utilizando a classe Stage
        */
        for (int i = 0; i < Consts.NUM_CELLS; i++) {
            for (int j = 0; j < Consts.NUM_CELLS; j++) {
                try {
                    if(mapa[i][j] == ' ')
                    {
                        BackgroundElement caminhoaux = null;
                        Image newImage;
                        for (int k = 0; k < elementosCenario.size(); k++)
                        {
                            if (elementosCenario.get(k) instanceof BackgroundElement)
                            {
                                if (((BackgroundElement)elementosCenario.get(k)).getTipo().equals("caminho"))
                                {
                                    caminhoaux = (BackgroundElement)elementosCenario.get(k);
                                    if(caminhoaux.getI() == i && caminhoaux.getJ() == j)
                                    {
                                        if(caminhoaux.getHasPowerPellet())
                                        {
                                            newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "powerpellet.png");
                                        }
                                        else
                                        {
                                            newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "bricks.png");
                                        }
                                        g2.drawImage(newImage, j * Consts.CELL_SIZE, i * Consts.CELL_SIZE, Consts.CELL_SIZE, Consts.CELL_SIZE, null);  
                                        break;
                                    }
                                }
                            }
                        }
                    }                   
                } catch (IOException ex) {
                    Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        this.controller.drawAllElements(elementosCenario, g2);
        this.controller.processAllElements(elementosCenario, pacman, som);
        this.setTitle("Posição atual: " + pacman.getStringPosition());
        
        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }
    
    public void go() {
        TimerTask task = new TimerTask() {
            
            public void run() {
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.DELAY);
    }
    
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            //Se estava parado, então reiniciar o som
            if(pacman.getMovDirection() == Pacman.STOP){
                som.tocar();
            }
            pacman.setMovDirection(Pacman.MOVE_UP);
            pacman.novaImagem("pacman_cima.png");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(pacman.getMovDirection() == Pacman.STOP){
                som.tocar();
            }
            pacman.setMovDirection(Pacman.MOVE_DOWN);
            pacman.novaImagem("pacman_baixo.png");
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(pacman.getMovDirection() == Pacman.STOP){
                som.tocar();
            }
            pacman.setMovDirection(Pacman.MOVE_LEFT);
            pacman.novaImagem("pacman_esquerda.png");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(pacman.getMovDirection() == Pacman.STOP){
                som.tocar();
            }
            pacman.setMovDirection(Pacman.MOVE_RIGHT);
            pacman.novaImagem("pacman_direita.png");
        } else if (e.getKeyCode() == KeyEvent.VK_S)
        {
            salvarJogo();
        }
        /*else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            pacman.setMovDirection(Pacman.STOP);
        }*/
        
        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }
    
    private void salvarJogo()
    {
        //1 - Crie um objeto FileOutputStream
        FileOutputStream fileStream;
        ObjectOutputStream os;
        try {
            fileStream = new FileOutputStream("ProgressoPacman.ser");
            try {
                //2 - Crie um ObjectOutputStream
                os = new ObjectOutputStream(fileStream);
                //3 - Grave os objetos
                for (int i = 0; i < elementosCenario.size(); i++)
                {
                    try {
                        os.writeObject(elementosCenario.get(i)); //Serializa o objeto referenciado por user e grava no arquivo myGame.ser
                    } catch (IOException ex) {
                        Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    //4 - Feche ObjectOutputStream
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void carregarProgresso()
    {
        //1 - Crie um objeto FileInputStream
        FileInputStream fileStream;
        try {
            fileStream = new FileInputStream("ProgressoPacman.ser");
            //2 - Crie um ObjectInputStream
            ObjectInputStream os;
            try {
                os = new ObjectInputStream(fileStream);

                Object obj;
                try {
                    //3 - Leia os objetos
                    obj = os.readObject();
                    this.addElement((CabecalhoSalvamento)obj);

                    for (int i =0; i <= 360; i++)
                    {
                        obj = os.readObject();
                        //4 - Converta os objetos
                        if (i < ((CabecalhoSalvamento)elementosCenario.get(0)).getNumParedes())
                            this.addElement((BackgroundElement) obj);
                        else if ((i >= ((CabecalhoSalvamento)elementosCenario.get(0)).getNumParedes()) && (i < (((CabecalhoSalvamento)elementosCenario.get(0)).getNumParedes() + ((CabecalhoSalvamento)elementosCenario.get(0)).getNumCaminhos())))
                            this.addElement((BackgroundElement) obj);
                        else
                        {
                            this.addElement((Pacman) obj);
                            pacman = (Pacman) obj;
                            pacman.setMovDirection(Pacman.STOP); //Para o jogo não carregar com o pacman em movimentos
                        }
                    }

                    //5 - Feche ObjectInputStream
                    os.close();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void novoJogo()
    {
        paredes = new ArrayList<Element>();
        caminho = new ArrayList<Element>();

        /*Cria e adiciona os elementos do cenario*/
        for (int i = 0; i < Consts.NUM_CELLS; i++) {
            for (int j = 0; j < Consts.NUM_CELLS; j++) {
                if(mapa[i][j] == '|')
                {
                    BackgroundElement parede = new BackgroundElement("parede","coracao.png");
                    parede.setPosition(i, j);
                    paredes.add(parede);
                }
                else if(mapa[i][j] == ' ')
                {
                    BackgroundElement caminhoLivre = new BackgroundElement("caminho","powerpellet.png");
                    caminhoLivre.setPosition(i, j);
                    caminhoLivre.setI(i);
                    caminhoLivre.setJ(j);
                    caminho.add(caminhoLivre);
                }
            }
        } 
        cabecalho = new CabecalhoSalvamento(paredes.size(), caminho.size());
        this.addElement(cabecalho);
        for (int i = 0; i < paredes.size(); i++)
        {
            this.addElement(paredes.get(i));
        }

        for (int i = 0; i < caminho.size(); i++)
        {
            this.addElement(caminho.get(i));
        }

        pacman = new Pacman("pacman_frente.png");
        pacman.setPosition(3, 1);
        this.addElement(pacman);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0604 - Pacman");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.black);
        setLocation(new java.awt.Point(20, 20));
        setMaximumSize(new java.awt.Dimension(214765432, 214765432));
        setResizable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(435, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}