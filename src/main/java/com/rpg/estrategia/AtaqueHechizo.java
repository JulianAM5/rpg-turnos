package com.rpg.estrategia;

import com.rpg.modelo.Personaje;
import com.rpg.modelo.ResultadoAtaque;

/** Magia: ignora por completo la defensa del objetivo. */
public class AtaqueHechizo implements EstrategiaAtaque {
    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        int base = Math.max(1, atacante.getAtaque());
        boolean critico = Azar.prob(0.10);
        int dano = critico ? (int) Math.round(base * 1.5) : base;
        return new ResultadoAtaque(dano, critico, "hechizo");
    }
}
