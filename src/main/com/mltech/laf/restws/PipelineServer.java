package com.mltech.laf.restws;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class PipelineServer {
	Component _component;

	public PipelineServer(int port) {
		_component = new Component();
		_component.getServers().add(Protocol.HTTP, port);
	}

	public void addApplication(PipelineApplication application) {
		_component.getDefaultHost().attach(application);
	}
	
	public void start() {
		try {
			_component.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
