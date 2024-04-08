package objetos;

import java.awt.Color;
import utilidades.StdDraw;

/**
 * Esta clase representa un objeto Rectangulo en un plano bidimensional.
 */
public class Rectangulo {
    private Punto supIzq; // El color de este punto indica el color del rectángulo
    private Punto infDer;

    /**
     * Constructor que inicializa un Rectangulo con dos puntos dados.
     *
     * @param supIzq El vértice superior izquierdo del rectángulo.
     * @param infDer El vértice inferior derecho del rectángulo.
     */
    public Rectangulo(Punto supIzq, Punto infDer) {
        this.supIzq = supIzq;
        this.infDer = infDer;
        setSupIzq(supIzq);
        setInfDer(infDer);
    }

    /**
     * Constructor que inicializa un Rectangulo con un punto superior izquierdo y dimensiones de ancho y alto.
     *
     * @param supIzq El vértice superior izquierdo del rectángulo.
     * @param ancho  El ancho del rectángulo.
     * @param alto   La altura del rectángulo.
     */
    public Rectangulo(Punto supIzq, double ancho, double alto) {
        this(supIzq, new Punto(supIzq.getX() + ancho, supIzq.getY() - alto));
    }

    /**
     * Constructor que inicializa un Rectangulo con coordenadas de vértices dados.
     *
     * @param supIzqX La coordenada x del vértice superior izquierdo del rectángulo.
     * @param supIzqY La coordenada y del vértice superior izquierdo del rectángulo.
     * @param infDerX La coordenada x del vértice inferior derecho del rectángulo.
     * @param infDerY La coordenada y del vértice inferior derecho del rectángulo.
     */
    public Rectangulo(double supIzqX, double supIzqY, double infDerX, double infDerY) {
        this(new Punto(supIzqX, supIzqY), new Punto(infDerX, infDerY));
    }

    /**
     * Calcula y devuelve el área del rectángulo.
     *
     * @return El área del rectángulo.
     */
    public double area() {
        return base() * altura();
    }

    /**
     * Calcula y devuelve la longitud de la base del rectángulo.
     *
     * @return La longitud de la base del rectángulo.
     */
    public double base() {
        return infDer.getX() - supIzq.getX();
    }

    /**
     * Calcula y devuelve la altura del rectángulo.
     *
     * @return La altura del rectángulo.
     */
    public double altura() {
        return supIzq.getY() - infDer.getY();
    }

    /**
     * Devuelve una representación en cadena del rectángulo.
     *
     * @return Una cadena que representa el rectángulo con sus vértices.
     */
    @Override
    public String toString() {
        return "Rectangulo [supIzq=" + supIzq + ", infDer=" + infDer + "]";
    }

    /**
     * Obtiene el vértice superior izquierdo del rectángulo.
     *
     * @return El vértice superior izquierdo del rectángulo.
     */
    public Punto getSupIzq() {
        return supIzq;
    }

    /**
     * Establece el vértice superior izquierdo del rectángulo.
     *
     * @param supIzq El nuevo vértice superior izquierdo del rectángulo.
     * @throws IllegalArgumentException Si el vértice es nulo o si las coordenadas del vértice son inválidas.
     */
    public void setSupIzq(Punto supIzq) {
        if (supIzq == null)
            throw new IllegalArgumentException("No se puede utilizar null como vértice superior izquierdo");
        if (supIzq.getX() >= infDer.getX() || supIzq.getY() <= infDer.getY())
            throw new IllegalArgumentException("Punto incorrecto para rectángulo");
        this.supIzq = supIzq;
    }

    /**
     * Obtiene el vértice inferior derecho del rectángulo.
     *
     * @return El vértice inferior derecho del rectángulo.
     */
    public Punto getInfDer() {
        return infDer;
    }

    /**
     * Establece el vértice inferior derecho del rectángulo.
     *
     * @param infDer El nuevo vértice inferior derecho del rectángulo.
     * @throws IllegalArgumentException Si el vértice es nulo o si las coordenadas del vértice son inválidas.
     */
    public void setInfDer(Punto infDer) {
        if (infDer == null)
            throw new IllegalArgumentException("No se puede utilizar null como vértice inferior derecho");
        if (supIzq.getX() >= infDer.getX() || supIzq.getY() <= infDer.getY())
            throw new IllegalArgumentException("Punto incorrecto para rectángulo");
        this.infDer = infDer;
    }

    /**
     * Dibuja el rectángulo en el lienzo con el grosor especificado.
     *
     * @param grosor El grosor del rectángulo.
     */
    public void dibujar(double grosor) {
        StdDraw.setPenColor(supIzq.getColor());
        StdDraw.setPenRadius(grosor);
        StdDraw.rectangle(centro().getX(), centro().getY(), base() / 2, altura() / 2);
    }

    /**
     * Dibuja el rectángulo en el lienzo.
     */
    public void dibujar() {
        dibujar(true);
    }

    /**
     * Dibuja el rectángulo en el lienzo con opción para rellenar o no.
     *
     * @param relleno true para rellenar el rectángulo, false para dibujar solo el contorno.
     */
    public void dibujar(boolean relleno) {
        StdDraw.setPenColor(supIzq.getColor());
        if (relleno)
            StdDraw.filledRectangle(centro().getX(), centro().getY(), base() / 2, altura() / 2);
        else {
            StdDraw.setPenRadius(0.005);
            StdDraw.rectangle(centro().getX(), centro().getY(), base() / 2, altura() / 2);
        }
    }

    /**
     * Calcula y devuelve el punto en el centro del rectángulo.
     *
     * @return El punto en el centro del rectángulo.
     */
    public Punto centro() {
        return supIzq.puntoMedio(infDer);
    }

    /**
     * Verifica si este rectángulo está completamente dentro de otro rectángulo dado.
     *
     * @param otro El otro rectángulo con el que se comparará este rectángulo.
     * @return true si este rectángulo está completamente dentro del otro, false de lo contrario.
     */
    public boolean dentroDe(Rectangulo otro) {
        return supIzq.getX() > otro.getSupIzq().getX() && supIzq.getY() < otro.getSupIzq().getY() &&
                infDer.getX() < otro.getInfDer().getX() && infDer.getY() > otro.getInfDer().getY();
    }

    /**
     * Calcula y devuelve el punto en el centro del lado derecho del rectángulo.
     *
     * @return El punto en el centro del lado derecho del rectángulo.
     */
    public Punto centroDer() {
        return new Punto(infDer.getX(), infDer.getY() + altura() / 2);
    }

    /**
     * Calcula y devuelve el punto en el centro del lado inferior del rectángulo.
     *
     * @return El punto en el centro del lado inferior del rectángulo.
     */
    public Punto centroInf() {
        return new Punto(infDer.getX() - base() / 2, infDer.getY());
    }

    /**
     * Calcula y devuelve el punto en el centro del lado izquierdo del rectángulo.
     *
     * @return El punto en el centro del lado izquierdo del rectángulo.
     */
    public Punto centroIzq() {
        return new Punto(supIzq.getX(), supIzq.getY() - altura() / 2);
    }

    /**
     * Calcula y devuelve el punto en el centro del lado superior del rectángulo.
     *
     * @return El punto en el centro del lado superior del rectángulo.
     */
    public Punto centroSup() {
        return new Punto(supIzq.getX() + base() / 2, supIzq.getY());
    }

    /**
     * Devuelve el punto en el vértice inferior izquierdo del rectángulo.
     *
     * @return El punto en el vértice inferior izquierdo del rectángulo.
     */
    public Punto infIzq() {
        return new Punto(supIzq.getX(), infDer.getY());
    }

    /**
     * Devuelve el punto en el vértice superior derecho del rectángulo.
     *
     * @return El punto en el vértice superior derecho del rectángulo.
     */
    public Punto supDer() {
        return new Punto(infDer.getX(), supIzq.getY());
    }

    /**
     * Establece el color del rectángulo.
     *
     * @param c El color a establecer para el rectángulo.
     */
    public void color(Color c) {
        supIzq.setColor(c);
    }

    /**
     * Establece las velocidades de movimiento para los vértices del rectángulo.
     *
     * @param mX La velocidad de movimiento en el eje x.
     * @param mY La velocidad de movimiento en el eje y.
     */
    public void movimiento(double mX, double mY) {
        supIzq.setMovX(mX);
        supIzq.setMovY(mY);
        infDer.setMovX(mX);
        infDer.setMovY(mY);
    }

    /**
     * Realiza el movimiento del rectángulo según sus velocidades actuales.
     */
    public void mover() {
        supIzq.mover();
        infDer.mover();
    }

    /**
     * Verifica si este rectángulo colisiona con otro rectángulo dado.
     *
     * @param otro El otro rectángulo con el que se verificará la colisión.
     * @return Un valor entero que indica la zona de colisión:
     *         0 - No hay colisión,
     *         1 - Colisión en el lado derecho,
     *         2 - Colisión en el lado izquierdo,
     *         3 - Colisión en el lado superior,
     *         4 - Colisión en el lado inferior,
     *         5 - Colisión en la esquina superior derecha,
     *         6 - Colisión en la esquina superior izquierda,
     *         7 - Colisión en la esquina inferior izquierda,
     *         8 - Colisión en la esquina inferior derecha.
     */
    public int colision(Rectangulo otro) {
        /* 0-No hay colision, 1-Derecha, 2-Izquierda, 3-Arriba, 4-Abajo,
           5-esquina sup dcha, 6-esquina sup izq, 7-esquina inf  izq, 8-esquina inf der, */
        int zonaColision = 0;
        if (infDer.getX() >= otro.getInfDer().getX())
            zonaColision = 1;
        if (supIzq.getX() <= otro.getSupIzq().getX())
            zonaColision = 2;
        if (supIzq.getY() >= otro.getSupIzq().getY())
            zonaColision = 3;
        if (infDer.getY() <= otro.getInfDer().getY())
            zonaColision = 4;
        if (infDer.getX() >= otro.getInfDer().getX() && supIzq.getY() >= otro.getSupIzq().getY())
            zonaColision = 5;
        if (supIzq.getX() <= otro.getSupIzq().getX() && supIzq.getY() >= otro.getSupIzq().getY())
            zonaColision = 6;
        if (infDer.getY() <= otro.getInfDer().getY() && supIzq.getX() <= otro.getSupIzq().getX())
            zonaColision = 7;
        if (infDer.getY() <= otro.getInfDer().getY() && infDer.getX() >= otro.getInfDer().getX())
            zonaColision = 8;
        return zonaColision;
    }
}
