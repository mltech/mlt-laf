package com.mltech.laf.processing;

import com.mltech.laf.annotations.Annotation;
import com.mltech.laf.annotations.FeatureSet;
import com.mltech.laf.document.Document;
import com.mltech.laf.parser.DocumentParser;


public class SimpleSegmentSplitter extends DocumentParser
{
	private String _separators;
	
	public void setSeparators(String separators) {
		_separators = separators;
	}

	@Override
	protected void parse(Document document) {
		String text = document.text();
		int len = text.length();
		int startPos = 0; //TODO: int???
		int endPos = 0; //TODO: int???
		for(String segment:text.split("[" + _separators + "]\\s*")) {
			startPos = text.indexOf(segment, (int) endPos);
			endPos = startPos + segment.length();
			if (endPos < len) endPos++;
			segment = text.substring(startPos, endPos);
			FeatureSet fs = new FeatureSet();
			fs.put("string", segment);
			document.annotations("segment").add(new Annotation(startPos, endPos, fs));
		}
	}

	@Override
	protected void init() {
		System.err.println("  using separators " + _separators);
	}
}
