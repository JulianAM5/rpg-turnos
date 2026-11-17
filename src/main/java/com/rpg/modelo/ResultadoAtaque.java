package com.rpg.modelo;

/** Resultado inmutable de un ataque. */
public class ResultadoAtaque {
    private final int dano;
    private final boolean critico;
    private final String efecto;

    public ResultadoAtaque(int dano, boolean critico, String efecto) {
        this.dano = dano;
        this.critico = critico;
        this.efecto = efecto;
    }

    public int getDano() { return dano; }
    public boolean isCritico() { return critico; }
    public String getEfecto() { return efecto; }

    /** Devuelve una copia con un texto de efecto adicional (usado por los decoradores). */
    public ResultadoAtaque conEfecto(String extra) {
        return new ResultadoAtaque(dano, critico, efecto + " + " + extra);
    }

    /** Devuelve una copia con el daño modificado. */
    public ResultadoAtaque conDano(int nuevoDano) {
        return new ResultadoAtaque(Math.max(0, nuevoDano), critico, efecto);
    }
}
