package com.mltech.laf.restws;

import java.net.URL;

import com.mltech.laf.document.BitextDocument;


public class BitextDocumentPipelineApplication extends PipelineApplication {
	public BitextDocumentPipelineApplication(URL pipelineConfigFile, String route) {
		super(pipelineConfigFile, route);
	}
	
	public String parse(String text1, String text2) {
		return parseAndExport(new BitextDocument(text1, text2));
	}
}
