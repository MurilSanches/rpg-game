package br.com.rpggame.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import br.com.rpggame.itens.Efeito;
import br.com.rpggame.model.ItemInfo;

public final class ItensConstants {
	public static final String POCAO_NOME = "Poção";
	public static final String POCAO_DESC = "Cura leve";
	public static final Efeito POCAO_EFEITO = Efeito.CURA;

	public static final String FACA_NOME = "Faca Afiada";
	public static final String FACA_DESC = "Causa dano direto";
	public static final Efeito FACA_EFEITO = Efeito.DANO;

	public static final String GARRA_NOME = "Garra Afiada";
	public static final String GARRA_DESC = "Dano";
	public static final Efeito GARRA_EFEITO = Efeito.DANO;

	public static final String ESCUDO_NOME = "Escudo Quebrado";
	public static final String ESCUDO_DESC = "Buff de defesa";
	public static final Efeito ESCUDO_EFEITO = Efeito.BUFF_DEFESA;

	public static final Map<String, ItemInfo> ITEMS;
	static {
		Map<String, ItemInfo> m = new HashMap<>();
		m.put("POCAO", new ItemInfo(POCAO_NOME, POCAO_DESC, POCAO_EFEITO));
		m.put("FACA_AFIADA", new ItemInfo(FACA_NOME, FACA_DESC, FACA_EFEITO));
		m.put("GARRA_AFIADA", new ItemInfo(GARRA_NOME, GARRA_DESC, GARRA_EFEITO));
		m.put("ESCUDO_QUEBRADO", new ItemInfo(ESCUDO_NOME, ESCUDO_DESC, ESCUDO_EFEITO));
		ITEMS = Collections.unmodifiableMap(m);
	}

	private ItensConstants() {}
}


