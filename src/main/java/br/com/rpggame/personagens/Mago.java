package br.com.rpggame.personagens;

import br.com.rpggame.itens.Inventario;
import br.com.rpggame.constants.MagoConstants;

public class Mago extends Personagem {
	private int mana;
	private int manaMaxima;
	private int manaGastoPorAtaque = 3;

	public Mago() {
		super(MagoConstants.NOME_PADRAO, MagoConstants.HP_PADRAO, MagoConstants.ATK_PADRAO, MagoConstants.DEF_PADRAO, MagoConstants.NIVEL_PADRAO, new Inventario());
		this.manaMaxima = MagoConstants.MANA_INICIAL;
		this.mana = this.manaMaxima;
	}

	public Mago(String nome, int hp, int atk, int def, int nivel, Inventario inv, int mana) {
		super(nome, hp, atk, def, nivel, inv);
		this.manaMaxima = Math.max(0, mana);
		this.mana = this.manaMaxima;
	}

	public Mago(Mago outro) {
		super(outro);
		this.mana = outro.mana;
		this.manaMaxima = outro.manaMaxima;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = Math.max(0, Math.min(mana, manaMaxima));
	}

	public int getManaMaxima() {
		return manaMaxima;
	}

	public void setManaMaxima(int manaMaxima) {
		this.manaMaxima = Math.max(0, manaMaxima);
		if (this.mana > this.manaMaxima) {
			this.mana = this.manaMaxima;
		}
	}

	@Override
	public int atacar(Personagem oponente) {
		int dano;
		if (mana >= manaGastoPorAtaque) {
	
			mana -= manaGastoPorAtaque;
			receberBuffAtaqueTemporario(3);
			dano = super.atacar(oponente);
		} else {
			dano = super.atacar(oponente);
		}
		return dano;
	}

	@Override
	public Personagem copiaProfunda() {
		return new Mago(this);
	}

	@Override
	protected void aoSubirNivel() {
		// Mago ganha menos atributos base, mas mais mana e custo de mana cresce
		setPontosVida(getPontosVida() + 6);
		setAtaque(getAtaque() + 3);
		setDefesa(getDefesa() + 2);

		// Aumenta o limite de mana e recarrega
		setManaMaxima(this.manaMaxima + 5);
		this.mana = this.manaMaxima;

		// Aumenta o custo de mana do ataque especial
		this.manaGastoPorAtaque += 1;
	}
}


