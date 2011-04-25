package com.mltech.laf.restws;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class BitextDocumentPipelineResource extends ServerResource {
	@Get
	public String represent() {
		String result = "";
		if (getQuery().getFirst("text1") == null || getQuery().getFirst("text2") == null)
			result = "-- Bad request --";
		else
			result = ((BitextDocumentPipelineApplication) this.getApplication()).parse(getQuery().getFirst("text1").getValue(), getQuery().getFirst("text2").getValue());
		return result;
	}
}