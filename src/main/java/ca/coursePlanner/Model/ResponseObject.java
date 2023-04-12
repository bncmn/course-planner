package ca.coursePlanner.Model;

/**
 * ResponseObject holds information for the /api/about page in the front-end.
 *
 * @author Diego Buencamino
 * @author Matt Tsai
 */

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
