/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.river_raid.graficos;

import com.river_raid.global.Personajes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author JHOAN CARRERO Y PAOLA PEREZ
 */
/**
 * Clase menu pausa que generara opciones al usuario para continuar o salir del juego. Y tambien para ir al menu principal
 * 
 */
public class MenuPausa extends JPanel{
    
    private final JButton botones[];
    /**
     * Constructor que inicializa y acomoda cada boton 
     */
    public MenuPausa(){
        
        super.setBounds(200,150,200,250);
        super.setLayout(null);
        super.setBackground(Personajes.COLORPASTO);
        
        botones = new JButton[5];
       
        
        for (int i = 0; i < botones.length ; i++) {
            botones[i] = new JButton();
        }
        
        for (JButton botone : botones) {
            botone.setHorizontalAlignment(SwingConstants.CENTER);
            botone.setForeground(Color.BLACK);
            botone.setFocusable(false);
        }
        botones[0].setFont(Personajes.FUENTE.deriveFont((float)12));
        botones[0].setText("CONTINUAR");
        botones[1].setFont(Personajes.FUENTE.deriveFont((float)12));
        botones[1].setText("MENU");
        botones[2].setFont(Personajes.FUENTE.deriveFont((float)12));
        botones[2].setText("SALIR");
        
        botones[0].setBounds(50,50, 100, 50);//CONTINUAR
        botones[1].setBounds(50,120,100, 50);//MENU
        botones[2].setBounds(50,190, 100, 50);//SALIR
        
        
        for (JButton botone : botones) {
            super.add(botone);
        }
    
        super.setVisible(false);
        super.setFocusable(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;

            g2.setFont(Personajes.FUENTE.deriveFont((float)20));

            g2.setColor(Personajes.COLORRIO);

            g2.drawString("RIVER RAID",50,20);
    }
    
    
    /**
     * Metodo para devolver el boton que se indica por parametro
     * @param i representa el boton indicado 
     * @return boton indicado 
     */
    public JButton getBotones(int i){
        return botones[i];
    }
    /**
     * Metodo para devolver el tamaño de los botones 
     * @return tamaño de botones 
     */
    public int getCantBotones(){
        return botones.length;
    }
    
}
