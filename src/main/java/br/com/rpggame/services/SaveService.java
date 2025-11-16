package br.com.rpggame.services;

import br.com.rpggame.personagens.Personagem;

public class SaveService {
	public Personagem save(Personagem atual) {
		return atual == null ? null : atual.copiaProfunda();
	}

	public Personagem restore(Personagem savePoint) {
		return savePoint == null ? null : savePoint.copiaProfunda();
	}
}


