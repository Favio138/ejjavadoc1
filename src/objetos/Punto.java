package objetos;

import java.awt.Color;
import utilidades.StdDraw;

/**
 * Esta clase representa un objeto Punto en un plano bidimensional.
 */
public class Punto {
    private double x;
    private double y;
    private Color color;
    private double movX;
    private double movY;

    /**
     * Obtiene la coordenada x del punto.
     *
     * @return La coordenada x del punto.
     */
    public double getX() {
        return x;
    }

    /**
     * Obtiene el color del punto.
     *
     * @return El color del punto.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Establece el color del punto.
     *
     * @param color El color a establecer para el punto.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Obtiene la velocidad de movimiento en el eje x del punto.
     *
     * @return La velocidad de movimiento en el eje x.
     */
    public double getMovX() {
        return movX;
    }

    /**
     * Establece la velocidad de movimiento en el eje x del punto.
     *
     * @param movX La velocidad de movimiento en el eje x a establecer.
     */
    public void setMovX(double movX) {
        this.movX = movX;
    }

    /**
     * Obtiene la velocidad de movimiento en el eje y del punto.
     *
     * @return La velocidad de movimiento en el eje y.
     */
    public double getMovY() {
        return movY;
    }

    /**
     * Establece la velocidad de movimiento en el eje y del punto.
     *
     * @param movY La velocidad de movimiento en el eje y a establecer.
     */
    public void setMovY(double movY) {
        this.movY = movY;
    }

    /**
     * Establece la coordenada x del punto.
     *
     * @param x La coordenada x a establecer para el punto.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Obtiene la coordenada y del punto.
     *
     * @return La coordenada y del punto.
     */
    public double getY() {
        return y;
    }

    /**
     * Establece la coordenada y del punto.
     *
     * @param y La coordenada y a establecer para el punto.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Devuelve una representación en cadena del punto.
     *
     * @return Una cadena que representa el punto con sus coordenadas.
     */
    @Override
    public String toString() {
        return "Punto [x=" + x + ", y=" + y + "]";
    }

    /**
     * Constructor que inicializa un Punto con coordenadas dadas.
     *
     * @param x La coordenada x del punto.
     * @param y La coordenada y del punto.
     */
    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
        color = Color.BLACK;
    }

    /**
     * Dibuja el punto en el lienzo con el color y grosor predeterminados.
     */
    public void dibujar() {
        if (color == null)
            dibujar(Color.BLACK, 0.02);
        else
            dibujar(color, 0.02);
    }

    /**
     * Dibuja el punto en el lienzo con el color y grosor especificados.
     *
     * @param c       El color del punto.
     * @param grosor  El grosor del punto.
     */
    public void dibujar(Color c, double grosor) {
        StdDraw.setPenColor(c);
        StdDraw.setPenRadius(grosor);
        StdDraw.point(x, y);
    }

    /**
     * Calcula la distancia entre este punto y otro punto dado.
     *
     * @param p El punto al que se calculará la distancia.
     * @return La distancia entre este punto y el punto dado.
     */
    public double distancia(Punto p) {
        return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
    }

    /**
     * Calcula el punto medio entre dos puntos dados.
     *
     * @param p1 El primer punto.
     * @param p2 El segundo punto.
     * @return El punto medio entre p1 y p2.
     */
    public static Punto puntoMedio(Punto p1, Punto p2) {
        return new Punto((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    /**
     * Calcula el punto medio entre este punto y otro punto dado.
     *
     * @param p El otro punto.
     * @return El punto medio entre este punto y el punto dado.
     */
    public Punto puntoMedio(Punto p) {
        return puntoMedio(this, p);
    }

    /**
     * Realiza el movimiento del punto según sus velocidades actuales.
     */
    public void mover() {
        x = x + movX;
        y = y + movY;
    }

    /**
     * Rota el punto alrededor del origen por un ángulo dado en grados.
     *
     * @param grados El ángulo de rotación en grados.
     */
    public void rotar(double grados) {
        double rad = Math.toRadians(grados);
        double nuevaX = x * Math.cos(rad) - y * Math.sin(rad);
        double nuevaY = x * Math.sin(rad) + y * Math.cos(rad);
        x = nuevaX;
        y = nuevaY;
    }

    /**
     * Verifica si este punto está dentro de un círculo dado.
     *
     * @param c El círculo en el que se verificará si este punto está dentro.
     * @return true si el punto está dentro del círculo, false de lo contrario.
     */
    public boolean dentroDe(Circulo c) {
        return distancia(c.getCentro()) < c.getRadio();
    }
}
