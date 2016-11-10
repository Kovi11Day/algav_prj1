package com.algav.patricia.string;

public class PATStringE implements IPATStringE{

	private String str;
	
	//a la fin de chaque mot on concatene l'equivalent de epsilon
	public PATStringE(String s1){
		//on a choisit epsilon comme le charactere ASCII 0
		char ascii = (char)0;
		String st = String.valueOf(ascii);
		String s2 = s1.concat(st);
		str = s2;
	}

	public String getStr() {
		return str;
	}


	
	
}
