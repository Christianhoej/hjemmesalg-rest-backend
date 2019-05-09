package adminclient.model;

public enum UrlConstants {
    ADMIN_STATISTICS(""),
    ADMIN_LOGIN(""),
    CATEGORY_CREATION(""),
    CATEGORY_UPDATE(""),
    CATEGORY_DELETE(" "),
    CATEGORY_GET_ALL(" ");

    private String url;
    UrlConstants(String url) {
        this.url = url;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }
}
