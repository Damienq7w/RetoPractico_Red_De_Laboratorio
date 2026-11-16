# Respuestas de la parte 3 (Decidir)

**A cargo de:** Cunalata Mendoza Damian Alexander

Situación de partida: en la parte 2 se cambió la latencia de Router → Servidor a 2 y se eliminó el enlace Servidor → PC2. En la parte 3 el Switch se daña y se elimina del grafo, junto con los enlaces que llegaban a él (Router → Switch) y los que salían de él (Switch → PC1 y Switch → PC2).

La red queda así:

```
Router    -> Servidor(2)
Servidor  ->
PC1       ->
PC2       -> Impresora(3)
Impresora ->
```

## 1. ¿Qué devuelve `esConexo("Router")` después de eliminar el Switch?

Devuelve **`false`**. Quedan 5 equipos y desde el Router el BFS solo alcanza 2: Router y Servidor.

## 2. ¿Qué equipos quedaron aislados?

**PC1, PC2 e Impresora.**

- El único camino hacia PC1 y PC2 pasaba por el Switch.
- El Servidor ya no llega a PC2 porque el enlace Servidor → PC2 se eliminó en la parte 2.
- La Impresora solo es alcanzable a través de PC2, así que queda aislada junto con él.

## 3. ¿Cuántos enlaces nuevos hacen falta, como mínimo, para reconectarlos?

**2 enlaces.**

- PC1 y PC2 quedaron sin ningún enlace de entrada. Un enlace solo llega a un equipo, así que cada uno necesita al menos uno: no se puede con menos de 2.
- La Impresora no necesita enlace propio porque PC2 → Impresora sigue existiendo: al reconectar PC2 se recupera también la Impresora. Por eso no hacen falta 3.

## 4. Enlaces agregados y comprobación

Se agregan **Router → PC1 (2)** y **Router → PC2 (2)**.

```
Router    -> PC2(2) PC1(2) Servidor(2)
Servidor  ->
PC1       ->
PC2       -> Impresora(3)
Impresora ->
```

Después de agregarlos, `esConexo("Router")` devuelve **`true`**: el BFS alcanza 5 de 5 en el orden Router, PC2, PC1, Servidor, Impresora.

Las pruebas P18 a P21 de `PruebasRed` comprueban estas respuestas: sin el Switch no es conexa (P18) y solo se llega a 2 equipos (P19); con un solo enlace nuevo sigue sin ser conexa (P20) y con dos vuelve a serlo (P21).

## Observación

El mínimo de 2 depende de lo que se hizo en la parte 2. Si no se hubiera eliminado Servidor → PC2, al caer el Switch solo habría quedado aislado PC1 y habría bastado 1 enlace.
