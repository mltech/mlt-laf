package com.mltech.laf.processing.pipeline;

public abstract class APipelineElement {
	protected abstract void init();
	
	public void initialize() {
		System.err.println("Starting " + this.getClass().getName());
		init();
	}
	
	@SuppressWarnings("unchecked")
	public static APipelineElement newInstance(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class<APipelineElement> klass = (Class<APipelineElement>) Class.forName(className);
		return klass.newInstance();
	}
	
	public void setInitValue(String attribute, String value) {
		String methodName = "set" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
		try {
			this.getClass().getMethod(methodName, String.class).invoke(this, value);
		} catch (NoSuchMethodException e) {
			throw new PipelineArgumentException("The field \""
					+ attribute + "\" does not exist for class \""
					+ this.getClass().getName() + "\".");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
