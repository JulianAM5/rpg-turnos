package com.rpg.combate;

import com.rpg.modelo.Personaje;
import com.rpg.modelo.ResultadoAtaque;
import java.util.ArrayList;
import java.util.List;

/**
 * Motor de combate por turnos. No conoce clases concretas ni formas de ataque:
 * solo habla con la interfaz Personaje y notifica a sus observadores.
 */
public class MotorCombate {
    private static final int MAX_TURNOS = 200;
    private final List<CombateListener> listeners = new ArrayList<>();

    public void agregarListener(CombateListener l) { listeners.add(l); }

    public Personaje iniciarCombate(Personaje heroe, Personaje enemigo) {
        listeners.forEach(l -> l.alIniciar(heroe, enemigo));
        int turno = 1;
        while (heroe.estaVivo() && enemigo.estaVivo() && turno <= MAX_TURNOS) {
            final int t = turno;
            listeners.forEach(l -> l.alInicioTurno(t));

            // El más veloz actúa primero (empate: el héroe).
            Personaje primero = enemigo.getVelocidad() > heroe.getVelocidad() ? enemigo : heroe;
            Personaje segundo = primero == heroe ? enemigo : heroe;

            ejecutarTurno(primero, segundo);
            if (primero.estaVivo() && segundo.estaVivo()) ejecutarTurno(segundo, primero);

            listeners.forEach(l -> l.alFinTurno(t, heroe, enemigo));
            turno++;
        }
        Personaje ganador = heroe.estaVivo() && !enemigo.estaVivo() ? heroe
                : enemigo.estaVivo() && !heroe.estaVivo() ? enemigo : null;
        final int total = turno - 1;
        listeners.forEach(l -> l.alFinalizar(heroe, enemigo, ganador, total));
        return ganador;
    }

    private void ejecutarTurno(Personaje atacante, Personaje defensor) {
        ResultadoAtaque r = atacante.atacar(defensor);
        int antes = defensor.getHp();
        defensor.setHp(antes - r.getDano());
        int despues = defensor.getHp();
        listeners.forEach(l -> l.alAtaque(atacante, defensor, r, antes, despues));

        if (!defensor.estaVivo()) return;
        // Efectos de fin de turno del que actuó (DOT recibido, regeneración...)
        for (String msg : atacante.alFinalDeTurno())
            listeners.forEach(l -> l.alEfecto(msg));
    }
}
