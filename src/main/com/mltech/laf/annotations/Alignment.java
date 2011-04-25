package com.mltech.laf.annotations;

import java.util.List;

public class Alignment {
	private FeatureSet _features;
	private List<Annotation> _list1;
	private List<Annotation> _list2;

	public String feature(String name) {
		return _features.get(name);
	}

	public Alignment(List<Annotation> list1, List<Annotation> list2) {
		_list1 = list1;
		_list2 = list2;
	}

	public Alignment(List<Annotation> list1, List<Annotation> list2, FeatureSet features) {
		this(list1, list2);
		_features = features;
	}

	public List<Annotation> list1() {
		return _list1;
	}

	public List<Annotation> list2() {
		return _list2;
	}
}
