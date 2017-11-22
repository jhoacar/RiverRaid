/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemigos;

import global.Objeto;
import global.Personajes;

/**
 *
 * @author JHOAN CARRERO Y PAOLA PEREZ
 * 
 */

/**
*Clase abstracta de enemigos la cual engloba todos los enemigos del juego
**/
public abstract class Enemigos extends Objeto implements Personajes{
    
    
    protected boolean direccion; // Verdadero para la derecha, Falso para la izquierda
    
    /**
    *CONSTRUCTOR PARAMETRICO ENCARGADO DE CONSTRUIR EL ENEMIGO
     * @param x posicion en x del enemigo
     * @param y posicion en y del enemigo 
     * @param direccion derecha o izquierda
    **/
    public Enemigos(int x, int y,boolean direccion)
    {
        posx=x;
        posy=y;
        this.direccion=direccion;
        vivo=true;
    }
    /**
     * Metodo para devolver la direccion de los enemigos  
     * @return direccion obtenida
     */
    public boolean getdireccion()
    {
        return direccion;
    }
    /**
     * Metodo para asignar dicha direccion de los enemigos 
     * @param direccion asignada 
     */
    public void setdireccion(boolean direccion)
    {
        this.direccion=direccion;
    }
    /**
     * Metodo abstracto para mover los enemigos 
     * @param velocidad velocidad con la que se movera 
     * @param BORDE_IZQUIERDO numero del borde izquierdo
     * @param BORDE_DERECHO numero del borde derecho 
     */
    public abstract void moverx(int velocidad,int BORDE_IZQUIERDO,int BORDE_DERECHO);
    
    
}
