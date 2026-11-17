package com.rpg.decorador;

import com.rpg.modelo.Personaje;
import com.rpg.modelo.ResultadoAtaque;

/**
 * +5 ataque y efecto DOT: cada golpe que causa daño envenena al objetivo
 * (3 de daño al final de su turno durante 3 turnos; volver a envenenar refresca la duración).
 * Es el decorador que modifica el comportamiento de atacar().
 */
public class VenenoDecorator extends PersonajeDecorator {
    public static final int DOT_DANO = 3;
    public static final int DOT_TURNOS = 3;

    public VenenoDecorator(Personaje p) { super(p); }

    @Override public int getAtaque() { return personaje.getAtaque() + 5; }

    @Override
    public ResultadoAtaque atacarComo(Personaje atacante, Personaje objetivo) {
        ResultadoAtaque r = personaje.atacarComo(atacante, objetivo);
        if (r.getDano() > 0) {
            objetivo.agregarDot(DOT_DANO, DOT_TURNOS);
            return r.conEfecto("veneno");
        }
        return r;
    }

    @Override public String getDescripcion() { return personaje.getDescripcion() + " + Veneno"; }
}
