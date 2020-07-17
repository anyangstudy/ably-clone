package com.study.anyang.dto;

public class CodeLabelValue {

	private String value;
	private String label;
	
	public CodeLabelValue() {
		super();
	}

	public CodeLabelValue(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
