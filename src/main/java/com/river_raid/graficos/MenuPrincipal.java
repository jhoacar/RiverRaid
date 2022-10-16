/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.river_raid.graficos;

import com.river_raid.global.CargarImagen;
import com.river_raid.global.Personajes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author JHOAN CARRERO Y PAOLA PEREZ
 */
/**
 * Clase constante menu principal encargada de crear los botones que sale en el
 * menu
 */
public final class MenuPrincipal extends JPanel {

    private final JButton botones[];

    private final JPanel empezar;

    private final JLabel etiquetaNombre;
    private final JTextField PedirNombre;
    private final JButton SalirEditorNombre;
    private final JButton SalirInstrucciones;
    private final JPanel instrucciones;

    private final JLabel ayuda;

    private final int ANCHO, ALTO;

    private final Image fondo;
    private final JLabel etiquetaTiempo;
    private final JButton SalirTop10;
    private String[] Puntuaciones;

    private final JPanel Top10;
    private final JLabel[] etiquetasPuntuaciones;
    private final JPanel creditos;
    private final JLabel etiquetaCreditos[];
    private final JButton SalirCreditos;

    private String nombre;
    private int tiempo;

    private final JComboBox<String> PedirTiempo;
    private final JButton comenzarjuego;

    /**
     * Constructor parametrico que crea los botones y le da acciones
     * 
     * @param ancho ancho del menu
     * @param alto  alto del menu
     */
    public MenuPrincipal(int ancho, int alto) {

        ANCHO = ancho;

        ALTO = alto;
        fondo = CargarImagen.loadImage("entorno/Fondo.jpg");

        super.setBounds(0, 0, ANCHO, ALTO);
        super.setLayout(null);
        super.setBackground(Personajes.COLORPASTO);

        botones = new JButton[5];

        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton();
        }

        for (JButton botone : botones) {
            botone.setHorizontalAlignment(SwingConstants.CENTER);// PARA CENTRAR LAS LETRAS DEL LABEL
            botone.setForeground(Color.BLACK);
            botone.setBackground(Personajes.NARANJA);
            botone.setFocusable(false);
        }
        botones[0].setFont(Personajes.FUENTE.deriveFont((float) 15));// DERIVE FONT AJUSTA LA FUENTE A LA NECESIDAD
        botones[0].setText("JUGAR");
        botones[1].setFont(Personajes.FUENTE.deriveFont((float) 15));
        botones[1].setText("INSTRUCCIONES");
        botones[2].setFont(Personajes.FUENTE.deriveFont((float) 15));
        botones[2].setText("TOP 10");
        botones[3].setFont(Personajes.FUENTE.deriveFont((float) 15));
        botones[3].setText("CREDITOS");
        botones[4].setFont(Personajes.FUENTE.deriveFont((float) 15));
        botones[4].setText("SALIR");

        botones[0].setBounds(10, 250, 150, 70);// JUGAR
        botones[1].setBounds(10, 330, 150, 70);// INSTRUCCIONES
        botones[2].setBounds(10, 410, 150, 70);// TOP 10
        botones[3].setBounds(10, 490, 150, 70);// CREDITOS
        botones[4].setBounds(430, 490, 150, 70);// SALIR

        // INICIO DE LAS ACCIONES DE PRESIONAR EL BOTON DE JUGAR

        SalirEditorNombre = new JButton("X");
        SalirEditorNombre.setFont(Personajes.FUENTE.deriveFont((float) 8));
        SalirEditorNombre.setBounds(255, 5, 40, 40);
        SalirEditorNombre.setFocusable(false);

        SalirEditorNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empezar.setVisible(false);// DESAPAREZCO EL EDITOR DE NOMBRE
                for (JButton botone : botones) { // APAREZCO CADA BOTON AL DARLE CLICK EN LA X
                    botone.setVisible(true);
                }
            }
        });

        empezar = new JPanel();
        empezar.setBounds(150, 150, 300, 400);// Este JPanel lo abre el boton de jugar
        empezar.setLayout(null);
        empezar.setBackground(Personajes.NARANJA);

        etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setBounds(50, 50, 100, 30);// Este JLabel es el que muestra en el JPanel de empezar
        etiquetaNombre.setFont(Personajes.FUENTE.deriveFont((float) 20));

        PedirNombre = new JTextField();
        PedirNombre.setBounds(50, 100, 150, 25);
        PedirNombre.setFont(Personajes.FUENTE.deriveFont((float) 20));

        nombre = "SIN NOMBRE";

        PedirNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombre = PedirNombre.getText();
            }
        });

        etiquetaTiempo = new JLabel("Tiempo:");
        etiquetaTiempo.setBounds(50, 150, 100, 30);// Este JLabel es el que muestra en el JPanel de empezar
        etiquetaTiempo.setFont(Personajes.FUENTE.deriveFont((float) 20));

        final String tiempos[] = { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "110", "120" };

        PedirTiempo = new JComboBox<String>(tiempos);
        PedirTiempo.setMaximumRowCount(5);
        PedirTiempo.setBounds(50, 200, 150, 25);
        PedirTiempo.setFont(Personajes.FUENTE.deriveFont((float) 20));

        tiempo = Integer.parseInt(tiempos[0]);// SE OBTIENE EL TIEMPO POR DEFECTO

        PedirTiempo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED)// EL CAMBIO DE LA ESCUCHADORA SEA POR QUE SE
                                                                 // SELECCIONO ALGO
                    tiempo = Integer.parseInt(tiempos[PedirTiempo.getSelectedIndex()]);// ESE ALGO QUE SELECCIONO SERA
                                                                                       // LA POSICION DEL ARRAY DE
                                                                                       // TIEMPO

            }
        });
        comenzarjuego = new JButton("COMENZAR");
        comenzarjuego.setBounds(53, 300, 200, 80);
        comenzarjuego.setFont(Personajes.FUENTE.deriveFont((float) 20));
        comenzarjuego.setBackground(Personajes.NARANJA);
        // NO LE AGREGO EL ACTIONLISTENER EN ESTA CLASE PORQUE EL JUEGO NO ESTA AQUI

        empezar.setVisible(false);
        empezar.add(etiquetaNombre);// n = 0
        empezar.add(PedirNombre); // n = 1
        empezar.add(etiquetaTiempo);// n = 2
        empezar.add(PedirTiempo);// n = 3
        empezar.add(comenzarjuego);// n = 4
        empezar.add(SalirEditorNombre);// n = 5

        // FIN DE INICIO

        // INICIO DE LAS ACCIONES DEL BOTON DE INSTRUCCIONES

        SalirInstrucciones = new JButton("X");
        SalirInstrucciones.setFont(Personajes.FUENTE.deriveFont((float) 8));
        SalirInstrucciones.setBounds(520, 5, 40, 40);
        SalirInstrucciones.setFocusable(false);

        SalirInstrucciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instrucciones.setVisible(false);
                for (JButton botone : botones) {
                    botone.setVisible(true);
                }
            }
        });
        instrucciones = new JPanel();
        instrucciones.setLayout(null);
        instrucciones.setBounds(10, 150, 570, 400);// Este JPanel lo abre el boton de instrucciones
        instrucciones.setBackground(Personajes.NARANJA);

        ayuda = new JLabel(new ImageIcon(CargarImagen.loadImage("entorno/Instrucciones.jpg")));
        ayuda.setBounds(0, 0, 570, 400);

        instrucciones.add(SalirInstrucciones);
        instrucciones.add(ayuda);

        instrucciones.setVisible(false);

        // FIN DE INICIO

        // INICIO DE LAS ACCIONES DEL BOTON DE TOP 10

        SalirTop10 = new JButton("X");
        SalirTop10.setFont(Personajes.FUENTE.deriveFont((float) 8));
        SalirTop10.setBounds(520, 5, 40, 40);
        SalirTop10.setFocusable(false);

        SalirTop10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Top10.setVisible(false);
                for (JButton botone : botones) {
                    botone.setVisible(true);
                }
            }
        });

        Top10 = new JPanel();
        Top10.setLayout(null);
        Top10.setBounds(10, 150, 570, 400);// Este JPanel lo abre el boton de instrucciones
        Top10.setBackground(Personajes.NARANJA);

        etiquetasPuntuaciones = new JLabel[10];// SOLO MOSTRARA 10 PUNTAJES

        CargarPuntuaciones();

        Top10.add(SalirTop10);

        Top10.setVisible(false);

        // FIN DE INICIO

        // INICIO DE LAS ACCIONES DEL BOTON DE CREDITOS

        SalirCreditos = new JButton("X");
        SalirCreditos.setFont(Personajes.FUENTE.deriveFont((float) 8));
        SalirCreditos.setBounds(520, 5, 40, 40);
        SalirCreditos.setFocusable(false);

        SalirCreditos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creditos.setVisible(false);
                for (JButton botone : botones) {
                    botone.setVisible(true);
                }
            }
        });

        creditos = new JPanel();
        creditos.setLayout(null);
        creditos.setBounds(10, 150, 570, 400);// Este JPanel lo abre el boton de instrucciones
        creditos.setBackground(Personajes.NARANJA);

        etiquetaCreditos = new JLabel[5];
        for (int i = 0; i < etiquetaCreditos.length; i++) {
            etiquetaCreditos[i] = new JLabel();
            etiquetaCreditos[i].setBounds(10, (150 + 40 * i), 570, 20);
            etiquetaCreditos[i].setFont(Personajes.FUENTE1.deriveFont((float) 20));
            etiquetaCreditos[i].setForeground(Color.BLACK);
        }
        etiquetaCreditos[0].setLocation(210, 50);
        etiquetaCreditos[0].setText("HECHO POR");

        etiquetaCreditos[1].setLocation(10, 150);
        etiquetaCreditos[1].setText(" *) CARRERO PINEDA JHOAN MANUEL ");

        etiquetaCreditos[2].setLocation(10, 190);
        etiquetaCreditos[2].setText(" *) PEREZ ALBORNOZ PAOLA ALEJANDRA");

        etiquetaCreditos[3].setLocation(10, 300);
        etiquetaCreditos[3].setText("Copyright © 2017 UNET ING. INFORMATICA.");

        etiquetaCreditos[4].setLocation(50, 350);
        etiquetaCreditos[4].setText("JHOAN_&_PAOLA, All Rights Reserved");

        creditos.add(SalirCreditos);

        for (JLabel etiquetaCredito : etiquetaCreditos) {
            creditos.add(etiquetaCredito);
        }
        creditos.setVisible(false);

        // FIN DE INICIO

        for (JButton botone : botones) {
            super.add(botone);
        }
        super.add(empezar);
        super.add(instrucciones);
        super.add(Top10);
        super.add(creditos);

        super.setFocusable(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(fondo, 0, 0, ANCHO, ALTO, this);

        g2.setFont(Personajes.FUENTE1.deriveFont((float) 60));

        g2.setColor(Personajes.NARANJA);

        g2.drawString("RIVER  RAID", 70, 100);

    }

    /**
     * Metodo que devuelve la accion del boton comenzar juego
     * 
     * @return boton que indica el comienzo del juego
     */
    public JButton getComenzarJuego() {
        return comenzarjuego;
    }

    /**
     * Metodo que devuelve la accion del boton empezar juego
     * 
     * @return boton que indica al empezar el juego
     */
    public JPanel getEmpezar() {
        return empezar;
    }

    /**
     * Metodo que devuelve la accion del boton de los 10 mejores jugadores
     * 
     * @return boton que indica los mejores jugadores
     */
    public JPanel getTop10() {
        return Top10;
    }

    /**
     * Metodo que devuelve los botones del menu principal
     * 
     * @param i numero de determinado boton
     * @return boton especifico del menu principal
     */
    public JButton getBotones(int i) {
        return botones[i];
    }

    /**
     * Metodo que devuelve los creditos del juego
     * 
     * @return creditos del juego
     */
    public JPanel getCreditos() {
        return creditos;
    }

    /**
     * Metodo encargado de devolver la cantidad de botones
     * 
     * @return
     */
    public int getCantBotones() {
        return botones.length;
    }

    /**
     * Metodo encargado de devolver el Panel de instrucciones
     * 
     * @return el panel de instrucciones
     */
    public JPanel getInstrucciones() {
        return instrucciones;
    }

    /**
     * Metodo encargado de guadar la informacion en el archivo
     * 
     * @param nombre     nombre del jugador a guardar
     * @param puntuacion puntuacion hecha por el usuario
     */
    public void GuardarPuntuacion(String nombre, int puntuacion) {

        try {
            BufferedWriter bf = new BufferedWriter(
                    new FileWriter(System.getProperty("user.dir") + "/highscore/puntuacion.txt", true));
            bf.write(nombre.toUpperCase() + "-" + puntuacion);
            bf.newLine();
            bf.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "SE HA GUARDADO SU PUNTUACION", "PARTIDA GUARDADA",
                JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Carga cada puntuacion guarda en el archivo
     */
    public void CargarPuntuaciones() {

        String linea;
        Vector<String> partidas = new Vector<String>();

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(System.getProperty("user.dir") + "/highscore/puntuacion.txt"));

            linea = br.readLine();

            while (linea != null) {// ESTE ALGORITMO RECORRE CADA LINEA DEL ARCHIVO

                partidas.add(linea);
                linea = br.readLine();
            }
            int tam = partidas.size();
            if (tam == 0) {
                Puntuaciones = new String[10];
                for (int i = 0; i < Puntuaciones.length; i++)
                    Puntuaciones[i] = ".... -0";
            } else if (tam < 10) {
                Puntuaciones = new String[10];

                for (int i = 0; i < 10; i++) {
                    if (i < tam)
                        Puntuaciones[i] = (String) partidas.get(i);
                    else
                        Puntuaciones[i] = ".... -0";
                }
            } else {
                Puntuaciones = new String[tam];
                for (int i = 0; i < Puntuaciones.length; i++)
                    Puntuaciones[i] = (String) partidas.get(i);
            }

            OrdenarPuntuaciones();

            br.close();

        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no se encuentra");
        } catch (IOException ex) {
            System.out.println("Algo va mal...");
        }
    }

    /**
     * Ordena de mayor a menor las puntuaciones con sus respectivos nombres
     */
    public void OrdenarPuntuaciones() {

        String nombres[] = new String[Puntuaciones.length];
        int puntuacion[] = new int[Puntuaciones.length];

        for (int i = 0; i < Puntuaciones.length; i++) {

            StringTokenizer token = new StringTokenizer(Puntuaciones[i], "-");
            nombres[i] = token.nextToken();
            if (nombres[i].length() > 8) // SI TIENE MAS DE OCHO LETRAS SOLO GUARDARA 8
                nombres[i] = nombres[i].substring(0, 8);
            puntuacion[i] = Integer.parseInt(token.nextToken()); // AQUI PUEDE QUE SE DE UN ERROR
        }

        for (int i = 0; i < puntuacion.length; i++) {// METODO DE LA BURBUJA
            for (int j = 0; j < puntuacion.length; j++) {
                if (puntuacion[i] > puntuacion[j]) {
                    int aux = puntuacion[i];
                    puntuacion[i] = puntuacion[j];
                    puntuacion[j] = aux;

                    String aux1 = nombres[i];
                    nombres[i] = nombres[j];
                    nombres[j] = aux1;
                }
            }
        }
        for (int i = 0; i < Puntuaciones.length; i++) {
            Puntuaciones[i] = nombres[i] + "     " + puntuacion[i];
        }

        if (Top10.getComponentCount() != 0) // SI TIENE ETIQUETAS
        {
            for (JLabel etiquetasPuntuacione : etiquetasPuntuaciones) {
                Top10.remove(etiquetasPuntuacione);// LAS BORRO
            }
        }

        for (int i = 0; i < etiquetasPuntuaciones.length; i++) {
            etiquetasPuntuaciones[i] = new JLabel();
            etiquetasPuntuaciones[i].setBounds(250, (80 + 30 * i), 570, 20);
            etiquetasPuntuaciones[i].setFont(Personajes.FUENTE.deriveFont((float) 15));
            etiquetasPuntuaciones[i].setForeground(Color.BLACK);
            etiquetasPuntuaciones[i].setText(Puntuaciones[i]);
            Top10.add(etiquetasPuntuaciones[i]);
        }

    }

    /**
     * Devuelve el nombre del jugador
     * 
     * @return un String como el nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo encargado de devolver el tiempo del juego
     * 
     * @return el tiempo de juego
     */
    public int getTiempo() {
        return tiempo;
    }

}
