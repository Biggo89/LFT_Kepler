/**
 * 
 */
package EsDFA;

/**
 * @author alessandro.grando
 *
 */
public class DFA {
	/**
	 * stringa su cui applicare il DFA.
	 */
	private static String args ;
	
	/**
	 * variabile che tiene traccia della computazione del DFA.
	 */
	private static int state = 0;
	
	/**
	 * variabile di appoggio che scorre la stringa in input
	 * carattere per carattere.
	 */
	private static int i = 0;
	
	/**
	 * controlla se la stringa ha una lungezza >=1;
	 * altrimenti non applica neanche il DFA.
	 */
	private static boolean flag = true;
	
	/**
	 * Crea una istanza di un oggetto che
	 * definisce i DFA
	 * @param argument stringa su cui applicare il DFA
	 */
	public DFA(String argument)
	{
		if (argument.length() == 0) flag = false;
		else args = argument;
	}
	
	/**
	 * Ottiene il flag
	 * @return true se il DFA può essere eseguito, altrimenti false.
	 */
	public boolean getFlag(){
		return flag;
	}
	
	/**
	 * DFA che riconosce le stringhe binare, il cui valore decimale è multiplo di 3.
	 * es 1.5
	 * @return true se la stringa è riconosciuta, false altrimenti;
	 */
	public boolean multipleOfThree(){
		if (!flag) return false;
		
		while(state >= 0 && i < args.length()){
			final char ch = args.charAt(i++);
			
			switch(state){
			
			case 0:
				if(ch == '1') state = 1;
				else if(ch == '0') state = 0;
				else state = -1;
				break;
			case 1:
				if(ch == '0') state = 2;
				else if(ch == '1') state = 0;
				else state = -1;
				break;
			case 2:
				if(ch == '0') state = 1;
				else if(ch == '1') state = 2;
				else state = -1;
				break;
			}
			
		}
		return state == 0;
	}
	
	/**
	 * DFA che riconosce le stringhe con 3 zeri consecutivi.
	 * es 1
	 * @return rue se la stringa è riconosciuta, false altrimenti;
	 */
	public boolean threeZeroAccepted(){
		if(!flag) return false;
		
		while(state >= 0 && i < args.length()){
			final char ch = args.charAt(i++);
			
			switch(state){
			
			case 0:
				if(ch == '0') state = 1;
				else if(ch == '1') state = 0;
				else state = -1;
				break;
			case 1:
				if(ch == '0') state = 2;
				else if(ch == '1') state = 0;
				else state = -1;
				break;
			case 2:
				if(ch == '0') state = 3;
				else if(ch == '1') state = 0;
				else state = -1;
				break;
			case 3:
				if(ch == '0' || ch == '1') state = 3;
				else state = -1;
				break;
			}
		}
		
		return state == 3;
	}
	
	/**
	 * DFA che riconosce le stringhe che non hanno 3 zeri consecutivi.
	 * @return rue se la stringa è riconosciuta, false altrimenti;
	 */
	public boolean threeZeroNotAccepted(){
		if(!flag) return false;
		while(i < args.length() && state >= 0){
			
			final char ch = args.charAt(i++);			
			switch(state){
			
			case 0:
				if(ch == '0') state = 1;
				else if(ch == '1') state = 2;
				else state = -1; 
				break;
			case 1:
				if(ch == '0') state = 3;
				else if(ch =='1') state = 2;
				else state = -1;
				break;
			case 2:
				if(ch == '0') state = 1;
				else if(ch == '1') state = 2;
				else state = -1;
				break;
			case 3:
				if(ch == '1') state = 2;
				else state = -1;
				break;
			}
		}
		return (state == 2 || state == 3 || state == 4);
	}
	
	/**
	 * DFA che ricosce le costanti numeriche(positive e negative) in virgola mobile, con esponenti (positivi e negativi) 
	 * @return rue se la stringa è riconosciuta, false altrimenti;
	 */
	public boolean numericConstant(){
		if(!flag) return false;
		while(i < args.length() && state >= 0){
			final char ch = args.charAt(i++);
			
			switch(state){
			
			case 0:
				if(ch == '.') state = 3;
				else if(DFA.checkPlusMinus(ch)) state = 2;
				else if(Character.isDigit(ch)) state = 1;
				else if(Character.isWhitespace(ch)) break;
				else state = -1;
				break;
			
			case 1:
				if(Character.isDigit(ch)) state = 1;
				else if(ch == '.') state = 3;
				else if(ch == 'e') state = 5;
				else if(Character.isWhitespace(ch)) break;
				else state = -1;
				break;
			case 2:
				if(Character.isDigit(ch)) state = 1;
				else if(ch == '.') state = 3;
				else if(Character.isWhitespace(ch)) break;
				else state = -1;
				break;
			case 3:
				if(Character.isDigit(ch)) state = 4;
				else if(Character.isWhitespace(ch)) break;
				else state = -1; 
				break;
			case 4:
				if(Character.isDigit(ch)) state = 4;
				else if(ch == 'e') state = 5;
				else if(Character.isWhitespace(ch)) break;
				else state = -1;
				break;
			case 5:
				if(Character.isDigit(ch)) state = 6;
				else if(Character.isWhitespace(ch)) break;
				else state = -1;
				break;
			case 6:
				if(Character.isDigit(ch)) state = 6;
				else if(Character.isWhitespace(ch)) break;
				else state = -1;
				break;
			}
		}
		return state == 1 || state == 4 || state == 6;
	}
	
	private static boolean checkPlusMinus(char temp){
		return ((temp == '-') ||  (temp == '+')) ? true : false;
	}
	
	/**
	 * accetta tutti i nomi di identificatori in Java;
	 * un identificatore è una sequenza non vuota di lettere, numeri e "underscore"
	 * che non comincia con un numero o con una sequenza di "underscore" superiore ad 1.
	 * @return rue se la stringa è riconosciuta, false altrimenti;
	 */
	public boolean javaIdentifiers()
	{	
		if (!flag) return false;
		while(state >= 0 && i<args.length())
		{
			final char ch = args.charAt(i++);
			
			switch(state)
			{
			case 0:
				if(Character.isLetter(ch)) state = 2;
				else if(ch == '_') state = 1;
				else state = -1;
				break;
			case 1:
				if(Character.isLetter(ch) || Character.isDigit(ch)) state = 2;
				else if(ch == '_') break;
				else state = -1;
				break;
			case 2:
				if(Character.isLetter(ch) || Character.isDigit(ch) || ch == '_') break;
				else state = -1;
				break;
			}
		}
		return state == 2 ? true : false;
		
	}
	
	/**
	 * DFA che riconosce la sintassi dei commenti in java e cioè le stringhe che appartengono alla seguente espressione regolare:
	 * (/*)+ ( (/*|a*)* | (a*| **)* ) ("*"/)+
	 * @return rue se la stringa è riconosciuta, false altrimenti;
	 */
	public boolean javaCommentAutomaton()
	{
		if(args.length() < 4) return false;
		
		while(state >= 0 && i < args.length())
		{
			final char ch = args.charAt(i++);
			
			switch(state)
			{
			case 0:
				if(ch == '/') state = 1;
				else state = -1;
				break;
			case 1:
				if(ch == '*') state = 2;
				else state = -1;
				break;
			case 2:
				if(ch == 'a' || ch == '/') break;
				else if(ch == '*') state = 3;
				else state = -1;
				break;
			case 3:
				if(ch == '*') break;
				else if(ch == 'a') state = 2;
				else if(ch == '/') state = 4;
				else state = -1;
				break;
			}
		}
		
		return state == 4;
	}

	public boolean fromNFAtoDFA()
	{
		if(!flag) return false;
		
		while(state >= 0 && i < args.length())
		{
			final char ch = args.charAt(i++);
			
			switch(state)
			{
			case 0:
				if(ch == 'a' || ch == 'A') state = 1;
				else if(ch == 'b' || ch == 'B') state = 2;
				else state = -1;
				break;
			case 1:
				if(ch == 'a' || ch == 'A') break;
				else if(ch == 'b' || ch == 'B') state = 3;
				else state = -1;
				break;
			case 2: 
				if(ch == 'a') state = 4;
				else state = -1;
				break;
			case 3:
				state = -1;
				break;
			case 4:
				if(ch == 'a') break;
				else state = -1;
				break;
			}
		}
		return state == 4 || state == 3;
	}

}
