package br.com.rpggame.personagens;

import br.com.rpggame.itens.Item;

public final class InimigoTemplate {
	public final String nome;
	public final int hp;
	public final int atk;
	public final int def;
	public final int nivel;
	public final Item loot;

	public InimigoTemplate(String nome, int hp, int atk, int def, int nivel, Item loot) {
		this.nome = nome;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.nivel = nivel;
		this.loot = loot;
	}
}


