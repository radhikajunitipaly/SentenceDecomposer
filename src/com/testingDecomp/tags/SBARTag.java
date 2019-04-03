package com.testingDecomp.tags;

import com.testingDecomp.core.*;

public class SBARTag extends DecompSentence {
	
	public SBARTag() {
		super();
	}
	
	@Override
	public void processTag() {
		String top = decomposerProcessingPartsMap.get("top");
		String element = decomposerProcessingPartsMap.get("element");
		String originalSubject = decomposerProcessingPartsMap.get("originalSubject");
		String subject = decomposerProcessingPartsMap.get("subject");
		stack.isEmpty();
		if (top.indexOf("(S")>=0 || top.indexOf("(SBAR")>=0) {
			
				
	        // a sentence is in element
	      	if(!originalSubject.equals("") && !element.contains(originalSubject) && !element.equals("")) {
	      		//if(!(originalSubject.contains("has") && element.contains("has"))) {
	      			element = subject+ " " + element;
	      		}
	      	if(!element.contains(".") && !element.equals("")) {
	      		element=element+".";
		        element = element.replace(" .", ".");
		        element = element.replace(" ,", ",");
		        System.out.println(element);
	      	}
	      	
	       // with next input token
	      	if(!element.equals("")) 
	      		decomposerProcessingPartsMap.put("element", element);
	        decomposerProcessingPartsMap.put("continueWhileLoop", "true");
	        decomposerProcessingPartsMap.put("tagProcessed", "true");
        }
	}

}
