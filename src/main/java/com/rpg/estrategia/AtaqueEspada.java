package com.rpg.estrategia;

import com.rpg.modelo.Personaje;
import com.rpg.modelo.ResultadoAtaque;

/** Golpe cuerpo a cuerpo: ataque menos defensa del objetivo. */
public class AtaqueEspada implements EstrategiaAtaque {
    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        int base = Math.max(1, atacante.getAtaque() - objetivo.getDefensa());
        boolean critico = Azar.prob(0.10);
        int dano = critico ? (int) Math.round(base * 1.5) : base;
        return new ResultadoAtaque(dano, critico, "espada");
    }
}
