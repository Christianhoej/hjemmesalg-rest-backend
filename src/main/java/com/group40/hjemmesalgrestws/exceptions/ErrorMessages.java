package com.group40.hjemmesalgrestws.exceptions;

public enum ErrorMessages {
    USER_ALREADY_EXIST("En bruger med den email findes allerede!"),

    CATEGORY_ALREADY_EXIST("Den kategori findes allerede!"),

    AD_DOES_NOT_EXIST("Annoncen findes ikke"),

    WRONG_LOGIN_CREDENTIALS("Forkert brugernavn eller kodeord."),

    COULD_NOT_DELETE_AD("Det var ikke muligt at slette annonce."),

    USER_DOES_NOT_EXIST("Brugeren kunne ikke findes"),

    CATEGORY_DOES_NOT_EXIST("Kategorien findes ikke.");

    private String errorMessage;
    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
