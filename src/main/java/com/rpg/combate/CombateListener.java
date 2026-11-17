package com.rpg.combate;

import com.rpg.modelo.Personaje;
import com.rpg.modelo.ResultadoAtaque;

/** Observer: reciben los eventos del combate (bitácora, HUD, estadísticas...). */
public interface CombateListener {
    default void alIniciar(Personaje heroe, Personaje enemigo) {}
    default void alInicioTurno(int turno) {}
    default void alAtaque(Personaje atacante, Personaje defensor, ResultadoAtaque r, int hpAntes, int hpDespues) {}
    default void alEfecto(String mensaje) {}
    default void alFinTurno(int turno, Personaje heroe, Personaje enemigo) {}
    default void alFinalizar(Personaje heroe, Personaje enemigo, Personaje ganador, int turnos) {}
}
