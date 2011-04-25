package com.mltech.laf.processing;

import com.mltech.laf.document.BitextDocument;
import com.mltech.laf.parser.BitextDocumentParser;


public class DocumentSwapper extends BitextDocumentParser {
	@Override
	protected void parse(BitextDocument document) {
		document.swapDefaultDocument();
	}

	@Override
	protected void init() {
	}
}
