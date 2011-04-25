package com.mltech.laf.document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mltech.laf.annotations.Alignment;


// TODO: -> word aligments, segments alignments...
public class BitextDocument implements IDocument {
	private Document _document1;
	private Document _document2;
	private Document _defaultDocument;
	//private HashMap<String, AlignmentSet> _alignments;
	private HashMap<String, List<Alignment>> _alignments;

	public BitextDocument(String text1, String text2) {
		this(new Document(text1), new Document(text2));
	}
	
	public BitextDocument(Document document1, Document document2) {
		_document1 = document1;
		_document2 = document2;
		_defaultDocument = _document1;
//		_alignments = new HashMap<String, AlignmentSet>();
		_alignments = new HashMap<String, List<Alignment>>();
	}

	public void swapDefaultDocument() {
		_defaultDocument = _defaultDocument == _document1 ? _document2 : _document1;
	}

	public Document document1() {
		return _document1;
	}

	public Document document2() {
		return _document2;
	}

	//TODO: refactor name as "aligments"
	public List<Alignment> alignment(String name) { //TODO: Unify with Document using type paraemters
		List<Alignment> as;
		if (_alignments.containsKey(name)) {
			as = _alignments.get(name);
		} else {
			as = new ArrayList<Alignment>();
			_alignments.put(name, as);
		}
		return as;
	}

	public IDocument document() {
		return _defaultDocument;
	}
}
