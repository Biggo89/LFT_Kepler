/**
 * 
 */
package Lexical_Analyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author alessandro.grando
 * 
 */
public class CustomParser {

	Lexer lex;
	Token look;
	BufferedReader buffer;

	public CustomParser(BufferedReader br) {
		lex = new Lexer();
		buffer = br;
		move();
	}

	private void move() {
		look = lex.lexical_scan(this.buffer);
		System.err.println("token = " + look.ToString());
	}

	private void error(String s) {
		throw new Error("near line" + lex.line + ":" + look.ToString());
	}

	private void match(int t) {
		if (look.tag == t) {
			if (look.tag != Tag.EOF)
				move();
		} else
			this.error("syntax error");
	}

	public void start() {
		expr();
		match(';');
		exprs();
	}

	public void exprs() {
		if (look.tag == Tag.EOF)
			return;
		else {
			expr();
			match(';');
			exprs();
		}
	}

	private void expr() {
		term();
		exprp();
	}

	private void exprp() {
		switch (look.tag) {
		case '+':
			match('+');
			term();
			exprp();
			break;
		case '-':
			match('-');
			term();
			exprp();
			break;

		}
	}

	private void term() {
		fact();
		termp();
	}

	private void termp() {
		switch (look.tag) {
		case '*':
			match('*');
			fact();
			termp();
			break;
		case '/':
			match('/');
			fact();
			termp();
			break;
		}
	}

	private void fact() {
		switch (look.tag) {
		case '(':
			match('(');
			expr();
			match(')');
			break;
		case Tag.NUM:
			match(Tag.NUM);
			break;
		default:
			this.error("syntax error");
		}
	}

	public static void main(String[] args) {
		/*
		 * Parser parser = new Parser(); parser.start();
		 */
		String path = "C:\\Users\\alessandro.grando\\workspace\\LFT_Kepler\\";
		String inputFileName = path + "InputParser.txt";
		try {
			BufferedReader br = new BufferedReader(
					new FileReader(inputFileName));
			CustomParser parser = new CustomParser(br);
			parser.start();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
