package objetos;

import java.awt.Color;
import utilidades.StdDraw;

/**
 * Esta clase representa un objeto Circulo.
 */
public class Circulo {

    private Punto centro;
    private double radio;

    /**
     * Constructor que inicializa un Circulo con un centro y un radio.
     *
     * @param p     El centro del círculo.
     * @param radio El radio del círculo.
     */
    public Circulo(Punto p, double radio) {
        setCentro(p);
        setRadio(radio);
    }

    /**
     * Constructor que inicializa un Circulo con coordenadas del centro y un radio.
     *
     * @param xCentro Coordenada x del centro del círculo.
     * @param yCentro Coordenada y del centro del círculo.
     * @param r       El radio del círculo.
     */
    public Circulo(double xCentro, double yCentro, double r) {
        this(new Punto(xCentro, yCentro), r);
    }

    /**
     * Constructor que inicializa un Circulo con centro en (0,0) y radio 1.
     */
    public Circulo() {
        this(new Punto(0, 0), 1);
    }

    /**
     * Obtiene el centro del círculo.
     *
     * @return El centro del círculo.
     */
    public Punto getCentro() {
        return centro;
    }

    /**
     * Establece el centro del círculo.
     *
     * @param centro El nuevo centro del círculo.
     * @throws IllegalArgumentException Si el centro es nulo.
     */
    public void setCentro(Punto centro) {
        if (centro == null)
            throw new IllegalArgumentException("Un círculo debe tener un objeto Punto como centro");
        this.centro = centro;
    }

    /**
     * Obtiene el radio del círculo.
     *
     * @return El radio del círculo.
     */
    public double getRadio() {
        return radio;
    }

    /**
     * Establece el radio del círculo.
     *
     * @param radio El nuevo radio del círculo.
     * @throws IllegalArgumentException Si el radio es menor o igual a cero.
     */
    public void setRadio(double radio) {
        if (radio <= 0)
            throw new IllegalArgumentException("Un círculo debe tener radio positivo");
        this.radio = radio;
    }

    /**
     * Devuelve una representación en cadena del círculo.
     *
     * @return Una cadena que representa el círculo.
     */
    @Override
    public String toString() {
        return "Circulo [centro=" + centro + ", radio=" + radio + "]";
    }

    /**
     * Dibuja el círculo relleno en el lienzo.
     */
    public void dibujar() {
        StdDraw.setPenColor(centro.getColor());
        StdDraw.filledCircle(centro.getX(), centro.getY(), radio);
    }

    /**
     * Dibuja el contorno del círculo con un grosor especificado en el lienzo.
     *
     * @param grosorContorno El grosor del contorno del círculo.
     */
    public void dibujar(double grosorContorno) {
        StdDraw.setPenColor(centro.getColor());
        StdDraw.setPenRadius(grosorContorno);
        StdDraw.circle(centro.getX(), centro.getY(), radio);
    }

    /**
     * Verifica si este círculo se superpone con otro círculo dado.
     *
     * @param c El otro círculo con el que se compara.
     * @return true si los círculos se superponen, false en caso contrario.
     */
    public boolean seSuperpone(Circulo c) {
        return this.centro.distancia(c.getCentro()) < this.radio + c.getRadio();
    }

    /**
     * Realiza un movimiento del círculo en el plano.
     *
     * @param movX La cantidad de movimiento en el eje x.
     * @param movY La cantidad de movimiento en el eje y.
     */
    public void movimiento(double movX, double movY) {
        centro.setMovX(movX);
        centro.setMovY(movY);
    }

    /**
     * Mueve el centro del círculo de acuerdo con las velocidades actuales.
     */
    public void mover() {
        centro.mover();
    }

    /**
     * Establece el color del círculo.
     *
     * @param c El color a establecer.
     */
    public void color(Color c) {
        centro.setColor(c);
    }

    /**
     * Verifica si hay colisión entre este círculo y un rectángulo dado.
     *
     * @param r El rectángulo con el que se verifica la colisión.
     * @return Un número que indica la zona de colisión:
     * 0 - No hay colisión
     * 1 - Derecha
     * 2 - Izquierda
     * 3 - Arriba
     * 4 - Abajo
     * 5 - Esquina superior derecha
     * 6 - Esquina superior izquierda
     * 7 - Esquina inferior izquierda
     * 8 - Esquina inferior derecha
     */
    public int colision(Rectangulo r) {
        int zonaColision = 0;
        if (centro.getX() + radio >= r.getInfDer().getX())
            zonaColision = 1;
        if (centro.getX() - radio <= r.getSupIzq().getX())
            zonaColision = 2;
        if (centro.getY() + radio >= r.getSupIzq().getY())
            zonaColision = 3;
        if (centro.getY() - radio <= r.getInfDer().getY())
            zonaColision = 4;
        if (centro.getX() + radio >= r.getInfDer().getX() && centro.getY() + radio >= r.getSupIzq().getY())
            zonaColision = 5;
        if (centro.getX() - radio <= r.getSupIzq().getX() && centro.getY() + radio >= r.getSupIzq().getY())
            zonaColision = 6;
        if (centro.getX() - radio <= r.getSupIzq().getX() && centro.getY() - radio <= r.getInfDer().getY())
            zonaColision = 7;
        if (centro.getX() + radio >= r.getInfDer().getX() && centro.getY() - radio <= r.getInfDer().getY())
            zonaColision = 8;

        return zonaColision;
    }
}
