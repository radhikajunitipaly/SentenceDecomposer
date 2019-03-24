package com.testingDecomp.tags;

import com.testingDecomp.core.*;
import java.util.HashMap;
import java.util.Map;

public class VBZTag extends DecompSentence {
	
	Map<String, String> decomposerProcessingPartsMap = new HashMap<String, String>();
	
	public VBZTag(Map<String, String> decomposerProcessingPartsMap) {
		this.decomposerProcessingPartsMap = decomposerProcessingPartsMap;
	}
	
	public Map<String, String> decompose() {
		String top = decomposerProcessingPartsMap.get("top");
		String element = decomposerProcessingPartsMap.get("element");
		String originalSubject = decomposerProcessingPartsMap.get("originalSubject");
		String vbz = decomposerProcessingPartsMap.get("vbz");
		if (top.indexOf("(VBZ")>=0 && !originalSubject.equals("") && vbz.equals("") && !element.equals("")) {
            // a VBZ is in element
      	    decomposerProcessingPartsMap.put("subject", decomposerProcessingPartsMap.get("subject") + " "+ element);
      	    decomposerProcessingPartsMap.put("vbz", element);
	        decomposerProcessingPartsMap.put("continueWhileLoop", "false");
         }
	    return decomposerProcessingPartsMap;
	}

}
