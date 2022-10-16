/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.river_raid.jugador;

import com.river_raid.global.CargarImagen;
import com.river_raid.global.Objeto;
import com.river_raid.global.Personajes;

/**
 *
 * @author JHOAN CARRERO Y PAOLA PEREZ
 */
public class Jugador extends Objeto implements Personajes {

    private final int velocidad = 10;
    private double combustible;

    /**
     * Constructor el cual inicializa las variables y le da una imagen
     */
    public Jugador() {
        super();

        combustible = 100;

        imagen = CargarImagen.loadImage("jugador/Jugador Frente.png");

    }

    /**
     * Metodo que me permite mover y evalua los extremos por si choca o no
     * 
     * @param IZQUIERDA       un booleano que me indica si se mueve hacia la
     *                        izquierda
     * @param DERECHA         un boolean que me indica si se mueve hacia la derecha
     * @param BORDE_IZQUIERDO un int que me indica el borde a la izquierda
     * @param BORDE_DERECHO   un int que me indice el borde a la derecha
     * @return si el estado del jugador esta activo, es decir, vivo o no vivo.
     */
    public boolean moverme(boolean IZQUIERDA, boolean DERECHA, int BORDE_IZQUIERDO, int BORDE_DERECHO) {
        if (IZQUIERDA) {
            if (vivo)
                imagen = CargarImagen.loadImage("jugador/Jugador Izquierda.png");

            if (posx - velocidad > BORDE_IZQUIERDO) {
                posx -= velocidad;
            } else {
                vivo = false;
                explosion();
            }

        } else if (DERECHA) {
            if (vivo)
                imagen = CargarImagen.loadImage("jugador/Jugador Derecha.png");

            if (posx + velocidad < BORDE_DERECHO - imagen.getWidth(null)) {
                posx += velocidad;
            } else {
                vivo = false;
                posx -= 10;
                explosion();
            }

        }

        return vivo;
    }

    /**
     * Metodo que evalua aun si llega a chocar sin moverse
     * 
     * @param BORDE_IZQUIERDO un int que me indice el borde a la izquierda
     * @param BORDE_DERECHO   un int que me indice el borde a la derecha
     * @return si el estado del jugador esta activo, es decir, vivo o no vivo.
     */
    public boolean sinmover(int BORDE_IZQUIERDO, int BORDE_DERECHO) {

        if (vivo) {

            imagen = CargarImagen.loadImage("jugador/Jugador Frente.png");
            if (posx + imagen.getWidth(null) >= BORDE_DERECHO) {
                vivo = false;
                posx -= 10;
                explosion();
            }
            if (posx <= BORDE_IZQUIERDO) {
                vivo = false;
                explosion();
            }
        }
        return vivo;

    }

    /**
     * Asigna el nuevo valor del combustible
     * 
     * @param combustible el nuevo valor del combustible
     */
    public void setcombustible(double combustible) {
        this.combustible = combustible;
    }

    /**
     * Devuelve la cantidad de combustible que tiene el jugador
     * 
     * @return un double del combustible
     */
    public double getcombustible() {
        return combustible;
    }

    @Override
    public void explosion() {
        if (!vivo)
            imagen = CargarImagen.loadImage("jugador/Explosion Jugador.jpg");

    }

}
