package com.mltech.laf.restws;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class DocumentPipelineResource extends ServerResource {
	@Get
	public String represent() {
		String result = "";
		if (getQuery().getFirst("text") == null)
			result = "-- Bad request --";
		else
			result = ((DocumentPipelineApplication) this.getApplication()).parse(getQuery().getFirst("text").getValue());
		return result;
	}
}