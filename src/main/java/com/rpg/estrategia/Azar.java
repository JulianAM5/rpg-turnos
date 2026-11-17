package com.rpg.estrategia;

import java.util.Random;

/** Fuente de aleatoriedad única; permite fijar una semilla para obtener corridas reproducibles. */
public final class Azar {
    private static Random random = new Random();
    private Azar() {}

    public static void semilla(long semilla) { random = new Random(semilla); }
    public static boolean prob(double p) { return random.nextDouble() < p; }
}
