package com.mltech.laf.filter;

import com.mltech.laf.document.IDocument;
import com.mltech.laf.processing.pipeline.APipelineElement;


public abstract class AFilter extends APipelineElement {
	public abstract void filter(IDocument document);
}
