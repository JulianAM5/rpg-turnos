package com.rpg.estrategia;

import com.rpg.modelo.Personaje;
import com.rpg.modelo.ResultadoAtaque;

/** Strategy: una forma de atacar. */
public interface EstrategiaAtaque {
    ResultadoAtaque atacar(Personaje atacante, Personaje objetivo);
}
