# Cunalata Mendoza Damian Alexander

**Rol:** líder técnico e integración
**Rama:** `Damian_Cunalata`
**Archivos:** `README.md`, `docs/Respuestas_Parte3.md`, `docs/tareas/`, estructura de carpetas

## Qué le corresponde

### 1. Estructura del repositorio

- Crear las carpetas `src/modelo`, `src/negocio`, `src/app`, `src/pruebas`, `docs/capturas` y `docs/tareas`.
- Dejar el esqueleto de cada clase con el paquete correcto y el nombre del responsable.
- Agregar a los otros cuatro integrantes como colaboradores del repositorio con permiso de escritura.

Commit: `chore: estructura del proyecto, README y esqueleto de clases`

### 2. Documentación

- Redactar el `README.md` con: enunciado, integrantes, origen del código base, representación de la red, estructura, operaciones y costo, compilación, salida del programa, pruebas, capturas, código de `grado(id)` y respuestas de la parte 3.
- Redactar los archivos de tareas de `docs/tareas/`.

### 3. Respuestas de la parte 3

Escribir `docs/Respuestas_Parte3.md` respondiendo, con su justificación:

1. Qué devuelve `esConexo("Router")` después de eliminar el Switch.
2. Qué equipos quedaron aislados.
3. Cuántos enlaces nuevos hacen falta como mínimo y por qué no pueden ser menos.
4. Qué enlaces se agregaron y cómo se comprobó.

Commit: `docs: respuestas de la parte 3 (Decidir)`

### 4. Integración

- Avisar a cada integrante cuando la parte de la que depende ya esté en `main` (ver el orden en [README.md](README.md)).
- Revisar cada pull request: bajar la rama, compilar y, si no hay errores, fusionarla en `main`.
- Al final, con todo fusionado, compilar y ejecutar `app.Main` y `pruebas.PruebasRed`, y verificar que la salida coincide con la del `README.md` y que las cuatro capturas se ven.

Revisar una rama antes de fusionarla:

```powershell
git fetch origin
git checkout NOMBRE-DE-LA-RAMA
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
```

## Cómo se comprueba

- El proyecto compila sin errores con todo fusionado.
- `java -cp out pruebas.PruebasRed` termina en `21/21 pruebas OK`.
- `java -cp out app.Main` imprime lo mismo que la sección "Salida del programa" del `README.md`.
- En GitHub, el `README.md` muestra las cuatro capturas.
