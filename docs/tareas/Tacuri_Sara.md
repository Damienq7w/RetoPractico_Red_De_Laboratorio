# Tacuri Santillan Mónica Sara

**Rol:** desarrollo
**Rama:** `Rama-Sara`
**Archivo:** `src/negocio/Grafo.java` (segunda mitad)

Empieza cuando la parte de Luis esté en `main`. Trabaja sobre el mismo archivo: agrega sus métodos después de `grado` sin borrar ni modificar los de Luis. Josue y Joel esperan esta parte para el `Main` y las pruebas.

## Qué le corresponde

### Actualizar y eliminar

| Método | Qué hace | Cuándo devuelve `false` |
|---|---|---|
| `boolean actualizarPeso(String o, String d, int peso)` | Cambia la latencia de un enlace que ya existe | Falta un equipo, el peso es negativo o el enlace no existe |
| `boolean eliminarArista(String o, String d)` | Quita el enlace o → d sin tocar los equipos | Falta un equipo o el enlace no existe |
| `boolean eliminarVertice(String id)` | Quita el equipo y todos los enlaces que llegan a él | El equipo no existe |

Método privado de apoyo: `boolean quitar(Vertice o, Vertice d)`, que recorre la lista de aristas de `o` con un **puntero previo** y salta la arista que llega a `d`. Si es la primera de la lista, se cambia el puntero del vértice; si no, el previo apunta a la siguiente.

`eliminarVertice` va en dos pasos:

1. Recorrer **todos** los vértices y quitar de cada uno la arista que llega al eliminado. Sin este paso quedaría, por ejemplo, Router → Switch apuntando a un equipo que ya no existe.
2. Desenlazar el vértice de la lista de vértices con un puntero previo.

Los enlaces que salen del equipo eliminado desaparecen con él, porque su lista de aristas cuelga del nodo.

### Recorrido y conexidad

| Método | Qué devuelve |
|---|---|
| `String recorridoBfs(String inicio)` | El orden de visita en anchura, con los nombres separados por un espacio. Cadena vacía si el inicio no existe. |
| `int bfs(String inicio)` | Cuántos equipos se alcanzan desde `inicio`. 0 si no existe. |
| `boolean esConexo(String inicio)` | `true` si el inicio existe y `bfs(inicio)` es igual a `totalVertices()` |

El BFS **no puede usar** `Queue`, `LinkedList` ni `HashSet`:

- **Visitados:** la marca `visitado` de cada `Vertice`. Antes de empezar se pone en `false` en todos.
- **Cola:** dos punteros, `frente` y `fin`, enlazados con el campo `sigCola` de cada `Vertice`. Encolar es enlazar al final; desencolar es avanzar `frente`.
- Cada vértice se marca como visitado **en el momento de encolarlo**, para que no entre dos veces.
- `bfs` ejecuta el recorrido y cuenta cuántos vértices quedaron marcados.

## Commit

```bash
git add src/negocio/Grafo.java
git commit -m "feat(negocio): actualizar, eliminar, BFS y esConexo"
```

## Cómo se comprueba

El proyecto compila sin errores. Con la red del reto construida en el orden del enunciado, y ejecutando las llamadas en este orden:

| Llamada | Resultado |
|---|---|
| `recorridoBfs("Router")` | `Router Servidor Switch PC2 PC1 Impresora` |
| `bfs("Router")` | 6 |
| `esConexo("Router")` | `true` |
| `actualizarPeso("Router", "Servidor", 2)` | `true` |
| `actualizarPeso("PC1", "Router", 2)` | `false` |
| `eliminarArista("Servidor", "PC2")` | `true` |
| `eliminarVertice("Switch")` | `true` |
| `grado("Router")` | 1 |
| `bfs("Router")` | 2 |
| `esConexo("Router")` | `false` |
| `esConexo("Router")` tras agregar Router → PC1 y Router → PC2 | `true` |

El BFS visita primero el Servidor y después el Switch porque las aristas se insertan al inicio: Router → Servidor se agregó después y queda primera en la lista del Router.
