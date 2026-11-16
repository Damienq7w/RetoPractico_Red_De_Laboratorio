package modelo;

// A cargo de: Tisalema Guashco Darwin Joel
// Nodo de la lista de aristas: representa un enlace dirigido con peso.
public class Arista {

    // Destino del enlace, latencia en milisegundos y siguiente enlace del origen.
    private final Vertice destino;
    private int peso;
    private Arista siguiente;

    public Arista(Vertice destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public Vertice getDestino() { return destino; }
    public int getPeso() { return peso; }
    public void setPeso(int peso) { this.peso = peso; }
    public Arista getSiguiente() { return siguiente; }
    public void setSiguiente(Arista siguiente) { this.siguiente = siguiente; }

}
