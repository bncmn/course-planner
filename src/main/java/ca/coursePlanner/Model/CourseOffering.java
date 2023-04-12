package ca.coursePlanner.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.fasterxml.jackson.core.io.NumberInput.parseInt;

/**
 * CourseOffering models a specific offering of a course. It contains information about:
 *  - when the offering was held (semester)
 *  - where it was held (location)
 *  - who was teaching the offering (instructors)
 * This object also holds a list of sections for each component of the offering (LEC/LAB/TUT).
 *
 * @author Diego Buencamino
 * @author Matt Tsai
 */

public class CourseOffering{
    private String semester;
    private String location;
    private String instructors;
    private List<Section> sections = new ArrayList<>();

    public CourseOffering(String semester, String location, String instructors) {
        this.semester = semester;
        this.location = location;
        this.instructors = instructors;
    }

    public String getSemester() {
        return semester;
    }

    public String getLocation() {
        return location;
    }

    public String getInstructors() {
        return instructors;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setInstructors(String instructors) {
        this.instructors = instructors;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (!(o instanceof CourseOffering)) return false;

        CourseOffering that = (CourseOffering) o;

        return super.equals(that)
                && Objects.equals(semester, that.semester)
                && Objects.equals(location, that.location);
    }
}
