package com.algav;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Scanner;

import com.algav.HybridesTries.Ajout;
import com.algav.HybridesTries.HybridesTries;
import com.algav.patricia.IPatriciaTrie;
import com.algav.patricia.PatriciaTrie;

public class ExtractFiles {
	
	public static IPatriciaTrie constructPATtrie(String filename) throws FileNotFoundException{
		InputStream oeuvre = new FileInputStream(filename);
		Scanner scanner = new Scanner(oeuvre).useDelimiter("\n");

		IPatriciaTrie patricia = new PatriciaTrie();
		ArrayList<String> test = new ArrayList<String>();
		//String inputWord;
		while(scanner.hasNext()){
			patricia.ajout(scanner.next());
			test.add(scanner.next());
		}
		test.sort(null);
		System.out.println((test).toString());
		System.out.println("TESTING: " + test.equals(patricia.listeMots()));
		return patricia;
	}
	
	public static HybridesTries constructHYBtrie(String filename) throws FileNotFoundException{
		InputStream oeuvre = new FileInputStream(filename);
		Scanner scanner = new Scanner(oeuvre).useDelimiter("\n");

		HybridesTries hybride = new HybridesTries();
		while(scanner.hasNext()){
			Ajout.ajoutString(scanner.next(),hybride);
			Ajout.ajoutString("haha",hybride);

		}
		return hybride;
	}
	
	public static void main (String[] arg){
		try{
			
			IPatriciaTrie p = constructPATtrie("./shakespeare/1henryiv.txt");
			HybridesTries h = constructHYBtrie("./shakespeare/1henryiv.txt");
			System.out.println("patricia: " + p.listeMots().toString());
			System.out.println("hybride:" + Ajout.liste(h).toString());
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return;
		}
	}
}
