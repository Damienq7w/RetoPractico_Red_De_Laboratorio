# Reto práctico: "La red del laboratorio"

Estructura de Datos · 3.er semestre de Software · FISEI-UTA · Docente: Ing. José Rubén Caiza
Reto propuesto por el Grupo 5 en su exposición de multilistas y grafos dinámicos (Unidad 4, 16/11/2026).

> **Estado del repositorio:** esqueleto. Cada archivo indica quién lo desarrolla; las secciones marcadas como PENDIENTE se completan cuando el responsable sube su parte.

## Contenido

1. [Enunciado del reto](#enunciado-del-reto)
2. [Integrantes y reparto](#integrantes-y-reparto)
3. [Origen del código base](#origen-del-código-base)
4. [Cómo se representa la red](#cómo-se-representa-la-red)
5. [Estructura del proyecto](#estructura-del-proyecto)
6. [Operaciones del grafo y su costo](#operaciones-del-grafo-y-su-costo)
7. [Compilación y ejecución](#compilación-y-ejecución)
8. [Pruebas](#pruebas)
9. [Capturas de la consola](#capturas-de-la-consola)
10. [Código de grado(id)](#código-de-gradoid)
11. [Respuestas de la parte 3](#respuestas-de-la-parte-3)
12. [Flujo de trabajo en Git](#flujo-de-trabajo-en-git)

## Enunciado del reto

Representar la red del laboratorio como un **grafo dirigido con multilistas**, usando solo las clases `Vertice`, `Arista` y `Grafo`. No se puede usar `HashMap`, `ArrayList` ni librerías de grafos.

**Equipos:** Router, Switch, PC1, PC2, Impresora, Servidor.

**Enlaces (latencia en ms):**

| Origen | Destino | Latencia |
|---|---|---|
| Router | Switch | 1 |
| Router | Servidor | 4 |
| Switch | PC1 | 2 |
| Switch | PC2 | 2 |
| PC2 | Impresora | 3 |
| Servidor | PC2 | 5 |

**Lo que se pide:**

1. **Construir.** Insertar los equipos y los enlaces y llamar a `mostrar()`. Probar que agregar "Router" otra vez y agregar PC1 → Proyector (3) devuelven `false`.
2. **Consultar.** Ejecutar `bfs("Router")`. Programar `grado(id)`, que devuelve -1 si el equipo no existe. Cambiar la latencia de Router → Servidor a 2 y eliminar Servidor → PC2.
3. **Decidir.** El Switch se dañó: eliminarlo y verificar con `esConexo("Router")`. ¿Qué equipos quedaron aislados? ¿Cuántos enlaces nuevos hacen falta, como mínimo, para reconectarlos? Agregarlos y comprobar.

**Entrega:** capturas de la consola, el código de `grado(id)` y las respuestas de la parte 3, subido a GitHub.

## Integrantes y reparto

| Integrante | Rol | A cargo de | Rama |
|---|---|---|---|
| Cunalata Mendoza Damian Alexander | Líder técnico e integración | Este README, estructura del repositorio, `docs/Respuestas_Parte3.md`, revisión e integración de las ramas en `main` y prueba final | `Rama-Damian` |
| Tisalema Guashco Darwin Joel | Desarrollo y pruebas | Paquete `modelo/` (`Vertice`, `Arista`), `pruebas/PruebasRed` y `docs/Pruebas.md` | `Rama-Joel` |
| Silva Camuendo Luis Alexander | Desarrollo | `Grafo`: `agregarVertice`, `agregarArista`, `mostrar`, `totalVertices` y **`grado(id)`** | `Rama-Luis` |
| Tacuri Santillan Mónica Sara | Desarrollo | `Grafo`: `actualizarPeso`, `eliminarArista`, `eliminarVertice`, `recorridoBfs`, `bfs` y `esConexo` | `Rama-Sara` |
| Camacho Monta Josue Jampier | Desarrollo y documentación | `app/Main` (las tres partes del reto) y las capturas de `docs/capturas/` | `Rama-Josue` |

**Chalco Tasna Kenneth Mateo** no participó en este reto porque no asistió a clases; no tiene clases ni tareas a su cargo y el trabajo se repartió entre los cinco integrantes restantes.

## Origen del código base

El reto se resuelve sobre el modelo que presentó el Grupo 5 en su repositorio [Grupo5-Multilistas-Grafos-Dinamicos](https://github.com/gamboarommel580-rgb/Grupo5-Multilistas-Grafos-Dinamicos): una lista de vértices con nodos propios y, colgada de cada vértice, su lista de aristas. Las clases `Vertice`, `Arista` y `Grafo` de este repositorio son una adaptación de ese código, reducida a las operaciones que usa este reto. El programa `Main` de la red del laboratorio, las pruebas `PruebasRed` y esta documentación son de este equipo.

## Cómo se representa la red

Cada **equipo** es un `Vertice` y cada **enlace** es una `Arista` con su latencia como peso. El grafo guarda un solo puntero, `cabeza`, al primer vértice.

- `Vertice`: nombre del equipo, puntero a su primera arista y puntero al siguiente vértice.
- `Arista`: vértice destino, latencia y puntero a la siguiente arista del mismo origen.

Los vértices y las aristas se insertan **al inicio** de su lista. Por eso `mostrar()` imprime los equipos en orden inverso al de inserción y el BFS visita primero el último enlace agregado. Así queda la red del reto en memoria:

```
cabeza
  |
[Servidor]  -> (PC2,5) -> null
  |
[Impresora] -> null
  |
[PC2]       -> (Impresora,3) -> null
  |
[PC1]       -> null
  |
[Switch]    -> (PC2,2) -> (PC1,2) -> null
  |
[Router]    -> (Servidor,4) -> (Switch,1) -> null
  |
 null
```

El grafo es **dirigido**: Router → Switch no implica Switch → Router. Por eso `esConexo("Router")` responde si desde el Router se alcanzan todos los equipos siguiendo el sentido de los enlaces.

Para cumplir la regla de no usar colecciones de Java, el BFS no usa `Queue` ni `HashSet`: la cola se enlaza con un puntero `sigCola` dentro de cada `Vertice` y los visitados son una marca booleana en el mismo nodo.

## Estructura del proyecto

```
src/
  modelo/   Vertice.java, Arista.java     nodos de las listas          (Joel)
  negocio/  Grafo.java                    operaciones del grafo        (Luis y Sara)
  app/      Main.java                     las tres partes del reto     (Josue)
  pruebas/  PruebasRed.java               21 pruebas sin JUnit         (Joel)
docs/
  capturas/                               capturas de la consola       (Josue)
  Pruebas.md                              salida completa de pruebas   (Joel)
  Respuestas_Parte3.md                    respuestas de la parte 3     (Damian)
```

Las capas van en un solo sentido: `app → negocio → modelo`. `Main` y `PruebasRed` solo llaman a métodos públicos de `Grafo`; nunca tocan un `Vertice` o una `Arista` directamente.

## Operaciones del grafo y su costo

V = número de equipos, A = número de enlaces, grado = enlaces que salen de un equipo.

| Método | Qué hace | Devuelve | Costo | Responsable |
|---|---|---|---|---|
| `agregarVertice(id)` | Inserta un equipo al inicio de la lista | `false` si es null, vacío o repetido | O(V) | Luis |
| `agregarArista(o, d, peso)` | Inserta el enlace o → d al inicio de la lista de o | `false` si falta un equipo, es un lazo, el peso es negativo o ya existe | O(V + grado) | Luis |
| `mostrar()` | Lista de adyacencia, una línea por equipo | `String` | O(V + A) | Luis |
| `totalVertices()` | Cuenta los equipos | `int` | O(V) | Luis |
| `grado(id)` | Cuenta los enlaces que salen del equipo | **-1 si el equipo no existe** | O(V + grado) | Luis |
| `actualizarPeso(o, d, peso)` | Cambia la latencia de un enlace existente | `false` si el enlace no existe | O(V + grado) | Sara |
| `eliminarArista(o, d)` | Quita un enlace con un puntero previo | `false` si no existe | O(V + grado) | Sara |
| `eliminarVertice(id)` | Quita los enlaces que llegan al equipo y luego lo desenlaza | `false` si no existe | O(V + A) | Sara |
| `recorridoBfs(inicio)` | Recorrido en anchura | Orden de visita como texto | O(V + A) | Sara |
| `bfs(inicio)` | Recorrido en anchura | Cuántos equipos alcanza | O(V + A) | Sara |
| `esConexo(inicio)` | Compara lo alcanzado con el total | `true` si se llega a todos | O(V + A) | Sara |

## Compilación y ejecución

Requiere JDK 11 o superior. Desde la carpeta del proyecto, en PowerShell:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
java -cp out app.Main
java -cp out pruebas.PruebasRed
```

En Git Bash o Linux, la primera línea cambia por:

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
```

`app.Main` ejecuta las tres partes del reto en orden. `pruebas.PruebasRed` debe terminar con `21/21 pruebas OK`.

## Pruebas

**A cargo de:** Tisalema Guashco Darwin Joel · Código en `src/pruebas/PruebasRed.java` · Salida completa en [docs/Pruebas.md](docs/Pruebas.md)

Las pruebas no usan JUnit: cada caso llama a un método de `Grafo`, compara con el valor esperado e imprime `OK` o `FALLA`. Al final se imprime el total.

| N.º | Parte | Caso | Esperado | Resultado |
|---|---|---|---|---|
| P01 | Límite | `mostrar()` con el grafo vacío | `(grafo vacio)` | PENDIENTE |
| P02 | Límite | `esConexo("Router")` con el grafo vacío | `false` | PENDIENTE |
| P03 | 1 | Total de equipos tras construir la red | 6 | PENDIENTE |
| P04 | 1 | Agregar "Router" otra vez | `false` | PENDIENTE |
| P05 | 1 | Agregar PC1 → Proyector (3) | `false` | PENDIENTE |
| P06 | 1 | Agregar otra vez Router → Switch | `false` | PENDIENTE |
| P07 | 1 | Agregar un enlace con latencia negativa | `false` | PENDIENTE |
| P08 | 2 | `bfs("Router")` | 6 | PENDIENTE |
| P09 | 2 | Orden del BFS desde Router | `Router Servidor Switch PC2 PC1 Impresora` | PENDIENTE |
| P10 | 2 | `grado("Router")` | 2 | PENDIENTE |
| P11 | 2 | `grado("Impresora")` | 0 | PENDIENTE |
| P12 | 2 | `grado("Proyector")` | -1 | PENDIENTE |
| P13 | 2 | Cambiar Router → Servidor a 2 | `true` | PENDIENTE |
| P14 | 2 | Cambiar la latencia de un enlace que no existe | `false` | PENDIENTE |
| P15 | 2 | Eliminar Servidor → PC2 | `true` y `grado("Servidor")` = 0 | PENDIENTE |
| P16 | 3 | Eliminar el Switch | `true` y quedan 5 equipos | PENDIENTE |
| P17 | 3 | `grado("Router")` sin el Switch | 1 | PENDIENTE |
| P18 | 3 | `esConexo("Router")` sin el Switch | `false` | PENDIENTE |
| P19 | 3 | `bfs("Router")` sin el Switch | 2 | PENDIENTE |
| P20 | 3 | `esConexo("Router")` con un solo enlace nuevo | `false` | PENDIENTE |
| P21 | 3 | `esConexo("Router")` con dos enlaces nuevos | `true` | PENDIENTE |

## Capturas de la consola

**A cargo de:** Camacho Monta Josue Jampier · Archivos en [docs/capturas/](docs/capturas/)

PENDIENTE: cuatro capturas de la ejecución real.

| Captura | Archivo | Qué muestra |
|---|---|---|
| Parte 1 · Construir | `docs/capturas/01_construir.png` | `mostrar()` y los dos `false` |
| Parte 2 · Consultar | `docs/capturas/02_consultar.png` | BFS, grados, cambio de latencia y enlace eliminado |
| Parte 3 · Decidir | `docs/capturas/03_decidir.png` | `esConexo` antes y después de agregar los enlaces |
| Pruebas | `docs/capturas/04_pruebas.png` | `21/21 pruebas OK` |

## Código de grado(id)

**A cargo de:** Silva Camuendo Luis Alexander (lo integra en este README Damian)

PENDIENTE: pegar aquí el método `grado(id)` de `src/negocio/Grafo.java` con una explicación breve de por qué devuelve -1 cuando el equipo no existe.

## Respuestas de la parte 3

**A cargo de:** Cunalata Mendoza Damian Alexander · Detalle en [docs/Respuestas_Parte3.md](docs/Respuestas_Parte3.md)

PENDIENTE: equipos aislados, número mínimo de enlaces nuevos y comprobación.

## Flujo de trabajo en Git

Cada integrante trabaja en su rama, hace sus commits con su propia cuenta y abre un pull request hacia `main`. El orden importa porque cada parte depende de la anterior:

| Orden | Integrante | Commit |
|---|---|---|
| 1 | Damian | `chore: estructura del proyecto, README y esqueleto de clases` |
| 2 | Joel | `feat(modelo): nodos Vertice y Arista` |
| 3 | Luis | `feat(negocio): Grafo con agregarVertice, agregarArista y mostrar` |
| 4 | Luis | `feat(negocio): grado(id) devuelve -1 si el equipo no existe` |
| 5 | Sara | `feat(negocio): actualizar, eliminar, BFS y esConexo` |
| 6 | Josue | `feat(app): Main del reto La red del laboratorio` |
| 7 | Joel | `test: 21 pruebas del reto sin JUnit` |
| 8 | Josue | `docs: capturas de la consola` |
| 9 | Damian | `docs: README final con grado(id) y respuestas de la parte 3` |
