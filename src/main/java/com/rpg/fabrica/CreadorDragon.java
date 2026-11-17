package com.rpg.fabrica;

import com.rpg.estrategia.AtaqueHechizo;
import com.rpg.modelo.Personaje;
import com.rpg.modelo.PersonajeBase;

public class CreadorDragon extends CreadorEnemigo {
    @Override
    public Personaje crearEnemigo() {
        Personaje e = new PersonajeBase("Dragón ancestral", 150, 19, 10, 11);
        e.setEstrategia(new AtaqueHechizo());
        return e;
    }
}
