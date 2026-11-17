package com.rpg.fabrica;

import com.rpg.estrategia.AtaquePunalada;
import com.rpg.modelo.Personaje;
import com.rpg.modelo.PersonajeBase;

public class CreadorGoblin extends CreadorEnemigo {
    @Override
    public Personaje crearEnemigo() {
        Personaje e = new PersonajeBase("Goblin salvaje", 60, 13, 5, 12);
        e.setEstrategia(new AtaquePunalada());
        return e;
    }
}
