/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;

/**
 *
 * @author Felipe
 */
public class CabecalhoSalvamento extends Element{
    private int numParedes;
    private int numCaminhos;

    public CabecalhoSalvamento(int numParedes, int numCaminhos) {
        this.numParedes = numParedes;
        this.numCaminhos = numCaminhos;
    }

    public int getNumParedes() {
        return numParedes;
    }

    public void setNumParedes(int numParedes) {
        this.numParedes = numParedes;
    }

    public int getNumCaminhos() {
        return numCaminhos;
    }

    public void setNumCaminhos(int numCaminhos) {
        this.numCaminhos = numCaminhos;
    }

    @Override
    public void autoDraw(Graphics g) {
        
    }
    
    
}
