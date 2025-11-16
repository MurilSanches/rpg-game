package br.com.rpggame.model;

public final class AtaqueResultado {
	private final int dano;
	private final int rolagem;

	public AtaqueResultado(int dano, int rolagem) {
		this.dano = dano;
		this.rolagem = rolagem;
	}

	public int getDano() {
		return dano;
	}

	public int getRolagem() {
		return rolagem;
	}
}


