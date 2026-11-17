package com.rpg.fabrica;

import com.rpg.estrategia.AtaqueHechizo;
import com.rpg.modelo.Personaje;
import com.rpg.modelo.PersonajeBase;

public class CreadorMago extends CreadorPersonaje {
    @Override
    public Personaje crearPersonaje(String nombre) {
        validarNombre(nombre);
        Personaje p = new PersonajeBase(nombre, 100, 20, 10, 15);
        p.setEstrategia(new AtaqueHechizo());
        return p;
    }
}
