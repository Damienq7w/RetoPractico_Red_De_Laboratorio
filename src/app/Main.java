package app;

import negocio.Grafo;

public class Main {

    public static void main(String[] args) {
        Grafo red = new Grafo();

        construir(red);
        consultar(red);
        decidir(red);
    }

    private static void construir(Grafo red) {
        System.out.println("=== PARTE 1 | CONSTRUIR ===");

        String[] equipos = {"Router", "Switch", "PC1", "PC2", "Impresora", "Servidor"};
        for (String equipo : equipos) {
            red.agregarVertice(equipo);
        }

        red.agregarArista("Router", "Switch", 1);
        red.agregarArista("Router", "Servidor", 4);
        red.agregarArista("Switch", "PC1", 2);
        red.agregarArista("Switch", "PC2", 2);
        red.agregarArista("PC2", "Impresora", 3);
        red.agregarArista("Servidor", "PC2", 5);

        System.out.println(red.mostrar());
        System.out.println("Agregar Router otra vez = " + red.agregarVertice("Router"));
        System.out.println("Agregar PC1 -> Proyector (3 ms) = "
                + red.agregarArista("PC1", "Proyector", 3));
    }

    private static void consultar(Grafo red) {
        System.out.println("\n=== PARTE 2 | CONSULTAR ===");

        System.out.println("BFS desde Router = " + red.recorridoBfs("Router"));
        System.out.println("Equipos alcanzados = " + red.bfs("Router")
                + " de " + red.totalVertices());

        System.out.println("Grado de Router = " + red.grado("Router"));
        System.out.println("Grado de Switch = " + red.grado("Switch"));
        System.out.println("Grado de Impresora = " + red.grado("Impresora"));
        System.out.println("Grado de Proyector = " + red.grado("Proyector"));

        System.out.println("Cambiar Router -> Servidor a 2 ms = "
                + red.actualizarPeso("Router", "Servidor", 2));
        System.out.println("Eliminar Servidor -> PC2 = "
                + red.eliminarArista("Servidor", "PC2"));

        System.out.println(red.mostrar());
    }

    private static void decidir(Grafo red) {
        System.out.println("\n=== PARTE 3 | DECIDIR ===");

        System.out.println("Eliminar Switch = " + red.eliminarVertice("Switch"));
        System.out.println(red.mostrar());
        System.out.println("¿Se llega a todos desde Router? " + red.esConexo("Router"));
        System.out.println("Equipos alcanzados desde Router = " + red.bfs("Router")
                + " de " + red.totalVertices());
        System.out.println("Recorrido desde Router = " + red.recorridoBfs("Router"));
        System.out.println("Equipo aislado: PC1");
        System.out.println("Equipos inalcanzables desde Router: PC1, PC2 e Impresora");
        System.out.println("Enlaces nuevos necesarios como mínimo: 2");

        System.out.println("Agregar Router -> PC1 (2 ms) = "
                + red.agregarArista("Router", "PC1", 2));
        System.out.println("¿Se llega a todos con un enlace nuevo? " + red.esConexo("Router"));

        System.out.println("Agregar Router -> PC2 (2 ms) = "
                + red.agregarArista("Router", "PC2", 2));
        System.out.println(red.mostrar());
        System.out.println("¿Se llega a todos con dos enlaces nuevos? " + red.esConexo("Router"));
        System.out.println("Equipos alcanzados desde Router = " + red.bfs("Router")
                + " de " + red.totalVertices());
        System.out.println("Recorrido desde Router = " + red.recorridoBfs("Router"));
    }
}