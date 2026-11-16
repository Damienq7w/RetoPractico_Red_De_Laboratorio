# Silva Camuendo Luis Alexander

**Rol:** desarrollo
**Rama:** `Rama-Luis`
**Archivo:** `src/negocio/Grafo.java` (primera mitad)

Empieza cuando la entrega 1 de Joel (`Vertice` y `Arista`) esté en `main`. Sara trabaja después en el mismo archivo, así que no puede empezar hasta que esta parte esté fusionada.

## Qué le corresponde

La clase `Grafo` (paquete `negocio`) con un único atributo privado, `cabeza`, de tipo `Vertice`, y estos métodos:

### Métodos privados de apoyo

| Método | Qué hace |
|---|---|
| `Vertice buscar(String id)` | Recorre la lista de vértices y devuelve el que tiene ese nombre, o `null`. Si `id` es `null` devuelve `null`. |
| `Arista buscarArista(Vertice o, Vertice d)` | Recorre la lista de aristas de `o` y devuelve la que llega a `d`, o `null`. |

`buscar` es privado para que desde fuera nadie reciba un `Vertice` y pueda romper la lista.

### Métodos públicos

| Método | Qué hace | Cuándo devuelve `false` |
|---|---|---|
| `boolean agregarVertice(String id)` | Crea el vértice y lo inserta **al inicio** de la lista: el nuevo apunta a `cabeza` y pasa a ser `cabeza` | `id` es `null`, está vacío o el equipo ya existe |
| `boolean agregarArista(String o, String d, int peso)` | Crea la arista hacia `d` y la inserta **al inicio** de la lista de aristas de `o` | Falta alguno de los dos equipos, origen y destino son el mismo, el peso es negativo o el enlace ya existe |

| Método | Qué devuelve |
|---|---|
| `String mostrar()` | La lista de adyacencia, una línea por equipo con el formato `Router -> Servidor(4) Switch(1)`. Si no hay equipos, `(grafo vacio)`. Un equipo sin enlaces se muestra como `PC1 ->`. |
| `int totalVertices()` | Cuántos equipos hay |
| `int grado(String id)` | Cuántos enlaces **salen** del equipo. **-1 si el equipo no existe.** |

### `grado(id)`

Es el método que el reto pide programar y entregar, por eso va en un commit propio.

1. Buscar el vértice con `buscar(id)`.
2. Si no existe, devolver -1.
3. Recorrer su lista de aristas contando los nodos.
4. Devolver el contador.

Devuelve -1 y no 0 porque 0 es un grado válido: la Impresora existe y no tiene enlaces de salida.

## Commits

```bash
git add src/negocio/Grafo.java
git commit -m "feat(negocio): Grafo con agregarVertice, agregarArista y mostrar"
```

```bash
git add src/negocio/Grafo.java
git commit -m "feat(negocio): grado(id) devuelve -1 si el equipo no existe"
```

## Cómo se comprueba

El proyecto compila sin errores después de cada commit. Con la red del reto construida en el orden del enunciado:

| Llamada | Resultado |
|---|---|
| `agregarVertice("Router")` la primera vez | `true` |
| `agregarVertice("Router")` otra vez | `false` |
| `agregarArista("PC1", "Proyector", 3)` | `false` |
| `agregarArista("Router", "Switch", 9)` | `false` |
| `totalVertices()` | 6 |
| `grado("Router")` | 2 |
| `grado("Impresora")` | 0 |
| `grado("Proyector")` | -1 |

`mostrar()` debe imprimir exactamente:

```
Servidor -> PC2(5)
Impresora ->
PC2 -> Impresora(3)
PC1 ->
Switch -> PC2(2) PC1(2)
Router -> Servidor(4) Switch(1)
```

Los equipos salen en orden inverso al de inserción porque se inserta al inicio.
