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
/**
 * Clase combustible la cual le permite al jugador seguirse moviendo
 */
public class Combustible extends Objeto implements Personajes {

    /**
     * Constructor que inicia las variables y le da imagen al combustible
     */
    public Combustible() {
        super();
        imagen = CargarImagen.loadImage("ambiente/Combustible.jpg");
    }

    @Override
    public void explosion() {
        if (!vivo)
            imagen = CargarImagen.loadImage("ambiente/Explosion Combustible.jpg");
    }

}
