/**
 * 
 */
package Lexical_Analyzer;
import java.io.IOException;
import java.util.*;
/**
 * @author alessandro.grando
 *
 */
public class Lexer {
	
	public static int line = 1;
	private char peek = ' ';
	
	Hashtable<String,Word> words = new Hashtable<String, Word>();
	
	void reserve (Word w) {	words.put(w.lexeme, w);	}
	
	public Lexer(){
		this.reserve(new Word(Tag.VAR, "var"));
		this.reserve(new Word(Tag.PRINT, "print"));
		this.reserve(new Word(Tag.BOOLEAN, "boolean"));
		this.reserve(new Word(Tag.INTEGER, "integer"));
		this.reserve(new Word(Tag.NOT, "not"));
		this.reserve(new Word(Tag.TRUE, "true"));
		this.reserve(new Word(Tag.FALSE, "false"));
	}
	
	private void readch() {
		try{
			peek = (char) System.in.read();
		}catch(IOException ex){
			peek = (char) -1;
		}
	}
	
	public void wsDiscard() {
		while(peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r')
		{
			if(peek == '\n') line++;
			readch();
		}
	}
	
	public Token lexical_scan(){
		/*while(peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r')
		{
			if(peek == '\n') line++;
			readch();
		}*/
		wsDiscard();
		switch (peek) {
		
		case ',':
			peek = ' ';
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
			if(peek == '&'){
				peek = ' ';
				return Word.and;
			}else {
				System.err.println("Erroneous character after & " + peek);
				return null;
			}
		case '|':
			readch();
			if(peek == '|'){
				peek = ' ';
				return Word.or;
			}else {
				System.err.println("Erroneous character after | " + peek);
				return null;
			}
		case '=':
			readch();
			if(peek == '='){
				peek = ' ';
				return Word.eq;
			}else {
				System.err.println("Erroneous character after = " + peek);
				return null;
			}
		case '<':
			readch();
			if(peek == '='){
				peek = ' ';
				return Word.le;
			}else if(peek == '>'){
				peek = ' ';
				return Word.ne;
			}else if(peek == ' '){
				peek = ' ';
				return Token.lt;
			}else {
				System.err.println("Erroneous character after < " + peek);
				return null;
			}
		case '>':
			readch();
			if(peek == '='){
				peek = ' ';
				return Word.ge;
			}else if(peek == ' '){
				peek = ' ';
				return Token.gt;
			}else {
				System.err.println("Erroneous character after > " + peek);
				return null;
			}
		case ':':
			readch();
			if(peek == ' '){
				readch();
			}
			if(Character.isLetter(peek)){
				String s = "";
				do{
					s += peek;
					readch();
				}while(Character.isDigit(peek) || Character.isLetter(peek));
				if((Word)words.get(s) != null) return (Word)words.get(s);
			}
			else if(peek == '='){
					peek = ' ';
					return Word.assign;
			}else {
					System.err.println("Erroneous character after :" + peek);
					return null;
			}
		default:
			if(Character.isLetter(peek))
			{
				String s = "";
				do {
					s += peek;
					readch();
				} while (Character.isDigit(peek) || Character.isLetter(peek));
				
				if((Word)words.get(s) != null) return (Word)words.get(s);
				else{
					Word w = new Word(Tag.ID, s);
					words.put(s, w);
					peek = ' ';
					return w;
				}
			}
			else {
				if(Character.isDigit(peek))
				{
					String temp = "";
					do{
						temp += peek;
						readch();
					}while(Character.isDigit(peek));
					
					if((Word)words.get(temp) != null) return (Word)words.get(temp);
					else{
						Word w = new Word(Tag.NUM, temp);
						words.put(temp, w);
						peek = ' ';
						return w;
					}
					
				}
				else if(peek == '$'){
					return new Token(Tag.EOF);
				}
				else {
					System.err.println("Erroneous character!!" + peek);
					return null;
				}
			}
		}
			
	}
	
	public static void main(String[] args)
	{
		Lexer lex = new Lexer();
		
		Token tok;
		do{
			tok = lex.lexical_scan();
			System.out.println("Scan: " + tok.ToString());
		}while(tok.tag != Tag.EOF);
	}
	
}



























