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
	
	int level=0;
  	static Stack<String> stack=new Stack<String>();
  
  	ClassLoader classLoader = getClass().getClassLoader();	
	private File folder = null;
  
	public void procTree(String fileName){
		BufferedReader br = null;
		DecompSentence tagObject = null;
		
		this.folder = new File(classLoader.getResource(fileName).getFile());
		if(this.folder == null) {
			try {
				throw new FileNotFoundException("Folder " + fileName + " does not exist.");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (final File file : folder.listFiles()) {
			
			if (file.isDirectory()) {
				procTree(file.getName());
			} else {		
				Map<String, DecompSentence> tagObjectMap = new HashMap<>();
				Map<String, String> decomposerProcessingPartsMap = new HashMap<String, String>();
				decomposerProcessingPartsMap = putKeysWithIntialEmptyValues(decomposerProcessingPartsMap);
				try{
					
					String filename = getFileWithRelativePath(folder, file);			
					br = new BufferedReader(new FileReader(new File(filename)));
					//br=new BufferedReader(new FileReader(fileName));
					String line="";
					String tag="";
					List<String> wordsNotUsed = new ArrayList<String>(); 
				    wordsNotUsed.add("-LRB-");
				    wordsNotUsed.add("-RRB-");
				    wordsNotUsed.add("CRS");
				      
				    while ((line=br.readLine())!=null){
			
				        line=line.replace(")"," )");
				        StringTokenizer str=new StringTokenizer(line);
				        while (str.hasMoreTokens()){
				            String token=str.nextToken();
				            if (token.indexOf(")")<0){
				            	stack.push(token);
				            	continue; // with next input token
				            } else { // token is ")"
					            //printStack();
					            String element="";
					            String top="";
					            while (!stack.isEmpty()){
					                top=(String)stack.pop();
					                if (top.indexOf("(")>=0) {
					                    break;
					                }
				              //System.out.println("top="+top);
					                if(!wordsNotUsed.contains(top)) {
					                	element=top+" "+element;
					                	element=element.trim();
					                }
					            }
					            tag = top.substring(1);
					            if(tag.equals("S") || tag.equals("SBAR") || tag.equals("NP") || 
					            		tag.equals("VBZ") || tag.equals("VBN")) {
						            decomposerProcessingPartsMap.put("top", top);
						            decomposerProcessingPartsMap.put("element", element);
						            
						            if(!tagObjectMap.containsKey(tag)) {
						            	tagObject = (DecompSentence) Class.forName("com.testingDecomp.tags."+tag+"Tag").getConstructor(Map.class).
						            			newInstance(decomposerProcessingPartsMap);
						            	tagObjectMap.put(tag, tagObject);
						            } else {
						            	tagObject = tagObjectMap.get(tag);
						            }
						            
						            decomposerProcessingPartsMap = tagObject.decompose();
						            
						            if(decomposerProcessingPartsMap.get("continueWhileLoop") == "true"){
						            	decomposerProcessingPartsMap.put("continueWhileLoop", "false");
						            	continue;
						            }   
					            }
					            //printStack();
					            stack.push(element);
				            }
				       	}
				      }
				  } catch (Exception e) {
					  e.printStackTrace();
				  } finally {
					  if(br != null)
						try {
							br.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
	  System.out.println("");
	  System.out.println("###Printing stack");
	  System.out.println("");
    for(int i=0; i<stack.size(); i++) {
      System.out.println((String)stack.get(i));
    }
  }
  
  public abstract Map<String, String> decompose(); 
  
  private static String getFileWithRelativePath(final File folder, final File file) {
		return folder + "\\" + file.getName();
	}

}
