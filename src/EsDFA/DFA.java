/**
 * 
 */
package EsDFA;

/**
 * @author alessandro.grando
 *
 */
public class DFA {

	private static String args ;
	private static int state = 0;
	private static int i = 0;
	private static boolean flag = true;
	
	
	public DFA(String argument)
	{
		if (argument.length() == 0) flag = false;
		else args = argument;
	}
	
	public boolean getFlag(){
		return flag;
	}
	
	
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
