package ca.coursePlanner.Model;

import java.util.*;

public class Department {
    private String name;
    private List<Course> courses = new ArrayList<>();

    public Department(String deptCode) {
        this.name = deptCode;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        sortCoursesByCatalogNumber();
        return courses;
    }

    public void addNewCourse(Course course) {
        courses.add(course);
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;

        if (!(o instanceof Course)) return false;

        Department that = (Department) o;
        return Objects.equals(name, that.name);
    }

    public void sortCoursesByCatalogNumber() {
        Collections.sort(courses, Comparator.comparing(Course::getCatalogNumber));
    }

}
