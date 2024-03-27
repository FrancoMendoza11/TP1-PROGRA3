package logica;

import static org.junit.Assert.*;

import org.junit.Test;

public class movimientosTest {

    @Test
    public void testMovimientoArriba() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {0, 0, 2, 8},
            {8, 8, 8, 8},
            {8, 8, 0, 8},
            {16, 8, 8, 8}
        };
        juego.tablero = tableroInicial;
        assertTrue(juego.mover(1)); // Mover arriba
        
        int[][] tableroEsperado = {
            {16, 16, 2, 16},
            {16, 8, 16, 16},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }

    @Test
    public void testMovimientoAbajo() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {0, 0, 0, 0},
            {16, 0, 0, 0},
            {16, 0, 0, 0},
            {0, 0, 0, 0}
        };
        juego.tablero = tableroInicial;
        assertTrue(juego.mover(2)); // Mover abajo
        
        int[][] tableroEsperado = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {32, 0, 0, 0}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }

    @Test
    public void testMovimientoIzquierda() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {0, 0, 0, 0},
            {0, 8, 8, 16},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        juego.tablero = tableroInicial;
        assertTrue(juego.mover(3)); // Mover izquierda
        
        int[][] tableroEsperado = {
            {0, 0, 0, 0},
            {16, 16, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }

    @Test
    public void testMovimientoDerecha() {
        Juego2048 juego = new Juego2048();
        int[][] tableroInicial = {
            {0, 0, 0, 0},
            {8, 8, 16, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        juego.tablero = tableroInicial;
        assertTrue(juego.mover(4)); // Mover derecha
        
        int[][] tableroEsperado = {
            {0, 0, 0, 0},
            {0, 0, 16, 16},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        assertArrayEquals(tableroEsperado, juego.obtenerTablero());
    }

}

