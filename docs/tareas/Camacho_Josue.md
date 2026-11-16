# Camacho Monta Josue Jampier

**Rol:** desarrollo y documentación
**Rama:** `Rama-Josue`
**Archivos:** `src/app/Main.java`, `docs/capturas/`

Tiene dos entregas: el `Main` cuando el `Grafo` esté completo y las capturas cuando también estén las pruebas.

## Entrega 1 · Main

Empieza cuando la parte de Sara esté en `main`.

Clase `Main` (paquete `app`) con un método `main` que crea un `Grafo` y ejecuta las tres partes del reto en orden, imprimiendo el resultado de cada paso. Solo llama a métodos públicos de `Grafo`; no usa `Vertice` ni `Arista`.

### Parte 1 · Construir

1. Agregar los equipos en este orden: Router, Switch, PC1, PC2, Impresora, Servidor.
2. Agregar los enlaces en este orden: Router → Switch (1), Router → Servidor (4), Switch → PC1 (2), Switch → PC2 (2), PC2 → Impresora (3), Servidor → PC2 (5).
3. Imprimir `mostrar()`.
4. Imprimir el resultado de agregar "Router" otra vez.
5. Imprimir el resultado de agregar PC1 → Proyector (3).

### Parte 2 · Consultar

1. Imprimir `recorridoBfs("Router")` y cuántos equipos alcanza de cuántos.
2. Imprimir `grado` de Router, Switch, Impresora y Proyector.
3. Cambiar la latencia de Router → Servidor a 2 e imprimir el resultado.
4. Eliminar Servidor → PC2 e imprimir el resultado.
5. Imprimir `mostrar()`.

### Parte 3 · Decidir

1. Eliminar el Switch e imprimir el resultado.
2. Imprimir `mostrar()`.
3. Imprimir `esConexo("Router")`, cuántos equipos alcanza y cuáles.
4. Imprimir qué equipos quedaron aislados y el mínimo de enlaces nuevos.
5. Agregar Router → PC1 (2) y Router → PC2 (2), imprimiendo cada resultado.
6. Imprimir `mostrar()`.
7. Imprimir otra vez `esConexo("Router")`, cuántos equipos alcanza y cuáles.

El orden de inserción importa: si se cambia, cambian la salida de `mostrar()` y el orden del BFS.

Commit:

```bash
git add src/app/Main.java
git commit -m "feat(app): Main del reto La red del laboratorio"
```

## Entrega 2 · Capturas

Empieza cuando las pruebas de Joel estén en `main`.

Ejecutar en la consola propia:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
java -cp out app.Main
java -cp out pruebas.PruebasRed
```

Guardar cuatro capturas en `docs/capturas/`, en formato PNG y con estos nombres exactos (en minúsculas):

| Archivo | Qué debe verse completo |
|---|---|
| `01_construir.png` | Desde `=== 1. CONSTRUIR ===` hasta los dos `false` |
| `02_consultar.png` | Desde `=== 2. CONSULTAR ===` hasta el segundo `mostrar()` |
| `03_decidir.png` | Desde `=== 3. DECIDIR ===` hasta el último `esConexo("Router"): true` |
| `04_pruebas.png` | El final de `PruebasRed` con la línea `21/21 pruebas OK` |

En cada captura debe verse también el comando ejecutado. Si el nombre del archivo no es exacto, la imagen no aparece en el `README.md`.

Eliminar `docs/capturas/README.md`, que solo indicaba qué subir.

Commit:

```bash
git rm docs/capturas/README.md
git add docs/capturas
git commit -m "docs: capturas de la consola"
```

## Cómo se comprueba

- El proyecto compila sin errores.
- `java -cp out app.Main` imprime exactamente lo que aparece en la sección "Salida del programa" del [README principal](../../README.md).
- Si la salida no coincide, el problema está en `Grafo`: se avisa a Luis o a Sara. No se cambian los textos del `Main` para que coincida.
- En GitHub, el `README.md` muestra las cuatro capturas.
