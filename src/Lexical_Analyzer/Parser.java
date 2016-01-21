/**
 * Parsing
 */
package Lexical_Analyzer;

/**
 * @author alessandro.grando
 * 
 */
public class Parser {

	LexerConsole lex;
	Token look;

	public Parser() {
		lex = new LexerConsole();
		move();
	}

	private void move() {
		look = lex.lexical_scan();
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
		match(Tag.EOF);
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
		Parser parser = new Parser();
		parser.start();
	}
}
