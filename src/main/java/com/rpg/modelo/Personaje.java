package com.rpg.modelo;

import com.rpg.estrategia.EstrategiaAtaque;
import java.util.List;

/**
 * Componente común del patrón Decorator (lo implementan PersonajeBase y los decoradores)
 * y contexto del patrón Strategy (delega el ataque en una EstrategiaAtaque).
 */
public interface Personaje {
    String getNombre();
    int getAtaque();
    int getDefensa();
    int getVelocidad();
    int getHp();
    int getHpMax();
    void setHp(int hp);

    EstrategiaAtaque getEstrategia();
    void setEstrategia(EstrategiaAtaque estrategia);

    /** Ataca al objetivo. Siempre se evalúa "como" el personaje más externo de la cadena de decoradores. */
    ResultadoAtaque atacar(Personaje objetivo);

    /**
     * Ejecuta el ataque en nombre de {@code atacante} (la capa más externa).
     * Así la estrategia lee los atributos YA decorados y cada decorador de
     * daño puede intervenir en el resultado mientras la llamada baja por la cadena.
     */
    ResultadoAtaque atacarComo(Personaje atacante, Personaje objetivo);

    /** Aplica un daño periódico (DOT). Un nuevo DOT refresca al anterior. */
    void agregarDot(int danoPorTurno, int turnos);

    /** Efectos de fin de turno (DOT recibido, regeneración, etc.). Devuelve mensajes para la bitácora. */
    List<String> alFinalDeTurno();

    String getDescripcion();

    default boolean estaVivo() { return getHp() > 0; }
}
