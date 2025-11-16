package br.com.rpggame.model;

import br.com.rpggame.itens.Efeito;

public final class ItemInfo {
	public final String nome;
	public final String descricao;
	public final Efeito efeito;

	public ItemInfo(String nome, String descricao, Efeito efeito) {
		this.nome = nome;
		this.descricao = descricao;
		this.efeito = efeito;
	}
}


