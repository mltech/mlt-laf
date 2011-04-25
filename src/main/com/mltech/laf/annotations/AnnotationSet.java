package com.mltech.laf.annotations;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AnnotationSet extends ArrayList<Annotation> {
	private static final long serialVersionUID = 74898749080630689L;

	private LinkedHashMap<Integer, Integer> _annotationByStart = new LinkedHashMap<Integer, Integer>();
	private LinkedHashMap<Integer, Integer> _annotationByEnd = new LinkedHashMap<Integer, Integer>();
	private int _index = 0;
	private int _min = 0;
	private int _max = 0;

	// AnnotationSet(Collection<Annotation> c) {
	// super(c);
	// }

	@Override
	public boolean add(Annotation annotation) {
		// TODO Auto-generated method stub
		int annotationStart = annotation.start();
		int annotationEnd = annotation.end();
		_annotationByStart.put(annotationStart, _index);
		_annotationByEnd.put(annotationEnd, _index);
		_min = Math.min(_min, annotationStart);
		_max = Math.max(_max, annotationEnd);
		_index++;
		return super.add(annotation);
	}

	public List<Annotation> subAnnotationSet(int start, int end) {

		Integer annotationStart = _annotationByStart.get(start);
		if (annotationStart == null) {
			for (int s = start; s <= _max; s++) {
				if (_annotationByStart.containsKey(s)) {
					annotationStart = _annotationByStart.get(s);
					break;
				}
			}
		}

		Integer annotationEnd = _annotationByEnd.get(end);
		if (annotationEnd == null) {
			for (int e = annotationStart; e <= _max; e++)
				if (_annotationByEnd.containsKey(e)) {
					annotationEnd = _annotationByEnd.get(e);
					break;
				}
		}

		return subList(annotationStart, annotationEnd + 1);
	}
}
