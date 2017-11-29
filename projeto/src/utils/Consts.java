package utils;

import control.TelaCreditos;
import control.TelaInicial;
import control.TelaRecordes;
import java.io.File;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class Consts {
    public static final int CELL_SIZE = 20;
    public static final int NUM_CELLS = 28;
    
    public static final int WALK_STEP_DEC_PLACES = 1;
    public static final double WALK_STEP = 0.1;
    
    public static final String PATH = File.separator+"imgs"+File.separator;
    
    public static final int DELAY = 17;
    
    public static final TelaInicial screenMenu = new TelaInicial();
    public static final TelaCreditos screenCreditos = new TelaCreditos();
    public static final TelaRecordes screenRecordes = new TelaRecordes();
}
