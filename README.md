RPG de Texto em Java (POO)

Descrição
Um RPG de texto simples que demonstra conceitos essenciais de Programação Orientada a Objetos (POO) em Java: abstração, encapsulamento, herança, polimorfismo, composição, sobrescrita, interfaces, comparabilidade, igualdade, cópia/clone e boas práticas.

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

Decisões de design
- Encapsulamento: todos os atributos são privados com validações nos setters.
- Igualdade e hash: Item define equals/hashCode por (nome, efeito), consistente com seu contrato.
- Ordenação: Item implementa Comparable por nome (desempate por efeito).
- Cópia profunda: inventário e personagens possuem construtores de cópia que duplicam coleções/objetos mutáveis (sem aliasing).
- Polimorfismo: subclasses (Guerreiro/Mago/Arqueiro) especializam cálculo de ataque/efeitos, mantendo API comum de Personagem.
- Testabilidade: Dado permite configurar semente para gerar resultados determinísticos.

Jogabilidade (resumo)
- Explorador: chance de encontrar inimigo, encontrar item ou nada.
- Batalha: rolagens de dado somadas ao ataque; dano se ultrapassar a defesa do oponente.
- Itens: inventário com múltiplas unidades; efeitos de cura, buff, dano.
- Fuga: tentativa de escapar baseada em rolagem.
- Save/Restore: salvar uma cópia profunda do personagem e restaurar depois.


