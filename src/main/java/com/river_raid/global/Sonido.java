/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.river_raid.global;

import java.io.BufferedInputStream;
import javazoom.jl.player.Player;

/**
 *
 * @author JHOAN CARRERO Y PAOLA PEREZ
 */
/**
 * Clase encargada del sonido del juego
 */
public class Sonido {

  private Player player;
  private String filename;

  /**
   * Constructor parametrico de la clase sonido
   * 
   * @param ruta direccion en el disco del sonido
   */
  public Sonido(String ruta) {
    filename = "sonidos/" + ruta;
    System.out.println(filename);
  }

  /**
   * Metodo encargado de reproducir el sonido
   */
  public void reproducir() {
    try {
      BufferedInputStream buffer = new BufferedInputStream(ClassLoader.getSystemResourceAsStream(filename));
      player = new Player(buffer);
      player.play();
    } catch (Exception e) {
      System.out.println("Un error ha ocurrido reproduciendo " + filename + ": " + e.getMessage());
    }
  }

  /**
   * Metodo encargado de detener el sonido
   */
  public void detener() {
    try {
      player.close();
    } catch (Exception e) {

      System.out.println("Un error ha ocurrido deteniendo " + filename + ": " + e.getMessage());
    }
  }
}
