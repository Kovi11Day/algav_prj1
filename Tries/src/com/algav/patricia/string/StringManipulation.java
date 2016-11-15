package com.algav.patricia.string;

import com.algav.patricia.exceptions.PATStringException;

public class StringManipulation {
////////***************string_manipulation************************///////////////
	
public static String stringValid(String s){
	for (int i = 0; i < s.length(); ++i){
		char c = s.charAt(i);
		int ascii = (int)c;
		//on utilise les caracters ASCII d'indice 1 a 127
		if (ascii < 1 || ascii > 127)
			throw new PATStringException("String out of character set\n");
	}
	return s;
}

//concatenates epsilon at the end of the word
public static String concatEpsilon (String s){
	
	char epsilon_char = (char)0;
	String epsilon_str = String.valueOf(epsilon_char);
	return s.concat(epsilon_str);
}

//get ascii code of first character of word
public static int asciiFirst(String s){
	char c = s.charAt(0);
	return (int)c;
}

//verify if first character is epsilon
//note: there cannot be another character after epsilon
public static boolean firstIsEpsilon(String s) throws PATStringException{
	char first = s.charAt(0);
	int first_ascii = (int)first;
	if (first_ascii == 0){
		if (s.length() > 1){
			throw new 
				PATStringException("Characters after epsilon");
		}
		return true;
	}else{
		return false;
	}
}

//returns longest common prefixe of 2 strings
public static String prefixe(String s1, String s2){
	String result = "";
	int len = Math.min(s1.length(), s2.length());
	for (int i = 0; i < len; ++i){
		if (s1.charAt(i)==s2.charAt(i)){
			result = result.concat(String.valueOf(s1.charAt(i)));
		}else{	
				break;
			}
	}
	return result;
}

//renvoie le reste du mot quand on tronc le prefixe
//si prefixe de longeur 0, renvoie s
public static String rest (String s, String prefixe){
	return s.substring(prefixe.length(),s.length());
}

//returns true if word end with epsilon, false otherwise
public static boolean containsEpsilon (String s){
	return ((int)s.charAt(s.length()-1) == 0);
}

//truncs off last character of string 
public static String truncLast (String s){
	return s.substring(0, s.length()-1);
}

public static void main(String[] s){
	String s1 = "abcd";
	System.out.println(containsEpsilon(concatEpsilon(s1)));
	System.out.println(prefixe("tac", "tacb"));
}
}
