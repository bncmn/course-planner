package ca.coursePlanner.Model;

public class ResponseObject {
    private String appName;
    private String authorName;

    public ResponseObject(String appName, String authorName) {
        this.appName = appName;
        this.authorName = authorName;
    }

    public String getAppName() {
        return appName;
    }

    public String getAuthorName() {
        return authorName;
    }
}
