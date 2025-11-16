package br.com.rpggame.constants;

public final class InimigosProgressaoConstants {

	// Escalas de atributos por nível (exemplo: 0.15 = +15% por nível acima do 1)
	public static final double LOBO_ESCALA_ATRIBUTOS = 0.15;
	public static final double BANDIDO_ESCALA_ATRIBUTOS = 0.18;
	public static final double ESQUELETO_ESCALA_ATRIBUTOS = 0.20;

	// XP base concedido por inimigo derrotado
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
		return 0.10; // padrão
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
		return 10; // padrão
	}
}


