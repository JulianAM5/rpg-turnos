# RPG por turnos en Java (Maven)

Prototipo de consola que aplica **Strategy**, **Factory Method** y **Decorator** (+ **Observer** opcional para la bitácora).

## Ejecutar
```bash
mvn -q compile exec:java            # interactivo
mvn -q compile exec:java -Dexec.args="--seed=7"   # corrida reproducible
# o empaquetado:
mvn -q package && java -jar target/rpg-turnos-1.0.0.jar
```
Requiere JDK 17+. Entrada: nombre, clase (1-4), equipamiento separado por comas (1-5, en orden de aplicación) y enemigo (1-3).

## Dónde está cada patrón
| Patrón | Paquete | Clases |
|---|---|---|
| Strategy | `estrategia` | `EstrategiaAtaque`, `AtaqueEspada`, `AtaqueHechizo`, `AtaqueFlecha`, `AtaquePunalada` |
| Factory Method | `fabrica` | `CreadorPersonaje` (Guerrero, Mago, Arquero, Pícaro), `CreadorEnemigo` (Goblin, Orco, Dragón) |
| Decorator | `decorador` | `PersonajeDecorator`, `Espada`, `Armadura`, `Veneno`, `AmuletoRegeneracion`, `Bendicion` |
| Observer (opcional) | `combate` | `CombateListener`, `BitacoraConsola`, `EstadisticasListener` |

## Decisión de diseño importante
En el código base, `PersonajeBase.atacar` pasa `this` a la estrategia, por lo que los atributos de los decoradores **no** se verían al calcular el daño. Se resolvió con `atacarComo(atacante, objetivo)`: el ataque baja por la cadena de decoradores llevando la referencia a la capa más externa. Así la estrategia lee ATK/DEF ya decorados y `VenenoDecorator` puede modificar el resultado (efecto DOT).

## Equipamiento
- Modifican atributos: Espada (+10 ATK), Armadura (+8 DEF), Amuleto (+3 DEF), Bendición (+15%, el orden importa).
- Modifican `atacar()`: Veneno (+5 ATK, DOT de 3 de daño × 3 turnos).
- Efecto de fin de turno: Amuleto (+2 HP/turno).
