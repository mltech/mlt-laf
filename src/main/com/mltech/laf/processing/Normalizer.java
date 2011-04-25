package com.mltech.laf.processing;

import com.mltech.laf.annotations.Annotation;
import com.mltech.laf.document.Document;
import com.mltech.laf.parser.DocumentParser;


public class Normalizer extends DocumentParser {
	@Override
	protected void parse(Document document) {
//		text = text.replace("< ! ! >", " "); // TODO: do not use that anymore
//		text = text.replace("|", "<pipe/>"); // TODO: replace that in de-tokenization
//		text = text.replaceAll("^[\t ]+", "").replaceAll("[\t ]+$", "");
//		text = text.replaceAll("[\t ]{2,}", " ");
//		
//		// Normalize full-width and half-width ASCII to normal ASCII
//		// http://dev.networkerror.org/utf8/?start=65400&end=65500&cols=4
//		// TODO: put in a seprate file for JP and ZH???
		//String text = java.text.Normalizer.normalize(document.text(), java.text.Normalizer.Form.NFKC);
		
//		return text;
		for (Annotation annotation:document.annotations("token")) {
			annotation.addFeature("norm", java.text.Normalizer.normalize(annotation.feature("string"), java.text.Normalizer.Form.NFKC));
		}
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}
}
