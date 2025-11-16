package br.com.rpggame.itens;

import java.util.Objects;

import br.com.rpggame.interfaces.Usavel;
import br.com.rpggame.personagens.Personagem;

public final class Item implements Comparable<Item>, Usavel, Cloneable {
	private final String nome;
	private final String descricao;
	private final Efeito efeito;
	private int quantidade;

	public Item() {
		this("Item", "Sem descrição", Efeito.OUTRO, 0);
	}

	public Item(String nome, String descricao, Efeito efeito, int quantidade) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome do item inválido");
		}
		if (descricao == null) {
			throw new IllegalArgumentException("Descrição não pode ser nula");
		}
		if (efeito == null) {
			throw new IllegalArgumentException("Efeito não pode ser nulo");
		}
		if (quantidade < 0) {
			throw new IllegalArgumentException("Quantidade não pode ser negativa");
		}
		
		this.nome = nome;
		this.descricao = descricao;
		this.efeito = efeito;
		this.quantidade = quantidade;
	}

	public Item(Item outro) {
		this(outro.nome, outro.descricao, outro.efeito, outro.quantidade);
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Efeito getEfeito() {
		return efeito;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		if (quantidade < 0) {
			throw new IllegalArgumentException("Quantidade não pode ser negativa");
		}
		this.quantidade = quantidade;
	}

	@Override
	public int compareTo(Item o) {
		int byNome = this.nome.compareToIgnoreCase(o.nome);
		if (byNome != 0) return byNome;
		return this.efeito.compareTo(o.efeito);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Item)) return false;
		Item other = (Item) obj;
		return Objects.equals(this.nome, other.nome) && this.efeito == other.efeito;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, efeito);
	}

	@Override
	public Item clone() {
		return new Item(this);
	}

	@Override
	public void aplicar(Personagem alvo) {
		switch (efeito) {
			case CURA:
				// Cura escala levemente com o nível do personagem
				int nivel = alvo.getNivel();
				int curaBase = 10;
				double fatorNivel = 1.0 + 0.1 * (nivel - 1); // escala baixa
				int cura = (int) Math.round(curaBase * fatorNivel);
				alvo.receberCura(cura);
				break;
			case BUFF_ATAQUE:
				alvo.receberBuffAtaqueTemporario(2);
				break;
			case BUFF_DEFESA:
				alvo.receberBuffDefesaTemporario(2);
				break;
			case DANO:
				alvo.sofrerDano(8);
				break;
			default:
				break;
		}
	}

	@Override
	public String toString() {
		return nome + " x" + quantidade + " (" + efeito + ")";
	}
}


