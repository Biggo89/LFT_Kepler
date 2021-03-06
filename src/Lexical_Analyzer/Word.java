/**
 * 
 */
package Lexical_Analyzer;

/**
 * @author alessandro.grando
 *
 */
public class Word extends Token{
	
	public String lexeme = "";
	
	public Word(int tag, String s)	{	super(tag); lexeme = s;	}
	
	public String ToString(){
		return "<" + tag + ", " + lexeme + ">";
	}
	
	public static final Word
			and = new Word(Tag.AND , "&&"),
			or = new Word(Tag.OR, "||"),
			eq = new Word(Tag.EQ, "=="),
			le = new Word(Tag.LE, "<="),
			ge = new Word(Tag.GE, ">="),
			ne = new Word(Tag.NE, "<>"),
			assign = new Word(Tag.ASSIGN, ":=");

}
