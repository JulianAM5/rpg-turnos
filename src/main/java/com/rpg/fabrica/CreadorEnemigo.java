package com.rpg.fabrica;

import com.rpg.modelo.Personaje;

/** Factory Method: creador abstracto de enemigos. */
public abstract class CreadorEnemigo {
    public abstract Personaje crearEnemigo();
}
