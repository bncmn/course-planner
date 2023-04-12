package ca.coursePlanner.Wrappers;
import java.util.List;

/**
 * This wrapper packages instances of the Watcher object to be displayed
 * by the front-end.
 *
 * @author Diego Buencamino
 * @author Matt Tsai
 */

public class ApiWatcherWrapper {
    public long watcherId;
    public ApiDepartmentWrapper department;
    public ApiCourseWrapper course;
    public List<String> events;

    public ApiWatcherWrapper(long watcherId, ApiDepartmentWrapper department, ApiCourseWrapper course){
        this.watcherId = watcherId;
        this.department = department;
        this.course = course;
        this.events = events;
    }

}
