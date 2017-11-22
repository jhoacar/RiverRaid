/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package global;

import java.applet.Applet;
import java.applet.AudioClip;
/**
 *
 * @author JHOAN CARRERO Y PAOLA PEREZ
 */
/**
 * Clase encargada del sonido del juego 
 */
public class Sonido {
    
    private final AudioClip sonido;
    /**
     * Constructor parametrico de la clase sonido
     * @param ruta direccion en el disco del sonido 
     */
    public Sonido(String ruta){
        sonido = Applet.newAudioClip(getClass().getResource(ruta));
    }
    /**
     * Metodo encargado de reproducir el sonido 
     */
    public void reproducir(){
        sonido.play();
    }
    
    /**
     * Metodo encargado de detener el sonido 
     */
    public void detener(){
        sonido.stop();
    }
}
