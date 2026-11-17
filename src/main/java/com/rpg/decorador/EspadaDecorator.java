package com.rpg.decorador;

import com.rpg.modelo.Personaje;

/** +10 ataque. */
public class EspadaDecorator extends PersonajeDecorator {
    public EspadaDecorator(Personaje p) { super(p); }

    @Override public int getAtaque() { return personaje.getAtaque() + 10; }
    @Override public String getDescripcion() { return personaje.getDescripcion() + " + Espada"; }
}
