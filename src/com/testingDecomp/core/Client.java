package com.testingDecomp.core;

import java.io.File;

public class Client {
	
	ClassLoader classLoader = getClass().getClassLoader();	
	File folder = new File(classLoader.getResource("test").getFile());
	
	 public static void main(String[] args){
		Client client = new Client();
		DecompSentence.procTree(client.folder);
		DecompSentence decompSentence = new CompositeTag();
		decompSentence.processTag();
	 }

}
