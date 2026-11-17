package com.rpg.ui;

import com.rpg.decorador.*;
import com.rpg.modelo.Personaje;
import java.util.function.UnaryOperator;

/** Catálogo de equipamiento: el cliente aplica decoradores sin conocer sus clases concretas. */
public enum Equipo {
    ESPADA("Espada (+10 ATK)", EspadaDecorator::new),
    ARMADURA("Armadura (+8 DEF)", ArmaduraDecorator::new),
    VENENO("Veneno (+5 ATK, DOT)", VenenoDecorator::new),
    AMULETO("Amuleto de regeneración (+3 DEF, +2 HP/turno)", AmuletoRegeneracionDecorator::new),
    BENDICION("Bendición (+15% atributos)", BendicionDecorator::new);

    private final String etiqueta;
    private final UnaryOperator<Personaje> envoltorio;

    Equipo(String etiqueta, UnaryOperator<Personaje> envoltorio) {
        this.etiqueta = etiqueta;
        this.envoltorio = envoltorio;
    }

    public String etiqueta() { return etiqueta; }
    public Personaje aplicar(Personaje p) { return envoltorio.apply(p); }
}
