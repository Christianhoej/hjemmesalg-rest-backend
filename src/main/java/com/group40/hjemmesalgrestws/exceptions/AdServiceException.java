package com.group40.hjemmesalgrestws.exceptions;

public class AdServiceException extends RuntimeException {
    private static final long serialVersionUID = 1233454L;

    private String fix;
    public AdServiceException(String message, String fix) {
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
