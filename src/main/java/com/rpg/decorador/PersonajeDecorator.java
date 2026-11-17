package com.rpg.decorador;

import com.rpg.estrategia.EstrategiaAtaque;
import com.rpg.modelo.Personaje;
import com.rpg.modelo.ResultadoAtaque;
import java.util.List;

/** Decorator abstracto: envuelve a un Personaje y delega todo por defecto. */
public abstract class PersonajeDecorator implements Personaje {
    protected final Personaje personaje;

    public PersonajeDecorator(Personaje personaje) {
        this.personaje = personaje;
    }

    @Override public String getNombre() { return personaje.getNombre(); }
    @Override public int getAtaque() { return personaje.getAtaque(); }
    @Override public int getDefensa() { return personaje.getDefensa(); }
    @Override public int getVelocidad() { return personaje.getVelocidad(); }
    @Override public int getHp() { return personaje.getHp(); }
    @Override public int getHpMax() { return personaje.getHpMax(); }
    @Override public void setHp(int hp) { personaje.setHp(hp); }
    @Override public EstrategiaAtaque getEstrategia() { return personaje.getEstrategia(); }
    @Override public void setEstrategia(EstrategiaAtaque e) { personaje.setEstrategia(e); }

    /** El ataque se evalúa como la capa más externa (this), para que la estrategia vea todos los modificadores. */
    @Override
    public ResultadoAtaque atacar(Personaje objetivo) {
        return atacarComo(this, objetivo);
    }

    @Override
    public ResultadoAtaque atacarComo(Personaje atacante, Personaje objetivo) {
        return personaje.atacarComo(atacante, objetivo);
    }

    @Override public void agregarDot(int dano, int turnos) { personaje.agregarDot(dano, turnos); }
    @Override public List<String> alFinalDeTurno() { return personaje.alFinalDeTurno(); }
    @Override public String getDescripcion() { return personaje.getDescripcion(); }
}
