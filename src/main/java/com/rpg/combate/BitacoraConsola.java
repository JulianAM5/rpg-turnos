package com.rpg.combate;

import com.rpg.modelo.Personaje;
import com.rpg.modelo.ResultadoAtaque;

/** Observer: bitácora + HUD en consola. */
public class BitacoraConsola implements CombateListener {
    @Override
    public void alIniciar(Personaje heroe, Personaje enemigo) {
        System.out.println("\n=== COMBATE INICIADO ===");
    }

    @Override
    public void alInicioTurno(int turno) {
        System.out.println("\n--- TURNO " + turno + " ---");
    }

    @Override
    public void alAtaque(Personaje a, Personaje d, ResultadoAtaque r, int antes, int despues) {
        System.out.println(a.getDescripcion() + " usa " + a.getEstrategia().getClass().getSimpleName()
                + " sobre " + d.getNombre());
        System.out.println("  Daño: " + r.getDano() + (r.isCritico() ? " (¡CRÍTICO!)" : "")
                + " | Efecto: " + r.getEfecto());
        System.out.println("  " + d.getNombre() + " HP: " + antes + " -> " + despues);
    }

    @Override
    public void alEfecto(String mensaje) {
        System.out.println("  * " + mensaje);
    }

    @Override
    public void alFinTurno(int turno, Personaje h, Personaje e) {
        System.out.println("  [Estado] " + h.getNombre() + ": " + h.getHp() + "/" + h.getHpMax()
                + " HP | " + e.getNombre() + ": " + e.getHp() + "/" + e.getHpMax() + " HP");
    }

    @Override
    public void alFinalizar(Personaje h, Personaje e, Personaje ganador, int turnos) {
        System.out.println("\n=== COMBATE FINALIZADO ===");
        if (ganador == null) {
            System.out.println("Empate (se alcanzó el límite de turnos).");
        } else {
            System.out.println("Ganador: " + ganador.getDescripcion() + " (HP restante: " + ganador.getHp() + ")");
            System.out.println(ganador == h ? "*** VICTORIA ***" : "*** DERROTA ***");
        }
    }
}
