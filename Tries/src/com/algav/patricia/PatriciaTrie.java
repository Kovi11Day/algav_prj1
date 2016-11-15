package com.algav.patricia;

import java.awt.List;
import java.util.LinkedList;

import com.algav.patricia.exceptions.OutOfCharacterSetException;
import com.algav.patricia.exceptions.PATStringException;
import com.algav.patricia.exceptions.PatriciaException;

import static com.algav.patricia.string.StringManipulation.*;

public class PatriciaTrie implements IPatriciaTrie{

	private IPATCase[] patTrie;
	private static final int SIZE = 128;
	
	//creer un noeud vide et y ajouter un seul mot
	public PatriciaTrie(String mot){
		this.patTrie = new IPATCase[SIZE];
		if (mot.length() == 0 || mot == null){
			//return;
			throw new PatriciaException("patricia trie must contain "
					+ "at least one word");
		}
		if (containsEpsilon(mot)){
			this.patTrie[asciiFirst(mot)]
					= new PATCase (mot);
		}else{
			stringValid(mot);
			this.patTrie[asciiFirst(mot)]
					= new PATCase (concatEpsilon(mot));
		}
	}
	/*
	public PatriciaTrie(){
		this.patTrie = new IPATCase[SIZE];
		
	}
	*/
	public String getWord (int i){
		return this.patTrie[i].getWord();
	}
	
	public void setWord(int i, String word){
		if (this.patTrie[i] == null){
			this.patTrie[i] = new PATCase(word);
		}else{
			this.patTrie[i].setWord(word);
		}
	}
	
	public IPatriciaTrie getSon (int i){
		//if son == null create case
		return this.patTrie[i].getSon();
	}
	
	public void setSon (int i, IPatriciaTrie node){
		if (this.patTrie[i] == null){
			throw new PatriciaException("Set son to non existing index");
		}
		this.patTrie[i].setSon(node);
	}
	
	public IPATCase getCase (int i){
		return this.patTrie[i];
	}
	
	public void setCase (int i, String s){
		this.patTrie[i] = new PATCase(s);
	}
	
	
	/////patricia methods
	//ajout d'un mot dans un patricia trie
	//note: le patricia trie ne peut pas etre vide
	public void ajout(String word){
		if (word == null || word.length() == 0){
			throw new PatriciaException("specify word to insert");
		}
		sysAjout(concatEpsilon(stringValid(word)));
	}
	
	public void sysAjout (String word){ 
		
		//cas1
		if (word == null || word.length() == 0 ){
			System.out.println("cas1");
			return;
		}
		
		//cas2
		if (this.getCase(asciiFirst(word)) == null){
			System.out.println("cas2");
			this.setCase(asciiFirst(word), word);
			return;
		}
		
		//cas3
		//mot deja existant
		if (word.equals((String) this.getWord(asciiFirst(word)))){
			System.out.println("cas3");
			return;
		}
		//cas4
		//mot dans case prefixe de mot ajouter
		if (word.startsWith(this.getWord(asciiFirst(word)))){
			System.out.println("cas4");
			String rest = rest(word, 
						prefixe(word,this.getWord(asciiFirst(word))));
			
			//ajouter reste du mot dans fils
			(this.getSon(asciiFirst(word))).sysAjout(rest);
		}
		
		//cas5
		else{
			System.out.println("cas5");
			String prefixe = prefixe(word, this.getWord(asciiFirst(word)));
			String restWordInput = rest(word, prefixe);
			String restWordInDic = rest(this.getWord(asciiFirst(word)),prefixe);
		
			IPatriciaTrie newNode = new PatriciaTrie(restWordInDic);
			
			//breaking up struct to accomodate new word
			this.setWord(asciiFirst(prefixe), prefixe);
			//newNode.sysAjout(restWordInDic);
			newNode.setWord(asciiFirst(restWordInput), restWordInput);
			newNode.setSon(asciiFirst(restWordInDic), this.getSon(asciiFirst(prefixe)));
			this.setSon(asciiFirst(prefixe), newNode);
		
			
		}
			
	}

	public LinkedList<String> listeMots(){
		LinkedList<String> liste = new LinkedList<String>();
		return sysListeMots(liste, "");
	}
	
	public LinkedList<String> sysListeMots(LinkedList<String> liste, String prefixe){
		for (int i = 0; i < SIZE; ++i){
			String newPrefixe = "";
			newPrefixe = newPrefixe.concat(prefixe);
			if (this.getCase(i) != null){
				if (containsEpsilon(this.getWord(i))){
					String truncEps = truncLast(this.getWord(i));
					newPrefixe = newPrefixe.concat(truncEps);
					liste.add(newPrefixe);
				}else{
					newPrefixe = newPrefixe.concat(this.getWord(i));
				}
				if (this.getSon(i) != null)
					this.getSon(i).sysListeMots(liste, newPrefixe);
			}
		}
		return liste;	
	}
	
	public int comptageMots(){
		int nb = 0;
		for (int i = 0; i < SIZE; ++i){
			if (this.getCase(i) != null){
			if (containsEpsilon(this.getWord(i)))
				nb++;
			
			if (this.getSon(i) != null)
				nb = nb + this.getSon(i).comptageMots();
			}
		}
		return nb;
	}
	
	public int comptageNil(){
		int nb = 0;
		for (int i = 0; i < SIZE; ++i){
			if (this.getCase(i) == null)
				++nb;
			else{
				if (this.getSon(i) != null)
					nb = nb + this.getSon(i).comptageNil();
			}	
		}
		return nb;
	}
	
	public boolean recherche(String word){
		if (word == null || word.length() == 0){
			throw new PatriciaException("enter word to search for");
		}
		return (sysRecherche(concatEpsilon(word)));
	}
	
	public boolean sysRecherche(String word){
		
		int i = asciiFirst(word);
		//System.out.println(word.length());
		//cas1
		//la case a l'indice i est vide
		if (this.getCase(i) == null){
			System.out.println("cas1");
			return false;
		}
	
		//cas2
		if (this.getWord(i).equals(word)){
			System.out.println("cas2");
			return true;
		}
		
		//cas3
		//contenu de la case i est pas un prefixe du mot
		if (!(prefixe(this.getWord(i),word).equals(this.getWord(i)))){
			System.out.println("cas3");
			return false;
		}
			
		//cas4
		if (this.getSon(i) == null){
			System.out.println("cas4");
			return false;
		}
		//cas5
		else{
			System.out.println("cas5");
			//System.out.println(rest(word, this.getWord(i)));
			return this.getSon(i).sysRecherche(rest(word, this.getWord(i)));
		}
		
	}
	
	public int hauteur(){
		int maxh = 0;
		int h;
		for (int i = 0; i < SIZE; ++i){
			h = 0;
			if (this.getCase(i) != null && this.getSon(i)!=null)
				h = 1 + this.getSon(i).hauteur();
			
			maxh = Math.max(maxh, h);
		}
		return maxh;
	}
	
	public static void main(String[] s){
		IPatriciaTrie dic = new PatriciaTrie("at");
		dic.ajout("at");
		dic.ajout("tac");
		dic.ajout("cgga");
		dic.ajout("tac");
		dic.ajout("tacg");
		dic.ajout("tacgh");
		dic.ajout("tacgi");
		dic.ajout("tacgil");
		dic.ajout("tacb");
		dic.ajout("cggb");
		LinkedList<String> l = dic.listeMots();
		
		//IPatriciaTrie dic2 = new PatriciaTrie("he2");
		//String input = 
		//"A quel genial professeur de dactylographie";
		//"sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier de la machine a ecrire ?";
		System.out.println("nb mots: " + dic.comptageMots());
		System.out.println("nb nil: " + dic.comptageNil());
		System.out.println(l.toString());
		System.out.println("starting search.................");
		System.out.println("ended");
		System.out.println("hauteur: " + dic.hauteur());
	}
	
	
	
}
