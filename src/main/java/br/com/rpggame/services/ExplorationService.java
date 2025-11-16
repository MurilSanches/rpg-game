package br.com.rpggame.services;

import java.io.IOException;

import br.com.rpggame.Dado;
import br.com.rpggame.data.InimigosBaseData;
import br.com.rpggame.data.LootData;
import br.com.rpggame.io.ConsoleIO;
import br.com.rpggame.itens.Inventario;
import br.com.rpggame.itens.Item;
import br.com.rpggame.constants.InimigosProgressaoConstants;
import br.com.rpggame.personagens.Inimigo;
import br.com.rpggame.personagens.InimigoTemplate;
import br.com.rpggame.personagens.Personagem;

public class ExplorationService {
	private static final int DADO_PADRAO = 6;

	private final ConsoleIO io;

	public ExplorationService(ConsoleIO io) {
		this.io = io;
	}

	public Inimigo explorar(Personagem jogador) throws IOException {
		int evento = Dado.rolar(DADO_PADRAO);
		io.println("Rolagem de exploração: " + evento);
		if (evento <= 2) {
			io.println("Você não encontrou nada de interessante.");
			return null;
		} else if (evento <= 4) {
			io.println("Você encontrou um item!");
			int idx = Dado.rolar(LootData.EXPLORACAO_ITENS.size()) - 1;
			Item achado = LootData.EXPLORACAO_ITENS.get(idx).clone();
			jogador.getInventario().adicionar(achado);
			io.println("Adicionado ao inventário: " + achado);
			return null;
		} else {
			io.println("Um inimigo apareceu!");
			return gerarInimigoAleatorio(jogador);
		}
	}

	public Inimigo gerarInimigoAleatorio(Personagem jogador) {
		int idx = Dado.rolar(InimigosBaseData.INIMIGOS_BASE.size()) - 1;
		InimigoTemplate t = InimigosBaseData.INIMIGOS_BASE.get(idx);
		int nivelJogador = jogador.getNivel();

		double escalaDefHp = InimigosProgressaoConstants.obterEscalaAtributos(t.nome);
		double escalaAtk = InimigosProgressaoConstants.obterEscalaAtaque(t.nome);
		int hpEscalado = escalarAtributo(t.hp, escalaDefHp, nivelJogador);
		int atkEscalado = escalarAtributo(t.atk, escalaAtk, nivelJogador);
		int defEscalado = escalarAtributo(t.def, escalaDefHp, nivelJogador);

		Inimigo i = new Inimigo(t.nome, hpEscalado, atkEscalado, defEscalado, nivelJogador, new Inventario());
		i.getInventario().adicionar(new Item(t.loot));
		return i;
	}

	private int escalarAtributo(int base, double escalaPorNivel, int nivel) {
		if (nivel <= 1) return base;
		double fator = 1.0 + escalaPorNivel * (nivel - 1);
		return (int) Math.max(1, Math.round(base * fator));
	}
}


