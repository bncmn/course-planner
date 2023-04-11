package ca.coursePlanner.Wrappers;
import java.util.List;

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
