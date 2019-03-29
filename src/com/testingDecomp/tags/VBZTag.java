package com.testingDecomp.tags;

import com.testingDecomp.core.*;

public class VBZTag extends DecompSentence {
	
	public VBZTag() {
		super();
	}
	
	public void processTag() {
		String top = decomposerProcessingPartsMap.get("top");
		String element = decomposerProcessingPartsMap.get("element");
		String originalSubject = decomposerProcessingPartsMap.get("originalSubject");
		String vbz = decomposerProcessingPartsMap.get("vbz");
		if (top.indexOf("(VBZ")>=0 && vbz.equals("") &&	originalSubject!=null && !originalSubject.equals("") 
				&& element!=null && !element.equals("")) {
            // a VBZ is in element
      	    decomposerProcessingPartsMap.put("subject", decomposerProcessingPartsMap.get("subject") + " "+ element);
      	    decomposerProcessingPartsMap.put("vbz", element);
	        decomposerProcessingPartsMap.put("continueWhileLoop", "false");
         }
	}

}
