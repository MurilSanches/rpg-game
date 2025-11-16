package br.com.rpggame.services;

import java.io.IOException;

import br.com.rpggame.Dado;
import br.com.rpggame.io.ConsoleIO;
import br.com.rpggame.personagens.Inimigo;
import br.com.rpggame.personagens.Personagem;

public class CombatService {
	private final ConsoleIO io;
	private final ItemService itemService;

	public CombatService(ConsoleIO io, ItemService itemService) {
		this.io = io;
		this.itemService = itemService;
	}

	public void batalhar(Personagem jogador, Inimigo inimigo) throws IOException {
		io.println("Enfrentando " + inimigo.getNome() + " (HP: " + inimigo.getPontosVida() + ")");
		while (jogador.estaVivo() && inimigo.estaVivo()) {
			io.println("");
			io.println("Turno: " + jogador.getNome() + " HP=" + jogador.getPontosVida() + " | " + inimigo.getNome() + " HP=" + inimigo.getPontosVida());
			io.println("Ações: 1) Atacar  2) Usar item  3) Tentar fugir");
			String op = io.readLine();
			if ("2".equals(op)) {
				itemService.usarItem(jogador);
			} else if ("3".equals(op)) {
				if (tentarFugirEmCombate()) {
					io.println("Você conseguiu fugir!");
					return;
				} else {
					io.println("Falhou em fugir!");
				}
			} else {
				int dano = jogador.atacar(inimigo);
				io.println(jogador.getNome() + " ataca! Dano causado: " + dano);
			}
			if (!inimigo.estaVivo()) break;
			int danoInimigo = inimigo.atacar(jogador);
			io.println(inimigo.getNome() + " ataca! Dano causado: " + danoInimigo);
		}
		if (jogador.estaVivo()) {
			io.println("Vitória! Você derrotou " + inimigo.getNome());
		} else {
			io.println("Derrota...");
		}
	}

	public boolean tentarFugirEmCombate() {
		int rolagem = Dado.rolar();
		return rolagem >= 5;
	}
}


