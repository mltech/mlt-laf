package com.mltech.laf.parser;

import com.mltech.laf.document.IDocument;
import com.mltech.laf.processing.pipeline.APipelineElement;


public abstract class AParser extends APipelineElement {
	public abstract void parse(IDocument document);
}
