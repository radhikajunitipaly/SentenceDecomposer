package com.testingDecomp.tags;

import com.testingDecomp.core.*;

public class VBZTag extends DecompSentence {
	
	public VBZTag() {
		super();
	}
	
	@Override
	public void processTag() {
		String top = decomposerProcessingPartsMap.get("top");
		String element = decomposerProcessingPartsMap.get("element");
		String originalSubject = decomposerProcessingPartsMap.get("originalSubject");
		String subject = decomposerProcessingPartsMap.get("subject");
		String vbz = decomposerProcessingPartsMap.get("vbz");
		if (top.indexOf("(VBZ")>=0 && vbz.equals("") &&	originalSubject!=null && !originalSubject.equals("") 
				&& element!=null && !element.equals("")) {
            // a VBZ is in element
      	    decomposerProcessingPartsMap.put("subject", decomposerProcessingPartsMap.get("subject") + " "+ element);
      	    decomposerProcessingPartsMap.put("vbz", element);
	        decomposerProcessingPartsMap.put("continueWhileLoop", "false");
         } else if (top.indexOf("(VBZ")>=0 && !vbz.equals("") && originalSubject!=null && !originalSubject.equals("") 
 				&& element!=null && !element.equals("")) {
             // a VBZ is in element
        	String tempElement = "";
 			String tempTop = "";
 			int count = 1;
 			while (!stack.isEmpty()) {
 				tempTop = stack.pop();
 				if(tempTop.indexOf("(")>=0 && (tempTop.equals("(S") || tempTop.equals("(SBAR"))) {
 					break;
 				} else if(tempTop.indexOf("(")>=0 && !(tempTop.equals("(S") || tempTop.equals("(SBAR"))) {
 					continue;
 				}
 				tempElement=tempElement+" "+tempTop;
 				count ++;
 			}	
 			
        	int start = subject.lastIndexOf(" ");
        	String predicateToReplace = subject.substring(start).trim();
        	tempElement = tempElement.replace(predicateToReplace, element.trim()).trim();
        	stack.push("(S");
        	stack.push(tempElement);
        	
        	subject = subject.substring(0, start+1);
       	    decomposerProcessingPartsMap.put("subject", subject + " "+ element);
       	    decomposerProcessingPartsMap.put("vbz", element);
       	    decomposerProcessingPartsMap.put("element", "");
 	        decomposerProcessingPartsMap.put("continueWhileLoop", "true");
          }
	}

}
