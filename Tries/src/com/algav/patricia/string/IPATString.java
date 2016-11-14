package com.algav.patricia.string;

import com.algav.patricia.exceptions.PATStringException;

public interface IPATString {
	
	//IPATString is any string with character restricted to first 128 ASCII characters 
	String getStr();
	
	//concatenates epsilon at the end of the word
	IPATString concatEpsilon ();
	
	//get ascii code of first character of word
	int asciiFirst();
	
	//verify if first character is epsilon
		//note: there cannot be another character after epsilon
	boolean firstIsEpsilon() throws PATStringException;
}
