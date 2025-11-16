package br.com.rpggame.services;

import java.io.IOException;
import java.util.List;

import br.com.rpggame.io.ConsoleIO;
import br.com.rpggame.itens.Item;
import br.com.rpggame.personagens.Personagem;

public class ItemService {

	private final ConsoleIO io;

	public ItemService(ConsoleIO io) {
		this.io = io;
	}

	public void usarItem(Personagem jogador) throws IOException {
		List<Item> itens = jogador.getInventario().listarOrdenado();
		if (itens.isEmpty()) {
			io.println("Inventário vazio!");
			return;
		}

		io.println("Itens disponíveis:");
		for (int i = 0; i < itens.size(); i++) {
			Item item = itens.get(i);
			io.println((i + 1) + ") " + item.getNome() + " x" + item.getQuantidade() + " (" + item.getEfeito() + ")");
		}

		io.print("Escolha o número do item (ou 0 para cancelar): ");
		String entrada = io.readLine();
		if (entrada == null || entrada.trim().isEmpty()) {
			io.println("Uso de item cancelado.");
			return;
		}

		try {
			int escolha = Integer.parseInt(entrada.trim());
			if (escolha == 0) {
				io.println("Uso de item cancelado.");
				return;
			}

			boolean ok = jogador.getInventario().usarItemPorIndice(escolha, jogador);
			if (ok) {
				Item itemUsado = itens.get(escolha - 1);
				io.println("Você usou: " + itemUsado.getNome());
				io.println("HP atual: " + jogador.getPontosVida());
			} else {
				io.println("Não foi possível usar o item. Número inválido.");
			}
		} catch (NumberFormatException e) {
			io.println("Entrada inválida. Digite um número.");
		} catch (IndexOutOfBoundsException e) {
			io.println("Número inválido. Escolha um item da lista.");
		}
	}
}


