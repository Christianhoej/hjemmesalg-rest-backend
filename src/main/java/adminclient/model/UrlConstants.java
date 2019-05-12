package adminclient.model;

public enum UrlConstants {
    ADMIN_STATISTICS("http://130.225.170.204:19926/homely/admin"),
    ADMIN_LOGIN("http://130.225.170.204:19926/homely/admin"),
    CATEGORY_CREATION("http://130.225.170.204:19926/homely/category"),
    CATEGORY_UPDATE_BASE("http://130.225.170.204:19926/homely/category/"),
    CATEGORY_DELETE("http://130.225.170.204:19926/homely/category/"),
    CATEGORY_GET_ALL("http://130.225.170.204:19926/homely/category");

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
