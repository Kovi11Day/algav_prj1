package com.algav.patricia.string;

public class Main {
	public static void main (String[] args){
		String s1 = "abc";
		char ascii = (char)0;
		String st = String.valueOf(ascii);
		System.out.println(st + "\n");
		String s2 = s1.concat(st);
		System.out.println(s2);
		int len1 = s1.length();
		int len2 = s2.length();
		System.out.println(len1 + "\n" +len2);
	}
}
