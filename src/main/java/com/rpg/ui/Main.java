package com.rpg.ui;

import com.rpg.combate.BitacoraConsola;
import com.rpg.combate.EstadisticasListener;
import com.rpg.combate.MotorCombate;
import com.rpg.estrategia.Azar;
import com.rpg.fabrica.*;
import com.rpg.modelo.Personaje;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Cliente de consola. Uso: java -jar rpg-turnos-1.0.0.jar [--seed=N]
 * Solo conoce las fábricas y la interfaz Personaje.
 */
public class Main {
    private static final Scanner IN = new Scanner(System.in, StandardCharsets.UTF_8);
    private static final boolean ECO = System.console() == null; // si la entrada es redirigida, se muestra lo leído

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        long semilla = System.nanoTime();
        for (String a : args)
            if (a.startsWith("--seed=")) semilla = Long.parseLong(a.substring(7));
        Azar.semilla(semilla);

        System.out.println("=== RPG POR TURNOS (Strategy + Factory Method + Decorator) ===");
        System.out.println("Semilla aleatoria: " + semilla);

        // --- Factory Method: personaje ---
        String nombre = leer("Nombre: ");
        CreadorPersonaje[] creadores = { new CreadorGuerrero(), new CreadorMago(), new CreadorArquero(), new CreadorPicaro() };
        String[] clases = { "Guerrero", "Mago", "Arquero", "Pícaro" };
        System.out.println("Clases: 1) Guerrero  2) Mago  3) Arquero  4) Pícaro");
        int c = leerEntero("Clase: ", 1, clases.length) - 1;
        Personaje heroe = creadores[c].crearPersonaje(nombre);

        // --- Decorator: equipamiento ---
        Equipo[] equipos = Equipo.values();
        System.out.println("Equipamiento (separado por comas, orden de aplicación; vacío = nada):");
        for (int i = 0; i < equipos.length; i++)
            System.out.println("  " + (i + 1) + ") " + equipos[i].etiqueta());
        String linea = leer("Equipamiento: ");
        if (!linea.isBlank()) {
            for (String t : linea.split(",")) {
                try {
                    int idx = Integer.parseInt(t.trim()) - 1;
                    heroe = equipos[idx].aplicar(heroe);
                } catch (RuntimeException ex) {
                    System.out.println("  (opción ignorada: '" + t.trim() + "')");
                }
            }
        }

        // --- Factory Method: enemigo ---
        CreadorEnemigo[] creadoresEnemigo = { new CreadorGoblin(), new CreadorOrco(), new CreadorDragon() };
        System.out.println("Enemigos: 1) Goblin  2) Orco  3) Dragón");
        int e = leerEntero("Enemigo: ", 1, creadoresEnemigo.length) - 1;
        Personaje enemigo = creadoresEnemigo[e].crearEnemigo();

        System.out.println("\nHéroe: " + heroe.getDescripcion() + "  [" + clases[c] + "]");
        System.out.println("ATK: " + heroe.getAtaque() + " | DEF: " + heroe.getDefensa()
                + " | VEL: " + heroe.getVelocidad() + " | HP: " + heroe.getHp());
        System.out.println("Enemigo: " + enemigo.getNombre() + " | ATK: " + enemigo.getAtaque()
                + " | DEF: " + enemigo.getDefensa() + " | VEL: " + enemigo.getVelocidad()
                + " | HP: " + enemigo.getHp());

        // --- Strategy + Observer: combate ---
        MotorCombate motor = new MotorCombate();
        motor.agregarListener(new BitacoraConsola());
        motor.agregarListener(new EstadisticasListener());
        motor.iniciarCombate(heroe, enemigo);
    }

    private static String leer(String prompt) {
        System.out.print(prompt);
        String s = IN.nextLine();
        if (ECO) System.out.println(s);
        return s;
    }

    private static int leerEntero(String prompt, int min, int max) {
        while (true) {
            try {
                int n = Integer.parseInt(leer(prompt).trim());
                if (n >= min && n <= max) return n;
            } catch (NumberFormatException ignored) { }
            System.out.println("  Opción inválida, elige entre " + min + " y " + max + ".");
        }
    }
}
