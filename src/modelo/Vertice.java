package modelo;

// A cargo de: Tisalema Guashco Darwin Joel
// Nodo de la lista de vertices: representa un equipo de la red.
public class Vertice {

    // Datos de la lista principal y lista de aristas que salen del equipo.
    private final String id;
    private Arista primeraArista;
    private Vertice siguiente;

    // Marcas auxiliares para ejecutar BFS sin Queue ni colecciones.
    private boolean visitado;
    private Vertice sigCola;

    public Vertice(String id) {
        this.id = id;
    }

    public String getId() { return id; }
    public Arista getPrimeraArista() { return primeraArista; }
    public void setPrimeraArista(Arista primeraArista) { this.primeraArista = primeraArista; }
    public Vertice getSiguiente() { return siguiente; }
    public void setSiguiente(Vertice siguiente) { this.siguiente = siguiente; }
    public boolean isVisitado() { return visitado; }
    public void setVisitado(boolean visitado) { this.visitado = visitado; }
    public Vertice getSigCola() { return sigCola; }
    public void setSigCola(Vertice sigCola) { this.sigCola = sigCola; }

}
