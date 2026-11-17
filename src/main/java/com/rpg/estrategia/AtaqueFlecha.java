package com.rpg.estrategia;

import com.rpg.modelo.Personaje;
import com.rpg.modelo.ResultadoAtaque;

/** Disparo a distancia: solo la mitad de la defensa cuenta; crítico frecuente. */
public class AtaqueFlecha implements EstrategiaAtaque {
    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        int base = Math.max(1, atacante.getAtaque() - objetivo.getDefensa() / 2);
        boolean critico = Azar.prob(0.20);
        int dano = critico ? (int) Math.round(base * 2.0) : base;
        return new ResultadoAtaque(dano, critico, "flecha");
    }
}
