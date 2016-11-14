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
		
		this.patTrie[asciiFirst(mot)] 
				= new PATCase (concatEpsilon(mot));
	}
	
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
			return;
		}
		sysAjout(concatEpsilon(stringValid(word)));
	}
	
	public void sysAjout (String word){ 
		//cas1
		/*
		if (this == null){
			System.out.println("patricia trie is null");
			return;
		}
		*/
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
			//get rest of word
			System.out.println("cas4");
			
			String rest = rest(word, 
						prefixe(word,this.getWord(asciiFirst(word))));
			System.out.println(this.getWord(asciiFirst(word)));
			System.out.println(word);
			System.out.println(prefixe(word,this.getWord(asciiFirst(word))));
			System.out.println("rest: " + rest);
			//ajouter reste du mot dans fils
			(this.getSon(asciiFirst(word))).sysAjout(rest);
		}
		
		//cas5
		else{
			System.out.println("cas5");
			String prefixe = prefixe(word, this.getWord(asciiFirst(word)));
			String restWordInput = rest(word, prefixe);
			String restWordInDic = rest(this.getWord(asciiFirst(word)),prefixe);
			/*
			System.out.println(prefixe);
			System.out.println("restWordInput: " + restWordInput);
			System.out.println("restWordInDic: " +restWordInDic);
			*/
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
	
	public static void main(String[] s){
		IPatriciaTrie dic = new PatriciaTrie("aatg");
		dic.ajout("at");
		dic.ajout("tac");
		dic.ajout("cgga");
		dic.ajout("tac");
		dic.ajout("tacg");
		dic.ajout("tacb");
		dic.ajout("cggb");
		dic.ajout("");
		LinkedList<String> l = dic.listeMots();
		System.out.println(l.toString());
	}
	
	
}
