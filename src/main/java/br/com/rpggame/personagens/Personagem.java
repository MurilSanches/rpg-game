package br.com.rpggame.personagens;

import br.com.rpggame.Dado;
import br.com.rpggame.itens.Inventario;

public abstract class Personagem {
	private static final int XP_BASE_PARA_NIVEL = 20;
	private static final double XP_MULTIPLICADOR_NIVEL = 1.5;

	private String nome;
	private int pontosVida;
	private int ataque;
	private int defesa;
	private int nivel;
	private int experiencia;
	private int experienciaParaProximoNivel;
	private Inventario inventario;

	protected int buffAtaqueTemporario;
	protected int buffDefesaTemporario;

	protected Personagem() {
		this("SemNome", 30, 5, 3, 1, new Inventario());
	}

	protected Personagem(String nome, int pontosVida, int ataque, int defesa, int nivel, Inventario inventario) {
		setNome(nome);
		setPontosVida(pontosVida);
		setAtaque(ataque);
		setDefesa(defesa);
		setNivel(nivel);
		this.experiencia = 0;
		this.experienciaParaProximoNivel = XP_BASE_PARA_NIVEL;
		this.inventario = inventario == null ? new Inventario() : inventario;
	}

	protected Personagem(Personagem outro) {
		this(outro.nome, outro.pontosVida, outro.ataque, outro.defesa, outro.nivel,
			outro.inventario == null ? new Inventario() : outro.inventario.clone());
		this.experiencia = outro.experiencia;
		this.experienciaParaProximoNivel = outro.experienciaParaProximoNivel;
		this.buffAtaqueTemporario = outro.buffAtaqueTemporario;
		this.buffDefesaTemporario = outro.buffDefesaTemporario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome inválido");
		}
		this.nome = nome;
	}

	public int getPontosVida() {
		return pontosVida;
	}

	public void setPontosVida(int pontosVida) {
		if (pontosVida < 0) pontosVida = 0;
		this.pontosVida = pontosVida;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		if (ataque < 0) throw new IllegalArgumentException("Ataque não pode ser negativo");
		this.ataque = ataque;
	}

	public int getDefesa() {
		return defesa;
	}

	public void setDefesa(int defesa) {
		if (defesa < 0) throw new IllegalArgumentException("Defesa não pode ser negativa");
		this.defesa = defesa;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		if (nivel <= 0) throw new IllegalArgumentException("Nível deve ser positivo");
		this.nivel = nivel;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public int getExperienciaParaProximoNivel() {
		return experienciaParaProximoNivel;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		if (inventario == null) throw new IllegalArgumentException("Inventário não pode ser nulo");
		this.inventario = inventario;
	}

	public void ganharExperiencia(int xp) {
		if (xp <= 0) return;
		this.experiencia += xp;
		while (this.experiencia >= this.experienciaParaProximoNivel) {
			this.experiencia -= this.experienciaParaProximoNivel;
			subirNivel();
		}
	}

	private void subirNivel() {
		this.nivel++;
		aoSubirNivel();
		this.experienciaParaProximoNivel =
			(int) Math.max(XP_BASE_PARA_NIVEL,
				Math.round(this.experienciaParaProximoNivel * XP_MULTIPLICADOR_NIVEL));
	}

	protected void aoSubirNivel() {
		setPontosVida(getPontosVida() + 5);
		setAtaque(getAtaque() + 2);
		setDefesa(getDefesa() + 1);
	}

	public boolean estaVivo() {
		return pontosVida > 0;
	}

	public void sofrerDano(int dano) {
		if (dano < 0) dano = 0;
		setPontosVida(pontosVida - dano);
	}

	public void receberCura(int cura) {
		if (cura < 0) return;
		setPontosVida(pontosVida + cura);
	}

	public void receberBuffAtaqueTemporario(int valor) {
		if (valor <= 0) return;
		this.buffAtaqueTemporario += valor;
	}

	public void receberBuffDefesaTemporario(int valor) {
		if (valor <= 0) return;
		this.buffDefesaTemporario += valor;
	}

	protected int calcularAtaqueEfetivo() {
		return getAtaque() + buffAtaqueTemporario + Dado.rolar();
	}

	protected int calcularDefesaEfetiva() {
		return getDefesa() + buffDefesaTemporario;
	}

	public int atacar(Personagem oponente) {
		int ataqueEfetivo = calcularAtaqueEfetivo();
		int defesaOponente = oponente.calcularDefesaEfetiva();
		int dano = Math.max(0, ataqueEfetivo - defesaOponente);
		if (dano > 0) {
			oponente.sofrerDano(dano);
		}
		
		this.buffAtaqueTemporario = 0;
		this.buffDefesaTemporario = 0;
		return dano;
	}

	public abstract Personagem copiaProfunda();
}


