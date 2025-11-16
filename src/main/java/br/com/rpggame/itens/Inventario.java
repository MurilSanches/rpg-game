package br.com.rpggame.itens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.rpggame.personagens.Personagem;

public class Inventario implements Cloneable {
	private final Map<Item, Item> itens = new HashMap<>();

	public Inventario() {}

	public Inventario(Inventario outro) {
		for (Item item : outro.itens.keySet()) {
			Item copiado = item.clone();
			this.itens.put(copiado, copiado);
		}
	}

	public void adicionar(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Item não pode ser nulo");
		}
		Item existente = itens.get(item);
		if (existente == null) {
			itens.put(item, item);
		} else {
			int novaQtd = existente.getQuantidade() + item.getQuantidade();
			existente.setQuantidade(novaQtd);
		}
	}

	public boolean remover(String nome, int quantidade) {
		if (nome == null || nome.trim().isEmpty()) return false;

		if (quantidade <= 0) return false;

		Optional<Item> opt = itens.keySet().stream()
			.filter(i -> i.getNome().equalsIgnoreCase(nome))
			.findFirst();

		if (!opt.isPresent()) return false;

		Item existente = opt.get();
		
		if (existente.getQuantidade() < quantidade) return false;

		existente.setQuantidade(existente.getQuantidade() - quantidade);
		if (existente.getQuantidade() == 0) {
			itens.remove(existente);
		}
		
		return true;
	}

	public List<Item> listarOrdenado() {
		List<Item> copia = new ArrayList<>();
		for (Item item : itens.keySet()) {
			copia.add(item.clone());
		}
		Collections.sort(copia);
		return copia;
	}

	public boolean usarItem(String nome, Personagem alvo) {
		if (nome == null || nome.trim().isEmpty()) {
			return false;
		}
		
		if (alvo == null) {
			return false;
		}
		
		String nomeNormalizado = normalizarNome(nome);
		
		Item itemEncontrado = null;
		for (Item item : itens.keySet()) {
			String nomeItem = normalizarNome(item.getNome());
			if (nomeItem.equalsIgnoreCase(nomeNormalizado)) {
				itemEncontrado = item;
				break;
			}
		}

		if (itemEncontrado == null) {
			return false;
		}

		if (itemEncontrado.getQuantidade() <= 0) {
			return false;
		}
		
		try {
			itemEncontrado.aplicar(alvo);
			itemEncontrado.setQuantidade(itemEncontrado.getQuantidade() - 1);

			if (itemEncontrado.getQuantidade() == 0) {
				itens.remove(itemEncontrado);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean usarItemPorIndice(int indice, Personagem alvo) {
		if (alvo == null) {
			return false;
		}
		
		List<Item> itensListados = listarOrdenado();
		if (indice < 1 || indice > itensListados.size()) {
			return false;
		}
		
		Item itemSelecionado = itensListados.get(indice - 1);
		
		Item itemOriginal = null;
		for (Item item : itens.keySet()) {
			if (item.equals(itemSelecionado)) {
				itemOriginal = item;
				break;
			}
		}
		
		if (itemOriginal == null) {
			return false;
		}
		
		if (itemOriginal.getQuantidade() <= 0) {
			return false;
		}
		
		try {
			itemOriginal.aplicar(alvo);
			itemOriginal.setQuantidade(itemOriginal.getQuantidade() - 1);

			if (itemOriginal.getQuantidade() == 0) {
				itens.remove(itemOriginal);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public int tamanho() {
		return itens.size();
	}
	
	private String normalizarNome(String nome) {
		if (nome == null) {
			return "";
		}
		String normalizado = nome.trim().replaceAll("\\s+", " ");
		normalizado = normalizado.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
		return normalizado;
	}

	@Override
	public Inventario clone() {
		return new Inventario(this);
	}

	@Override
	public String toString() {
		List<Item> list = listarOrdenado();
		if (list.isEmpty()) return "(vazio)";
		StringBuilder sb = new StringBuilder();

		for (Item i : list) {
			sb.append("- ").append(i.toString()).append("\n");
		}
		return sb.toString();
	}
}


