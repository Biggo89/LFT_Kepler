/**
 * 
 */
package Lexical_Analyzer;
import java.io.BufferedReader;

import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;

/**
 * @author alessandro.grando
 *
 */
public class Valutatore {
	
	private Lexer lex;
	private Token look;
	private BufferedReader br;

	
	public Valutatore(Lexer l)
	{
		lex = l;
		move();
	}
	/**
	 * 
	 */
	private void move()
	{
		look = lex.lexical_scan(br);
		System.err.println("token = " + look.ToString());
	}
	
	/**
	 * 
	 * @param t
	 */
	private void match(int t) {
		if (look.tag == t) {
			if (look.tag != Tag.EOF)
				move();
		} else
			this.error("syntax error");
	}
	/**
	 * 
	 * @param s
	 */
	private void error(String s) {
		throw new Error("near line" + lex.line + ":" + look.ToString());
	}
	/**
	 * 
	 */
	public void start()
	{
		int expr_val;
		expr_val = expr();
		
		match(Tag.EOF);
		
		System.out.println(expr_val);
	}
	
	private int expr()
	{
		int term_val, exprp_val,exprp_i,expr_val;
		term_val = term();
		exprp_i = term_val;
		exprp_val = exprp(exprp_i);
		expr_val = exprp_val;
		return expr_val;
	}
	
	private int exprp(int exprp_i)
	{
		int exprp_val,term_val,exprp_1_val,exprp_1_i;
		switch(look.tag)
		{
		case '+':
			term_val = term();
			exprp_1_i = term_val + exprp_i;
			exprp_1_val = exprp(exprp_1_i);
			exprp_val = exprp_1_val;
			break;
		case '-':
			term_val = term();
			exprp_1_i = term_val - exprp_i;
			exprp_1_val = exprp(exprp_1_i);
			exprp_val = exprp_1_val;
			break;
		default:
			exprp_val = exprp_i;	
		}
		return exprp_val;
	}
	
	private int term()
	{
		int term_val,fact_val,termp_i,termp_val;
		fact_val = fact();
		termp_i = fact_val;
		termp_val = termp(termp_i);
		term_val = termp_val;
		return term_val;
	}
	private int termp(int termp_i)
	{
		int termp_val,term_val,termp_1_val,termp_1_i;
		switch(look.tag)
		{
		case '*':
			term_val = term();
			termp_1_i = term_val * termp_i;
			termp_1_val = termp(termp_1_i);
			termp_val = termp_1_val;
			break;
		case '/':
			term_val = term();
			termp_1_i = term_val / termp_i;
			termp_1_val = termp(termp_1_i);
			termp_val = termp_1_val;
			break;
		default:
			termp_val = termp_i;	
		}
		return termp_val;
	}
	
	
	private int fact()
	{
		int fact_val=-1, expr_val;
		switch(look.tag)
		{
		case '(':
			expr_val = expr();
			fact_val = expr_val;
			match(')');
			break;
		case Tag.NUM:
			fact_val = lex.peek;
			break;
		}
		return fact_val;
	}
	
	
	/*
	 **
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
