package com.mltech.laf.parser;

import com.mltech.laf.document.BitextDocument;
import com.mltech.laf.document.IDocument;

public abstract class BitextDocumentParser extends AParser {
	@Override
	public void parse(IDocument document) {
		parse((BitextDocument) document);
	}
	
	protected abstract void parse(BitextDocument document);
}
