package com.mltech.laf.annotations;

public class Annotation {
	private FeatureSet _features;
	private int _start;
	private int _end;
	
	public String feature(String name) {
		return _features.get(name);
	}
	
	public void addFeature(String name, String value) {
		_features.put(name, value);
	}
	
	public int start() {
		return _start;
	}

	public int end() {
		return _end;
	}

	public Annotation(int start, int end, FeatureSet features) {
		_start = start;
		_end = end;
		_features = features;
	}
}
