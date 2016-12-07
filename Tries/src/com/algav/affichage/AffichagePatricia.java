package com.algav.affichage;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.algav.patricia.PatriciaTrie;
import com.algav.patricia.TestPatricia;

public class AffichagePatricia {

	public static void afficherPat(PatriciaTrie p, String filename){
		
	}
	
	public static String fileHeader(String graphName){
		return ("digraph " + graphName + " {\n" + "node [shape=record];" );
	}
	
	public static String fileFooter(){
		return "}";
	}
	
	public static String startLine(int i){
		String output = new String();
		output = output + "patricia"+i+ " [label=\"" ;
		return output;
	}
	
	public static String endLine(){
		return "\"];";
	}
	
	public static String printCase(String content, int i){
		return "<f"+i+"> " + content + "|" ;
	}
	public static String printLastCase(String content, int i){
		return "<f"+i+"> " + content + "\"];";
	}
	
	public static String printNode(PatriciaTrie n, int i){
		String output = new String();
		output = output + startLine(i);
		for (int k = 0; k < n.getSize()-1; ++k){
			if (n.getCase(k)!=null)
				output = output + printCase(n.getWord(k),k);
		}
		output = output + printLastCase(n.getWord(n.getSize()-1),n.getSize()-1);
		return output;
	}
	public static void main(String[] str){
		TestPatricia t = new TestPatricia("./shakespeare/test.txt");

		    // Convert the string to a
		    // byte array.
		    String s = printNode((PatriciaTrie)t.getPatriciaTrie(), 0);
		    byte data[] = s.getBytes();
		    Path p = Paths.get("./test.txt");

		    try (OutputStream out = new BufferedOutputStream(
		      Files.newOutputStream(p))) {
		      out.write(data, 0, data.length);
		    } catch (IOException x) {
		      System.err.println(x);
		    }
		  }
		//System.out.println(fileHeader("test"));
		//System.out.println(printNode((PatriciaTrie)t.getPatriciaTrie(), 0));
		/*System.out.println(startLine(0) + 
				printCase("a",  0) + printCase( "b", 1) + 
				printLastCase( "c",  2) + endLine());*/
		//System.out.println(fileFooter());
	}

