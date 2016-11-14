package com.algav.patricia.string;

import com.algav.patricia.exceptions.OutOfCharacterSetException;
import com.algav.patricia.exceptions.PATStringException;


public class PATString implements IPATString{
	
	private String str;
	
	public PATString(String str) throws OutOfCharacterSetException {
		for (int i = 0; i < str.length(); ++i){
			char c = str.charAt(i);
			int ascii = (int)c;
			//on utilise les caracters ASCII d'indice 1 a 127
			if (ascii < 1 || ascii > 127)
				throw new OutOfCharacterSetException();
		}
		this.str = str;
	}

	public String getStr() {
		return str;
	}

	//concatenates epsilon at the end of the word
	public IPATString concatEpsilon (){
		
		char epsilon_char = (char)0;
		String epsilon_str = String.valueOf(epsilon_char);
		IPATString str2 = 
				new PATString(this.getStr().concat(epsilon_str));
		return str2;
	}
	
	//get ascii code of first character of word
	public int asciiFirst(){
		char c = this.getStr().charAt(0);
		return (int)c;
	}
	
	//verify if first character is epsilon
	//note: there cannot be another character after epsilon
	public boolean firstIsEpsilon() throws PATStringException{
		char first = this.getStr().charAt(0);
		int first_ascii = (int)first;
		if (first_ascii == 0){
			if (this.getStr().length() > 1){
				throw new 
					PATStringException("Characters after epsilon");
			}
			return true;
		}else{
			return false;
		}
	}
}
