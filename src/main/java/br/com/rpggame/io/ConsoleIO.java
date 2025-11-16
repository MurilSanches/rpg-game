package br.com.rpggame.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Set;

public class ConsoleIO {
	private final BufferedReader in;
	private final PrintStream out;

	public ConsoleIO() {
		this(new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset())), System.out);
	}

	public ConsoleIO(BufferedReader in, PrintStream out) {
		this.in = in;
		this.out = out;
	}

	public void println(String s) {
		out.println(s);
	}

	public void print(String s) {
		out.print(s);
	}

	public String readLine() throws IOException {
		return in.readLine();
	}

	public String readNonEmpty(String prompt) throws IOException {
		while (true) {
			if (prompt != null) print(prompt);
			String s = readLine();
			if (s != null && !s.trim().isEmpty()) return s.trim();
			println("Valor inválido, tente novamente.");
		}
	}

	public String readOption(String prompt, Set<String> validOptions) throws IOException {
		while (true) {
			if (prompt != null) print(prompt);
			String s = readLine();
			if (s != null && validOptions.contains(s.trim())) return s.trim();
			println("Opção inválida.");
		}
	}
}


