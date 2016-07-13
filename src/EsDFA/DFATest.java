package EsDFA;

public class DFATest {

	public static void main(String[] args) {
			DFA automata = new DFA("1110000");
			if(automata.getFlag()){
				System.out.println(automata.threeZeroAccepted() ? "OK" : "NOPE");
			}
	}

}
