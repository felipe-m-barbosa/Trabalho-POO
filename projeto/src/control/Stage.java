package control;

import elements.*;
import java.awt.Color;
import java.awt.Font;
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
 * @author Felipe, João
 * Baseado em material do Prof. Luiz Eduardo
 */
public class Stage extends javax.swing.JFrame implements KeyListener {
    
    public long tempo = 0;
    public static long tempoInicio;
    public static Graphics g;
    public static Graphics g2;
    public int fase = 0;
    public static int numPacDots = 0;
    public Som som;
    public ImageIcon imageIcon;
    private Pacman pacman;
    private Fantasma vermelho;
    private Fantasma ciano;
    private Fantasma rosa;
    private Fantasma laranja;
    private CabecalhoSalvamento cabecalho;
    private final ArrayList<Element> elementosCenario;
    ArrayList<Element> paredes = null;
    ArrayList<Element> caminho = null;
    private final GameController controller = new GameController();
    private final char mapa[][][] ={{
    {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
    {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
    {'^', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '^', '^', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '^', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'|', ' ', '^', '^', '^', '|', ' ', '^', '^', '^', '^', '|', ' ', '^', '|', ' ', '^', '^', '^', '^', '|', ' ', '^', '^', '^', '|', ' ', '|'},
    {'|', ' ', '_', '_', '_', '+', ' ', '_', '_', '_', '_', '+', ' ', '_', '+', ' ', '_', '_', '_', '_', '+', ' ', '_', '_', '_', '+', ' ', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'|', ' ', '^', '^', '^', '|', ' ', '^', '|', ' ', '^', '^', '^', '^', '^', '^', '^', '^', ' ', '^', '|', ' ', '^', '^', '^', '|', ' ', '|'},
    {'|', ' ', '_', '_', '_', '+', ' ', '^', '|', ' ', '_', '_', '_', '^', '^', '_', '_', '+', ' ', '^', '|', ' ', '_', '_', '_', '+', ' ', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', '^', '|', ' ', ' ', ' ', ' ', '^', '|', ' ', ' ', ' ', ' ', '^', '|', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'|', ' ', '^', '^', '^', '|', ' ', '^', '^', '^', '^', '|', ' ', '^', '|', ' ', '^', '^', '^', '^', '|', ' ', '^', '^', '^', '|', ' ', '|'},
    {'|', ' ', '^', '^', '^', '|', ' ', '^', '^', '_', '_', '+', ' ', '_', '+', ' ', '_', '_', '_', '^', '|', ' ', '^', '^', '^', '|', ' ', '|'},
    {'|', ' ', '_', '_', '_', '+', ' ', '^', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '^', '|', ' ', '_', '_', '_', '+', ' ', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', '^', '|', ' ', '^', '_', '_', ' ', ' ', '_', '_', '|', ' ', '^', '|', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'_', '_', '_', '_', '_', '_', ' ', '_', '+', ' ', '^', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '_', '+', ' ', '_', '_', '_', '_', '_', '+'},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '^', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {'^', '_', '_', '_', '_', '_', ' ', '^', '|', ' ', '^', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '^', '|', ' ', '_', '_', '_', '_', '_', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', '^', '|', ' ', '_', '_', '_', '_', '_', '_', '_', '+', ' ', '^', '|', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'|', ' ', '^', '^', '^', '|', ' ', '^', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '^', '|', ' ', '^', '^', '^', '|', ' ', '|'},
    {'|', ' ', '_', '_', '_', '+', ' ', '_', '+', ' ', '_', '_', '_', '^', '^', '_', '_', '+', ' ', '_', '+', ' ', '_', '_', '_', '+', ' ', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '^', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'|', ' ', '_', '_', '_', '|', ' ', '^', '|', ' ', '^', '|', ' ', '^', '|', ' ', '^', '|', ' ', '^', '|', ' ', '^', '_', '_', '_', ' ', '|'},
    {'|', ' ', ' ', ' ', ' ', '|', ' ', '^', '|', ' ', '^', '|', ' ', '_', '+', ' ', '^', '|', ' ', '^', '|', ' ', '|', ' ', ' ', ' ', ' ', '|'},
    {'^', '_', '_', '+', ' ', '+', ' ', '^', '|', ' ', '_', '+', ' ', ' ', ' ', ' ', '_', '+', ' ', '^', '|', ' ', '+', ' ', '_', '_', '_', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', '^', '|', ' ', ' ', ' ', ' ', '^', '|', ' ', ' ', ' ', ' ', '^', '|', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'|', ' ', '_', '_', '_', '_', '_', '_', '_', '+', ' ', '_', '_', '_', '_', '_', '+', ' ', '_', '_', '_', '_', '_', '_', '_', '+', ' ', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '+'},
    },
        
    {
    {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
    {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
    {'^', '_', '_', '_', '_', '_', '^', '_', '_', '_', '_', '^', '_', '_', '_', '^', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'|', ' ', '_', '_', '+', ' ', '+', ' ', '_', '+', ' ', '+', ' ', '|', ' ', '+', ' ', '|', ' ', '_', '+', ' ', '_', '_', '_', '|', ' ', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|'},
    {'^', '_', '+', ' ', '^', '_', '_', '_', '_', '^', '_', '_', '_', '_', '+', ' ', '^', '_', '_', '_', '+', ' ', '_', '|', ' ', '_', '_', '|'},
    {'|', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
    {'|', ' ', '|', ' ', '+', ' ', '^', '+', ' ', '_', '+', ' ', '_', '_', '_', '_', '+', ' ', '_', '_', '_', '|', ' ', '|', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', '|', ' ', '+', ' ', '^', '_', '_', '_', '+', ' ', '_', '_', '_', '_', '_', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|'},
    {'|', ' ', '+', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|', ' ', '+', ' ', '+', ' ', '|'},
    {'|', ' ', ' ', ' ', '^', '_', '|', ' ', '|', ' ', '_', '_', '_', '_', '_', '_', '_', '+', ' ', '|', ' ', '+', ' ', ' ', ' ', ' ', ' ', '|'},
    {'^', '_', '|', ' ', '+', ' ', '|', ' ', '+', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+', ' ', ' ', ' ', '|', ' ', '|', ' ', '|'},
    {'|', ' ', '+', ' ', ' ', ' ', '|', ' ', ' ', ' ', '^', '_', '_', ' ', ' ', '_', '_', '|', ' ', ' ', ' ', '|', ' ', '+', ' ', '|', ' ', '|'},
    {'|', ' ', ' ', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|', ' ', '|', ' ', ' ', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', '|', ' ', '+', ' ', '|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|', ' ', '+', ' ', '|', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', '|', ' ', ' ', ' ', '|', ' ', '_', '_', '_', '_', '_', '_', '_', '+', ' ', '|', ' ', ' ', ' ', '|', ' ', '|', ' ', '|'},
    {'|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|'},
    {'|', ' ', '+', ' ', '|', ' ', '|', ' ', '_', '_', '_', '_', '+', ' ', '_', '_', '_', '_', '_', '+', ' ', '|', ' ', '+', ' ', '+', ' ', '|'},
    {'|', ' ', ' ', ' ', '|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|'},
    {'^', '_', '|', ' ', '|', ' ', '_', '_', '_', '_', '_', '_', '^', '_', '_', '_', '_', '_', '_', '_', '_', '+', ' ', '^', '_', '+', ' ', '|'},
    {'|', ' ', '|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
    {'|', ' ', '+', ' ', '_', '^', '_', '_', '+', ' ', '_', '_', '_', '^', '_', '+', ' ', '_', '_', '_', '_', '_', '_', '+', ' ', '|', ' ', '|'},
    {'|', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|'},
    {'|', ' ', '_', '+', ' ', '_', '+', ' ', '_', '_', '|', ' ', '_', '_', '_', '+', ' ', '_', '_', '_', '_', '+', ' ', '_', '_', '_', '_', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '+'},
    },
            
    {
    {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
    {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
    {'^', '_', '_', '_', '_', '_', '_', '_', '^', '_', '_', '_', '_', '+', ' ', '_', '_', '_', '_', '^', '_', '_', '_', '_', '_', '_', '_', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'|', ' ', '_', '_', '_', '_', '+', ' ', ' ', ' ', '_', '^', '|', ' ', '_', '^', '^', '+', ' ', ' ', ' ', '_', '_', '_', '_', '+', ' ', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '^', '|', ' ', ' ', '^', '|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'^', '^', '_', '_', ' ', '^', '_', '_', '_', '|', ' ', '_', '_', '+', ' ', '_', '+', ' ', '^', '_', '_', '_', '|', ' ', '_', '_', '^', '|'},
    {'|', '+', ' ', ' ', ' ', '_', ' ', ' ', ' ', '+', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+', ' ', ' ', ' ', '+', ' ', ' ', ' ', '_', '|'},
    {'|', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', '^', '|', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', '|'},
    {'^', '+', ' ', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '+', ' ', '_', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'^', '_', '+', ' ', '_', '_', '_', '+', ' ', '_', '_', '_', '_', '_', '_', '_', '_', '_', '+', ' ', '_', '_', '_', '+', ' ', '_', '_', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'^', '_', '_', '+', ' ', '_', '_', '+', ' ', '_', '_', '_', '_', '_', '_', '_', '_', '_', '+', ' ', '_', '_', '+', ' ', '_', '_', '_', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'^', '_', '_', '_', '+', ' ', '_', '+', ' ', '_', '_', '^', '+', ' ', ' ', '_', '^', '_', '+', ' ', '_', '+', ' ', '_', '_', '_', '_', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'^', '_', '_', '+', ' ', '_', '_', '+', ' ', '_', '_', '_', '_', '_', '_', '_', '_', '_', '+', ' ', '_', '_', '+', ' ', '_', '_', '_', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'^', '_', '+', ' ', '_', '_', '_', '+', ' ', '_', '_', '_', '_', '_', '_', '_', '_', '_', '+', ' ', '_', '_', '_', '+', ' ', '_', '_', '|'},
    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
    {'^', '+', ' ', '_', '_', '^', '_', '+', ' ', '_', '_', '_', '_', '^', '^', '_', '_', '_', '+', ' ', '_', '_', '^', '_', '+', ' ', '_', '|'},
    {'^', ' ', ' ', ' ', ' ', '+', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '_', '+', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+', ' ', ' ', ' ', ' ', '|'},
    {'^', '+', ' ', '|', ' ', ' ', ' ', '^', '_', '_', '_', '|', ' ', ' ', ' ', ' ', '^', '_', '_', '_', '|', ' ', ' ', ' ', '|', ' ', '_', '|'},
    {'|', ' ', ' ', '+', ' ', '_', '^', '+', ' ', ' ', ' ', '_', '^', '^', '^', '^', '_', ' ', ' ', ' ', '_', '^', '+', ' ', '+', ' ', ' ', '|'},
    {'|', ' ', '+', ' ', ' ', ' ', '+', ' ', ' ', '|', ' ', ' ', '_', '_', '_', '+', ' ', ' ', '|', ' ', ' ', '+', ' ', ' ', ' ', '+', ' ', '|'},
    {'|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '^', '^', '^', ' ', ' ', ' ', ' ', ' ', ' ', '^', '^', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
    {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '+', ' ', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '+'},
    }};

    public Stage() 
    {
        som = new Som();
        som.tocar();
        
        //O que a linha abaixo ta fazendo ?? Precisa dessa porra ??
        Drawing.setGameScreen(this);
        
        //Pq esse init componentes não cria a porra do label ?? Ou será que cria ??
        initComponents();

        this.addKeyListener(this);   /*teclado*/
        
        //Cria a janela do tamanho do tabuleiro + insets (bordas) da janela/
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
        g = getBufferStrategy().getDrawGraphics();
        
        //Criamos um contexto grafico/
        g2 = g.create(getInsets().right, getInsets().top, getWidth() - getInsets().left, getHeight() - getInsets().bottom);
        
        for (int i = 1; i < elementosCenario.size(); i++) {
            if ((elementosCenario.get(i) instanceof BackgroundElement))
            {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + elementosCenario.get(i).getNomeImagem());
                    g2.drawImage(newImage, (int)elementosCenario.get(i).pos.getY() * Consts.CELL_SIZE, (int)elementosCenario.get(i).pos.getX() * Consts.CELL_SIZE, Consts.CELL_SIZE, Consts.CELL_SIZE, null);
                } catch (IOException ex) {
                    Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        String vidas = "Vidas: ";
        vidas = vidas + Integer.toString(pacman.vidas);
        String pontos = "Pontos: ";
        pontos = pontos + Integer.toString(pacman.pontuacao);
        String faseString = "Fase: ";
        faseString = faseString + Integer.toString(fase);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g2.drawString(vidas, 5, 15);
        g2.drawString(pontos, 5, 35);
        g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g2.drawString(faseString, 220, 25);
        
        this.controller.drawDynamicElements(elementosCenario, g2);
        this.controller.processAllElements(elementosCenario, pacman, vermelho, rosa, ciano, laranja,som);
        
        
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
                if (numPacDots == 0)
                {
                    //Inicia a próxima fase
                    fase++;
                    elementosCenario.clear();
                    novoJogo();
                }
                
                if ((System.currentTimeMillis() - tempoInicio)/1000 > 20)
                {
                    System.out.println("DEUUUUUUUUUU");
                    tempoInicio = System.currentTimeMillis();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.DELAY);
        
    }
    
    //Função que muda o movimento do pacman de acordo com a tecla pressionada
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                //Se estava parado, então reiniciar o som
                if(pacman.getMovDirection() == Pacman.STOP){
                    som.tocar();
                }   pacman.setMovDirection(Pacman.MOVE_UP);
                break;
            case KeyEvent.VK_DOWN:
                if(pacman.getMovDirection() == Pacman.STOP){
                    som.tocar();
                }   pacman.setMovDirection(Pacman.MOVE_DOWN);
                break;
            case KeyEvent.VK_LEFT:
                if(pacman.getMovDirection() == Pacman.STOP){
                    som.tocar();
                }   pacman.setMovDirection(Pacman.MOVE_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                if(pacman.getMovDirection() == Pacman.STOP){
                    som.tocar();
                }   pacman.setMovDirection(Pacman.MOVE_RIGHT);
                break;
            case KeyEvent.VK_S:
                salvarJogo();
                break;
            default:
                break;
        }
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
        //toda vez q começo um novo jogo, seto o valor de tempo inicial para este momento
        tempoInicio = System.currentTimeMillis();
        
        paredes = new ArrayList<Element>();
        caminho = new ArrayList<Element>();

        //Cria e adiciona os elementos do cenario/
        for (int i = 0; i < Consts.NUM_CELLS; i++) {
            for (int j = 0; j < Consts.NUM_CELLS; j++) {
                if(mapa[fase][i][j] == '|')
                {
                    BackgroundElement parede = new BackgroundElement("parede","parede_lateral.png");
                    parede.setPosition(i, j);
                    paredes.add(parede);
                }
                else if(mapa[fase][i][j] == '_')
                {
                    BackgroundElement parede = new BackgroundElement("parede","parede_baixo.png");
                    parede.setPosition(i, j);
                    paredes.add(parede);
                }
                else if(mapa[fase][i][j] == '+')
                {
                    BackgroundElement parede = new BackgroundElement("parede","quina.png");
                    parede.setPosition(i, j);
                    paredes.add(parede);
                }
                else if(mapa[fase][i][j] == '^')
                {
                    BackgroundElement parede = new BackgroundElement("parede","quina_sem_borda.png");
                    parede.setPosition(i, j);
                    paredes.add(parede);
                }
                else if(mapa[fase][i][j] == ' ')
                {
                    BackgroundElement caminhoLivre = new BackgroundElement("caminho","caminho_vinho_com_pp.png");
                    caminhoLivre.setPosition(i, j);
                    caminhoLivre.setI(i);
                    caminhoLivre.setJ(j);
                    caminho.add(caminhoLivre);
                }
                else if(mapa[fase][i][j] == '*')
                {
                    BackgroundElement fundoCabecalho = new BackgroundElement("fundoCabecalho","caminho_vinho.png");
                    fundoCabecalho.setPosition(i, j);
                    fundoCabecalho.setI(i);
                    fundoCabecalho.setJ(j);
                    //Será que adicionar o fundoCabecalho como parede da merda ?
                    paredes.add(fundoCabecalho);
                }
            }
        }
        
        //O cabeçalho salva o número de paredes e caminhos, para
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

        pacman = new Pacman("pacman_direita.png");
        pacman.setPosition(3, 1);
        pacman.go();
        this.addElement(pacman);
        
        //Esse trecho coloca os fantasmas no mapa
        vermelho = new Fantasma("blinky_baixo.png");
        vermelho.setPosition(14, 14);
        this.addElement(vermelho);
        
        //Número de PacDots do cenário. Quando chega a 0, mudamos de fase.
        numPacDots = caminho.size();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0604 - Pacman");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.black);
        setLocation(new java.awt.Point(20, 20));
        setMaximumSize(new java.awt.Dimension(214765432, 214765432));
        setPreferredSize(new java.awt.Dimension(500, 5));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
