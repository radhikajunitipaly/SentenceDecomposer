package com.testingDecomp.tags;

import com.testingDecomp.core.*;

public class INTag extends DecompSentence {
	
	public INTag() {
		super();
	}
	
	@Override
	public void processTag() {
		String top = decomposerProcessingPartsMap.get("top");
		String element = decomposerProcessingPartsMap.get("element");
		String subject = decomposerProcessingPartsMap.get("subject");
		if(inAddedToSubject)
			didNothingSoAddElementToStack = true;
		else if (top.indexOf("(IN")>=0 && element!=null && !element.equals("")) {
            // a NP is in element
			/*stack.push(element);
			decomposerProcessingPartsMap.put("subject", element+" "+subject);*/
			inAddedToSubject = true;
			didNothingSoAddElementToStack = true;
			decomposerProcessingPartsMap.put("continueWhileLoop", "true");
        }
	}
}
