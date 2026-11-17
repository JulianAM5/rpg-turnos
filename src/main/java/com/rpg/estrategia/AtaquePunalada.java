package com.rpg.estrategia;

import com.rpg.modelo.Personaje;
import com.rpg.modelo.ResultadoAtaque;

/** Ataque rápido con alta probabilidad de crítico. */
public class AtaquePunalada implements EstrategiaAtaque {
    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        int base = Math.max(1, atacante.getAtaque() - objetivo.getDefensa());
        boolean critico = Azar.prob(0.25);
        int dano = critico ? (int) Math.round(base * 2.0) : base;
        return new ResultadoAtaque(dano, critico, "puñalada");
    }
}
