package ca.coursePlanner.Model;

import java.util.Objects;

import static com.fasterxml.jackson.core.io.NumberInput.parseInt;

public class CourseOffering extends Course {
    private Semester semester;
    private String location;
    private String enrolmentCap;
    private String enrolmentTotal;
    private String instructors;
    private String componentCode;

    public CourseOffering(String semester, String subject, String catalogNumber, String location, String enrolmentCap, String enrolmentTotal, String instructors, String componentCode) {
        super(subject, catalogNumber);
        this.semester = new Semester(semester);
        this.location = location;
        this.enrolmentCap = enrolmentCap;
        this.enrolmentTotal = enrolmentTotal;
        this.instructors = instructors;
        this.componentCode = componentCode;
    }

    public Semester getSemester() {
        return semester;
    }

    public String getSubject() {
        return super.getSubject();
    }

    public String getCatalogNumber() {
        return super.getCatalogNumber();
    }

    public String getLocation() {
        return location;
    }

    public String getEnrolmentCap() {
        return enrolmentCap;
    }

    public String getEnrolmentTotal() {
        return enrolmentTotal;
    }

    public String getInstructors() {
        return instructors;
    }

    public String getComponentCode() {
        return componentCode;
    }

    public void addSpot(String cap, String total, String instructors) {
        int newCap = Integer.parseInt(cap);
        int newTotal = Integer.parseInt(total);
        int currentCap = Integer.parseInt(this.enrolmentCap);
        int currentTotal = Integer.parseInt(this.enrolmentTotal);

        this.enrolmentCap = String.valueOf(currentCap + newCap);
        this.enrolmentTotal = String.valueOf(currentTotal + newTotal);
        hasSameCampus(instructors);
    }

    public void hasSameCampus(String newInstructor) {
        if (!this.instructors.contains(newInstructor)) {
            this.instructors = this.instructors + ", " + newInstructor;
            System.out.println("After adding Ins: " + this.instructors);
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
//                && Objects.equals(enrolmentCap, that.enrolmentCap)
//                && Objects.equals(enrolmentTotal, that.enrolmentTotal)
//                && Objects.equals(instructors, that.instructors)
                && Objects.equals(componentCode, that.componentCode);
    }
}
