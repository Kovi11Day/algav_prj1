package com.algav.patricia.transformation;

import com.algav.HybridesTries.HybridesTries;
import com.algav.HybridesTries.ValueNonVide;
import com.algav.HybridesTries.ValueVide;
import com.algav.HybridesTries.Ajout;
import com.algav.patricia.PatriciaTrie;
import com.algav.patricia.TestPatricia;

import static com.algav.patricia.string.StringManipulation.*;

public class TransformationHYBtoPAT {
	
	public static PatriciaTrie transformationHybToPat(HybridesTries h){
		return  transHybToPat(new PatriciaTrie(),  h);
	}
	public static PatriciaTrie transHybToPat(PatriciaTrie p, HybridesTries h){
		
		int i = (int)(h.getRacine().getKey());
		
		p.setWord(i, String.valueOf(h.getRacine().getKey()));
		
		if (h.getRacine().getValue() instanceof ValueNonVide){
			if(h.getEq().isVide())
				p.setWord(i, concatEpsilon(p.getWord(i)));
			else{
				PatriciaTrie son1 = new PatriciaTrie();
				son1.setCase(0, String.valueOf((char)0));
				p.setSon(i, son1); //unstable pat: non root node having 1 entry
			}
		}
		
		if (h.troisFilsVide()) //si h.estFeuille
			return p;
		if (!h.getInf().isVide())
			p = transHybToPat(p,h.getInf());
		if (!h.getSup().isVide())
			p = transHybToPat(p,h.getSup());
		
		HybridesTries iter = h.getEq();
		
		//compresser chaine simple de caractères
		while (!iter.isVide() && iter.getInf().isVide() && iter.getSup().isVide()){
			p.setWord(i, p.getWord(i).concat(String.valueOf(iter.getRacine().getKey())));

			if (iter.getRacine().getValue() instanceof ValueNonVide){
				if (iter.getEq().isVide()){
					p.setWord(i, concatEpsilon(p.getWord(i)));
				}else{
					PatriciaTrie son = new PatriciaTrie();
					son.setCase(0, String.valueOf((char)0));
					p.setSon(i, transHybToPat(son,h.getEq()));
				}
			}
			iter = iter.getEq();
		}
		
		if (!iter.isVide()){
			if (p.getSon(i) == null)
				p.setSon(i, transHybToPat(new PatriciaTrie(), iter));
			else
				p.setSon(i, transHybToPat((PatriciaTrie)p.getSon(i), iter));
		}
		return p;
	}
	
	public static void main(String[] s){
		TestPatricia testeur = new TestPatricia("./shakespeare/test.txt");
		System.out.println("input: " + testeur.getRawFileList());
		System.out.println("original pat: " + testeur.getExpectedResult());
		HybridesTries h = Transformation.transformationPatToHyb((PatriciaTrie)testeur.getPatriciaTrie());
		System.out.println("hyb from trans: " + Ajout.liste(h));
		System.out.println("verdict PAT_to_HYB: " + Ajout.liste(h).equals(testeur.getExpectedResult()));
		PatriciaTrie p = transformationHybToPat(h);
		//System.out.println("pat from trans:" + p.listeMots());
		System.out.println("verdict HYB_to_PAT: " + p.listeMots().equals(testeur.getExpectedResult()));

	}
}
