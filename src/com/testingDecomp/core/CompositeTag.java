package com.testingDecomp.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class CompositeTag extends DecompSentence {

	static Map<String, DecompSentence> tagObjectMap = new HashMap<>();
	
	public CompositeTag() {
		super();
	}
	
	@Override
	public void processTag() {
		
		List<String> wordsNotUsed = new ArrayList<String>(); 
		wordsNotUsed.add("-LRB-");
	    wordsNotUsed.add("-RRB-");
	    wordsNotUsed.add("CRS");
	    
		DecompSentence tagObject = null;
		String line = "";
		String tag = "";
		StringTokenizer stringTokens;
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(new File(fileName)));
			while ((line=br.readLine())!=null){
				
		        line=line.replace(")"," )");
		        stringTokens=new StringTokenizer(line);
		        while (stringTokens.hasMoreTokens()){
		            String token=stringTokens.nextToken();
		            if (token.indexOf(")")<0){
		            	stack.push(token);
		            	continue; // with next input token
		            } else { // token is ")"
			            //printStack();
			            String element="";
			            String top="";
			            while (!stack.isEmpty()){
			                top=stack.pop();
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
			            if(tag.equals("PRP$")) 
			            	tag = "PRPDollar";
			            if(tag.equals("S") || tag.equals("CC") || tag.equals("SBAR") || tag.equals("NP") || 
			            		tag.equals("VBZ") || tag.equals("VBN") || tag.contains("PRPDollar") || tag.contains("IN")) {
				            decomposerProcessingPartsMap.put("top", top);
				            decomposerProcessingPartsMap.put("element", element);
				            
				            tagObject = get(tag);
				            
				            if(tagObject != null)
				            	tagObject.processTag();
				            if(!didNothingSoAddElementToStack) {
				            	decomposerProcessingPartsMap.put("continueWhileLoop", "false");
				            	element = decomposerProcessingPartsMap.get("element");
				            }
				            didNothingSoAddElementToStack = false;
				            if(decomposerProcessingPartsMap.get("continueWhileLoop") == "true"){
				            	decomposerProcessingPartsMap.put("continueWhileLoop", "false");
				            	continue;
				            }   
			            }
			            if(!element.trim().equals(""))
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
					e.printStackTrace();
				}
		  }
		}
		  
	public DecompSentence get(String tag) {
		
		DecompSentence tagObject = tagObjectMap.get(tag);
		if(tagObject!=null) {
			return tagObject;
		}
		try {
			tagObject = (DecompSentence) Class.
					forName("com.testingDecomp.tags."+tag+"Tag").
					newInstance();
			tagObjectMap.put(tag, tagObject);
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} 
		return tagObject;
	}
  
}
