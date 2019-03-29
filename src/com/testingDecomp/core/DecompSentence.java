package com.testingDecomp.core;

import java.io.*;
import java.util.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2013</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public abstract class DecompSentence {
	
  	static Stack<String> stack=new Stack<String>();
  	protected static Map<String, String> decomposerProcessingPartsMap = 
  			new HashMap<String, String>();
  	static String fileName = null;
  
	public static void procTree(File folder){
		if(folder == null) {
			try {
				throw new FileNotFoundException("Folder " + folder + " does not exist.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		for (final File file : folder.listFiles()) {
			if (file.isDirectory()) {
				procTree(file);
			} else {		
				decomposerProcessingPartsMap = putKeysWithIntialEmptyValues(decomposerProcessingPartsMap);
				try{
					fileName = getFileWithRelativePath(folder, file);			
				  } catch (Exception e) {
					  e.printStackTrace();
				  }
			}
		}
	}
  
	private static Map<String, String> putKeysWithIntialEmptyValues(
		  Map<String, String> decomposerProcessingPartsMap) {
		decomposerProcessingPartsMap.put("subject", "");
		decomposerProcessingPartsMap.put("originalSubject", "");
        decomposerProcessingPartsMap.put("np", "");
        decomposerProcessingPartsMap.put("vbz", "");
        decomposerProcessingPartsMap.put("vbn", "");
        decomposerProcessingPartsMap.put("continueWhileLoop", "false");
      	decomposerProcessingPartsMap.put("tagProcessed", "false");
      	return decomposerProcessingPartsMap;
	}

	void printStack() {
		for(int i=0; i<stack.size(); i++) {
			System.out.println((String)stack.get(i));
		}
	}
  
	public abstract void processTag(); 
  
	private static String getFileWithRelativePath(final File folder, final File file) {
		return folder + "\\" + file.getName();
	}
}
