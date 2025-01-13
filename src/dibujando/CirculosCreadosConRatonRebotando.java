package dibujando;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import objetos.Circulo;
import objetos.Punto;
import objetos.Rectangulo;
import utilidades.StdDraw;

/**
 * La clase CirculosCreadosConRatonRebotando proporciona la lógica para ejecutar el programa.
 * Genera círculos al hacer clic en la pantalla, los cuales rebotan .
 * 
 * @author favio
 * @version 1.8, 04/03/24
 * @since 1.8
 */
public class CirculosCreadosConRatonRebotando {
    
    /**
     * Variable booleana que controla el estado de si se ha pulsado recientemente el ratón.
     */
    private static boolean recienPulsado=false;

    /**
     * Método principal para ejecutar el programa.
     * 
     * @param args Argumentos de la línea de comandos (no utilizados)
     * @param ventana Rectangulo que toma todo el tamaño de la ventana.
     * @param circ Array List de circulos.
     * @param r random para obtener numeros random.
     * @param c circulo generado en las coordenadas de la posicion del raton.
     * @param col define el color (aleatorio) del circulo generado.
     * @param movXactual determina el movimiento de pixeles del eje x en el circulo
     * @param movYactual determina el movimiento de pixeles del eje y en el circulo
     * @param colision numero entero que define  el estado de el circulo.
     */
    public static void main(String[] args) {
        StdDraw.setXscale(-100, 100);
        StdDraw.setYscale(-100, 100);
        StdDraw.enableDoubleBuffering();
        
        // Rectangulo que representa la ventana
        Rectangulo ventana = new Rectangulo(new Punto(-100, 100), new Punto(100, -100));
        
        // Circulos dentro de la ventana, añadiremos un círculo por cada click
        List<Circulo> circ = new ArrayList<Circulo>();
                
        for (;;) {
            StdDraw.clear();
            
            for (Circulo c : circ) {
                c.dibujar();
                c.mover();
            }
            
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.text(0, 90, "Click para añadir círculos: " + circ.size());
            
            if (clickRaton()) {
                Random r = new Random();
                Circulo c = new Circulo(StdDraw.mouseX(), StdDraw.mouseY(), r.nextDouble(5, 10));
                c.movimiento(r.nextDouble(-2, 2), r.nextDouble(-2, 2));
                Color col = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
                c.color(col);
                circ.add(c);
            }
            
            double movXactual = 0;
            double movYactual = 0;
            
            for (Circulo c : circ) {
                int colision = c.colision(ventana);
                switch (colision) {
                    case 1:
                    case 2: // Colisión izq o dcha
                        movXactual = c.getCentro().getMovX();
                        movYactual = c.getCentro().getMovY();
                        c.movimiento(-movXactual, movYactual);
                        break;
                    case 3:
                    case 4: // Colisión arriba o abajo
                        movXactual = c.getCentro().getMovX();
                        movYactual = c.getCentro().getMovY();
                        c.movimiento(movXactual, -movYactual);
                        break;
                    case 5:
                    case 6:
                    case 7:
                    case 8: // Colisión en esquina (dos lados)
                        movXactual = c.getCentro().getMovX();
                        movYactual = c.getCentro().getMovY();
                        c.movimiento(-movXactual, -movYactual);
                        break;
                }
            }
            
            StdDraw.show();
            StdDraw.pause(10);
        }
    }

    /**
     * Método para pintar los ejes coordenados.
     */
    private static void pintarEjes() {
        // Ejes
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.003);
        StdDraw.line(0, -100, 0, 100);
        StdDraw.text(2, 98, "y");
        StdDraw.line(-100, 0, 100, 0);
        StdDraw.text(98, 2, "x");
    }

    /**
     * Método para detectar si se ha hecho clic con el ratón.
     * 
     * @return true si se ha hecho clic, false de lo contrario
     */
    private static boolean clickRaton() {
        if (StdDraw.isMousePressed()) {
            if (!recienPulsado) {
                recienPulsado = true;
                return true;
            }
        } else
            recienPulsado = false;
        return false;
    }

}
