package ca.coursePlanner.Wrappers;

/**
 * This wrapper packages requests to POST a new watcher to be sent to the actual
 * model.
 *
 * @author Diego Buencamino
 * @author Matt Tsai
 */

public class ApiPayloadWatcherWrapper {
    public int deptId;
    public int courseId;

    public ApiPayloadWatcherWrapper(int deptId, int courseId) {
        this.deptId = deptId;
        this.courseId = courseId;
    }
}
