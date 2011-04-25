package com.mltech.laf.processing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mltech.laf.annotations.Annotation;
import com.mltech.laf.annotations.FeatureSet;
import com.mltech.laf.document.Document;


public class SimpleTokenizer extends Tokenizer {
	private String _punctuation;

	
	public void setPunctuation(String punctuation) {
		_punctuation = punctuation;
	}

	@Override
	public void parse(Document document) {
		int startPos = 0;
		Pattern puncPattern = Pattern.compile("([" + _punctuation + "\\s])");
		Pattern spacePattern = Pattern.compile("\\s");
		Matcher m = puncPattern.matcher(document.text());
		FeatureSet fs;
		while (m.find()) {
			if (startPos != m.start()) {
				fs = new FeatureSet();
				fs.put("string", document.text().substring(startPos, m.start()));
				fs.put("type", "word");
				document.annotations("token").add(new Annotation(startPos, m.start() - 1, fs));
			}
			if (!spacePattern.matcher(m.group()).matches()) {
				fs = new FeatureSet();
				fs.put("string", m.group());
				fs.put("type", "punctuation");
				document.annotations("token").add(new Annotation(m.start(), m.end(), fs));
			}
			startPos = m.end();
		}
	}

	@Override
	protected void init() {
	}
}
