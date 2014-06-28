
package ontology;

import java.io.*;
import read.*;
import java.util.*;
import java.util.regex.*;

public class Tok {
	
	//Properties
	
	FileReader f;
	String s;
	Data[] d;
	
	//Constructor
	
	public Tok(String s){
		this.s=s;
		try{
			this.read();
		}
		catch(Exception e){
		}
	}
	
	//Methods
	
	public void read(){
		s = Keyboard.read_string();
		s = s + ".txt";
		
	}
	
	public void tokenize(){
		
	}
}
