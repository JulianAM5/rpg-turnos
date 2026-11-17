package com.rpg.decorador;

import com.rpg.modelo.Personaje;

/** +8 defensa. */
public class ArmaduraDecorator extends PersonajeDecorator {
    public ArmaduraDecorator(Personaje p) { super(p); }

    @Override public int getDefensa() { return personaje.getDefensa() + 8; }
    @Override public String getDescripcion() { return personaje.getDescripcion() + " + Armadura"; }
}
