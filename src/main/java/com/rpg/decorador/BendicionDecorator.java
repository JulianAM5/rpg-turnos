package com.rpg.decorador;

import com.rpg.modelo.Personaje;

/**
 * +15% a ataque, defensa y velocidad (redondeado). Es multiplicativo sobre lo que ya
 * hayan sumado los decoradores internos, así que el ORDEN de equipamiento importa.
 */
public class BendicionDecorator extends PersonajeDecorator {
    private static final double FACTOR = 1.15;

    public BendicionDecorator(Personaje p) { super(p); }

    @Override public int getAtaque() { return (int) Math.round(personaje.getAtaque() * FACTOR); }
    @Override public int getDefensa() { return (int) Math.round(personaje.getDefensa() * FACTOR); }
    @Override public int getVelocidad() { return (int) Math.round(personaje.getVelocidad() * FACTOR); }
    @Override public String getDescripcion() { return personaje.getDescripcion() + " + Bendición"; }
}
