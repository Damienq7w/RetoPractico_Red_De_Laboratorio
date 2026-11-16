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

    // ===== ACTUALIZAR, ELIMINAR Y RECORRER =====
    // A cargo de: Tacuri Santillan Monica Sara
    // PENDIENTE: actualizarPeso, eliminarArista, eliminarVertice, recorridoBfs, bfs y esConexo
}
