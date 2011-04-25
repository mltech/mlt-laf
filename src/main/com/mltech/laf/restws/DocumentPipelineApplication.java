package com.mltech.laf.restws;

import java.net.URL;

import com.mltech.laf.document.Document;


public class DocumentPipelineApplication extends PipelineApplication {
	public DocumentPipelineApplication(URL pipelineConfigFile, String route) {
		super(pipelineConfigFile, route);
	}

	public String parse(String text) {
		return parseAndExport(new Document(text));
	}
}
