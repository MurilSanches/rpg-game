package br.com.rpggame.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import br.com.rpggame.model.EnemyInfo;

public final class InimigosBaseConstants {
	// Lobo Feroz
	public static final String LOBO_NOME = "Lobo Feroz";
	public static final int LOBO_HP = 25;
	public static final int LOBO_ATK = 6;
	public static final int LOBO_DEF = 2;
	public static final int LOBO_NIVEL = 1;
	public static final int LOBO_LOOT_QTD = 1;
	public static final String LOBO_LOOT_KEY = "GARRA_AFIADA";

	// Bandido
	public static final String BANDIDO_NOME = "Bandido";
	public static final int BANDIDO_HP = 30;
	public static final int BANDIDO_ATK = 5;
	public static final int BANDIDO_DEF = 3;
	public static final int BANDIDO_NIVEL = 1;
	public static final int BANDIDO_LOOT_QTD = 1;
	public static final String BANDIDO_LOOT_KEY = "POCAO";

	// Esqueleto
	public static final String ESQUELETO_NOME = "Esqueleto";
	public static final int ESQUELETO_HP = 28;
	public static final int ESQUELETO_ATK = 5;
	public static final int ESQUELETO_DEF = 4;
	public static final int ESQUELETO_NIVEL = 1;
	public static final int ESQUELETO_LOOT_QTD = 1;
	public static final String ESQUELETO_LOOT_KEY = "ESCUDO_QUEBRADO";

	public static final Map<String, EnemyInfo> INIMIGOS;
	static {
		Map<String, EnemyInfo> m = new HashMap<>();
		m.put("LOBO", new EnemyInfo(LOBO_NOME, LOBO_HP, LOBO_ATK, LOBO_DEF, LOBO_NIVEL, LOBO_LOOT_KEY, LOBO_LOOT_QTD));
		m.put("BANDIDO", new EnemyInfo(BANDIDO_NOME, BANDIDO_HP, BANDIDO_ATK, BANDIDO_DEF, BANDIDO_NIVEL, BANDIDO_LOOT_KEY, BANDIDO_LOOT_QTD));
		m.put("ESQUELETO", new EnemyInfo(ESQUELETO_NOME, ESQUELETO_HP, ESQUELETO_ATK, ESQUELETO_DEF, ESQUELETO_NIVEL, ESQUELETO_LOOT_KEY, ESQUELETO_LOOT_QTD));
		INIMIGOS = Collections.unmodifiableMap(m);
	}

	private InimigosBaseConstants() {}
}


