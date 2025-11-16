package br.com.rpggame.data;

import java.util.Arrays;
import java.util.List;

import br.com.rpggame.itens.Item;

public final class LootData {
	public static final List<Item> EXPLORACAO_ITENS = Arrays.asList(
		ItensBaseData.pocao(1),
		ItensBaseData.facaAfiada(1)
	);

	private LootData() {}
}


