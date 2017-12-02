package elements;

import java.awt.Graphics;

/**
 *
 * @author Felipe
 */
public class BackgroundElement extends Element{
    
    private String tipo;
    private String tipoFruta;
    private boolean temPacDot;
    private boolean temPowerPallet;
    private boolean temFruta;
    
    private int i, j;

    //Construtor de BackGroundElement, utiliza o construtor de Element mais o que diz respeito a backGround
    public BackgroundElement(String tipo, String imageName) {
        super(imageName);
        this.tipo = tipo;
        if (tipo.equals("parede"))
            this.isTransposable = false;
        if (tipo.equals("caminho"))
            this.temPacDot = true;
        if (tipo.equals("powerpellet")){
            this.temPacDot = true;
            this.temPowerPallet = true;
        }
    }

    public String getTipoFruta() {
        return tipoFruta;
    }

    public void setTipoFruta(String tipoFruta) {
        this.tipoFruta = tipoFruta;
    }

    public boolean getTemPowerPallet() {
        return temPowerPallet;
    }

    public void setTemPowerPallet(boolean temPowerPallet) {
        this.temPowerPallet = temPowerPallet;
    }

    public boolean getTemFruta() {
        return temFruta;
    }

    public void setTemFruta(boolean temFruta) {
        this.temFruta = temFruta;
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
    
    public boolean getTemPacDot() {
        return temPacDot;
    }

    public void setTemPacDot(boolean isPowerPellet) {
        this.temPacDot = isPowerPellet;
    }
    
    @Override
    public void autoDraw(Graphics g) {
    }
    
}
