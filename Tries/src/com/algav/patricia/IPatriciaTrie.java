package com.algav.patricia;

import java.util.LinkedList;

import com.algav.patricia.string.IPATString;

public interface IPatriciaTrie {
	
	String getWord (int i);
	void setWord(int i, String word);
	IPatriciaTrie getSon (int i);
	IPATCase getCase (int i);
	void setSon (int i, IPatriciaTrie node);
	
	//sys methods remove from interface after testing
	void sysAjout (String word);
	LinkedList<String> sysListeMots(LinkedList<String> liste, String prefixe);

	
	//patricia methods
	public void ajout(String word);
	public LinkedList<String> listeMots();
}
