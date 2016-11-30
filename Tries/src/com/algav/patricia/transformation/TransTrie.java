package com.algav.patricia.transformation;

import java.util.LinkedList;

public class TransTrie {
	private LinkedList<TransNode> root;
	private LinkedList<TransNode> eq;
	private LinkedList<TransNode> inf;
	private LinkedList<TransNode> sup;
	
	public TransTrie(LinkedList<TransNode> inf, LinkedList<TransNode> eq, LinkedList<TransNode> sup,
			LinkedList<TransNode> root){
		this.eq = eq;
		this.inf = inf;
		this.sup = sup;
		this.root = root;
	}
	
	public TransTrie(LinkedList<TransNode> root){
		this.root = root;
		this.eq = null;
		this.inf = null;
		this.sup = null;

	}
	
	public LinkedList<TransNode> getRoot() {
		return root;
	}
	public LinkedList<TransNode> getEq() {
		return eq;
	}
	public LinkedList<TransNode> getInf() {
		return inf;
	}
	public LinkedList<TransNode> getSup() {
		return sup;
	}

	public void setRoot(LinkedList<TransNode> root) {
		this.root = root;
	}

	public void setEq(LinkedList<TransNode> eq) {
		this.eq = eq;
	}

	public void setInf(LinkedList<TransNode> inf) {
		this.inf = inf;
	}

	public void setSup(LinkedList<TransNode> sup) {
		this.sup = sup;
	}
	
	
	
	
}
