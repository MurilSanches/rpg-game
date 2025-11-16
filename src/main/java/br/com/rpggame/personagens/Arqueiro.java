package br.com.rpggame.personagens;

import br.com.rpggame.Dado;
import br.com.rpggame.itens.Inventario;
import br.com.rpggame.constants.ArqueiroConstants;

public class Arqueiro extends Personagem {
	public Arqueiro() {
		super(ArqueiroConstants.NOME_PADRAO, ArqueiroConstants.HP_PADRAO, ArqueiroConstants.ATK_PADRAO, ArqueiroConstants.DEF_PADRAO, ArqueiroConstants.NIVEL_PADRAO, new Inventario());
	}

	public Arqueiro(String nome, int hp, int atk, int def, int nivel, Inventario inv) {
		super(nome, hp, atk, def, nivel, inv);
	}

	public Arqueiro(Arqueiro outro) {
		super(outro);
	}

	@Override
	protected int calcularAtaqueEfetivo() {
		int base = super.calcularAtaqueEfetivo();
		
		int rolagem = Dado.rolar();
		if (rolagem >= 4) {
			double bonusBase = 2.0;
			double fatorNivel = 1.0 + 0.1 * (getNivel() - 1);
			int bonus = (int) Math.round(bonusBase * fatorNivel);
			base += bonus;
		}
		return base;
	}

	@Override
	protected void aoSubirNivel() {
		setPontosVida(getPontosVida() + 8);
		setAtaque(getAtaque() + 4);
		setDefesa(getDefesa() + 2);
	}

	@Override
	public Personagem copiaProfunda() {
		return new Arqueiro(this);
	}
}


