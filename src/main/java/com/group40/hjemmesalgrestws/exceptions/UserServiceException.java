package com.group40.hjemmesalgrestws.exceptions;

public class UserServiceException extends RuntimeException{

    private static final long serialVersionUID = 123774L;

    private String fix;

    public UserServiceException(String errorMessage, String fix) {
        super(errorMessage);
        this.fix = fix;

    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }
}
