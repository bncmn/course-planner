package ca.coursePlanner.Model;

import java.util.Objects;

import static com.fasterxml.jackson.core.io.NumberInput.parseInt;

public class CourseOffering{
    private String semester;
    private String location;
    private int enrolmentCapacity;
    private int enrolmentTotal;
    private String instructors;
    private String componentCode;

    public CourseOffering(String semester, String location, int enrolmentCapacity, int enrolmentTotal, String instructors, String componentCode) {
        this.semester = semester;
        this.location = location;
        this.enrolmentCapacity = enrolmentCapacity;
        this.enrolmentTotal = enrolmentTotal;
        this.instructors = instructors;
        this.componentCode = componentCode;
    }

    public String getSemester() {
        return semester;
    }

    public String getLocation() {
        return location;
    }

    public int getEnrolmentCapacity() {
        return enrolmentCapacity;
    }

    public int getEnrolmentTotal() {
        return enrolmentTotal;
    }

    public String getInstructors() {
        return instructors;
    }

    public String getComponentCode() {
        return componentCode;
    }

//    public void addSpot(int cap, int total, String instructors) {
//        int newCap = cap;
//        int newTotal = total;
//        int currentCap = this.enrolmentCap;
//        int currentTotal = this.enrolmentTotal;
//
//        this.enrolmentCap = currentCap + newCap;
//        this.enrolmentTotal = currentTotal + newTotal;
//        profSameCampus(instructors);
//    }

    public void setEnrolmentCapacity(int enrolmentCapacity) {
        this.enrolmentCapacity = enrolmentCapacity;
    }

    public void setEnrolmentTotal(int enrolmentTotal) {
        this.enrolmentTotal = enrolmentTotal;
    }

    public void profSameCampus(String newInstructor) {
        if (!this.instructors.contains(newInstructor)) {
            this.instructors = this.instructors + ", " + newInstructor;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (!(o instanceof CourseOffering)) return false;

        CourseOffering that = (CourseOffering) o;

        return super.equals(that)
                && Objects.equals(semester, that.semester)
                && Objects.equals(location, that.location)
                && Objects.equals(componentCode, that.componentCode);
    }

    // This is to check if the two section has different prof or not
    public boolean sameCampus(Object o) {
        if (o == this) return true;

        if (!(o instanceof CourseOffering)) return false;

        CourseOffering that = (CourseOffering) o;

        return super.equals(that)
                && Objects.equals(semester, that.semester)
                && Objects.equals(location, that.location);
    }
}
