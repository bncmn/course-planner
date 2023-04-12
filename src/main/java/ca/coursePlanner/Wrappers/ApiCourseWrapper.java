package ca.coursePlanner.Wrappers;

/**
 * This wrapper packages instances of the Course object to be displayed
 * by the front-end.
 *
 * @author Diego Buencamino
 * @author Matt Tsai
 */

public class ApiCourseWrapper {
    public int courseId;
    public String catalogNumber;

    public ApiCourseWrapper(int courseId, String catalogNumber) {
        this.courseId = courseId;
        this.catalogNumber = catalogNumber;
    }
}
