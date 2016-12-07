package com.algav.patricia.transformation;

import com.algav.HybridesTries.Ajout;
import com.algav.HybridesTries.HybridesTries;
import com.algav.patricia.PatriciaTrie;
import com.algav.patricia.TestPatricia;

public class TestTransformation {
	public static void main(String[] s){
		TestPatricia testeur = new TestPatricia("./shakespeare/cleopatra.txt");
		System.out.println("input: " + testeur.getRawFileList());
		System.out.println("original pat: " + testeur.getExpectedResult());
		//System.out.println("original" + testeur.getExpectedResult().get(6));
		HybridesTries h = Transformation.transformationPatToHyb((PatriciaTrie)testeur.getPatriciaTrie());
		System.out.println("hyb from trans: " + Ajout.liste(h));
		System.out.println("verdict PAT_to_HYB: " + Ajout.liste(h).equals(testeur.getExpectedResult()));
		PatriciaTrie p = TransformationHYBtoPAT.transformationHybToPat(h);
		System.out.println("pat from trans:" + p.listeMots());
		System.out.println("verdict HYB_to_PAT: " + p.listeMots().equals(testeur.getExpectedResult()));

	}
}
