package br.com.rpggame.personagens;

import br.com.rpggame.itens.Inventario;
import br.com.rpggame.constants.InimigoConstants;

public class Inimigo extends Personagem {
	public Inimigo() {
		super(InimigoConstants.NOME_PADRAO, InimigoConstants.HP_PADRAO, InimigoConstants.ATK_PADRAO, InimigoConstants.DEF_PADRAO, InimigoConstants.NIVEL_PADRAO, new Inventario());
	}

	public Inimigo(String nome, int hp, int atk, int def, int nivel, Inventario inv) {
		super(nome, hp, atk, def, nivel, inv);
	}

	public Inimigo(Inimigo outro) {
		super(outro);
	}

	@Override
	public Personagem copiaProfunda() {
		return new Inimigo(this);
	}
}


