package com.testingDecomp.tags;

import com.testingDecomp.core.*;

public class VBNTag extends DecompSentence {
	
	public VBNTag() {
		super();
	}
	
	@Override
	public void processTag() {
		String top = decomposerProcessingPartsMap.get("top");
		String element = decomposerProcessingPartsMap.get("element");
		String originalSubject = decomposerProcessingPartsMap.get("originalSubject");
		String vbz = decomposerProcessingPartsMap.get("vbz");
		String vbn = decomposerProcessingPartsMap.get("vbn");
		if (top.indexOf("(VBN")>=0 && originalSubject!=null && !originalSubject.equals("") && !vbz.equals("") 
				&& vbn.equals("") && element!=null && !element.equals("")) {
            // a VBN is in element
			decomposerProcessingPartsMap.put("subject", decomposerProcessingPartsMap.get("subject") + " "+ element); 
			decomposerProcessingPartsMap.put("vbn", element);
	        decomposerProcessingPartsMap.put("continueWhileLoop", "true");
        }
	}

}
