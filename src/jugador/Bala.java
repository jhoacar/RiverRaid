/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jugador;

import global.Objeto;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author JHOAN
 */
/**
 * Clase bala la cual es la encargada de destruir los enemigos
 */
public class Bala extends Objeto{
    
    
    /**
     * Constructor encarcagado de iniciar las variables y asignarle una imagen a la bala
     */
    public Bala()
    {
        super();
        imagen = new ImageIcon("src/recursos/jugador/Bala.jpg").getImage();
        
    }
    /**
     * Constructor paramatricamente que posiciona la bala
     * @param posicion_bala punto en x e y donde ubicara la bala
     */
    public Bala(Point posicion_bala)
    {
        posx=posicion_bala.x;
        posy=posicion_bala.y;
        vivo=true;
        imagen = new ImageIcon("src/recursos/jugador/Bala.jpg").getImage();
    }
}