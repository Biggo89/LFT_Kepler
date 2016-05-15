/**
 * 
 */
package Lexical_Analyzer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author aless
 *
 */
public class SymbolTable {
	Map<String, Type> TypeMap = new HashMap<String,Type>();
	Map<String, Integer> OffsetMap = new HashMap<String, Integer>();
	
	
	public void insert(String s, Type t, int address)
	{
		if(!TypeMap.containsKey(s)) TypeMap.put(s, t);
		else throw new IllegalArgumentException("Variabile già dichiarata.");
		if(!OffsetMap.containsValue(address)) OffsetMap.put(s, address);
		else throw new IllegalArgumentException("Riferimento ad una locazione di memoria già occupata da un'altra variabile.");
		
	}
	
	public Type lookupType(String s)
	{
		if(TypeMap.containsKey(s)) return TypeMap.get(s);
		throw new IllegalArgumentException("Varibile sconosciuta." + s);
	}
	
	public int lookupAddress(String s)
	{
		if(OffsetMap.containsKey(s)) return OffsetMap.get(s);
		throw new IllegalArgumentException("Variabile Sconosciuta.");
	}
}
