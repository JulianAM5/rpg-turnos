package com.rpg.fabrica;

import com.rpg.estrategia.AtaquePunalada;
import com.rpg.modelo.Personaje;
import com.rpg.modelo.PersonajeBase;

public class CreadorPicaro extends CreadorPersonaje {
    @Override
    public Personaje crearPersonaje(String nombre) {
        validarNombre(nombre);
        Personaje p = new PersonajeBase(nombre, 85, 16, 6, 20);
        p.setEstrategia(new AtaquePunalada());
        return p;
    }
}
