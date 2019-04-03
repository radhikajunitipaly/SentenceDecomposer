package com.testingDecomp.tags;

import com.testingDecomp.core.*;

public class CCTag extends DecompSentence {
	
	public CCTag() {
		super();
	}
	
	@Override
	public void processTag() {
		String top = decomposerProcessingPartsMap.get("top");
		String element = decomposerProcessingPartsMap.get("element");
		String originalSubject = decomposerProcessingPartsMap.get("originalSubject");
		String subject = decomposerProcessingPartsMap.get("subject");
		if (top.indexOf("(CC")>=0 && originalSubject!=null && !originalSubject.equals("") && element!=null && !element.equals("")) {
			String tempElement = "";
			String tempTop = "";
			while (!stack.isEmpty()) {
				tempTop = stack.pop();
				if(tempTop.indexOf("(")>=0) {
					break;
				}
				tempElement=tempElement+tempTop;
			}	
			stack.push(tempElement);
			//stack.push(")");
			stack.push("(S");
			stack.push(subject);
			decomposerProcessingPartsMap.put("firstPartOfConjunction", tempElement);
			decomposerProcessingPartsMap.put("element", "");
			decomposerProcessingPartsMap.put("continueWhileLoop", "true");
        }
	}
}
