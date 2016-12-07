package com.algav.patricia;

import java.util.ArrayList;
import java.util.LinkedList;


public interface IPatriciaTrie {
	
	String getWord (int i);
	void setWord(int i, String word);
	IPatriciaTrie getSon (int i);
	IPATCase getCase (int i);
	void setSon (int i, IPatriciaTrie node);
	public boolean isLeaf();
	//sys methods remove from interface after testing
	void sysAjout (String word);
	LinkedList<String> sysListeMots(LinkedList<String> liste, String prefixe);
	public boolean sysRecherche(String word);
	//public ArrayList<Integer> sysProfondeurTotal(Integer prof);
	void sysSuppression(String mot);
	//practical ones
	public boolean isEmpty();
	public void deleteCase (int i);
	//patricia methods
	public void ajout(String word);
	public LinkedList<String> listeMots();
	public int comptageMots();
	public int comptageNil();
	public boolean recherche(String word);
	public int hauteur();
	int prefixe(String strPrefixe);
	public double profondeurMoyenne();
	public void suppression(String mot);
	public IPatriciaTrie fusion(IPatriciaTrie p);
	//protected IPATCase[] getPatTrie();
	public int getSize();
	public int getNbCases();
	public IPatriciaTrie clone();
	public int profondeurTotal();
	public int nbFeuilles();
}
