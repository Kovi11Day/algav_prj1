package com.algav.patricia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.algav.HybridesTries.Ajout;
import com.algav.HybridesTries.HybridesTries;

public class TestPatricia {
	public static void testConstruction(String filename) throws FileNotFoundException{
		//test create, listMots, remove
		//create patricia and hybride, add words, use listeMots() compare lists with expetedResult

		InputStream oeuvre = new FileInputStream(filename);
		Scanner scanner = new Scanner(oeuvre).useDelimiter("\n");
		
		ArrayList<String> expectedResult = new ArrayList<String>();
		IPatriciaTrie p = new PatriciaTrie();
		String input = null;
		while(scanner.hasNext()){
			input = scanner.next();
			if (!(expectedResult.contains(input))){
				System.out.println(input);

			expectedResult.add(input);
			expectedResult.sort(null);
			p.ajout(input);
			}
			if ((!(p.listeMots().equals(expectedResult)) || input.equals("xx"))){
				//System.out.println("expected list: " + (expectedResult).toString());
				//System.out.println("patricia list: " + (p.listeMots()).toString());
				return;
			}
		}
		System.out.println("SUCCESSFUL :D");

		//System.out.println("expected list: " + (expectedResult).toString());
		//System.out.println("expected size: " + expectedResult.size());
		//expectedResult.remove("about");
		//System.out.println("expected size: " + expectedResult.size());

	}
	
	public static void testSuppression(String filename) throws FileNotFoundException{
		InputStream oeuvre = new FileInputStream(filename);
		Scanner scanner = new Scanner(oeuvre).useDelimiter("\n");
		
		LinkedList<String> expectedResult = new LinkedList<String>();
		IPatriciaTrie p = new PatriciaTrie();
		String input = null;
		while(scanner.hasNext()){
			input = scanner.next();
			if (!(expectedResult.contains(input))){
				expectedResult.add(input);
				p.ajout(input);
			}
		}
		
		expectedResult.sort(null);
		LinkedList<String> patriciaList = p.listeMots();

		Random random = new Random();
		int len = expectedResult.size();
		int index = 0;
		int limit = len;
		
		for (int i = 0; i < len-1; ++i){
			index = random.nextInt(limit);
			System.out.println(expectedResult.get(index));
			p.suppression(expectedResult.get(index));
			expectedResult.remove(index);
			patriciaList = p.listeMots();
			limit--;
			if (!(expectedResult.equals(patriciaList))){
				System.out.println("expected list: " + (expectedResult).toString());
				System.out.println("patricia list: " + (p.listeMots()).toString());
				return;
			}
		}
		scanner.close();
		System.out.println("expected list: " + (expectedResult).toString());
		System.out.println("patricia list: " + (p.listeMots()).toString());
		System.out.println("SUCCESSFUL :D");
	}
	
	
	public static void testFusion(String filename) throws FileNotFoundException{
		InputStream oeuvre = new FileInputStream(filename);
		Scanner scanner = new Scanner(oeuvre).useDelimiter("\n");
		
		LinkedList<String> expectedResult = new LinkedList<String>();
		LinkedList<String> noDoubles = new LinkedList<String>();

		IPatriciaTrie p1 = new PatriciaTrie();
		IPatriciaTrie p2 = new PatriciaTrie();
		IPatriciaTrie control = new PatriciaTrie();

		String input = null;
		
		while(scanner.hasNext()){
			input = scanner.next();
			expectedResult.add(input);
			
			if (!(noDoubles.contains(input))){
				noDoubles.add(input);
			}
		}
		
		int len = expectedResult.size();

		for(int i = 0; i < (int)Math.floor(len); ++i){
			p1.ajout(expectedResult.get(i));
			control.ajout(expectedResult.get(i));
		}
		for(int i = (int)Math.floor(len); i < len; ++i){
			p2.ajout(expectedResult.get(i));
			control.ajout(expectedResult.get(i));
		}
		
		IPatriciaTrie p = p1.fusion(p2);
		
		LinkedList<String> patriciaList = p.listeMots();
		LinkedList<String> controlList = control.listeMots();

		noDoubles.sort(null);

		if (!(controlList.equals(noDoubles))){
			System.out.println("FATAL: CONTROL WRONG");
			return;
		}
		if (patriciaList.equals(noDoubles)){
			System.out.println("SUCCESSFUL :D");
		}else{
			System.out.println("expected list: " + (noDoubles).toString());
			System.out.println("patricia list: " + (p.listeMots()).toString());
		}
		
		scanner.close();
	
	}
	
	
	public static void main (String[] args){
		try{
			
			//expected result
			//testConstruction("./shakespeare/1henryiv.txt");
			//testSuppression("./shakespeare/macbeth.txt");
			testFusion("./shakespeare/macbeth.txt");
		}catch(Exception e){
			System.out.println(e.getMessage());
			return;
		}
	}
}

