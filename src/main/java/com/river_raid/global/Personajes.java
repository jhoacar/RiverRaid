/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.river_raid.global;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author JHOAN
 */
public interface Personajes {
    
    /**
     * Amarillo oscuro
     */
    public static final Color AMARILLO = new Color(249,209,53);
    /**
     * Naranja
     */
    public static final Color NARANJA = new Color(252,148,7);
    /**
     * Azul del lago
     */
    public static final Color COLORRIO = new Color(46,50,184);
    /**
     * Verde del pasto  
     */
    public static final Color COLORPASTO = new Color(110,156,66);
    /**
     * Verde claro
     */
    public static final Color COLORVERDE = new Color(84, 139, 84);
    /**
     * Azul claro 
     */
    public static final Color AZULITO = new Color(86, 193, 220);
    /**
     * Fuente del juego, estilo y tamaño de las letras  
     */
    public static final Font FUENTE = new Font("Stencil",Font.PLAIN,40);
    /**
     * Fuente del juego, estilo y tamaño de las letras  
     */
    public static final Font FUENTE1 = new Font("Snap ITC",Font.PLAIN,40);
    /**
     * Metodo encargado de cambiar el estado del objeto
     */
    public void explosion();
    
}
