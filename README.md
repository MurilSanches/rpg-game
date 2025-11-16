RPG de Texto em Java (POO)

Descrição
Um RPG de texto simples que demonstra conceitos essenciais de Programação Orientada a Objetos (POO) em Java: abstração, encapsulamento, herança, polimorfismo, composição, sobrescrita, interfaces, comparabilidade, igualdade, cópia/clone e boas práticas.

O foco do projeto é ser **didático**, mostrando um código organizado em camadas (modelos, serviços, IO, dados/constantes) e fácil de estender (novos itens, inimigos, classes, regras de progressão).

Como compilar e executar
Pré-requisitos: JDK 8+ instalado e visível no PATH (javac/java).

Opção 1) Maven (recomendado)
- Requisitos: Maven 3.8+ e JDK 8+
- Rodar diretamente:
```bash
mvn -q -DskipTests exec:java
```
- Gerar JAR e rodar:
```bash
mvn -q -DskipTests package
java -jar target/rpg-game-0.1.0.jar
```

Opção 2) Scripts (Windows/Linux/macOS)
- Requisitos: Maven 3.8+ e JDK 8+
- Windows PowerShell:
```powershell
.\scripts\run.ps1 -Build
```
- Linux/macOS:
```bash
chmod +x scripts/run.sh
./scripts/run.sh --build
```

Opção 3) Manual (javac/java)
- Windows PowerShell:
```powershell
Get-ChildItem src\main\java -Recurse -Filter *.java `
  | ForEach-Object { $_.FullName.Substring($PWD.Path.Length + 1) } `
  | Set-Content sources.txt
javac -encoding UTF-8 -d out '@sources.txt'
java -cp out br.com.rpggame.Jogo
```
- Linux/macOS:
```bash
find src/main/java -name "*.java" > sources.txt
javac -encoding UTF-8 -d out @sources.txt
java -cp out br.com.rpggame.Jogo
```

Opcional: definir semente para testes determinísticos
No início do jogo, você pode informar uma semente (número longo) para tornar as rolagens reproduzíveis.

Estrutura de pacotes
- br.com.rpggame: ponto de entrada e utilidades gerais (Jogo, Dado)
- br.com.rpggame.itens: itens, inventário e enum de efeitos (Item, Inventario, Efeito)
- br.com.rpggame.personagens: hierarquia de personagens (Personagem, Guerreiro, Mago, Arqueiro, Inimigo)
- br.com.rpggame.interfaces: contratos opcionais (Usavel)
 - br.com.rpggame.constants: valores fixos e configuração de personagens, itens e inimigos
 - br.com.rpggame.data: “tabelas” de dados (itens base, inimigos base, loot de exploração)
 - br.com.rpggame.services: serviços de regras de negócio (combate, exploração, save/restore)
 - br.com.rpggame.io: abstração de entrada/saída no console (ConsoleIO)

Decisões de design
- Encapsulamento: todos os atributos são privados com validações nos setters.
- Igualdade e hash: Item define equals/hashCode por (nome, efeito), consistente com seu contrato.
- Ordenação: Item implementa Comparable por nome (desempate por efeito).
- Cópia profunda: inventário e personagens possuem construtores de cópia que duplicam coleções/objetos mutáveis (sem aliasing).
- Polimorfismo: subclasses (Guerreiro/Mago/Arqueiro) especializam cálculo de ataque/efeitos, mantendo API comum de Personagem.
- Testabilidade: Dado permite configurar semente para gerar resultados determinísticos.

Mecânicas principais
- Loop principal (classe `Jogo`):
  - Mostra status do herói (nome, HP, nível).
  - Menu com ações: explorar, usar item, fugir, ver inventário, salvar, restaurar, sair.
- Exploração (`ExplorationService`):
  - Sorteia um evento com dado: nada, encontrar item, ou encontrar inimigo.
  - Quando encontra inimigo, gera um inimigo com **nível igual ao do jogador** e atributos escalados por constantes.
- Combate (`CombatService`):
  - Turnos alternados: jogador escolhe entre atacar, usar item ou tentar fugir.
  - Ataque: usa polimorfismo (`atacar`, `calcularAtaqueEfetivo`) de cada classe concreta.
  - Fuga em combate: baseada em rolagem de dado.

Personagens e progressão
- Personagem base (`Personagem`):
  - Atributos: nome, HP, ataque, defesa, nível, inventário.
  - Atributos de estado: buffs temporários de ataque/defesa, experiência atual, XP para o próximo nível.
  - Métodos principais:
    - `atacar(Personagem)`: usa dado + ataque + buffs, descontando da defesa do oponente.
    - `ganharExperiencia(int)`: acumula XP, faz level up automático quando necessário.
    - `aoSubirNivel()`: ganhoss de atributos ao subir de nível (sobrescrito nas subclasses).
- Classes concretas:
  - `Guerreiro`:
    - Tem chance de crítico em rolagem alta.
    - Ao subir de nível ganha mais HP, ataque e defesa (tank/dano).
  - `Arqueiro`:
    - Bônus quando a rolagem de ataque é ≥ 4; esse bônus escala com o nível.
    - Ao subir de nível ganha bom ataque, HP moderado, defesa um pouco menor.
  - `Mago`:
    - Possui `mana` e `manaMaxima`, além de `manaGastoPorAtaque`.
    - Se tiver mana suficiente, gasta mana para buffar o ataque no turno.
    - Ao subir de nível aumenta um pouco atributos base, aumenta o máximo de mana, recarrega mana e aumenta o custo de mana por ataque especial.

Itens e inventário
- `Item`:
  - Imutáveis em nome/descrição/efeito; quantidade é mutável.
  - Efeitos: `CURA`, `BUFF_ATAQUE`, `BUFF_DEFESA`, `DANO`, `OUTRO`.
  - Método `aplicar(Personagem)`:
    - Poções de cura escalam levemente com o nível do personagem (cura maior em níveis altos).
- `Inventario`:
  - Usa `Map<Item, Item>` para agrupar múltiplas unidades do mesmo item.
  - Métodos principais:
    - `adicionar(Item)`, `remover(String,int)`, `listarOrdenado()`, `usarItem(...)`, `usarItemPorIndice(...)`.
  - Há construtor de cópia profunda para suportar save/restore e loot de inimigos.
- Uso de item no jogo:
  - A interface lista os itens numerados (1, 2, 3, ...).
  - O jogador escolhe o número do item para usar (evita problemas de digitação de nomes com acento).

Progressão de inimigos e XP
- `InimigosBaseConstants` + `InimigosProgressaoConstants`:
  - Definem atributos base por tipo de inimigo (HP, ATK, DEF, nível inicial, loot, XP base).
  - Definem escala de atributos por nível para cada inimigo (ex.: +15% por nível acima do 1).
- `ExplorationService.gerarInimigoAleatorio(Personagem jogador)`:
  - Usa o nível do jogador para escalar os atributos do inimigo.
- XP do jogador:
  - Ao derrotar um inimigo, o jogador recebe XP baseado no tipo de inimigo + um pequeno extra pelo nível dele.
  - Quando o XP atual ultrapassa o XP necessário, o personagem sobe de nível automaticamente e seus atributos são ajustados conforme a sua classe.

Jogabilidade (resumo)
- Explorador: chance de encontrar inimigo, encontrar item ou nada.
- Batalha: rolagens de dado somadas ao ataque; dano se ultrapassar a defesa do oponente.
- Itens: inventário com múltiplas unidades; efeitos de cura, buff, dano.
- Fuga: tentativa de escapar baseada em rolagem.
- Save/Restore: salvar uma cópia profunda do personagem e restaurar depois.


