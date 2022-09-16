/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemigos;

import global.Personajes;
import javax.swing.ImageIcon;


/**
 *
 * @author JHOAN CARRERO Y PAOLA PEREZ
 */

/**
*Clase barco que es un enemigo en el juego para el jugador 
**/
public class Barco extends Enemigos implements Personajes{
    /**
    *Constructor parametrico de la clase barco, inicializa las variables y le da una imagen 
     * @param x posicion en x del enemigo
     * @param y posicion en y del enemigo 
     * @param direccion derecha o izquierda
    **/
    public Barco(int x,int y,boolean direccion)
    {
        super(x,y,direccion);
        
        if(direccion)
            imagen = new ImageIcon("src/recursos/enemigos/Barco Derecha.jpg").getImage();
        else
            imagen = new ImageIcon("src/recursos/enemigos/Barco Izquierda.jpg").getImage();
    }
    @Override
    public void moverx(int velocidad,int BORDE_IZQUIERDO,int BORDE_DERECHO){
        
        if(direccion)
        {
            if(posx+velocidad>=BORDE_DERECHO-imagen.getWidth(null))
            {
                direccion=false;
                imagen = new ImageIcon("src/recursos/enemigos/Barco Izquierda.jpg").getImage();
            }
            else
                posx+=velocidad;
        }
        else
        {
            if(posx-velocidad<=BORDE_IZQUIERDO)
            {
                direccion=true;
                imagen = new ImageIcon("src/recursos/enemigos/Barco Derecha.jpg").getImage();
            }
            else
            posx-=velocidad;
        }
        
    }

    @Override
    public void explosion() {
            if(!vivo)
            imagen = new ImageIcon("src/recursos/enemigos/Explosion Barco.jpg").getImage();
    }
    
}
