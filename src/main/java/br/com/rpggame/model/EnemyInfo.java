package br.com.rpggame.model;

public final class EnemyInfo {
	public final String nome;
	public final int hp;
	public final int atk;
	public final int def;
	public final int nivel;
	public final String lootKey;
	public final int lootQuantidade;

	public EnemyInfo(String nome, int hp, int atk, int def, int nivel, String lootKey, int lootQuantidade) {
		this.nome = nome;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.nivel = nivel;
		this.lootKey = lootKey;
		this.lootQuantidade = lootQuantidade;
	}
}


