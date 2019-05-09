package adminclient.model;

public enum UrlConstants {
    ADMIN_STATISTICS("http://localhost:8080/Homely-ws/admin"),
    ADMIN_LOGIN("http://localhost:8080/Homely-ws/admin"),
    CATEGORY_CREATION("http://localhost:8080/Homely-ws/category"),
    CATEGORY_UPDATE_BASE("http://localhost:8080/Homely-ws/category/"),
    CATEGORY_DELETE("http://localhost:8080/Homely-ws/category/"),
    CATEGORY_GET_ALL("http://localhost:8080/Homely-ws/category");

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
