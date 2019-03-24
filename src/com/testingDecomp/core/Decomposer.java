package com.testingDecomp.core;

import java.util.Map;

public class Decomposer extends DecompSentence {

	@Override
	public Map<String, String> decompose() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args){
	    //DecompSentence ds=new DecompSentence();
		// procTree(new DecompSentence("crs.tree").file);
		  final String fileoriginal = "test";
		  Decomposer decomposer = new Decomposer();
		  decomposer.procTree(fileoriginal);
	  }
	

}
