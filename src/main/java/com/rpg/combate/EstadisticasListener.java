package com.rpg.combate;

import com.rpg.modelo.Personaje;
import com.rpg.modelo.ResultadoAtaque;

/** Observer: enriquece la bitácora con estadísticas finales (daño total, críticos). */
public class EstadisticasListener implements CombateListener {
    private int danoHeroe, danoEnemigo, criticosHeroe, criticosEnemigo;
    private Personaje heroe;

    @Override public void alIniciar(Personaje heroe, Personaje enemigo) { this.heroe = heroe; }

    @Override
    public void alAtaque(Personaje a, Personaje d, ResultadoAtaque r, int antes, int despues) {
        int real = antes - despues;
        if (a == heroe) { danoHeroe += real; if (r.isCritico()) criticosHeroe++; }
        else            { danoEnemigo += real; if (r.isCritico()) criticosEnemigo++; }
    }

    @Override
    public void alFinalizar(Personaje h, Personaje e, Personaje ganador, int turnos) {
        System.out.println("\n--- ESTADÍSTICAS FINALES ---");
        System.out.println("Turnos jugados: " + turnos);
        System.out.println(h.getNombre() + ": daño directo " + danoHeroe + " | críticos " + criticosHeroe);
        System.out.println(e.getNombre() + ": daño directo " + danoEnemigo + " | críticos " + criticosEnemigo);
    }
}
