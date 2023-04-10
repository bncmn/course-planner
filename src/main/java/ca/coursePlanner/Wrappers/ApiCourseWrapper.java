package ca.coursePlanner.Wrappers;

public class ApiCourseWrapper {
    public int courseId;
    public String catalogNumber;

    public ApiCourseWrapper(int courseId, String catalogNumber) {
        this.courseId = courseId;
        this.catalogNumber = catalogNumber;
    }
}
