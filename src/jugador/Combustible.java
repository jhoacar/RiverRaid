/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jugador;

import global.Objeto;
import global.Personajes;
import javax.swing.ImageIcon;

/**
 *
 * @author JHOAN CARRERO Y PAOLA PEREZ
 */
/**
 * Clase combustible la cual le permite al jugador seguirse moviendo
 */
public class Combustible extends Objeto implements Personajes{
    
    
    /**
     * Constructor que inicia las variables y le da imagen al combustible
     */
    public Combustible()
    {
        super();
        imagen = new ImageIcon("src/recursos/ambiente/Combustible.jpg").getImage();
    }
    @Override
    public void explosion()
    {
        if(!vivo)
        imagen = new ImageIcon("src/recursos/ambiente/Explosion Combustible.jpg").getImage();
    }
    
}
