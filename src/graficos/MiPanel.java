/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import global.Objeto;
import enemigos.Barco;
import enemigos.Enemigos;
import enemigos.Helicoptero;
import global.CargarImagen;
import global.Personajes;
import global.Sonido;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import jugador.Bala;
import jugador.Combustible;
import jugador.Jugador;

/**
 *
 * @author JHOAN CARRERO Y PAOLA PEREZ
 */
/**
 * 
 * Clase general la cual realiza la logica del juego
 */
public final class MiPanel extends JPanel{
    
    private final int ANCHO_VENTANA;
    private final int ALTO_VENTANA;
    private final int ALTO_IMAGEN;
    private final int ALTO_MAPA;
    
    private Timer TimerJugador;
    private Jugador jugador;
    private boolean teclas[];
    private boolean escape;
    private int puntuacion = 0;
    private int vidas_jugador = 3;
    private float tiempo = 200;
    private String nombre;
    
    private Timer TimerMapa;
    private Mapa mapa;
    private int velocidad_mapa;
    private final  int velocidad_minima_mapa = 3;
    private final  int velocidad_maxima_mapa = 30;
    private final int velocidad_inicial_mapa = 7;
    private final int aceleracion_mapa = 3;
    private final int frenado_mapa = 2;
    
    private Timer TimerBala;
    private Bala bala;
    private final int velocidad_bala = 20;
    private boolean disparo = false;
    
    private Timer TimerEnemigos;
    private Enemigos[] enemigos;
    private final int num_max_enemigos = 5;
    private final int velocidad_helicoptero = 5;
    private final int velocidad_barco = 2;
    
    private Timer TimerCombustible;
    private Combustible[] combustibles;
    private final int num_max_combustible = 3;
    
    private final Sonido gun;
    private final Sonido explosion;
    private final Sonido recarga;
    private final Sonido fondo;
    
    private final Escuchadora escucha;
    private final Timer TimerEscuchadora;
    
    private final  MenuPrincipal menu;
    private final  MenuPausa menupausa;
    
    private boolean gano=false;
    private boolean perdio=false;
    /**
    * Constructor parametrico el cual inicia el menu principal y el menu de pausa asi como las otras variables a usar
     * @param ancho el ancho de la ventana
     * @param alto el alto de la ventana
    */
    public MiPanel(int ancho,int alto)
    {
        super.setLayout(null);
        
        menu = new MenuPrincipal(ancho,alto);
        
        super.add(menu);
        
        menupausa = new MenuPausa();
        
        super.add(menupausa);
        
        gun = new Sonido("Disparo.wav");
        
        explosion = new Sonido("Explosion.wav");
        
        recarga = new Sonido("Recarga.wav");
        
        fondo = new Sonido("Fondo.wav");
        
        fondo.reproducir();
        
        escucha = new Escuchadora();
        
        TimerEscuchadora = new Timer(50,escucha);
        
        ANCHO_VENTANA = ancho;
        
        ALTO_VENTANA = alto;
        
        ALTO_IMAGEN = ALTO_VENTANA/4;
        
        ALTO_MAPA = ALTO_VENTANA - ALTO_IMAGEN;
        
        super.setFocusable(true);
        
        cargarAcciones();
        
    }
    /**
     * Carga acciones de cada boton del menu principal y del menu de pausa
     */
    public void cargarAcciones(){
        
       menu.getBotones(0).addMouseListener(new MouseAdapter() { // BOTON JUGAR
            @Override
            public void mousePressed(MouseEvent e) {
                
                menu.getEmpezar().setVisible(true);
                
                for (int i = 0; i < menu.getCantBotones(); i++) {
                    menu.getBotones(i).setVisible(false);
                }

            }
            @Override
            public void mouseEntered(MouseEvent e){
                menu.getBotones(0).setForeground(Personajes.AMARILLO);
            }
            @Override
            public void mouseExited(MouseEvent e){
                menu.getBotones(0).setForeground(Color.BLACK);
            }
        });
       
       
        menu.getBotones(1).addMouseListener(new MouseAdapter() { // BOTON INSTRUCCIONES
            @Override
            public void mousePressed(MouseEvent e) {
                
                menu.getInstrucciones().setVisible(true);
                
                for (int i = 0; i < menu.getCantBotones(); i++) {
                    menu.getBotones(i).setVisible(false);
                }

            }
            @Override
            public void mouseEntered(MouseEvent e){
                menu.getBotones(1).setForeground(Personajes.AMARILLO);
            }
            @Override
            public void mouseExited(MouseEvent e){
                menu.getBotones(1).setForeground(Color.BLACK);
            }
        });
        
        
        
        menu.getBotones(2).addMouseListener(new MouseAdapter() { // BOTON TOP 10
            @Override
            public void mousePressed(MouseEvent e) {
                
                
                menu.getTop10().setVisible(true);
                menu.CargarPuntuaciones();
                
                for (int i = 0; i < menu.getCantBotones(); i++) {
                    menu.getBotones(i).setVisible(false);
                }

            }
            @Override
            public void mouseEntered(MouseEvent e){
                menu.getBotones(2).setForeground(Personajes.AMARILLO);
            }
            @Override
            public void mouseExited(MouseEvent e){
                menu.getBotones(2).setForeground(Color.BLACK);
            }
        });
        
        
        menu.getBotones(3).addMouseListener(new MouseAdapter() { // BOTON CREDITOS
            @Override
            public void mousePressed(MouseEvent e) {
                
                menu.getCreditos().setVisible(true);
                
                for (int i = 0; i < menu.getCantBotones(); i++) {
                    menu.getBotones(i).setVisible(false);
                }

            }
            @Override
            public void mouseEntered(MouseEvent e){
                menu.getBotones(3).setForeground(Personajes.AMARILLO);
            }
            @Override
            public void mouseExited(MouseEvent e){
                menu.getBotones(3).setForeground(Color.BLACK);
            }
        });
        
        
        menu.getBotones(4).addMouseListener(new MouseAdapter() { // BOTON SALIR
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
            @Override
            public void mouseEntered(MouseEvent e){
                menu.getBotones(4).setForeground(Personajes.AMARILLO);
            }
            @Override
            public void mouseExited(MouseEvent e){
                menu.getBotones(4).setForeground(Color.BLACK);
            }
        });
        
        menu.getComenzarJuego().addActionListener(new ActionListener() { // BOTON ENCARGADO DE INICIAR EL JUEGO
            @Override
            public void actionPerformed(ActionEvent e) {
                
                menu.setVisible(false);
                menu.getEmpezar().setVisible(false);
                ((JTextField)(menu.getEmpezar().getComponent(1))).setText("");
                
                for (int i = 0; i < menu.getCantBotones(); i++) {
                    menu.getBotones(i).setVisible(false);
                }
                
                inicio_juego();
                tiempo = menu.getTiempo();
                nombre = menu.getNombre();
            }
        });
        menupausa.getBotones(0).addMouseListener(new MouseAdapter() { //BOTON DE CONTINUAR PARA EL MENU PAUSA
            @Override
            public void mousePressed(MouseEvent e) {
                reiniciar_juego();
            }
            @Override
            public void mouseEntered(MouseEvent e){
                menupausa.getBotones(0).setForeground(Personajes.COLORRIO);
            }
            @Override
            public void mouseExited(MouseEvent e){
                menupausa.getBotones(0).setForeground(Color.BLACK);
            }
        
        });
        menupausa.getBotones(1).addMouseListener(new MouseAdapter() { // BOTON DE MENU PARA EL MENU PAUSA
            @Override
            public void mousePressed(MouseEvent e) {
                
                menupausa.setVisible(false);
                
                menu.setVisible(true);
                menu.setFocusable(true);
                for (int i = 0; i < menu.getCantBotones(); i++) {
                    menu.getBotones(i).setVisible(true);
                }
                salir_juego(false,true);
            }
            @Override
            public void mouseEntered(MouseEvent e){
                menupausa.getBotones(1).setForeground(Personajes.COLORRIO);
            }
            @Override
            public void mouseExited(MouseEvent e){
                menupausa.getBotones(1).setForeground(Color.BLACK);
            }
        
        });
        menupausa.getBotones(2).addMouseListener(new MouseAdapter() { // BOTON DE SALIR PARA EL MENU PAUSA
            @Override
            public void mousePressed(MouseEvent e) {
                menu.GuardarPuntuacion(nombre, puntuacion);
                System.exit(0);
            }
            @Override
            public void mouseEntered(MouseEvent e){
                menupausa.getBotones(2).setForeground(Personajes.COLORRIO);
            }
            @Override
            public void mouseExited(MouseEvent e){
                menupausa.getBotones(2).setForeground(Color.BLACK);
            }
        });
    }
    
    
    /**
     * Metodo para iniciar el juego
     */
    
    public void inicio_juego()
    {
        fondo.detener();
        
        super.setFocusable(true);
        
        super.addKeyListener(escucha);
        
        menu.setVisible(false);
        
        vidas_jugador = 3;
        
        puntuacion = 0;
        
        inicio_partida();
        
        TimerMapa = new Timer(50, new TimerMapa());
        
        TimerJugador = new Timer(50, new TimerJugador());
        
        TimerBala = new Timer(20, new TimerBala());
        
        TimerEnemigos = new Timer(50, new TimerEnemigos());
        
        TimerCombustible = new Timer(50, new TimerCombustible());
        
        reiniciar_juego();
    }
    /**
     * Metodo para iniciar la partida
     */
    public void inicio_partida()
    {
        gano=false;
        
        perdio=false;
        
        velocidad_mapa = 0;
        
        mapa = new Mapa(ANCHO_VENTANA,ALTO_MAPA);
        
        jugador = new Jugador();
        
        jugador.setposx((ANCHO_VENTANA-jugador.getimagen().getWidth(null))/2);
        
        jugador.setposy(ALTO_MAPA-jugador.getimagen().getHeight(null));
        
        escape = false;
        
        teclas = new boolean [KeyEvent.VK_DOWN+1];
        
        for(int x=0;x<teclas.length;x++)
            teclas[x] = false;
        
        combustibles = new Combustible[num_max_combustible];
        
        enemigos = new Enemigos[num_max_enemigos];
        
        
        int i = 0;
        int j = 0;
        
        for(int x=0;x<num_max_combustible+num_max_enemigos;x++){
            
            if((int)(Math.random()*2)==0&&i<num_max_combustible)
            {
                combustibles[i] = new Combustible();
                aparecer(combustibles[i],i,true);
                i++;
            }    
            else if(j<num_max_enemigos)
            {
                creo_enemigo(j);
                aparecer(enemigos[j],j,true);
                j++;
            }
            else
                x--;
        }
        
    }
    
    /**
     * Metodo para pausar el juego
     */
    public void parar_juego()
    {
        
        TimerMapa.stop();
        
        TimerJugador.stop();
        
        TimerEnemigos.stop();
        
        TimerCombustible.stop();
        
    }
    /**
     * Metodo para reiniciar el juego
     */
    public void reiniciar_juego()
    {
        
        menupausa.setVisible(false);
        
        menupausa.setFocusable(false);
                
        TimerMapa.start();
        
        TimerJugador.start();
        
        TimerEnemigos.start();
        
        TimerCombustible.start();
        
        TimerEscuchadora.start();
    }
    
    /**
     * Metodo para salir el juego
     * @param gano indica si se sale del juego cuando haya ganado o perdido
     * @param pausa indica si se sale del juego cuando haya estado en el menu de pausa
     */
    public void salir_juego(boolean gano,boolean pausa)
    {
        parar_juego();
        
        fondo.reproducir();
        
        super.removeKeyListener(escucha);
        
        TimerEscuchadora.stop();
        
        if(!pausa){
            
            repaint();
            
            if(gano){
                
                this.gano=gano;
                JOptionPane.showMessageDialog(null,"                            GANO","ESTADO DE JUEGO",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/recursos/entorno/YouWin.jpg"));
            }
            else
            {
                this.perdio=!gano;
                JOptionPane.showMessageDialog(null,"                            PERDIO","ESTADO DE JUEGO",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/recursos/entorno/YouWin.jpg"));
            }
            
        }
        
        menu.GuardarPuntuacion(nombre, puntuacion);
        
        menu.setVisible(true);
        for(int i=0;i<menu.getCantBotones();i++)
            menu.getBotones(i).setVisible(true);
    }
    
    /**
     * Metodo para entrar al menu de pausa
     */
    public void menupausa(){
    
        parar_juego();
        menupausa.setVisible(true);
        menupausa.setFocusable(true);
        
    }
    /**
     * Metodo que crea aleatoriamente un enemigo con su respectiva orientacion (hacia la izquierda o derecha)
     * @param x numero de enemigo a crear
    */
    public void creo_enemigo(int x) {
        int tipo_enemigo = (int)(Math.random()*2);
                if(tipo_enemigo==1)
                    enemigos[x] = new Helicoptero(0,0,(int)(Math.random()*2)==0);
                else
                    enemigos[x] = new Barco(0,0,(int)(Math.random()*2)==0);
    
    }
    /**
     * Metodo que devuelve una posicion correcta, alejada del objeto mandado por parametro
     * @param objeto
     * @param NumObjeto
     * @param inicio 
     */
    public void aparecer(Objeto objeto , int NumObjeto , boolean inicio){
        
        int maxy;
        int DELTA;
        
        if(inicio) // AL COMIENZO VOY A GENERAR NUMEROS ALEATORIOS DESDE UNA IMAGEN ATRAS, PARA QUE NO SE VISUALIZEN HASTA MAS DE LA MITAD DE LA VENTANA
        {
            maxy = 2*ALTO_IMAGEN;
            DELTA = (int)(Math.random()*300+50);
        }
        else // LUEGO CUANDO LOS ELIMINEN O YA HAYA BAJADO, LOS POSICIONA DESDE MUY ATRAS HASTA EL BORDE QUE ES CERO PARA QUE EL USUARIO NO LOS LLEGASE A VER
        {
            maxy = 0;
            DELTA = (int)(Math.random()*100+50);
        }
        
        int posmenor = maxy;
        
        for(int x=0;x<enemigos.length;x++)
        {
            if(enemigos[x]!=null){
                if(objeto instanceof Enemigos)
                {
                    if(NumObjeto != x)
                    {
                        if(enemigos[x].getposy()<posmenor)
                            posmenor=enemigos[x].getposy();
                    }
                }
                else
                {
                    if(enemigos[x].getposy()<posmenor)
                            posmenor=enemigos[x].getposy();
                }
            }
        }
        for(int x=0;x<combustibles.length;x++)
        {
            if(combustibles[x]!=null){
                if(objeto instanceof Combustible)
                {
                    if(NumObjeto != x)
                    {
                        if(combustibles[x].getposy()<posmenor)
                            posmenor=combustibles[x].getposy();
                    }
                }
                else
                {
                    if(combustibles[x].getposy()<posmenor)
                            posmenor=combustibles[x].getposy();
                }
            }
        }
        
        
        int posy = posmenor - objeto.getimagen().getHeight(null) - DELTA ;
        
        if(posy<=0&&posy+objeto.getimagen().getHeight(null)>=0)
            posy = - objeto.getimagen().getHeight(null);
        
                
        int minx = mapa.getBORDEIZQUIERDO(posy);     
        int maxx = mapa.getBORDEDERECHO(posy) - mapa.getBORDEIZQUIERDO(posy) - objeto.getimagen().getWidth(null);
        
        int posx = (int)(Math.random()*(maxx))+minx;  
        
        objeto.setposx(posx);
        objeto.setposy(posy);
    }
    /**
     * Metodo encargado de pintar el juego
     * @param g2 graficos encargados de pintar el juego
     */
    public void pintarJuego(Graphics2D g2){
        
        g2.drawImage(mapa.getmapa(), mapa.getposx(),mapa.getposy(), this);
        
        for(int x=0;x<combustibles.length;x++)
        {
            
            g2.drawImage(combustibles[x].getimagen(),combustibles[x].getposx(),combustibles[x].getposy() , this);
            
            if(!combustibles[x].getvivo())
            {
                combustibles[x] = new Combustible();
                aparecer(combustibles[x],x,false);
            }
        }
        
        for (int x=0;x<enemigos.length;x++) {
            
            g2.drawImage(enemigos[x].getimagen(), enemigos[x].getposx(), enemigos[x].getposy(), this);
                
            if(!enemigos[x].getvivo())
            {
                creo_enemigo(x);
                aparecer(enemigos[x],x,false);        
            }
        }
        g2.drawImage(jugador.getimagen(), jugador.getposx(), jugador.getposy(), this);
        
        g2.drawImage(mapa.getestado_combustible(),0,ALTO_VENTANA-ALTO_IMAGEN, this);
        
        g2.drawImage(mapa.getmedidor_combustible(), (int) (162+2.67*jugador.getcombustible()) ,ALTO_VENTANA-102, this);
        
        for(int x=0;x<vidas_jugador;x++)
        g2.drawImage(CargarImagen.loadImage("src/recursos/entorno/Vida.jpg"), 30 + 30*x, ALTO_VENTANA-100,null);
        
        g2.setColor(Color.BLACK);
        
        g2.setFont(Personajes.FUENTE.deriveFont((float)20));
        
        g2.drawString("HIGHSCORE: "+puntuacion,300-6*("HIGHSCORE: "+puntuacion).length(),ALTO_VENTANA-120);
        
        g2.drawString("TIME: "+(int)tiempo,300-6*("TIME: "+(int)tiempo).length(),ALTO_VENTANA-50);
        
        if(gano){
            g2.drawImage(CargarImagen.loadImage("src/recursos/entorno/YouWin.png"),0,0,ANCHO_VENTANA,ALTO_VENTANA,null);
        }
        if(perdio){
            g2.drawImage(CargarImagen.loadImage("src/recursos/entorno/GameOver.jpg"),0,0,ANCHO_VENTANA,ALTO_VENTANA,null);
        }
        if(disparo){
            g2.drawImage(bala.getimagen(), bala.getposx(),bala.getposy(), this);
        }
        
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g); 
        
        Graphics2D g2 = (Graphics2D) g;
        
        pintarJuego(g2);
        
    }
    /**
     * Clase timer jugador encargado de procesar los movimientos y acciones del jugador
     */
    class TimerJugador implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
          
            if(velocidad_mapa>0&&jugador.getcombustible()>0)
            jugador.setcombustible(jugador.getcombustible()-0.1);
            
            if( (int)jugador.getcombustible()<=0)
            {
                jugador.setvivo(false);
                jugador.explosion();
                
                explosion.reproducir();
                
                vidas_jugador--;
                if(vidas_jugador<0)
                    salir_juego(false,false);
                
            }
            
            if(vidas_jugador>0&&!jugador.getvivo()){
                jugador.setvivo(true);
                inicio_partida();
            }
             
        }
    }

    
    /**
     * Clase timer mapa encargado de procesar los movimientos y acciones del mapa
     */
    class TimerMapa implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(velocidad_mapa!=0)
            tiempo-=TimerMapa.getDelay()/1000.0;
            
            if(tiempo<0)
                salir_juego(true,false);
            
            
            mapa.setposy(mapa.getposy()+velocidad_mapa);
            
            for (Enemigos enemigo : enemigos){
                enemigo.setposy(enemigo.getposy() + velocidad_mapa);
                if (enemigo instanceof Helicoptero) {
                    enemigo.moverx(velocidad_helicoptero,mapa.getBORDEIZQUIERDO(enemigo.getposy()),mapa.getBORDEDERECHO(enemigo.getposy()));
                } else {
                    enemigo.moverx(velocidad_barco,mapa.getBORDEIZQUIERDO(enemigo.getposy()),mapa.getBORDEDERECHO(enemigo.getposy()));
                }
            }
                
            for(Combustible combustible : combustibles)
                combustible.setposy(combustible.getposy()+velocidad_mapa);
            
            
            if(mapa.getposy()>=0)
            {
                mapa.crearMapa();
            }
            repaint();
        }
    }
    /**
     * Clase timer bala encargado de procesar los movimientos y acciones de la bala
     */
    class TimerBala implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            
                    
            if(bala.getvivo())
            bala.setposy(bala.getposy()-velocidad_bala);
                   
            for (Enemigos enemigo : enemigos) {
                if (bala.getvivo()&&(bala.area().intersects(enemigo.area()) || bala.area().contains(enemigo.area())) ) {
                    puntuacion+=100;
                    bala.setvivo(false);
                    disparo=false;
                    enemigo.setvivo(false);
                    enemigo.explosion();
                    
                    explosion.reproducir();
                    
                    break; 
                }
            }
            for (Combustible combustible : combustibles) {
                if (bala.area().intersects(combustible.area()) && bala.getvivo() || bala.area().contains(combustible.area())) {
                    if(puntuacion>0)
                        puntuacion-=100;
                    if(puntuacion<0)
                        puntuacion=0;
                    bala.setvivo(false);
                    disparo=false;
                    combustible.setvivo(false);
                    combustible.explosion();
                    
                    explosion.reproducir();
                    
                    break; 
                }
            }
        
        }

    }
    /**
     * Clase timer enemigos encargado de procesar los movimientos y acciones de los enemigos
     */
    class TimerEnemigos implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            
            for (Enemigos enemigo : enemigos) {
                
                if (enemigo.getvivo()&& jugador.getvivo() && (enemigo.area().intersects(jugador.area()) || enemigo.area().contains(jugador.area()))) {
                    
                    jugador.setvivo(false);
                    jugador.explosion();
                    enemigo.setvivo(false);
                    enemigo.explosion();
                    
                    explosion.reproducir();
                    vidas_jugador--;
                    if(vidas_jugador<=0)
                        salir_juego(false,false);
                    
                    
                    break;
                }
            }
            
            for(int x=0;x<enemigos.length;x++){
                if(enemigos[x].getposy()>=ALTO_MAPA){
                    creo_enemigo(x);
                    aparecer(enemigos[x],x,false);
                }
            }
            
        }
        
    }
    /**
     *  Clase timer combustible encargado de procesar los movimientos y acciones del combustible
     */
    class TimerCombustible implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            
            
            for (int x=0;x<combustibles.length;x++) {
                
                if(combustibles[x].area().intersects(jugador.area())||combustibles[x].area().contains(jugador.area()))
                {
                    
                    jugador.setcombustible(100);
                
                    recarga.reproducir();
                }
                
                if(combustibles[x].getposy()>ALTO_MAPA)
                {
                    aparecer(combustibles[x], x, false);
                            
                }
            }
            
        }
        
    }
    /**
     *  Clase timer escuchadora encargado de procesar las teclas
     */
    class Escuchadora implements KeyListener,ActionListener{

        @Override
        public void keyTyped(KeyEvent e) {
            if(e.getKeyCode()>=0&&e.getKeyCode()<=KeyEvent.VK_DOWN)
                teclas[e.getKeyCode()]= true;
            
        }
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()>=0&&e.getKeyCode()<=KeyEvent.VK_DOWN)
                teclas[e.getKeyCode()]= true;
        }
        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode()>=0&&e.getKeyCode()<=KeyEvent.VK_DOWN)
                teclas[e.getKeyCode()]= false; 
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(velocidad_mapa!=0)
            {
                if (teclas[KeyEvent.VK_LEFT]) {


                    if(jugador.getvivo()&&!jugador.moverme(true, false,mapa.getBORDEIZQUIERDO(jugador.getposy()),mapa.getBORDEDERECHO(jugador.getposy())))
                    {
                        explosion.reproducir();
                        
                        vidas_jugador--;
                        if(vidas_jugador<=0)
                            salir_juego(false,false);


                    }
                }
                
                if (teclas[KeyEvent.VK_RIGHT]) {


                    if(jugador.getvivo()&&!jugador.moverme(false, true,mapa.getBORDEIZQUIERDO(jugador.getposy()),mapa.getBORDEDERECHO(jugador.getposy())))
                    {
                        explosion.reproducir();
                        
                        vidas_jugador--;
                    
                        if(vidas_jugador<=0)
                        salir_juego(false,false);
                    }

                }
                
                if (teclas[KeyEvent.VK_DOWN]) {


                    if(velocidad_mapa-frenado_mapa>=velocidad_minima_mapa && velocidad_mapa!=0 )
                    velocidad_mapa-=frenado_mapa;
                }
                
                
                
                if(teclas[KeyEvent.VK_SPACE]){
                
                disparo = true;
                
                
                
                if(bala==null||bala.getposy()<0-bala.getimagen().getHeight(null)||!bala.getvivo())
                {
                    gun.reproducir();
                    
                    bala = new Bala();
                    bala.setposx(jugador.getposx()+jugador.getimagen().getWidth(null)/2-bala.getimagen().getWidth(null)/2);
                    bala.setposy(jugador.getposy()-bala.getimagen().getHeight(null));
                }
                
                
                TimerBala.start();
            
                }
            }
            
            if (teclas[KeyEvent.VK_UP]) {

                    jugador.setcombustible(jugador.getcombustible()-0.5);

                    if(velocidad_mapa+aceleracion_mapa<=velocidad_maxima_mapa)
                    velocidad_mapa+=aceleracion_mapa;
            }
            
            
            if(teclas[KeyEvent.VK_ESCAPE])
            {
                escape=!escape;
                if(escape)
                    menupausa();
                else
                    reiniciar_juego();
            }
            if (!teclas[KeyEvent.VK_DOWN]&& !teclas[KeyEvent.VK_UP]&& velocidad_mapa!=0) // SI YA NO TENGO PRESIONADA LA TECLA HACIA ABAJO NI LA TECLA HACIA ARRIBA
            {
                velocidad_mapa=velocidad_inicial_mapa;
            }
            if(!teclas[KeyEvent.VK_LEFT] && !teclas[KeyEvent.VK_RIGHT]) //SI YA NO TENGO PRESIONADA NI LA TECLA IZQUIERDA NI LA DERECHA
            {    
               if(jugador.getvivo()&&!jugador.sinmover(mapa.getBORDEIZQUIERDO(jugador.getposy()),mapa.getBORDEDERECHO(jugador.getposy())))
               {
                    explosion.reproducir();
                        
                    vidas_jugador--;
                    if(vidas_jugador<=0)
                     salir_juego(false,false);
               }
                   
            }
        
        }
        
    }
    
}
