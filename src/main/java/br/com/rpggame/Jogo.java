package br.com.rpggame;

import java.io.IOException;
import java.util.Locale;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import br.com.rpggame.itens.Efeito;
import br.com.rpggame.itens.Inventario;
import br.com.rpggame.itens.Item;
import br.com.rpggame.personagens.Arqueiro;
import br.com.rpggame.personagens.Guerreiro;
import br.com.rpggame.personagens.Inimigo;
import br.com.rpggame.personagens.Mago;
import br.com.rpggame.personagens.Personagem;
import br.com.rpggame.data.LootData;
import br.com.rpggame.constants.InimigosProgressaoConstants;
import br.com.rpggame.io.ConsoleIO;
import br.com.rpggame.services.CombatService;
import br.com.rpggame.services.ExplorationService;
import br.com.rpggame.services.SaveService;
import br.com.rpggame.services.ItemService;

public class Jogo {
	private static final int DADO_PADRAO = 6;
	private static final Set<String> OPCOES_MENU = new HashSet<>(Arrays.asList("0","1","2","3","4","5","6"));
	private static final Set<String> OPCOES_CLASSE = new HashSet<>(Arrays.asList("1","2","3"));

	private final ConsoleIO io = new ConsoleIO();
	private final ItemService itemService = new ItemService(io);
	private final CombatService combate = new CombatService(io, itemService);
	private final ExplorationService exploracao = new ExplorationService(io);
	private final SaveService saveService = new SaveService();
	
	private Personagem jogador;
	private Personagem savePoint;

	public static void main(String[] args) {
		Locale.setDefault(new Locale("pt", "BR"));
		new Jogo().iniciar();
	}

	private void iniciar() {
		try {
			io.println("=== RPG de Texto (POO) ===");
			configurarSemente();
			criarJogador();
			loopPrincipal();
		} catch (IOException e) {
			io.println("Erro de entrada: " + e.getMessage());
		}
	}

	private void configurarSemente() throws IOException {
		io.print("Deseja informar uma semente (enter para aleatório)? ");
		String s = io.readLine();
		if (s != null && !s.trim().isEmpty()) {
			try {
				long seed = Long.parseLong(s.trim());
				Dado.configurarSemente(seed);
				io.println("Semente configurada.");
			} catch (NumberFormatException e) {
				io.println("Semente inválida. Usando aleatório.");
			}
		}
	}

	private void criarJogador() throws IOException {
		String nome = io.readNonEmpty("Informe o nome do herói: ");
		io.println("Escolha a classe: 1) Guerreiro  2) Mago  3) Arqueiro");
		String op = io.readOption("Opção: ", OPCOES_CLASSE);
		if ("1".equals(op)) {
			Guerreiro g = new Guerreiro();
			g.setNome(nome);
			this.jogador = g;
		} else if ("2".equals(op)) {
			Mago m = new Mago();
			m.setNome(nome);
			this.jogador = m;
		} else {
			Arqueiro a = new Arqueiro();
			a.setNome(nome);
			this.jogador = a;
		}

		jogador.getInventario().adicionar(br.com.rpggame.data.ItensBaseData.pocao(2));
		jogador.getInventario().adicionar(br.com.rpggame.data.ItensBaseData.facaAfiada(1));
	}

	private void loopPrincipal() throws IOException {
		while (jogador.estaVivo()) {
			io.println("");
			io.println("Herói: " + jogador.getNome()
				+ " | HP: " + jogador.getPontosVida() + "/" + jogador.getPontosVidaMaximo()
				+ " | Nível: " + jogador.getNivel()
				+ " | XP: " + jogador.getExperiencia() + "/" + jogador.getExperienciaParaProximoNivel());
			io.println("Ações: 1) Explorar  2) Usar item  3) Fugir  4) Inventário  5) Salvar  6) Restaurar  0) Sair");
			String op = io.readOption("Escolha: ", OPCOES_MENU);
			switch (op) {
				case "1":
					explorar();
					break;
				case "2":
					usarItem();
					break;
				case "3":
					tentarFugirForaDeCombate();
					break;
				case "4":
					io.println("Inventário:");
					io.print(jogador.getInventario().toString());
					break;
				case "5":
					savePoint = clonarJogador();
					io.println("Ponto de salvamento criado.");
					break;
				case "6":
					if (savePoint != null) {
						jogador = saveService.restore(savePoint);
						io.println("Estado restaurado do save point.");
					} else {
						io.println("Nenhum save point disponível.");
					}
					break;
				case "0":
					io.println("Saindo. Até mais!");
					return;
			}
		}
		io.println("Seu herói tombou em batalha. Fim de jogo.");
	}

	private void explorar() throws IOException {
		Inimigo inimigo = exploracao.explorar(jogador);
		if (inimigo != null) {
			combate.batalhar(jogador, inimigo);
			if (jogador.estaVivo() && !inimigo.estaVivo()) {
				saquear(inimigo);
				concederExperiencia(inimigo);
			}
		}
	}

	private void saquear(Inimigo inimigo) {
		Inventario loot = inimigo.getInventario().clone();
		for (Item item : loot.listarOrdenado()) {
			if (item.getQuantidade() > 0) {
				item.setQuantidade(1);
				jogador.getInventario().adicionar(item);
				io.println("Você saqueou: " + item.getNome());
			}
		}
	}

	private void concederExperiencia(Inimigo inimigo) {
		String nome = inimigo.getNome();
		int xpBase = InimigosProgressaoConstants.obterXpBase(nome);
		int xp = xpBase + (inimigo.getNivel() - 1) * 2;
		io.println("Você ganhou " + xp + " XP.");

		int nivelAntes = jogador.getNivel();
		jogador.ganharExperiencia(xp);
		if (jogador.getNivel() > nivelAntes) {
			io.println("Você subiu para o nível " + jogador.getNivel() + "!");
			io.println("Seus atributos aumentaram.");
		}
	}

	private void tentarFugirForaDeCombate() {
		int rolagem = Dado.rolar();
		io.println("Rolagem para tentar fugir: " + rolagem);
		if (rolagem >= 4) {
			io.println("Você encontrou um caminho seguro e avançou sem problemas.");
		} else {
			io.println("Você se perdeu por um tempo, mas retornou ao ponto inicial.");
		}
	}

	private void usarItem() throws IOException {
		itemService.usarItem(jogador);
	}

	private String lerNaoVazio() throws IOException {
		return io.readNonEmpty(null);
	}

	private Personagem clonarJogador() {
		return saveService.save(jogador);
	}
}

