/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import graficos.MiPanel;
import java.awt.HeadlessException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author JHOAN CARRERO y PAOLA PEREZ
 */
/**
 * Clase juego, principal para el comienzo del juego
*/
public class Juego extends JFrame{
    
    private final MiPanel g;
    
    private final int ancho_ventana = 600;
    private final int alto_ventana = 600;
    
    /**
     *Constructor que inicializa la ventana con el tamaño predefinido
     */
    public Juego() throws HeadlessException
    {
        g = new MiPanel(ancho_ventana,alto_ventana);
        
        
        super.setTitle("RIVER RAID");
        super.setSize(ancho_ventana,alto_ventana);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setIconImage(new ImageIcon("src/recursos/Icono.jpg").getImage());
        
        super.setResizable(false);
        super.setLayout(null);
        super.setContentPane(g);
        
        super.setVisible(true);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Juego juego = new Juego();
    }
}
