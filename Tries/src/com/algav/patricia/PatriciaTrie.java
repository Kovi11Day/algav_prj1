package com.algav.patricia;

public class PatriciaTrie implements IPatriciaTrie{

	public static void main(String [] args){
		String s = "abc";
		for (int i = 0; i < s.length(); ++i){
			char c = s.charAt(i);
			System.out.println((int)c + "\n");
		}
	}
}
