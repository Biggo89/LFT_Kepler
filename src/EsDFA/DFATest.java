package EsDFA;

public class DFATest {

	public static void main(String[] args) {
			DFA automata = new DFA("/*aaa*/***/");
			if(automata.getFlag()){
				System.out.println(automata.javaCommentAutomaton() ? "OK" : "NOPE");
			}
	}

}
