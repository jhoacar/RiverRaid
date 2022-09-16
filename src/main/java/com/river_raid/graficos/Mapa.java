/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import global.CargarImagen;
import global.Objeto;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author JHOAN CARRERO Y PAOLA PEREZ
 */
/**
 * Clase constante mapa la cual se encarga de crear el mapa del juego 
 */
public final class Mapa extends Objeto{
    
    private BufferedImage mapa;
    
    private final BufferedImage mapas[];
    
    private final int ANCHO_MARCO,ALTO_MARCO;
    private final int ALTO_IMAGEN;
    private final int NUM_MAPS = 11;
    
    private final Image estado_combustible;
    private final Image medidor_combustible;
    
    /**
     * Constructor parametrico de la clase mapa, crea el mapa, lo posiciona, carga las imagenes
     * @param ancho ancho del mapa
     * @param alto alto del mapa
     */
    public Mapa(int ancho,int alto)
    {
        ANCHO_MARCO = ancho;
        
        ALTO_MARCO = alto;
        
        ALTO_IMAGEN = ALTO_MARCO/3;//Numero de alto en el tamaño de las imagenes
        
        posx = 0;
        
        posy = 0 - ALTO_IMAGEN; // POSICIONO AL MAPA UNA POSICION ARRIBA DE LA IMAGEN PARA QUE EL USUARIO NO LA VEA
        
        mapa = new BufferedImage(ANCHO_MARCO,ALTO_MARCO+ALTO_IMAGEN,BufferedImage.TYPE_INT_RGB);
        
        estado_combustible = CargarImagen.loadImage("src/recursos/entorno/Estado Combustible.jpg"); // CAMBIAR
        
        medidor_combustible = CargarImagen.loadImage("src/recursos/entorno/Medidor Combustible.jpg");
    
        
        mapas = new BufferedImage [NUM_MAPS];
        
        
        for (int x=0;x<mapas.length;x++) 
            mapas[x] = CargarImagen.loadImage("src/recursos/ambiente/Mapa"+x+".jpg");
        
        
        
        for(int x=0;x<4;x++)
        {
            mapa.setRGB(0,
            x*ALTO_IMAGEN,
            ANCHO_MARCO,
            ALTO_IMAGEN,
            getArray(generarMapa()),
            0,
            ANCHO_MARCO);
            
        }
        
        
        mapa.setRGB(0, 
                3*ALTO_IMAGEN,
                ANCHO_MARCO,
                ALTO_IMAGEN,
                getArray(mapas[0]),
                0,
                mapas[0].getWidth()); // LE PASO LA CARRETERA COMO ULTIMA IMAGEN
        
        
    
    }
    
    /**
     * Metodo para generar uno de los mapas a partir del 1 hasta el num mapas establecido
     * @return el mapa seleccionado 
     */
    public BufferedImage generarMapa(){
        int num = (int) ((Math.random())*(NUM_MAPS-1)+1);
        
        return mapas[num];
    }
    
    /**
     * Metodo que devuelve un vector de enteros que representan cada color de pixel de la imagen
     * @param im imagen a la que se le obtiene la cantidad de enteros de pixeles 
     * @return obtiene la imagen convertido en un vector de enteros de puros colores
     */
    public int[] getArray(BufferedImage im){
        return im.getRGB(0,0,im.getWidth(),im.getHeight(),null,0,im.getWidth());
    
    }
    
    /**
     * Metodo para posicionar las imagenes en el mapa 
     */
    public void crearMapa(){
        
        posy = 0 - ALTO_IMAGEN; // SUBO EL MAPA DE NUEVO PARA QUE NO VEA LA IMAGEN QUE SE VA CREAR A CONTINUACION
        
        BufferedImage respaldomapa = mapa.getSubimage(0,0,ANCHO_MARCO,ALTO_MARCO);
        
        
        mapa.setRGB(0,
                    ALTO_IMAGEN,
                    ANCHO_MARCO,
                    ALTO_MARCO,
                    getArray(respaldomapa),
                    0,
                    ANCHO_MARCO);
        
        
        mapa.setRGB(0,
                    0,
                    ANCHO_MARCO,
                    ALTO_IMAGEN,
                    getArray(generarMapa()),
                    0,
                    ANCHO_MARCO);
        
        
    }
    /**
     * Metodo para asignar el mapa 
     * @param mapa 
     */
    public void setmapa(BufferedImage mapa){
        this.mapa = mapa;
    }
    /**
     * Metodo para devolver el mapa 
     * @return mapa asignado 
     */
    public Image getmapa(){
        return mapa;
    }
    /**
     * Metodo que devuelve el borde izquierdo del mapa 
     * @param y posicion en y del objeto 
     * @return numero del borde izquierdo
     */
    public int getBORDEIZQUIERDO(int y){
        return 200;
    }
    /**
     * Metodo que devuelve el borde derecho del mapa
     * @param y posicion en y del objeto 
     * @return numero del borde derecho
     */
    public int getBORDEDERECHO(int y){
        return 399;
    }
    /**
     * Metodo que devuelve la imagen del estado combustible 
     * @return imagen del combustible 
     */
    public Image getestado_combustible(){
        return estado_combustible;
    }
    /**
     * Metodo que devuelve la imagen del medidor del combustible 
     * @return imagen del medidor de combustible 
     */
    public Image getmedidor_combustible(){
        return medidor_combustible;
    }
}
