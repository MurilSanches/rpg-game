package br.com.rpggame;

import java.util.Random;

public final class Dado {
	private static final int DEFAULT_FACES = 6;
	private static Random rng = new Random();

	private Dado() {}

	public static void configurarSemente(Long seed) {
		if (seed == null) {
			rng = new Random();
		} else {
			rng = new Random(seed);
		}
	}

	public static int rolar() {
		return rolar(DEFAULT_FACES);
	}

	public static int rolar(int faces) {
		if (faces <= 0) {
			throw new IllegalArgumentException("Número de faces deve ser positivo");
		}
		return rng.nextInt(faces) + 1;
	}
}


