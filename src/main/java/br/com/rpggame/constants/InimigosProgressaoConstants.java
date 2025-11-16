package br.com.rpggame.constants;

public final class InimigosProgressaoConstants {

	public static final double LOBO_ESCALA_ATRIBUTOS = 0.15;
	public static final double BANDIDO_ESCALA_ATRIBUTOS = 0.18;
	public static final double ESQUELETO_ESCALA_ATRIBUTOS = 0.20;

	public static final double LOBO_ESCALA_ATAQUE = 0.60;
	public static final double BANDIDO_ESCALA_ATAQUE = 0.60;
	public static final double ESQUELETO_ESCALA_ATAQUE = 0.70;

	public static final int LOBO_XP_BASE = 12;
	public static final int BANDIDO_XP_BASE = 15;
	public static final int ESQUELETO_XP_BASE = 18;

	private InimigosProgressaoConstants() {}

	public static double obterEscalaAtributos(String nomeInimigo) {
		if (InimigosBaseConstants.LOBO_NOME.equals(nomeInimigo)) {
			return LOBO_ESCALA_ATRIBUTOS;
		}
		if (InimigosBaseConstants.BANDIDO_NOME.equals(nomeInimigo)) {
			return BANDIDO_ESCALA_ATRIBUTOS;
		}
		if (InimigosBaseConstants.ESQUELETO_NOME.equals(nomeInimigo)) {
			return ESQUELETO_ESCALA_ATRIBUTOS;
		}
		return 0.10;
	}

	public static double obterEscalaAtaque(String nomeInimigo) {
		if (InimigosBaseConstants.LOBO_NOME.equals(nomeInimigo)) {
			return LOBO_ESCALA_ATAQUE;
		}
		if (InimigosBaseConstants.BANDIDO_NOME.equals(nomeInimigo)) {
			return BANDIDO_ESCALA_ATAQUE;
		}
		if (InimigosBaseConstants.ESQUELETO_NOME.equals(nomeInimigo)) {
			return ESQUELETO_ESCALA_ATAQUE;
		}
		return 0.50;
	}

	public static int obterXpBase(String nomeInimigo) {
		if (InimigosBaseConstants.LOBO_NOME.equals(nomeInimigo)) {
			return LOBO_XP_BASE;
		}
		if (InimigosBaseConstants.BANDIDO_NOME.equals(nomeInimigo)) {
			return BANDIDO_XP_BASE;
		}
		if (InimigosBaseConstants.ESQUELETO_NOME.equals(nomeInimigo)) {
			return ESQUELETO_XP_BASE;
		}
		return 10;
	}
}


