package br.com.rpggame.personagens;

import br.com.rpggame.Dado;
import br.com.rpggame.itens.Inventario;
import br.com.rpggame.constants.GuerreiroConstants;

public class Guerreiro extends Personagem {
	public Guerreiro() {
		super(GuerreiroConstants.NOME_PADRAO, GuerreiroConstants.HP_PADRAO, GuerreiroConstants.ATK_PADRAO, GuerreiroConstants.DEF_PADRAO, GuerreiroConstants.NIVEL_PADRAO, new Inventario());
	}

	public Guerreiro(String nome, int hp, int atk, int def, int nivel, Inventario inv) {
		super(nome, hp, atk, def, nivel, inv);
	}

	public Guerreiro(Guerreiro outro) {
		super(outro);
	}

	@Override
	protected int calcularAtaqueEfetivo() {
		int base = super.calcularAtaqueEfetivo();

		int rolagem = Dado.rolar();
		if (rolagem == 6) {
			base += 4; 
		}
		return base;
	}

	@Override
	protected void aoSubirNivel() {
		// Guerreiro ganha mais vida, ataque e defesa
		setPontosVida(getPontosVida() + 12);
		setAtaque(getAtaque() + 4);
		setDefesa(getDefesa() + 3);
	}

	@Override
	public Personagem copiaProfunda() {
		return new Guerreiro(this);
	}
}


