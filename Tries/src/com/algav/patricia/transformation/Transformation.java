package com.algav.patricia.transformation;

import java.util.LinkedList;

import com.algav.patricia.IPatriciaTrie;
import com.algav.patricia.TestPatricia;

public class Transformation {

	public static LinkedList<TransNode> compress (IPatriciaTrie p){
		if (p == null){
			return null;
		}
		
		LinkedList<TransNode> result = new LinkedList<TransNode>();
		
		for(int i = 0; i < p.getSize(); ++i){
			if (p.getCase(i) != null){
				result.add(new TransNode(p.getWord(i), compress(p.getSon(i))));
			}
		}
		return result;
	}
	
	public static LinkedList<TransNode> eclater(LinkedList<TransNode> cp){
		if (cp.size() == 1)
			return cp;
		
		//cp.subList //for root=eclater(sublist(i;k)), left=eclater(sublist(l;m)), right=eclater(sublist(o,p))
		return null;
	}
	
	public static void main(String[] args){
		TestPatricia t = new TestPatricia("./shakespeare/allswell.txt");
		compress(t.getPatriciaTrie());
	}
}