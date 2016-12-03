package com.algav;

import java.util.ArrayList;

import com.algav.HybridesTries.Ajout;
import com.algav.HybridesTries.HybridesTries;
import com.algav.patricia.PatriciaTrie;

public class Calculation {
	//private final static int NTESTS = 100000; 
	private final static int NTESTS = 100000; 
   
	public static double constructionHybride( ArrayList<String> words){
		 long temps_total = 0;
		 double moyenne ;
		 System.out.println("a");
		 for(int cpt = 0; cpt < NTESTS; cpt++){
			// System.out.println("b");
			 long avant = System.currentTimeMillis();
			 HybridesTries h = new HybridesTries();
			 for(int i = 0 ; i < words.size();i++){
				  h = Ajout.ajoutString(words.get(i), h);
			 }
			 //System.out.println("c");
			 long après = System.currentTimeMillis();
			 long écoulé = après - avant;
	         if (écoulé >= 0) {
	            temps_total += écoulé;
	         }
		 }
		 moyenne = temps_total / (double) NTESTS;
		 return  moyenne;
		
	}
	
	public static double constructionPatricia(ArrayList<String> word){
		 long temps_total = 0;
		 double moyenne ;
		 for(int cpt = 0; cpt < NTESTS; cpt++){
			 long avant = System.currentTimeMillis();
			 PatriciaTrie p = new PatriciaTrie(word.get(0));
			 for(int i = 1; i < word.size(); i++ ){
					p.ajout(word.get(i));
				}
			 long après = System.currentTimeMillis();
			 long écoulé = après - avant;
	         if (écoulé >= 0) {
	            temps_total += écoulé;
	         }
		 }
		 moyenne = temps_total / (double) NTESTS;
		 return  moyenne;
		
		
		
		
	}
	
	public static double constructionHybrideEquilibre( ArrayList<String> words){
		 long temps_total = 0;
		 double moyenne ;
		 for(int cpt = 0; cpt < NTESTS; cpt++){
			 long avant = System.currentTimeMillis();
			 HybridesTries h = new HybridesTries();
			 for(int i = 0 ; i< words.size();i++){
				 h = Ajout.ajoutStringEquilibrage(words.get(i), h);
			 }
			 long après = System.currentTimeMillis();
			 long écoulé = après - avant;
	         if (écoulé >= 0) {
	            temps_total += écoulé;
	         }
		 }
		 moyenne = temps_total / (double) NTESTS;
		 return  moyenne;
		
	}
	
	
	public static double addNewWordHybride(String word, HybridesTries h){
		long temps_total = 0;
		double moyenne ;
		 for(int cpt = 0; cpt < NTESTS; cpt++){
			 long avant = System.currentTimeMillis();
			  Ajout.ajoutString(word, h);
			 long après = System.currentTimeMillis();
			 long écoulé = après - avant;
	         if (écoulé >= 0) {
	            temps_total += écoulé;
	         }
		 }
		 moyenne = temps_total / (double) NTESTS;
		 return  moyenne;		
	}
	
	public static double addNewWordPatricia(String word, PatriciaTrie p){
		long temps_total = 0;
		double moyenne ;
		 for(int cpt = 0; cpt < NTESTS; cpt++){
			 long avant = System.currentTimeMillis();
			 p.ajout(word);
			 long après = System.currentTimeMillis();
			 long écoulé = après - avant;
	         if (écoulé >= 0) {
	            temps_total += écoulé;
	         }
		 }
		 moyenne = temps_total / (double) NTESTS;
		 return  moyenne;	
	}
	//ajout d'un mot dans un arbre equilibre puis equilibrage
	
	public static double addNewWordHybrideEquilibre(String word, HybridesTries h){
		long temps_total = 0;
		double moyenne ;
		 for(int cpt = 0; cpt < NTESTS; cpt++){
			 long avant = System.currentTimeMillis();
			 Ajout.ajoutStringEquilibrage(word, h);
			 long après = System.currentTimeMillis();
			 long écoulé = après - avant;
	         if (écoulé >= 0) {
	            temps_total += écoulé;
	         }
		 }
		 moyenne = temps_total / (double) NTESTS;
		 return  moyenne;		
	}
	
	public static double deleteWordsHybride(HybridesTries h,ArrayList<String> words){
		long temps_total = 0;
		double moyenne ;
		HybridesTries newh = null;
		//System.out.println(Ajout.liste(h).size());
		
		 for(int cpt = 0; cpt < NTESTS; cpt++){
			 long avant = System.currentTimeMillis();
			 newh = h;
			
			/* System.out.println(Ajout.liste(newh).contains("tragedy"));
			 System.out.println(Ajout.liste(newh).contains("place"));*/
			 for( String word : words){
				 newh = Ajout.supression(newh, word);
			 }
			 long après = System.currentTimeMillis();
			 long écoulé = après - avant;
	         if (écoulé >= 0) {
	            temps_total += écoulé;
	         }
		 }
		 moyenne = temps_total / (double) NTESTS;
		// System.out.println(Ajout.liste(newh).size());
		 return  moyenne;	
		 
	}
	
	
	public static double deleteWordsPatricia(PatriciaTrie p,ArrayList<String> words) 
			throws CloneNotSupportedException{
		long temps_total = 0;
		double moyenne ;
		PatriciaTrie newp ;
		 for(int cpt = 0; cpt < NTESTS; cpt++){
			 newp = p;
			 long avant = System.currentTimeMillis();
			 for( String word : words){
				 newp.suppression(word);
			 }
			 long après = System.currentTimeMillis();
			 long écoulé = après - avant;
	         if (écoulé >= 0) {
	            temps_total += écoulé;
	         }
		 }
		 moyenne = temps_total / (double) NTESTS;
		 return  moyenne;	
	}
	//h un arbre equilibre
	//apres chaque supression, reequilibrage
	public static double deleteWordsHybrideEquilibre(HybridesTries h,
			ArrayList<String> words){
		long temps_total = 0;
		double moyenne ;
		 for(int cpt = 0; cpt < NTESTS; cpt++){
			 long avant = System.currentTimeMillis();
			 HybridesTries newh = h;
			 for( String word : words){
				 newh = Ajout.supressionEqulibrage(newh, word);
			 }
			 long après = System.currentTimeMillis();
			 long écoulé = après - avant;
	         if (écoulé >= 0) {
	            temps_total += écoulé;
	         }
		 }
		 moyenne = temps_total / (double) NTESTS;
		 return  moyenne;	
	}
	
	public static double searchWord(HybridesTries h,String word){
		
		long temps_total = 0;
		double moyenne ;
		 for(int cpt = 0; cpt < NTESTS; cpt++){
			 long avant = System.currentTimeMillis();
			  Ajout.recherche(h, word);
			 long après = System.currentTimeMillis();
			 long écoulé = après - avant;
	         if (écoulé >= 0) {
	            temps_total += écoulé;
	         }
		 }
		 moyenne = temps_total / (double) NTESTS;
		 return  moyenne;	
		
	}
	
	public static double searchWord(PatriciaTrie p,String word){
		
		long temps_total = 0;
		double moyenne ;
		 for(int cpt = 0; cpt < NTESTS; cpt++){
			 long avant = System.currentTimeMillis();
			  p.recherche(word);
			 long après = System.currentTimeMillis();
			 long écoulé = après - avant;
	         if (écoulé >= 0) {
	            temps_total += écoulé;
	         }
		 }
		 moyenne = temps_total / (double) NTESTS;
		 return  moyenne;	
		
	}
	
	
	//temps de construction de la liste trié par ordre alphabetique
	public static double listHybride(HybridesTries h){
		long temps_total = 0;
		double moyenne ;
		 for(int cpt = 0; cpt < NTESTS; cpt++){
			 long avant = System.currentTimeMillis();
			  Ajout.liste(h);
			 long après = System.currentTimeMillis();
			 long écoulé = après - avant;
	         if (écoulé >= 0) {
	            temps_total += écoulé;
	         }
		 }
		 moyenne = temps_total / (double) NTESTS;
		 return  moyenne;	
		
	}
	
	public static double listPatricia(PatriciaTrie p){
		long temps_total = 0;
		double moyenne ;
		 for(int cpt = 0; cpt < NTESTS; cpt++){
			 long avant = System.currentTimeMillis();
			  p.listeMots();
			 long après = System.currentTimeMillis();
			 long écoulé = après - avant;
	         if (écoulé >= 0) {
	            temps_total += écoulé;
	         }
		 }
		 moyenne = temps_total / (double) NTESTS;
		 return  moyenne;	
		
	}
	
	public static double equilibrage(HybridesTries h){
		long temps_total = 0;
		double moyenne ;
		 for(int cpt = 0; cpt < NTESTS; cpt++){
			 long avant = System.currentTimeMillis();
			  Ajout.liste(h);
			 long après = System.currentTimeMillis();
			 long écoulé = après - avant;
	         if (écoulé >= 0) {
	            temps_total += écoulé;
	         }
		 }
		 moyenne = temps_total / (double) NTESTS;
		 return  moyenne;	
		
	}
	
	

}
