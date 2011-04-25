package com.mltech.laf.processing.pipeline;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mltech.laf.document.IDocument;
import com.mltech.laf.exporter.IDocumentExporter;
import com.mltech.laf.extractor.IDocumentExtractor;
import com.mltech.laf.filter.AFilter;
import com.mltech.laf.parser.AParser;


public class Pipeline {
	private static final long serialVersionUID = 1L;

	private XPath _xpath;
	private DocumentBuilder _loader;

	private List<AParser> _parsers;
	private List<AFilter> _filters;

	private IDocumentExtractor _extractor;
	private IDocumentExporter _exporter;
	private Element _configDOM;

	public Pipeline() {
	}

	@SuppressWarnings("unchecked")
	public Pipeline(URL xmlConfig) {
		_parsers = new ArrayList<AParser>();
		_filters = new ArrayList<AFilter>();

		XPathFactory xpfactory = XPathFactory.newInstance();
		_xpath = xpfactory.newXPath();
		DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
		try {
			_loader = docfactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		try {
			Document document = _loader.parse(xmlConfig.getFile());
			_configDOM = document.getDocumentElement();

			configurePipelineElements(_parsers, "parser");
			
			configurePipelineElements(_filters, "filter");

			// Extractor
			String extractorName = getXpathText(_configDOM, "/pipeline/extract/class/text()");
			Class<IDocumentExtractor> extractorClass = (Class<IDocumentExtractor>) Class.forName(extractorName);
			_extractor = (IDocumentExtractor) extractorClass.newInstance();

			// Exporter
			String exporterName = getXpathText(_configDOM, "/pipeline/export/class/text()");
			Class<IDocumentExporter> exporterClass = (Class<IDocumentExporter>) Class.forName(exporterName);
			_exporter = (IDocumentExporter) exporterClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private <T extends APipelineElement> void configurePipelineElements(List<T> elements, String elementType)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		NodeList filters = getXpathElements(_configDOM, "//pipeline/" + elementType + "s/" + elementType);
		for (int i = 0; i < filters.getLength(); i++) {
			Node filter = filters.item(i);
			String className = getXpathElements(filter, "class/text()").item(0).getNodeValue();
			T pe = (T) T.newInstance(className);
			NodeList options = getXpathElements(filter, "options/option");
			for (int j = 0; j < options.getLength(); j++) {
				pe.setInitValue(getXpathText(options.item(j), "@name"), getXpathText(options.item(j), "text()"));
			}
			pe.init();
			elements.add(pe);
		}
	}

	private NodeList getXpathElements(Node xmlElement, String xpath) {
		NodeList result = null;
		try {
			result = (NodeList) _xpath.compile(xpath).evaluate(xmlElement, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getXpathText(Node xmlElement, String xpath) {
		String result = null;
		try {
			result = (String) _xpath.compile(xpath).evaluate(xmlElement, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void parse(IDocument document) {
		for (AParser p : _parsers) {
//			BitextDocument bitextDocument = (BitextDocument) document;
			p.parse(document);
		}
		for (AFilter f : _filters) {
			f.filter(document);
		}
	}

	public IDocument extract(String text) {
		return _extractor.extract(text);
	}

	public String export(IDocument document) {
		return _exporter.export(document);
	}
}
