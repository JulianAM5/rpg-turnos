package com.rpg.fabrica;

import com.rpg.estrategia.AtaqueEspada;
import com.rpg.modelo.Personaje;
import com.rpg.modelo.PersonajeBase;

public class CreadorOrco extends CreadorEnemigo {
    @Override
    public Personaje crearEnemigo() {
        Personaje e = new PersonajeBase("Orco bruto", 90, 17, 8, 9);
        e.setEstrategia(new AtaqueEspada());
        return e;
    }
}
