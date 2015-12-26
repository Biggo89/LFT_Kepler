package EsDFA;

public class DFATest {

	public static void main(String[] args) {
			DFA automata = new DFA("aaaaaab");
			if(automata.getFlag()){
				System.out.println(automata.fromNFAtoDFA() ? "OK" : "NOPE");
			}
	}

}
