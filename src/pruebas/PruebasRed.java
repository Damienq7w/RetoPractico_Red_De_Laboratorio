package pruebas;

import negocio.Grafo;

public class PruebasRed {
    private int ejecutadas;
    private int aprobadas;

    public static void main(String[] args) {
        new PruebasRed().ejecutar();
    }

    private void ejecutar() {
        Grafo red = new Grafo();

        comprobar("(grafo vacio)".equals(red.mostrar()), "Mostrar grafo vacío");
        comprobar(!red.esConexo("Router"), "Grafo vacío sin conectividad");

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

        comprobar(red.totalVertices() == 6, "Total inicial de equipos");
        comprobar(!red.agregarVertice("Router"), "Rechazar Router duplicado");
        comprobar(!red.agregarArista("PC1", "Proyector", 3), "Rechazar destino inexistente");
        comprobar(!red.agregarArista("Router", "Switch", 1), "Rechazar enlace duplicado");
        comprobar(!red.agregarArista("PC1", "PC2", -1), "Rechazar latencia negativa");

        comprobar(red.bfs("Router") == 6, "BFS alcanza los seis equipos");
        comprobar("Router Servidor Switch PC2 PC1 Impresora".equals(red.recorridoBfs("Router")),
                "Orden del BFS desde Router");
        comprobar(red.grado("Router") == 2, "Grado de Router");
        comprobar(red.grado("Impresora") == 0, "Grado de Impresora");
        comprobar(red.grado("Proyector") == -1, "Grado de equipo inexistente");

        comprobar(red.actualizarPeso("Router", "Servidor", 2)
                && red.mostrar().contains("(Servidor,2)"), "Actualizar latencia a 2 ms");
        comprobar(!red.actualizarPeso("PC1", "PC2", 2), "Rechazar actualización de enlace inexistente");
        comprobar(red.eliminarArista("Servidor", "PC2")
                && red.grado("Servidor") == 0, "Eliminar enlace de Servidor a PC2");

        comprobar(red.eliminarVertice("Switch") && red.totalVertices() == 5, "Eliminar Switch");
        comprobar(red.grado("Router") == 1, "Grado de Router sin Switch");
        comprobar(!red.esConexo("Router"), "Red desconectada sin Switch");
        comprobar(red.bfs("Router") == 2, "BFS alcanza dos equipos sin Switch");

        comprobar(red.agregarArista("Router", "PC1", 2)
                && !red.esConexo("Router"), "Un enlace nuevo no basta");
        comprobar(red.agregarArista("Router", "PC2", 2)
                && red.esConexo("Router"), "Dos enlaces nuevos reconectan la red");

        System.out.printf("%n%d/%d pruebas OK%n", aprobadas, ejecutadas);
    }

    private void comprobar(boolean correcto, String caso) {
        ejecutadas++;
        if (correcto) {
            aprobadas++;
        }
        System.out.printf("P%02d [%s] %s%n", ejecutadas, correcto ? "OK" : "FALLA", caso);
    }
}