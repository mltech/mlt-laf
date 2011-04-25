package com.mltech.laf.restws;

import java.net.URL;


import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.mltech.laf.document.IDocument;
import com.mltech.laf.processing.pipeline.Pipeline;

public class PipelineApplication extends Application {
	Pipeline _pipeline;
	String _route;

	public PipelineApplication(URL pipelineConfigFile, String route) {
		System.err.println("Starting pipeline web service");
	 _pipeline = new Pipeline(pipelineConfigFile);
	 _route = route;
		System.err.println("Started");
	}

	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach(_route, BitextDocumentPipelineResource.class);
		return router;
	}
	
	protected String parseAndExport(IDocument document) {
		_pipeline.parse(document);
		return _pipeline.export(document);
	}
}