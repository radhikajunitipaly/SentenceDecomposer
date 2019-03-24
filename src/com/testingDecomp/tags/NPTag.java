package com.testingDecomp.tags;

import com.testingDecomp.core.*;
import java.util.HashMap;
import java.util.Map;

public class NPTag extends DecompSentence {
	
	Map<String, String> decomposerProcessingPartsMap = new HashMap<String, String>();
	
	public NPTag(Map<String, String> decomposerProcessingPartsMap) {
		this.decomposerProcessingPartsMap = decomposerProcessingPartsMap;
	}
	
	public Map<String, String> decompose() {
		String top = decomposerProcessingPartsMap.get("top");
		String element = decomposerProcessingPartsMap.get("element");
		String subject = decomposerProcessingPartsMap.get("subject");
		if (top.indexOf("(NP")>=0 && subject.equals("") && !element.equals("")) {
            // a NP is in element
			decomposerProcessingPartsMap.put("subject",  element);
			decomposerProcessingPartsMap.put("originalSubject",  element);
	        decomposerProcessingPartsMap.put("continueWhileLoop", "false");
        }
	    return decomposerProcessingPartsMap;
	}
}
