package Lexical_Analyzer;

import java.util.*;
import java.io.*;

public class LexerConsole {
	public int line = 1;

	private char peek = ' ';

	private int state;

	Hashtable<String, Word> words = new Hashtable<String, Word>();

	void reserve(Word w) {
		words.put(w.lexeme, w);
	}

	private boolean javaIdentifier(String str) {
		state = 0;
		int i = 0;
		while (state >= 0 && i < str.length()) {
			char ch = str.charAt(i++);
			switch (state) {
			case 0:
				if (Character.isLetter(ch))
					state = 2;
				else if (ch == '_')
					state = 1;
				else
					state = -1;
				break;
			case 2:
				if (Character.isLetter(ch) || Character.isDigit(ch)
						|| ch == '_')
					break;
				else
					state = -1;
				break;
			case 1:
				if (Character.isDigit(ch) || Character.isLetter(ch))
					state = 2;
				else if (ch == '_')
					break;
				else
					state = -1;
				break;
			}
		}
		return state == 2;
	}

	public LexerConsole() {
		this.reserve(new Word(Tag.VAR, "var"));
		this.reserve(new Word(Tag.PRINT, "print"));
		this.reserve(new Word(Tag.BOOLEAN, "boolean"));
		this.reserve(new Word(Tag.INTEGER, "integer"));
		this.reserve(new Word(Tag.NOT, "not"));
		this.reserve(new Word(Tag.TRUE, "true"));
		this.reserve(new Word(Tag.FALSE, "false"));
		this.reserve(new Word(Tag.IF, "if"));
		this.reserve(new Word(Tag.THEN, "then"));
		this.reserve(new Word(Tag.ELSE, "else"));
		this.reserve(new Word(Tag.WHILE, "while"));
		this.reserve(new Word(Tag.DO, "do"));
		this.reserve(new Word(Tag.BEGIN, "begin"));
		this.reserve(new Word(Tag.END, "end"));
	}

	private void readch() {
		try {
			peek = (char) System.in.read();
		} catch (IOException ex) {
			peek = (char) -1;
		}
	}

	private void wsDiscard() {
		while (peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r') {
			if (peek == '\n')
				line++;
			readch();
		}
	}

	public Token lexical_scan() {
		wsDiscard();
		switch (peek) {

		case ',':
			peek = ' ';// allow me to not take a loop, thus I read next char.
			return Token.comma;
		case ';':
			peek = ' ';
			return Token.semicolon;
		case '(':
			peek = ' ';
			return Token.lpar;
		case ')':
			peek = ' ';
			return Token.rpar;
		case '+':
			peek = ' ';
			return Token.plus;
		case '-':
			peek = ' ';
			return Token.minus;
		case '*':
			peek = ' ';
			return Token.mult;
		case '/':
			peek = ' ';
			return Token.div;
		case '&':
			readch();
			if (peek == '&') {
				peek = ' ';
				return Word.and;
			} else {
				System.err.println("Erroneous character after & " + peek);
				return null;
			}
		case '|':
			readch();
			if (peek == '|') {
				peek = ' ';
				return Word.or;
			} else {
				System.err.println("Erroneous character after | " + peek);
				return null;
			}
		case '=':
			readch();
			if (peek == '=') {
				peek = ' ';
				return Word.eq;
			} else {
				System.err.println("Erroneous character after = " + peek);
				return null;
			}
		case '<':
			readch();
			if (peek == '=') {
				peek = ' ';
				return Word.le;
			} else if (peek == '>') {
				peek = ' ';
				return Word.ne;
			} else if (peek == ' ') {
				peek = ' ';
				return Token.lt;
			} else {
				System.err.println("Erroneous character after < " + peek);
				return null;
			}
		case '>':
			readch();
			if (peek == '=') {
				peek = ' ';
				return Word.ge;
			} else if (peek == ' ') {
				peek = ' ';
				return Token.gt;
			} else {
				System.err.println("Erroneous character after > " + peek);
				return null;
			}
		case ':':
			readch();
			if (Character.isLetter(peek)) {
				String s = "";
				do {
					s += peek;
					readch();
				} while (Character.isDigit(peek) || Character.isLetter(peek));
				if ((Word) words.get(s) != null)
					return (Word) words.get(s);
			} else if (peek == '=') {
				peek = ' ';
				return Word.assign;
			} else {
				System.err.println("Erroneous character after :" + peek);
				return null;
			}
		default:
			if (Character.isLetter(peek) || peek == '_') {
				String s = "";
				do {
					s += peek;
					readch();
				} while (Character.isDigit(peek) || Character.isLetter(peek)
						|| peek == '_');

				if ((Word) words.get(s) != null)
					return (Word) words.get(s);
				else if (this.javaIdentifier(s)) {
					Word w = new Word(Tag.ID, s);
					words.put(s, w);
					return w;
				} else {
					System.err.println("invalid pattern identifier: " + s);
					return null;
				}

			} else {
				if (Character.isDigit(peek)) {
					String temp = "";
					do {
						temp += peek;
						readch();
					} while (Character.isDigit(peek));

					Word w = new Word(Tag.NUM, temp);
					words.put(temp, w);
					return w;

				} else if (peek == '$') {
					return new Token(Tag.EOF);
					// return new Token(peek);
				} else {
					System.err.println("Erroneous character!!" + peek);
					return null;
				}
			}
		}

	}

	public static void main(String[] args) {
		LexerConsole lex = new LexerConsole();

		Token tok;
		do {
			tok = lex.lexical_scan();
			System.out.println("Scan: " + tok.ToString());
		} while (tok.tag != Tag.EOF);
		// while(tok.tag != '$');

	}

}
