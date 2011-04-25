package com.mltech.laf.document;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mltech.laf.annotations.Annotation;
import com.mltech.laf.annotations.AnnotationException;
import com.mltech.laf.annotations.AnnotationSet;
import com.mltech.utils.Utils;


public class Document implements IDocument {
	private HashMap<String, AnnotationSet> _annotations;
	private String _text;

	public Document() {
		_annotations = new HashMap<String, AnnotationSet>();
	}

	public Document(String text) {
		this();
		_text = text;
	}

	public Document(URL path) {
		this();
		_text = Utils.fileToString(path);
	}

	public String text() {
		return _text;
	}

	public void setText(String text) {
		_text = text;
	}

	public AnnotationSet annotations(String name) {
		AnnotationSet as;
		if (_annotations.containsKey(name)) {
			as = _annotations.get(name);
		} else {
			as = new AnnotationSet();
			_annotations.put(name, as);
		}
		return as;
	}

	public List<Annotation> subAnnotationSetNew(Annotation annotation, String name) {
		if (!_annotations.containsKey(name))
			throw new AnnotationException("The annotation \"" + name +
					"\" is not contained in the document");
		return _annotations.get(name).subAnnotationSet(annotation.start(),
				annotation.end());
	}

	// TODO: optimize
	public List<Annotation> subAnnotationSet(Annotation annotation, String name) {
		AnnotationSet as = null;
		int annotationStart = annotation.start();
		int annotationEnd = annotation.end();
		int start = -1;
		int end = -1;
		if (_annotations.containsKey(name)) {
			as = _annotations.get(name);
			for (Annotation a : as) {
				if (a.start() <= annotationStart)
					start++;
				if (a.end() <= annotationEnd)
					end++;
				else
					break;
			}
			// System.out.println("start: " + start + " / end: " + end);
		} else {
			throw new AnnotationException("The annotation \"" + name +
					"\" is not contained in the document");
		}

		if (start != -1)
			return as.subList(start, end + 1);
		else
			return new ArrayList<Annotation>();
	}

	public IDocument document() {
		return this;
	}
}
