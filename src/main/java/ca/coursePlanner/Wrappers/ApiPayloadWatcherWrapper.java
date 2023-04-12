package ca.coursePlanner.Wrappers;

public class ApiPayloadWatcherWrapper {
    public int deptId;
    public int courseId;

    public ApiPayloadWatcherWrapper(int deptId, int courseId) {
        this.deptId = deptId;
        this.courseId = courseId;
    }
}
