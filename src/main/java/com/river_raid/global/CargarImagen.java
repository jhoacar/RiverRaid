package com.river_raid.global;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * 
 * @author JHOAN CARRERO Y PAOLA PEREZ 
 */

/**
 * Clase que carga las imagenes pasadas por ruta
 */
public class CargarImagen {

    /**
     * Este metodo estatico se utiliza para cargar la imagen del disco
     * 
     * @param pathName ruta donde se encuentra la imagen
     * @return devuelve la imagen cargada
     **/
    public static BufferedImage loadImage(String pathName) {
        BufferedImage bimage = null;
        try {
            InputStream resourceBuffer = ClassLoader.getSystemResourceAsStream(pathName);
            bimage = ImageIO.read(resourceBuffer);
        } catch (IOException e) {
            System.out.println("No se encontro la ruta " + pathName + ": " + e.getMessage());
        }
        return bimage;
    }
}
