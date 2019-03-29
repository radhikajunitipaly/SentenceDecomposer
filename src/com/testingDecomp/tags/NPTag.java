package com.testingDecomp.tags;

import com.testingDecomp.core.*;

public class NPTag extends DecompSentence {
	
	public NPTag() {
		super();
	}
	
	public void processTag() {
		String top = decomposerProcessingPartsMap.get("top");
		String element = decomposerProcessingPartsMap.get("element");
		String subject = decomposerProcessingPartsMap.get("subject");
		if (top.indexOf("(NP")>=0 && subject!=null && subject.equals("") && element!=null && !element.equals("")) {
            // a NP is in element
			decomposerProcessingPartsMap.put("subject",  element);
			decomposerProcessingPartsMap.put("originalSubject",  element);
	        decomposerProcessingPartsMap.put("continueWhileLoop", "false");
        }
	}
}
