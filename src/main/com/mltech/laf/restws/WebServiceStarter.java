package com.mltech.laf.restws;

import java.io.File;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mltech.laf.restws.BitextDocumentPipelineApplication;
import com.mltech.laf.restws.PipelineServer;

public class WebServiceStarter {
	public static void main(String[] args) throws Exception {
		XPath xpath = XPathFactory.newInstance().newXPath();
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		if (args.length != 1)
			throw new WebServiceStarterException("WebserviceStarter takes exactly 1 argument: the path to the config file");

		File configFile = new File(args[0]);
		String configPath = configFile.getCanonicalPath().substring(0, configFile.getCanonicalPath().lastIndexOf(File.separator) + 1);
		
		Document document = db.parse(configFile);
		Element tree = document.getDocumentElement();

		int port = Integer.parseInt((String) xpath.compile("/wsserver/port/text()").evaluate(tree, XPathConstants.STRING));
		PipelineServer ps = new PipelineServer(port);

		NodeList webservices = (NodeList) xpath.compile("/wsserver/webservices/webservice").evaluate(tree, XPathConstants.NODESET);

		for (int i = 0; i < webservices.getLength(); i++) {
			Node webservice = webservices.item(i);
			URL config = new URL("file:" + configPath + (String) xpath.compile("pipelineconfig/text()").evaluate(webservice, XPathConstants.STRING));
			String route = (String) xpath.compile("route/text()").evaluate(webservice, XPathConstants.STRING);
			String applicationType = (String) xpath.compile("pipelinetype/text()").evaluate(webservice, XPathConstants.STRING);
			PipelineApplication application = null;
			if (applicationType.equals("simple"))
				application = new DocumentPipelineApplication(config, route);
			else if (applicationType.equals("bitext"))
				application = new BitextDocumentPipelineApplication(config, route);
			ps.addApplication(application);
			System.err.println("Attached " + config + " to route " + route);
		}
		ps.start();
	}
}