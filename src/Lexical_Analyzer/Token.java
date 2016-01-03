/**
 * 
 */
package Lexical_Analyzer;

/**
 * @author alessandro.grando
 *
 */
public class Token {
	public final int tag;
	
	//private static char[] special_char = new char[]{',',':',';','(',')','+','-','/','*','<','>'};
	
	//public static Token[] token = new Token[special_char.length];
	
	public Token(int x)	{	this.tag = x;	}
	
	public String ToString()	{	return "<" + tag + ", " +(char)tag  + ">";	}
	
	/*public static void inizializer(){
		
		for(int i=0; i < special_char.length; i++){
			token[i] = new Token(special_char[i]);
		}
	}*/
	
	public static final Token
			comma = new Token(','),
			colon = new Token(':'), 
			semicolon = new Token(';'),
			lpar = new Token('('),
			rpar = new Token(')'),
			plus = new Token('+'),
			minus = new Token('-'),
			mult = new Token('*'),
			div = new Token('/'),
			lt = new Token('<'),
			gt = new Token('>');
}
