package com.rpg.fabrica;

import com.rpg.estrategia.AtaqueFlecha;
import com.rpg.modelo.Personaje;
import com.rpg.modelo.PersonajeBase;

public class CreadorArquero extends CreadorPersonaje {
    @Override
    public Personaje crearPersonaje(String nombre) {
        validarNombre(nombre);
        Personaje p = new PersonajeBase(nombre, 90, 17, 8, 18);
        p.setEstrategia(new AtaqueFlecha());
        return p;
    }
}
