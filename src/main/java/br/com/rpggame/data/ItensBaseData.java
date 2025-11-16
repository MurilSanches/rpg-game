package br.com.rpggame.data;

import br.com.rpggame.itens.Item;
import br.com.rpggame.constants.ItensConstants;
import br.com.rpggame.model.ItemInfo;

public final class ItensBaseData {
	public static Item fromKey(String key, int quantidade) {
		if (key == null) throw new IllegalArgumentException("Chave do item não pode ser nula");
		ItemInfo info = ItensConstants.ITEMS.get(key);
		
		if (info == null) {
			throw new IllegalArgumentException("Item não mapeado: " + key);
		}
		return new Item(info.nome, info.descricao, info.efeito, Math.max(quantidade, 1));
	}

	public static Item pocao(int quantidade) {
		return fromKey("POCAO", quantidade);
	}

	public static Item facaAfiada(int quantidade) {
		return fromKey("FACA_AFIADA", quantidade);
	}

	public static Item garraAfiada(int quantidade) {
		return fromKey("GARRA_AFIADA", quantidade);
	}

	public static Item escudoQuebrado(int quantidade) {
		return fromKey("ESCUDO_QUEBRADO", quantidade);
	}

	private ItensBaseData() {}
}


