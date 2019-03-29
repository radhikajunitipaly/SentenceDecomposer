package com.testingDecomp.tags;

import com.testingDecomp.core.*;

public class STag extends DecompSentence {
	
	public STag() {
		super();
	}
	
	public void processTag() {
		String top = decomposerProcessingPartsMap.get("top");
		String element = decomposerProcessingPartsMap.get("element");
		String originalSubject = decomposerProcessingPartsMap.get("originalSubject");
		String subject = decomposerProcessingPartsMap.get("subject");
		if (top.indexOf("(S")>=0 || top.indexOf("(SBAR")>=0) {
	        // a sentence is in element
	      	if(!originalSubject.equals("") && !element.contains(originalSubject))
	          	  element = subject+ " " + element;
	      	if(!element.contains("."))
	      		element=element+".";
	        element = element.replace(" .", ".");
	        element = element.replace(" ,", ",");
	        System.out.println(element);
	       // with next input token
	        decomposerProcessingPartsMap.put("element", element);
	        decomposerProcessingPartsMap.put("continueWhileLoop", "true");
	        decomposerProcessingPartsMap.put("tagProcessed", "true");
        }
	}

}
