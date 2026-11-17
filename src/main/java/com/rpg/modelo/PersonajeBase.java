package com.rpg.modelo;

import com.rpg.estrategia.EstrategiaAtaque;
import java.util.ArrayList;
import java.util.List;

/** Componente concreto: personaje sin equipamiento. */
public class PersonajeBase implements Personaje {
    private final String nombre;
    private int hp;
    private final int hpMax;
    private final int ataque;
    private final int defensa;
    private final int velocidad;
    private EstrategiaAtaque estrategia;

    private int dotDano;
    private int dotTurnos;

    public PersonajeBase(String nombre, int hp, int ataque, int defensa, int velocidad) {
        this.nombre = nombre;
        this.hp = hp;
        this.hpMax = hp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
    }

    @Override public String getNombre() { return nombre; }
    @Override public int getAtaque() { return ataque; }
    @Override public int getDefensa() { return defensa; }
    @Override public int getVelocidad() { return velocidad; }
    @Override public int getHp() { return hp; }
    @Override public int getHpMax() { return hpMax; }
    @Override public void setHp(int hp) { this.hp = Math.max(0, Math.min(hpMax, hp)); }
    @Override public EstrategiaAtaque getEstrategia() { return estrategia; }
    @Override public void setEstrategia(EstrategiaAtaque e) { this.estrategia = e; }

    @Override
    public ResultadoAtaque atacar(Personaje objetivo) {
        return atacarComo(this, objetivo);
    }

    @Override
    public ResultadoAtaque atacarComo(Personaje atacante, Personaje objetivo) {
        return estrategia.atacar(atacante, objetivo);   // Strategy
    }

    @Override
    public void agregarDot(int danoPorTurno, int turnos) {
        this.dotDano = danoPorTurno;
        this.dotTurnos = turnos;
    }

    @Override
    public List<String> alFinalDeTurno() {
        List<String> mensajes = new ArrayList<>();
        if (dotTurnos > 0 && hp > 0) {
            int antes = hp;
            setHp(hp - dotDano);
            dotTurnos--;
            mensajes.add(nombre + " sufre " + (antes - hp) + " de daño por veneno ("
                    + antes + " -> " + hp + ", quedan " + dotTurnos + " turnos)");
        }
        return mensajes;
    }

    @Override
    public String getDescripcion() { return nombre + " (base)"; }
}
