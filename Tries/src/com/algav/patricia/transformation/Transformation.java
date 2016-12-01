package com.algav.patricia.transformation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.algav.HybridesTries.Ajout;
import com.algav.HybridesTries.HybridesTries;
import com.algav.HybridesTries.Node;
import com.algav.HybridesTries.ValueNonVide;
import com.algav.HybridesTries.ValueVide;
import com.algav.patricia.IPatriciaTrie;
import com.algav.patricia.TestPatricia;

import static com.algav.patricia.string.StringManipulation.*;

public class Transformation {

	public static LinkedList<TransNode> compress (IPatriciaTrie p){
		//remove all empty nodes from trie
		if (p == null)
			return null;
		
		LinkedList<TransNode> result = new LinkedList<TransNode>();
		
		for(int i = 0; i < p.getSize(); ++i){
			if (p.getCase(i) != null){
				result.add(new TransNode(p.getWord(i), compress(p.getSon(i))));
			}
		}
		return result;
	}
	
	public static void removeEpsNodes(LinkedList<TransNode> cp){
		if (cp == null)
			return;
		for (int i = 0; i < cp.size(); ++i){
			if (cp.get(i).getSon() != null && cp.get(i).getSon().get(0).getWord().equals((char)(0))){
				concatEpsilon(cp.get(i).getWord());
				cp.get(i).getSon().remove(0);
			}
			removeEpsNodes(cp.get(i).getSon());
		}
	}
	
	public static int middle(int len){
		return (int)Math.floor((len - 1)/2);
	}
	
	public static TransTrie eclater(List<TransNode> cp){
		if (cp == null)
			return null;
		
		TransTrie result = null;

		if (cp.size() == 1){
			result = new TransTrie(cp.get(0));
			result.setEq(eclater(cp.get(0).getSon()));
			
		}else if (cp.size() == 2){	
			result = new TransTrie(cp.get(0));
			result.setEq(eclater(cp.get(0).getSon()));
			result.setSup(new TransTrie(cp.get(0)));
			result.getSup().setEq(eclater(cp.get(1).getSon()));
		}else{
			result = new TransTrie(cp.get(middle(cp.size())));
			result.setEq(eclater(cp.get(middle(cp.size())).getSon()));
			result.setInf(eclater(cp.subList(0, middle(cp.size()))));
			result.setSup(eclater(cp.subList(middle(cp.size()) + 1, cp.size())));
		}
		return result;
	}
	
	public static HybridesTries[] expand (String s){
		HybridesTries first = null;
		HybridesTries iter = null;
		for (int i = 0; i < s.length(); ++i){
			if (s.charAt(i)==((char)0)){
				iter = new HybridesTries(new HybridesTries(),new HybridesTries(),new HybridesTries(),new Node(s.charAt(i),new ValueNonVide()));
			}else{
				iter = new HybridesTries(new HybridesTries(),new HybridesTries(),new HybridesTries(),new Node(s.charAt(i),new ValueVide()));
			}
			
			if (i == 0)
				first = iter;
			
			if (i != s.length()-1)
			iter = iter.getEq();
		}
		HybridesTries result[] = new HybridesTries[2];
		result[0] = first;
		result[1] = iter;
		
		return result;
	}
	
	public static HybridesTries transFinal(TransTrie tt){
		if (tt == null)
			return new HybridesTries();
		
		HybridesTries expanded[] = new HybridesTries[2]; 
		expanded = expand(tt.getRoot().getWord());
		
		expanded[1].setInf(transFinal(tt.getInf()));
		expanded[1].setEq(transFinal(tt.getEq()));
		expanded[1].setSup(transFinal(tt.getSup()));

		return expanded[0];

	}
	
	public static void main(String[] args){
		TestPatricia t = new TestPatricia("./shakespeare/allswell.txt");
		System.out.println(t.getRawFileList());
	
		LinkedList<TransNode> ct = compress(t.getPatriciaTrie());
		
		removeEpsNodes(ct);
		//mal construit h
		HybridesTries h = transFinal(eclater(ct));
		System.out.println("patricia: " + t.getExpectedResult().size());

		System.out.println("hybride: " + Ajout.comptageMots(h));
	}
}
