package com.algav.patricia.string;

import com.algav.patricia.exceptions.OutOfCharacterSetException;


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

	
}
