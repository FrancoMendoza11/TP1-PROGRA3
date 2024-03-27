package logica;

import java.util.Random;

public class Juego2048 {
	int TAMANO = 4;
	int[][] tablero;
	Random rand;
	Integer turno, puntos;

	public Juego2048() {
		reiniciarJuego();
	}

	public void reiniciarJuego() {
		tablero = new int[4][4];
		turno = 0;
		puntos = 0;
		agregarNumero();
		agregarNumero();
	}

	// Agregar numero 2 o 4;

	public void agregarNumero() {
		int fila, columna;
		rand = new Random();

		do {
			fila = rand.nextInt(4); // Genera un entero aleatorio entre 0 y 3;
			columna = rand.nextInt(4); // Genera un entero aleatorio entre 0 y 3;
		} while (tablero[fila][columna] != 0); // Si existe un valor en una posicion, seguir cambiando de forma
												// aleatoria los valores de fila y columna.

		// Cuando termina de encontrar una posicion vacia, asigno de forma aleatoria los
		// valores {2,4}
		// Por defecto, cualquier posicion de una matriz tiene asignado el valor 0

		tablero[fila][columna] = rand.nextInt(2) * 2 + 2;
		// nextInt genera un entero entre 0 y 2, es decir 0 <= n <= 1
		// Si quiero obtener un 2, multiplico por 2 (rand.nextInt(2)*2), 2.0 <=n<=1.2,
		// entonces 0<=n<=2
		// Si quiero obtener un 2 o un 4, sumo 2 (rand.nextInt(2)*2+2), entonces 2<=n<=4
	}

	public int[][] obtenerTablero() {
		return tablero;
	}

	public String obtenerTurno() {
		return turno.toString();
	}

	public boolean mover(int direccion) {
		boolean movimientoValido = false;
		switch (direccion) {
		case 1: // Arriba
			movimientoValido = moverArriba();
			break;
		case 2: // Abajo
			movimientoValido = moverAbajo();
			break;
		case 3: // Izquierda
			movimientoValido = moverIzquierda();
			break;
		case 4: // Derecha
			movimientoValido = moverDerecha();
			break;
		}
		turno++;
		return movimientoValido;
	}

	public boolean moverArriba() {
		boolean movimientoValido = false;
		for (int columna = 0; columna < TAMANO; columna++) {
			movimientoValido |= moverColumnaArriba(columna);
		}
		return movimientoValido;
	}

	private boolean moverColumnaArriba(int columna) {
		boolean movimientoValido = false;
		for (int fila = 1; fila < TAMANO; fila++) {
			if (tablero[fila][columna] != 0) {
				movimientoValido |= moverFichaHaciaArriba(columna, fila);
			}
		}
		return movimientoValido;
	}

	private boolean moverFichaHaciaArriba(int columna, int fila) {
		boolean movimientoValido = false;
		int filaActual = fila;
		while (filaActual > 0 && (tablero[filaActual - 1][columna] == 0
				|| tablero[filaActual - 1][columna] == tablero[filaActual][columna])) {
			if (tablero[filaActual - 1][columna] == 0) {
				tablero[filaActual - 1][columna] = tablero[filaActual][columna];
				tablero[filaActual][columna] = 0;
				filaActual--;
				movimientoValido = true;
			} else if (tablero[filaActual - 1][columna] == tablero[filaActual][columna]) {
				tablero[filaActual - 1][columna] *= 2;
				tablero[filaActual][columna] = 0;
				movimientoValido = true;
				sumarPuntos(tablero[filaActual - 1][columna]);
				break;
			}
		}
		return movimientoValido;
	}

	public boolean moverIzquierda() {
		boolean movimientoValido = false;
		for (int fila = 0; fila < TAMANO; fila++) {
			movimientoValido |= moverFilaIzquierda(fila);
		}
		return movimientoValido;
	}

	private boolean moverFilaIzquierda(int fila) {
		boolean movimientoValido = false;
		for (int columna = 1; columna < TAMANO; columna++) {
			if (tablero[fila][columna] != 0) {
				movimientoValido |= moverFichaHaciaIzquierda(fila, columna);
			}
		}
		return movimientoValido;
	}

	private boolean moverFichaHaciaIzquierda(int fila, int columna) {
		boolean movimientoValido = false;
		int columnaActual = columna;
		while (columnaActual > 0 && (tablero[fila][columnaActual - 1] == 0
				|| tablero[fila][columnaActual - 1] == tablero[fila][columnaActual])) {
			if (tablero[fila][columnaActual - 1] == 0) {
				tablero[fila][columnaActual - 1] = tablero[fila][columnaActual];
				tablero[fila][columnaActual] = 0;
				columnaActual--;
				movimientoValido = true;
			} else if (tablero[fila][columnaActual - 1] == tablero[fila][columnaActual]) {
				tablero[fila][columnaActual - 1] *= 2;
				tablero[fila][columnaActual] = 0;
				movimientoValido = true;
				sumarPuntos(tablero[fila][columnaActual - 1]);
				break;
			}
		}
		return movimientoValido;
	}

	private boolean moverDerecha() {
		boolean movimientoValido = false;
		for (int fila = 0; fila < TAMANO; fila++) {
			movimientoValido |= moverFilaDerecha(fila);
		}
		return movimientoValido;
	}

	private boolean moverFilaDerecha(int fila) {
		boolean movimientoValido = false;
		for (int columna = TAMANO - 2; columna >= 0; columna--) {
			if (tablero[fila][columna] != 0) {
				movimientoValido |= moverFichaHaciaDerecha(fila, columna);
			}
		}
		return movimientoValido;
	}

	private boolean moverFichaHaciaDerecha(int fila, int columna) {
		boolean movimientoValido = false;
		int columnaActual = columna;
		while (columnaActual < TAMANO - 1 && (tablero[fila][columnaActual + 1] == 0
				|| tablero[fila][columnaActual + 1] == tablero[fila][columnaActual])) {
			if (tablero[fila][columnaActual + 1] == 0) {
				tablero[fila][columnaActual + 1] = tablero[fila][columnaActual];
				tablero[fila][columnaActual] = 0;
				columnaActual++;
				movimientoValido = true;
			} else if (tablero[fila][columnaActual + 1] == tablero[fila][columnaActual]) {
				tablero[fila][columnaActual + 1] *= 2;
				tablero[fila][columnaActual] = 0;
				movimientoValido = true;
				sumarPuntos(tablero[fila][columnaActual + 1]); // Corregido: usar el valor de la ficha actual
				break;
			}
		}
		return movimientoValido;
	}

	public boolean moverAbajo() {
		boolean movimientoValido = false;
		for (int columna = 0; columna < TAMANO; columna++) {
			movimientoValido |= moverColumnaAbajo(columna);
		}
		return movimientoValido;
	}

	private boolean moverColumnaAbajo(int columna) {
		boolean movimientoValido = false;
		for (int fila = TAMANO - 2; fila >= 0; fila--) {
			if (tablero[fila][columna] != 0) {
				movimientoValido |= moverFichaHaciaAbajo(columna, fila);
			}
		}
		return movimientoValido;
	}

	private boolean moverFichaHaciaAbajo(int columna, int fila) {
		boolean movimientoValido = false;
		int filaActual = fila;
		while (filaActual < TAMANO - 1 && (tablero[filaActual + 1][columna] == 0
				|| tablero[filaActual + 1][columna] == tablero[filaActual][columna])) {
			if (tablero[filaActual + 1][columna] == 0) {
				tablero[filaActual + 1][columna] = tablero[filaActual][columna];
				tablero[filaActual][columna] = 0;
				filaActual++;
				movimientoValido = true;
			} else if (tablero[filaActual + 1][columna] == tablero[filaActual][columna]) {
				tablero[filaActual + 1][columna] *= 2;
				tablero[filaActual][columna] = 0;
				movimientoValido = true;
				sumarPuntos(tablero[filaActual + 1][columna]); // Corregido: usar el valor de la ficha actual
				break;
			}
		}
		return movimientoValido;
	}

	public boolean verificarVictoria() {
		for (int fila = 0; fila < TAMANO; fila++) {
			for (int columna = 0; columna < TAMANO; columna++) {
				if (tablero[fila][columna] == 2048) {
					return true; // Se ha alcanzado el valor 2048
				}
			}
		}
		return false;
	}

	public boolean verificarDerrota() {
		for (int fila = 0; fila < TAMANO; fila++) {
			for (int columna = 0; columna < TAMANO; columna++) {
				if (tablero[fila][columna] == 0) {
					return false; // Todavía hay espacios vacíos
				}
				if (fila < TAMANO - 1 && tablero[fila][columna] == tablero[fila + 1][columna]) {
					return false; // Hay al menos una combinación posible en vertical
				}
				if (columna < TAMANO - 1 && tablero[fila][columna] == tablero[fila][columna + 1]) {
					return false; // Hay al menos una combinación posible en horizontal
				}
			}
		}
		return true; // No hay movimientos válidos posibles
	}

	private void sumarPuntos(int valor) {
		// Sumar puntos dependiendo del valor de la combinación
		puntos += valor;
	}

	public String obtenerPuntos() {
		return puntos.toString();
	}

}
