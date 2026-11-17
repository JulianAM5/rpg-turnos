package com.rpg.decorador;

import com.rpg.modelo.Personaje;
import java.util.ArrayList;
import java.util.List;

/** +3 defensa y +2 HP al final de cada turno. */
public class AmuletoRegeneracionDecorator extends PersonajeDecorator {
    public static final int REGEN = 2;

    public AmuletoRegeneracionDecorator(Personaje p) { super(p); }

    @Override public int getDefensa() { return personaje.getDefensa() + 3; }

    @Override
    public List<String> alFinalDeTurno() {
        List<String> mensajes = new ArrayList<>(personaje.alFinalDeTurno());
        if (getHp() > 0 && getHp() < getHpMax()) {
            int antes = getHp();
            setHp(antes + REGEN);
            mensajes.add(getNombre() + " regenera " + (getHp() - antes) + " HP ("
                    + antes + " -> " + getHp() + ")");
        }
        return mensajes;
    }

    @Override public String getDescripcion() { return personaje.getDescripcion() + " + Amuleto"; }
}
