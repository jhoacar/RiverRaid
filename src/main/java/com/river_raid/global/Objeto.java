/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.river_raid.global;

import java.awt.Image;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author JHOAN CARRERO Y PAOLA PEREZ
 */

/**
 * Clase abstracta encargada de obtener la posicion en x, posicion en y, la imagen de cada uo de los personajes
 */
public abstract class Objeto {

    protected int posx,posy;
    protected boolean vivo;
    protected Image imagen;
    
    /**
     * Constructor por defecto, que posiciona los personajes en 0,0. Y los hace vivos
     */
    public Objeto()
    {
        
        posx=0;
        posy=0;
        vivo=true;
    }
    /**
     * Metodo para devolver las posiciones en x 
     * @return posicion en x de los personajes 
     */
    public int getposx()
    {
        return posx;
    }
    /**
     * Metodo para devolver las posiciones en y 
     * @return posicion en y de los personajes 
     */
    public int getposy()
    {
        return posy;
    }
    /**
     * Metodo para devolver las imagenes  
     * @return Imagen de los personajes
     */
    public Image getimagen()    
    {
        return imagen;
    }
    /**
     * Metodo para asignar las posiciones en x 
     * @param x nuevo valor de x
     */
    public void setposx(int x)
    {
        posx=x;
    }
    /**
     * Metodo para asignar las posiciones en y 
     * @param y nuevo valor de y
     */
    public void setposy(int y)
    {
        posy=y;
    }
    /**
     * Metodo para asignar las imagenes de los personajes 
     * @param imagen nueva imagen del personaje 
     */
    public void setimagen(Image imagen)
    {
        this.imagen = imagen;
    }
    /**
     * Metodo para asignar el estado de los personajes, vivo o muerto
     * @param vivo estado del personaje
     */
    public void setvivo(boolean vivo)
    {
        this.vivo=vivo;
    }
    /**
     * Metodo para devolver el estado de los personajes, vivo o muerto
     * @return nuevo estado del personaje
     */
    public boolean getvivo()
    {
        return vivo;
    }
    /**
     * Metodo para definir el area de las imagenes 
     * @return area de la imagen 
     */
    public Rectangle2D area(){
        return new Rectangle2D.Double(posx, posy, imagen.getWidth(null), imagen.getHeight(null));
        
    }
    
}
