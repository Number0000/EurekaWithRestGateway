package com.SmoothStack.EurekaAdmin.Exception;

public class InvalidResourceAccess extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String resourceName;
	private String fieldName;
	private Object fieldValue;
	
	public InvalidResourceAccess(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not available with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
	}
	
    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
