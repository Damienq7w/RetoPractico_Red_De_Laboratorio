# Tisalema Guashco Darwin Joel

**Rol:** desarrollo y pruebas
**Rama:** `Rama-Joel`
**Archivos:** `src/modelo/Vertice.java`, `src/modelo/Arista.java`, `src/pruebas/PruebasRed.java`, `docs/Pruebas.md`

Tiene dos entregas: los nodos al inicio y las pruebas cuando el `Grafo` esté completo.

## Entrega 1 · Los nodos

Empieza cuando el esqueleto esté en `main`. Luis no puede empezar hasta que esta entrega esté fusionada.

### `Vertice` (paquete `modelo`)

Representa un equipo de la red. Atributos privados:

| Atributo | Tipo | Para qué sirve |
|---|---|---|
| `id` | `final String` | Nombre del equipo |
| `aristas` | `Arista` | Primera arista de su lista; `null` si no tiene enlaces de salida |
| `sig` | `Vertice` | Siguiente vértice de la lista; `null` si es el último |
| `visitado` | `boolean` | Marca que usa el BFS |
| `sigCola` | `Vertice` | Enlace de la cola del BFS |

Métodos públicos:

- Constructor `Vertice(String id)`.
- `getId()`.
- `getAristas()` y `setAristas(Arista)`.
- `getSig()` y `setSig(Vertice)`.
- `isVisitado()` y `setVisitado(boolean)`.
- `getSigCola()` y `setSigCola(Vertice)`.

### `Arista` (paquete `modelo`)

Representa un enlace con su latencia. Atributos privados:

| Atributo | Tipo | Para qué sirve |
|---|---|---|
| `destino` | `final Vertice` | Equipo al que llega el enlace |
| `peso` | `int` | Latencia en ms |
| `sig` | `Arista` | Siguiente arista del mismo origen |

Métodos públicos:

- Constructor `Arista(Vertice destino, int peso)`.
- `getDestino()`.
- `getPeso()` y `setPeso(int)`.
- `getSig()` y `setSig(Arista)`.

Commit:

```bash
git add src/modelo
git commit -m "feat(modelo): nodos Vertice y Arista"
```

## Entrega 2 · Las pruebas

Empieza cuando la parte de Sara esté en `main`.

### `PruebasRed` (paquete `pruebas`)

Programa con `main` que prueba el reto sin JUnit:

- Un método `check(String nombre, boolean condicion)` que cuenta la prueba, imprime `OK` o `FALLA` seguido del nombre y suma los aciertos.
- Un método que construye la red del reto (6 equipos y 6 enlaces, en el orden del enunciado).
- Al final imprime `aciertos/total pruebas OK`.
- Solo llama a métodos públicos de `Grafo`.

Las 21 pruebas, en este orden:

| N.º | Caso | Esperado |
|---|---|---|
| P01 | `mostrar()` con el grafo vacío | `(grafo vacio)` |
| P02 | `esConexo("Router")` con el grafo vacío | `false` |
| P03 | `totalVertices()` tras construir la red | 6 |
| P04 | `agregarVertice("Router")` otra vez | `false` |
| P05 | `agregarArista("PC1", "Proyector", 3)` | `false` |
| P06 | `agregarArista("Router", "Switch", 9)` | `false` |
| P07 | `agregarArista("PC1", "PC2", -1)` | `false` |
| P08 | `bfs("Router")` | 6 |
| P09 | `recorridoBfs("Router")` | `Router Servidor Switch PC2 PC1 Impresora` |
| P10 | `grado("Router")` | 2 |
| P11 | `grado("Impresora")` | 0 |
| P12 | `grado("Proyector")` | -1 |
| P13 | `actualizarPeso("Router", "Servidor", 2)` | `true` |
| P14 | `actualizarPeso("PC1", "Router", 2)` | `false` |
| P15 | `eliminarArista("Servidor", "PC2")` | `true` y `grado("Servidor")` = 0 |
| P16 | `eliminarVertice("Switch")` | `true` y `totalVertices()` = 5 |
| P17 | `grado("Router")` sin el Switch | 1 |
| P18 | `esConexo("Router")` sin el Switch | `false` |
| P19 | `bfs("Router")` sin el Switch | 2 |
| P20 | `esConexo("Router")` tras agregar solo Router → PC1 | `false` |
| P21 | `esConexo("Router")` tras agregar también Router → PC2 | `true` |

De P13 en adelante las pruebas se hacen sobre el mismo grafo, en ese orden, porque cada una parte del estado que dejó la anterior.

### `docs/Pruebas.md`

Pegar la salida completa de la ejecución dentro de un bloque de código.

Commit:

```bash
git add src/pruebas docs/Pruebas.md
git commit -m "test: 21 pruebas del reto sin JUnit"
```

## Cómo se comprueba

- Entrega 1: el proyecto compila sin errores.
- Entrega 2: `java -cp out pruebas.PruebasRed` no imprime ningún `FALLA` y termina en `21/21 pruebas OK`.
- Si una prueba falla, el error está en `Grafo`: se avisa a Luis (P01, P03 a P07, P10 a P12) o a Sara (las demás). No se cambia el valor esperado para que pase.
