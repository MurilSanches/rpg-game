package br.com.rpggame.data;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import br.com.rpggame.constants.InimigosBaseConstants;
import br.com.rpggame.personagens.InimigoTemplate;
import br.com.rpggame.model.EnemyInfo;

public final class InimigosBaseData {
	private static final List<String> ORDEM_PADRAO = Arrays.asList("LOBO", "BANDIDO", "ESQUELETO");

	public static final List<InimigoTemplate> INIMIGOS_BASE = buildInimigosBase();

	private static List<InimigoTemplate> buildInimigosBase() {
		List<InimigoTemplate> lista = new ArrayList<>();
		for (String key : ORDEM_PADRAO) {
			EnemyInfo e = InimigosBaseConstants.INIMIGOS.get(key);
			if (e == null) continue;
			InimigoTemplate t = new InimigoTemplate(
				e.nome,
				e.hp,
				e.atk,
				e.def,
				e.nivel,
				ItensBaseData.fromKey(e.lootKey, e.lootQuantidade)
			);
			lista.add(t);
		}
		return lista;
	}

	private InimigosBaseData() {}
}


