package com.mltech.laf.parser;

import com.mltech.laf.document.Document;
import com.mltech.laf.document.IDocument;

public abstract class DocumentParser extends AParser {
	@Override
	public void parse(IDocument document) {
		parse((Document) document.document());
	}
	
	protected abstract void parse(Document document);
}
