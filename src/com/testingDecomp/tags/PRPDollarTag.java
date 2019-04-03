package com.testingDecomp.tags;

import com.testingDecomp.core.*;

public class PRPDollarTag extends DecompSentence {
	
	public PRPDollarTag() {
		super();
	}
	
	@Override
	public void processTag() {
		String top = decomposerProcessingPartsMap.get("top");
		String element = decomposerProcessingPartsMap.get("element");
		String originalSubject = decomposerProcessingPartsMap.get("originalSubject");
		if(prpAlreadyReplaced) {
			didNothingSoAddElementToStack = true;
		}
		if (top.indexOf("(PRP$")>=0 && originalSubject!=null && !originalSubject.equals("") && element!=null && !element.equals("")) {
            // a NP is in element
			//stack.push(")");
			//stack.push("(S");
			String tempElement = "";
 			String tempTop = "";
 			int count = 1;
 			while (!stack.isEmpty()) {
 				tempTop = stack.pop();
 				if(tempTop.indexOf(",")>=0) {
 					break;
 				}
 				tempElement=tempElement+" "+tempTop;
 				count ++;
 			}	
 			stack.push(tempElement);
			stack.push(originalSubject);
			decomposerProcessingPartsMap.put("element", ""); 			
	        prpAlreadyReplaced = true;
        }
	}
}
