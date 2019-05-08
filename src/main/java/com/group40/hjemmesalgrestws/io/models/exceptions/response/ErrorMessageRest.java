package com.group40.hjemmesalgrestws.io.models.exceptions.response;

import java.util.Date;

public class ErrorMessageRest {
    private Date timestamp;
    private int errorStatusCode;
    private String message;
    private String fix;

    public ErrorMessageRest(Date timestamp, int errorStatusCode, String message, String fix) {
        this.timestamp = timestamp;
        this.errorStatusCode = errorStatusCode;
        this.message = message;
        this.fix = fix;
    }

    public ErrorMessageRest() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getErrorStatusCode() {
        return errorStatusCode;
    }

    public void setErrorStatusCode(int errorStatusCode) {
        this.errorStatusCode = errorStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }
}
