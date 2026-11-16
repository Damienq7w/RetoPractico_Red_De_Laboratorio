# Tareas por integrante

Cada integrante tiene su archivo con lo que le corresponde: archivos, métodos, resultados esperados y commits.

| Integrante | Archivo | Rama |
|---|---|---|
| Cunalata Mendoza Damian Alexander | [Cunalata_Damian.md](Cunalata_Damian.md) | `Damian_Cunalata` |
| Tisalema Guashco Darwin Joel | [Tisalema_Joel.md](Tisalema_Joel.md) | `Rama-Joel` |
| Silva Camuendo Luis Alexander | [Silva_Luis.md](Silva_Luis.md) | `Luis-Silva` |
| Tacuri Santillan Mónica Sara | [Tacuri_Sara.md](Tacuri_Sara.md) | `Sara-Tacuri` |
| Camacho Monta Josue Jampier | [Camacho_Josue.md](Camacho_Josue.md) | `Josue-Camacho` |

Chalco Tasna Kenneth Mateo no tiene tareas asignadas porque no asistió a clases.

## Orden de trabajo

Cada parte depende de la anterior, así que el orden es obligatorio. Nadie empieza hasta que la parte previa esté fusionada en `main`.

| Orden | Integrante | Commit | Depende de |
|---|---|---|---|
| 1 | Damian | `chore: estructura del proyecto, README y esqueleto de clases` | — |
| 2 | Joel | `feat(modelo): nodos Vertice y Arista` | 1 |
| 3 | Luis | `feat(negocio): Grafo con agregarVertice, agregarArista y mostrar` | 2 |
| 4 | Luis | `feat(negocio): grado(id) devuelve -1 si el equipo no existe` | 3 |
| 5 | Sara | `feat(negocio): actualizar, eliminar, BFS y esConexo` | 4 |
| 6 | Josue | `feat(app): Main del reto La red del laboratorio` | 5 |
| 7 | Joel | `test: 21 pruebas del reto sin JUnit` | 5 |
| 8 | Josue | `docs: capturas de la consola` | 6 y 7 |
| 9 | Damian | Integración final y revisión de la documentación | 8 |

## Reglas para todos

- Solo se usan `Vertice`, `Arista` y `Grafo`. Prohibido `HashMap`, `ArrayList`, `Queue`, `LinkedList`, `HashSet` y cualquier librería de grafos.
- Los nombres de clases, métodos y paquetes deben ser exactamente los que indica cada archivo, porque las demás partes los llaman.
- Cada integrante hace sus commits con su propia cuenta de GitHub y en su propia rama.
- Antes de subir, el proyecto debe compilar sin errores.
- No se modifica el código de otro integrante. Si algo falla en la parte de otro, se le avisa.

## Comandos comunes

Preparar la rama (una sola vez):

```bash
git clone https://github.com/Damienq7w/RetoPractico_Red_De_Laboratorio.git
cd RetoPractico_Red_De_Laboratorio
git checkout -b NOMBRE-DE-TU-RAMA
```

Traer lo último de `main` antes de una segunda entrega:

```bash
git pull origin main
```

Compilar (PowerShell, desde la carpeta del proyecto):

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
```

Subir:

```bash
git push -u origin NOMBRE-DE-TU-RAMA
```

Después se abre un pull request de la rama hacia `main` en GitHub y se avisa a Damian para que lo revise y lo fusione.
