package com.algav.patricia.transformation;

import java.util.LinkedList;

public class TransNode {
	public TransNode(String word, LinkedList<TransNode> trie){
		this.trie = trie;
		this.word = word;
	}
	private String word;
	private LinkedList<TransNode> trie;
	
	public String getWord() {
		return word;
	}
	public LinkedList<TransNode> getTrie() {
		return trie;
	}
	
	
}
