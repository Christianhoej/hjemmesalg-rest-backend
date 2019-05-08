package com.group40.hjemmesalgrestws.exceptions;

public class CategoryServiceException extends RuntimeException{
    private static final long serialVersionUID = 4451234L;

    private String fix;
    public CategoryServiceException(String message, String fix) {
        super(message);
        this.fix = fix;

    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }
}
