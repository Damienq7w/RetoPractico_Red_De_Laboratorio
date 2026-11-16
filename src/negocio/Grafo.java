package negocio;

import modelo.Arista;
import modelo.Vertice;

// Grafo dirigido y ponderado con multilistas. Solo usa Vertice, Arista y Grafo.
public class Grafo {

    private Vertice cabeza; // primer vertice de la lista

    // ===== INSERCION Y CONSULTA =====
    // A cargo de: Silva Camuendo Luis Alexander

    // Busca un equipo por su nombre; null si no existe. O(V)
    private Vertice buscar(String id) {
        Vertice actual = cabeza;
        while (actual != null) {
            if (actual.getId().equals(id)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    // Inserta un equipo al inicio de la lista.
    // false si el id es null, vacio o repetido. O(V)
    public boolean agregarVertice(String id) {
        if (id == null || id.trim().isEmpty() || buscar(id) != null) {
            return false;
        }
        Vertice nuevo = new Vertice(id);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
        return true;
    }

    // Inserta el enlace origen -> destino al inicio de la lista de origen.
    // false si falta un equipo, es un lazo, el peso es negativo o ya existe. O(V + grado)
    public boolean agregarArista(String origen, String destino, int peso) {
        if (peso < 0 || origen == null || origen.equals(destino)) {
            return false;
        }
        Vertice vOrigen = buscar(origen);
        Vertice vDestino = buscar(destino);
        if (vOrigen == null || vDestino == null) {
            return false;
        }
        Arista actual = vOrigen.getPrimeraArista();
        while (actual != null) {
            if (actual.getDestino() == vDestino) {
                return false; // el enlace ya existe
            }
            actual = actual.getSiguiente();
        }
        Arista nueva = new Arista(vDestino, peso);
        nueva.setSiguiente(vOrigen.getPrimeraArista());
        vOrigen.setPrimeraArista(nueva);
        return true;
    }

    // Lista de adyacencia, una linea por equipo. O(V + A)
    public String mostrar() {
        if (cabeza == null) {
            return "(grafo vacio)";
        }
        StringBuilder sb = new StringBuilder();
        Vertice v = cabeza;
        while (v != null) {
            sb.append("[").append(v.getId()).append("]");
            Arista a = v.getPrimeraArista();
            while (a != null) {
                sb.append(" -> (").append(a.getDestino().getId())
                  .append(",").append(a.getPeso()).append(")");
                a = a.getSiguiente();
            }
            sb.append(" -> null");
            if (v.getSiguiente() != null) {
                sb.append("\n");
            }
            v = v.getSiguiente();
        }
        return sb.toString();
    }

    // Cuenta los equipos recorriendo la lista. O(V)
    public int totalVertices() {
        int total = 0;
        Vertice v = cabeza;
        while (v != null) {
            total++;
            v = v.getSiguiente();
        }
        return total;
    }

    // Cuenta los enlaces que salen del equipo.
    // -1 si el equipo no existe. O(V + grado)
    public int grado(String id) {
        Vertice v = buscar(id);
        if (v == null) {
            return -1;
        }
        int grado = 0;
        Arista a = v.getPrimeraArista();
        while (a != null) {
            grado++;
            a = a.getSiguiente();
        }
        return grado;
    }

// Cambia la latencia de un enlace existente.
    // false si el peso es negativo o el enlace no existe. O(V + grado)
    public boolean actualizarPeso(String origen, String destino, int peso) {
        if (peso < 0) {
            return false;
        }
        Vertice vOrigen = buscar(origen);
        if (vOrigen == null) {
            return false;
        }
        Arista a = vOrigen.getPrimeraArista();
        while (a != null) {
            if (a.getDestino().getId().equals(destino)) {
                a.setPeso(peso);
                return true;
            }
            a = a.getSiguiente();
        }
        return false;
    }

    // Quita el enlace origen -> destino usando un puntero previo.
    // false si no existe. O(V + grado)
    public boolean eliminarArista(String origen, String destino) {
        Vertice vOrigen = buscar(origen);
        Vertice vDestino = buscar(destino);
        if (vOrigen == null || vDestino == null) {
            return false;
        }
        return quitarEnlace(vOrigen, vDestino);
    }

    // Auxiliar: desenlaza de la lista de vOrigen la arista que va a vDestino.
    private boolean quitarEnlace(Vertice vOrigen, Vertice vDestino) {
        Arista previa = null;
        Arista actual = vOrigen.getPrimeraArista();
        while (actual != null) {
            if (actual.getDestino() == vDestino) {
                if (previa == null) {
                    vOrigen.setPrimeraArista(actual.getSiguiente());
                } else {
                    previa.setSiguiente(actual.getSiguiente());
                }
                return true;
            }
            previa = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }

    // Quita los enlaces que llegan al equipo y luego lo desenlaza de la lista.
    // false si no existe. O(V + A)
    public boolean eliminarVertice(String id) {
        Vertice objetivo = buscar(id);
        if (objetivo == null) {
            return false;
        }
        Vertice v = cabeza;
        while (v != null) {
            quitarEnlace(v, objetivo);
            v = v.getSiguiente();
        }
        Vertice previo = null;
        v = cabeza;
        while (v != objetivo) {
            previo = v;
            v = v.getSiguiente();
        }
        if (previo == null) {
            cabeza = objetivo.getSiguiente();
        } else {
            previo.setSiguiente(objetivo.getSiguiente());
        }
        return true;
    }

    // BFS sin Queue: la cola se enlaza con sigCola y los visitados se marcan
    // en el propio Vertice. Devuelve cuantos equipos alcanza. O(V + A)
    private int recorrer(String inicio, StringBuilder orden) {
        Vertice origen = buscar(inicio);
        if (origen == null) {
            return 0;
        }
        Vertice v = cabeza;
        while (v != null) {
            v.setVisitado(false);
            v.setSigCola(null);
            v = v.getSiguiente();
        }
        Vertice frente = origen;
        Vertice fondo = origen;
        origen.setVisitado(true);
        int alcanzados = 0;
        while (frente != null) {
            Vertice actual = frente;
            frente = frente.getSigCola();
            if (frente == null) {
                fondo = null;
            }

            if (orden != null) {
                if (alcanzados > 0) {
                    orden.append(" ");
                }
                orden.append(actual.getId());
            }
            alcanzados++;

            Arista arista = actual.getPrimeraArista();
            while (arista != null) {
                Vertice destino = arista.getDestino();
                if (!destino.isVisitado()) {
                    destino.setVisitado(true);
                    if (fondo == null) {
                        frente = destino;
                    } else {
                        fondo.setSigCola(destino);
                    }
                    fondo = destino;
                }
                arista = arista.getSiguiente();
            }
        }
        return alcanzados;
    }

    public String recorridoBfs(String inicio) {
        StringBuilder orden = new StringBuilder();
        recorrer(inicio, orden);
        return orden.toString();
    }

    public int bfs(String inicio) {
        return recorrer(inicio, null);
    }

    public boolean esConexo(String inicio) {
        int alcanzados = bfs(inicio);
        return alcanzados > 0 && alcanzados == totalVertices();
    }
}