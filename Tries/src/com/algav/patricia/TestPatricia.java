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
	
	public TestPatricia(String filename){
		this.filename = filename;
		try{
		InputStream oeuvre = new FileInputStream(filename);
		Scanner scanner = new Scanner(oeuvre).useDelimiter("\n");
		
		String input = null;
		while(scanner.hasNext()){
			input = scanner.next();
			rawfileList.add(input);
			if (!(expectedResult.contains(input))){
				expectedResult.add(input);
				noDoubles.add(input);
			}
		}
		expectedResult.sort(null);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return;
		}
		this.inputSize = rawfileList.size();
		for (int i = 0; i < getInputSize(); ++i){
			this.patriciaTrie.ajout(this.getRawFileList().get(i));
		}
	}
	public int getInputSize(){
		return inputSize;
	}
	public LinkedList<String> getRawFileList() {
		return rawfileList;
	}

	private String filename;
	private LinkedList<String> rawfileList = new LinkedList<String>();
	private LinkedList<String> noDoubles = new LinkedList<String>();//no doubles
	private LinkedList<String> expectedResult = new LinkedList<String>(); //sorted and no doubles
	private int inputSize;
	private IPatriciaTrie patriciaTrie= new PatriciaTrie();
	
	public LinkedList<String> getExpectedResult() {
		return expectedResult;
	}
	public LinkedList<String> getNoDoubles() {
		return noDoubles;
	}
	public IPatriciaTrie getPatriciaTrie(){
		return patriciaTrie;
	}
	
	public boolean testConstruction(){
		IPatriciaTrie p = new PatriciaTrie();
		for (int i = 0; i < getInputSize(); ++i){
			p.ajout(this.getRawFileList().get(i));
		}
		return p.listeMots().equals(this.getExpectedResult());
	}
	
	public boolean testSuppression(){
		IPatriciaTrie p = new PatriciaTrie();
		
		LinkedList<String> noDbl = (LinkedList<String>)this.getNoDoubles().clone();
		LinkedList<String> sorted = null;

		for (int i = 0; i < this.getRawFileList().size(); ++i){
			p.ajout(this.getRawFileList().get(i));
		}
		
		String nonExistant = noDbl.get(0);
		
		for (int i = 0; i < this.getNoDoubles().size() - 1; ++i){
			
			p.suppression(noDbl.get(0));
			noDbl.remove(0);
			sorted = (LinkedList<String>)noDbl.clone();
			sorted.sort(null);
			if (!(p.listeMots().equals(sorted))){
				System.out.println("expected list: " + (expectedResult).toString());
				System.out.println("patricia list: " + (p.listeMots()).toString());
				return false;
			}
		}
		return true;
	}

	public boolean testFusion() {
		IPatriciaTrie p1 = new PatriciaTrie();
		IPatriciaTrie p2 = new PatriciaTrie();
		IPatriciaTrie control = new PatriciaTrie();

		for(int i = 0; i < (int)Math.floor(inputSize/2); ++i){
			p1.ajout(this.getRawFileList().get(i));
			control.ajout(this.getRawFileList().get(i));
		}
		for(int i = (int)Math.floor(inputSize/2 + 1); i < inputSize; ++i){
			p2.ajout(this.getRawFileList().get(i));
			control.ajout(this.getRawFileList().get(i));
		}

		IPatriciaTrie p = p2.fusion(p1);
		
		if (p.listeMots().equals(this.expectedResult)){
			return true;
		}else{
			System.out.println("fusion   list: " + (p.listeMots()).toString());
			System.out.println("expected list: " + (this.expectedResult).toString());
			return false;
		}	
		
		
	}
	
	
	public static void main (String[] args){
		
		TestPatricia testeur = new TestPatricia("./shakespeare/allswell.txt");
		System.out.println("verdict testConstruction: "+ testeur.testConstruction());
		System.out.println("verdict testSuppression: "+testeur.testSuppression());
		System.out.println("verdict testFusion: " +testeur.testFusion());
		
	}
}

