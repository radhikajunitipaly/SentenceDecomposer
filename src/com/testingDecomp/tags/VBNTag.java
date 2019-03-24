package com.testingDecomp.tags;

import com.testingDecomp.core.*;
import java.util.HashMap;
import java.util.Map;

public class VBNTag extends DecompSentence {
	
	Map<String, String> decomposerProcessingPartsMap = new HashMap<String, String>();
	
	public VBNTag(Map<String, String> decomposerProcessingPartsMap) {
		this.decomposerProcessingPartsMap = decomposerProcessingPartsMap;
	}
	
	public Map<String, String> decompose() {
		String top = decomposerProcessingPartsMap.get("top");
		String element = decomposerProcessingPartsMap.get("element");
		String originalSubject = decomposerProcessingPartsMap.get("originalSubject");
		String vbz = decomposerProcessingPartsMap.get("vbz");
		String vbn = decomposerProcessingPartsMap.get("vbn");
		if (top.indexOf("(VBN")>=0 && !originalSubject.equals("") && !vbz.equals("") 
				&& vbn.equals("") && !element.equals("")) {
            // a VBN is in element
			decomposerProcessingPartsMap.put("subject", decomposerProcessingPartsMap.get("subject") + " "+ element); 
			decomposerProcessingPartsMap.put("vbn", element);
	        decomposerProcessingPartsMap.put("continueWhileLoop", "true");
        }
	    return decomposerProcessingPartsMap;
	}

}
