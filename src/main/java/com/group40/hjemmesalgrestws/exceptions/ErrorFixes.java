package com.group40.hjemmesalgrestws.exceptions;

public enum ErrorFixes {
    USERNAME_ALREADY_EXIST_TIP("Indtast en ny mail"),

    WRONG_LOGIN_CREDENTIALS("Forkert brugernavn eller kodeord."),

    CATEGORY_DOES_NOT_EXIST("Prøv at angive et andet kategori id."),

    CHANGE_EMAIL_OR_PUBLIC_USERID("Email eller brugerID passer ikke sammen, prøv at skifte den ene"),

    TRY_ANOTHER_USERID("Prøv et andet ID."),

    TRY_ANOTHER_EMAIL("Prøv en anden email"),

    CATEGORY_ALREADY_EXIST("Prøv at skifte navn på den kategori du er ved at oprette."),

    CATEGORY_BY_ID_DOES_NOT_EXIST("prøv at skifte id for at finde det du leder efter."),

    CATEGORY_BY_NAME_DOES_NOT_EXIST("Prøv at søge på en anden kategori"),

    AD_BY_ID_DOES_NOT_EXIST("Prøv at skifte ID for at finde den annonce du leder efter.");

    private String errorFix;
    ErrorFixes(String errorFix) {
        this.errorFix = errorFix;
    }
    public String getErrorFix(){
        return errorFix;
    }
    public void setErrorFix(String errorFix){
        this.errorFix = errorFix;
    }
}
